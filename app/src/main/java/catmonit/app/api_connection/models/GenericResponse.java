package catmonit.app.api_connection.models;

import com.google.gson.annotations.SerializedName;

public class GenericResponse {
    @SerializedName("message")
    private final String message;

    public GenericResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
