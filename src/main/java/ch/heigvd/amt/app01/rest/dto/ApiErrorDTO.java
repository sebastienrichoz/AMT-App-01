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

    private String message;

    /**
     * Constructor of the Error.
     *
     * @param message the message describing the error.
     */
    public ApiErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}