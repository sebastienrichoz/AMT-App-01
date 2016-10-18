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

@Stateless
public class UserManager implements UserManagerLocal {

    @Resource(lookup = "java:/jdbc/AMTApp01DS")
    private DataSource dataSource;

    @Override
    public void save(User user) {
        if (user.getId() == 0) {

            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (firstname, lastname, email, username, password) VALUES (?, ?, ?, ?, ?)");
                statement.setString(1, user.getFirstname());
                statement.setString(2, user.getLastname());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getUsername());
                statement.setString(5, user.getPassword());
                int r = statement.executeUpdate();
                // TODO: 13.10.16 checker affectedRows
                user.setId(findByUsername(user.getUsername()).getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET firstname = ?, lastname = ?, email = ?, username = ?, password = ? WHERE id = ?");
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getId());
            int r = statement.executeUpdate();
            // TODO: 13.10.16 checker affectedRows
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, user.getId());
            int r = statement.executeUpdate();
            // TODO: 13.10.16 checker affectedRows
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, firstname, lastname, email, username FROM users");
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                users.add(new User(
                        r.getInt("id"),
                        r.getString("firstname"),
                        r.getString("lastname"),
                        r.getString("email"),
                        r.getString("username")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, firstname, lastname, email, username FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                return new User(
                        r.getInt("id"),
                        r.getString("firstname"),
                        r.getString("lastname"),
                        r.getString("email"),
                        r.getString("username")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, firstname, lastname, email, username FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                return new User(
                        r.getInt("id"),
                        r.getString("firstname"),
                        r.getString("lastname"),
                        r.getString("email"),
                        r.getString("username")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, firstname, lastname, email, username FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet r = statement.executeQuery();
            if (r.next()) {
                return new User(
                        r.getInt("id"),
                        r.getString("firstname"),
                        r.getString("lastname"),
                        r.getString("email"),
                        r.getString("username")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
