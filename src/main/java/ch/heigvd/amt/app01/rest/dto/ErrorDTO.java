package ch.heigvd.amt.app01.rest.dto;

public class ErrorDTO {

    private String message;

    public ErrorDTO() {}

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}