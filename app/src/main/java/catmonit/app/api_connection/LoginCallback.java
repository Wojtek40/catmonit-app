package catmonit.app.api_connection;

import catmonit.app.data.model.LoggedInUser;

public interface LoginCallback {
    void onSuccess(LoggedInUser user);

    void onError(LoggedInUser user);
}
