package catmonit.app.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private final String displayName;
    private final String JWT;
    private final String server;

    public LoggedInUser(String displayName, String JWT, String server) {
        this.displayName = displayName;
        this.JWT = JWT;
        this.server = server;
    }


    public String getDisplayName() {
        return displayName;
    }

    public String getJWT() {
        return JWT;
    }

    public String getServer() {
        return server;
    }
}