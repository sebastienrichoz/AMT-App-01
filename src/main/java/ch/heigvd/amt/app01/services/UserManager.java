package ch.heigvd.amt.app01.services;

import ch.heigvd.amt.app01.models.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

// TODO: 13.10.16 Factoriser tout ça un minimum

/**
 * Implementation of the UserManager interface.
 *
 * Can be used as a stateless managed service.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
@Stateless
public class UserManager implements UserManagerLocal {

    @Resource(lookup = "java:/jdbc/AMTApp01DS")
    private DataSource dataSource;

    @Override
    public void save(User user) {
        int res;

        if (user.getId() == 0) {
            String query =
                    "INSERT INTO users (firstname, lastname, email, username, password) " +
                    "VALUES (?, ?, ?, ?, ?);";

            // Execute query
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(query);
            ) {
                stmt.setString(1, user.getFirstname());
                stmt.setString(2, user.getLastname());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getUsername());
                stmt.setString(5, user.getPassword());
                res = stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to create the user");
            }

            // Check the query result
            if (res != 1) {
                throw new RuntimeException("Unable to create the user");
            }

            // Update the user with his fresh ID
            user.setId(findByUsername(user.getUsername()).getId());
        }
        else {
            String query =
                    "UPDATE users " +
                    "SET firstname = ?, lastname = ?, email = ?, username = ?, password = ? " +
                    "WHERE id = ?;";

            // Execute query
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(query);
            ) {
                stmt.setString(1, user.getFirstname());
                stmt.setString(2, user.getLastname());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getUsername());
                stmt.setString(5, user.getPassword());
                stmt.setLong(6, user.getId());
                res = stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to update the user");
            }

            // Check the query result
            if (res != 1) {
                throw new RuntimeException("Unable to update the user");
            }
        }
    }

    @Override
    public void delete(User user) {
        String query =
                "DELETE FROM users " +
                "WHERE id = ?;";
        int res;

        // Execute query
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setLong(1, user.getId());
            res = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete the user");
        }

        // Check the query result
        if (res != 1) {
            throw new RuntimeException("Unable to delete the user");
        }
    }

    @Override
    public List<User> findAll() {
        String query =
                "SELECT id, firstname, lastname, email, username " +
                "FROM users;";
        ResultSet res;
        List<User> users = new ArrayList<>();

        // Execute query
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            res = stmt.executeQuery();
            while (res.next()) {
                users.add(new User(
                        res.getInt("id"),
                        res.getString("firstname"),
                        res.getString("lastname"),
                        res.getString("email"),
                        res.getString("username")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find users");
        }
        return users;
    }

    @Override
    public User findById(long id) {
        String query =
                "SELECT id, firstname, lastname, email, username " +
                "FROM users " +
                "WHERE id = ?;";

        // Execute query
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setLong(1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return new User(
                        res.getInt("id"),
                        res.getString("firstname"),
                        res.getString("lastname"),
                        res.getString("email"),
                        res.getString("username")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find user");
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        String query =
                "SELECT id, firstname, lastname, email, username " +
                "FROM users " +
                "WHERE username = ?;";

        // Execute query
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setString(1, username);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return new User(
                        res.getInt("id"),
                        res.getString("firstname"),
                        res.getString("lastname"),
                        res.getString("email"),
                        res.getString("username")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find user");
        }
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String query =
                "SELECT id, firstname, lastname, email, username " +
                        "FROM users " +
                        "WHERE username = ? " +
                        "AND password = ?;";

        // Execute query
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return new User(
                        res.getInt("id"),
                        res.getString("firstname"),
                        res.getString("lastname"),
                        res.getString("email"),
                        res.getString("username")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find user");
        }
        return null;
    }
}
