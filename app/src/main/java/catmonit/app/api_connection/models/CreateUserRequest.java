package catmonit.app.api_connection.models;

public class CreateUserRequest {
    private final String username;
    private final String password;

    public CreateUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
