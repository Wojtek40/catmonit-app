package catmonit.app.data;

import catmonit.app.data.model.LoggedInUser;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password, String server) {
//        MutableLiveData<Result<LoggedInUser>> loggedIn = new MutableLiveData<>(null);
//        ApiService authService = APIClient.getClient(server).create(ApiService.class);
//        Response<LoginResponse> response;
//        try {
//            response = authService.login(new LoginRequest(username, password)).execute();
//        } catch (
//                IOException e) {
//            return new Result.Error(e);
//        }
//        if (response.isSuccessful() && response.body() != null){
//            LoggedInUser user = new LoggedInUser(username, response.body().getToken(), server);
//            return new Result.Success<LoggedInUser>(user);
//        }
//        Gson gson = new Gson();
//        ErrorResponse er = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class)
//        return new Result.Error(new Exception())
        return null;
    }
    public void logout() {
        // TODO: revoke authentication
    }
}