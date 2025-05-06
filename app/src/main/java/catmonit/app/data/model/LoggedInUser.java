package catmonit.app.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private final String userId;
    private final String displayName;
    private final String JWT;
    private final String server;

    public LoggedInUser(String userId, String displayName, String JWT, String server) {
        this.userId = userId;
        this.displayName = displayName;
        this.JWT = JWT;
        this.server = server;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getJWT(){
        return JWT;
    }
    public String getServer() {
        return server;
    }
}