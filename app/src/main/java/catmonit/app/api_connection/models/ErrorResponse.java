package catmonit.app.api_connection.models;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @SerializedName("message")
    String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
