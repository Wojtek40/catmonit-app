package catmonit.app.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

import catmonit.app.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static final String ACCESS_TOKEN_NAME = "access_token";
    private static final String PREFS_NAME = "auth_prefs";
    private static final String SERVER_NAME = "server_name";
    private static final String DISPLAY_NAME = "display_name";
    private static volatile LoginRepository instance;
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

    public static LoginRepository getInstance() {
        return instance;
    }

    public static LoginRepository getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String accessToken = prefs.getString(ACCESS_TOKEN_NAME, null);
        LoginRepository lr = new LoginRepository(new LoginDataSource());
        if (accessToken == null) {
            return lr;
        }
        lr.user = new LoggedInUser(prefs.getString(DISPLAY_NAME, null), accessToken, prefs.getString(SERVER_NAME, null));
        instance = lr;
        return instance;
    }

    public boolean isLoggedIn() {
        if (user == null) return false;
        if (user.getJWT() == null) return false;
        DecodedJWT jwt = JWT.decode(user.getJWT());
        if (jwt.getExpiresAt().before(new Date())) {
            user = null;
            return false;
        }
        return true;
    }

    public void logout(Context context) {
        user = null;
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().remove(ACCESS_TOKEN_NAME).apply();
        dataSource.logout();
    }

    public LoggedInUser getLoggedInUser() {
        return user;
    }

    public void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password, String server) {
//        final Result<LoggedInUser>[] result = new Result[1];
//        final ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> {
//            try {
//                result[0] = dataSource.login(username, password, server);
//            } catch (Exception e) {
//                result[0]
//            }
//        });
//        if (result[0] instanceof Result.Success) {
//            setLoggedInUser(((Result.Success<LoggedInUser>) result[0]).getData());
//        }
//        return result[0];
        return null;
    }

    public void saveUserToSharedPrefereces(Context context) {
        if (user == null) {
            return;
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
                .putString(ACCESS_TOKEN_NAME, user.getJWT())
                .putString(DISPLAY_NAME, user.getDisplayName())
                .putString(SERVER_NAME, user.getServer())
                .apply();
    }
}