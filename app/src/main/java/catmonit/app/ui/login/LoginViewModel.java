package catmonit.app.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import catmonit.app.R;
import catmonit.app.api_connection.APIClient;
import catmonit.app.api_connection.ApiService;
import catmonit.app.api_connection.models.LoginRequest;
import catmonit.app.api_connection.models.LoginResponse;
import catmonit.app.data.LoginRepository;
import catmonit.app.data.model.LoggedInUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private final LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String serverAddress, String username, String password) {
//        Result<LoggedInUser> result = loginRepository.login(username, passwords, serverAddress);
//        if (result instanceof Result.Success){
//            LoggedInUser user = ((Result.Success<LoggedInUser>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(user.getJWT())));
//        }
        Log.println(Log.DEBUG, "", serverAddress);
        ApiService apiService = APIClient.getClient(serverAddress).create(ApiService.class);

        apiService.login(new LoginRequest(username, password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginRepository.setLoggedInUser(new LoggedInUser(username, response.body().getToken(), serverAddress));
                    loginResult.setValue(new LoginResult(new LoggedInUserView(response.body().getToken())));
                } else {
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                loginResult.setValue(new LoginResult(R.string.network_error));
                Log.println(Log.ERROR, "netw", Log.getStackTraceString(throwable));
            }
        });
    }

    public void loginDataChanged(String serverAddress, String username, String password) {
        if (!isServerValid(serverAddress)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_server, null, null));
        } else if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    private boolean isServerValid(String serverAddress) {
        return serverAddress != null && !serverAddress.isEmpty();
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        return username != null && !username.isEmpty();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty(); // && password.trim().length() > 5;
    }
}