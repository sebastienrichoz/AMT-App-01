package ch.heigvd.amt.app01.rest.dto;

/**
 * Class representing an error of the Rest API.
 * Can be serialized and forwarded to the client.
 *
 * The error only contains an error message, but could have an error code and a message code
 * Exemple : 404, not_found, the page that you are looking for has gone away
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
public class ApiErrorDTO {
    // TODO: 18.10.16 ajouter un type d'erreur, un code d'erreur

    private String message;

    public ApiErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}