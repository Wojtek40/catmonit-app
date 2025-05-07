package catmonit.app.data;

import android.content.Context;
import android.content.SharedPreferences;

import catmonit.app.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private static final String ACCESS_TOKEN_NAME = "access_token";
    private static final String PREFS_NAME = "auth_prefs";
    private static final String SERVER_NAME = "server_name";
    private static final String USERID = "userid";
    private static final String DISPLAY_NAME = "display_name";
    private final LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }
    public static LoginRepository getInstance(){
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout(Context context) {
        user = null;
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().remove(ACCESS_TOKEN_NAME).apply();
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public LoggedInUser getLoggedInUser(){
        return user;
    }

    public Result<LoggedInUser> login(String username, String password, String server) {
        // handle login
        Result<LoggedInUser> result = dataSource.login(username, password, server);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }

    public void saveUserToSharedPrefereces(Context context) {
        if (user == null){
            return;
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
                .putString(ACCESS_TOKEN_NAME, user.getJWT())
                .putString(DISPLAY_NAME, user.getDisplayName())
                .putString(USERID, user.getUserId())
                .putString(SERVER_NAME, user.getServer())
                .apply();
    }
    public static LoginRepository getInstance(Context context){
        if (instance != null) {
            return instance;
        }
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String accessToken = prefs.getString(ACCESS_TOKEN_NAME, null);
        LoginRepository lr = new LoginRepository(new LoginDataSource());
        if (accessToken == null) {
            return lr;
        }
        lr.user = new LoggedInUser(prefs.getString(USERID, null), prefs.getString(DISPLAY_NAME, null), accessToken, prefs.getString(SERVER_NAME, null));
        instance = lr;
        return instance;
    }
}