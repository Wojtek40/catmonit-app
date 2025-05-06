package catmonit.app.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import catmonit.app.data.LoginRepository;
import catmonit.app.data.Result;
import catmonit.app.data.model.LoggedInUser;
import catmonit.app.R;

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
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password, serverAddress);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));

        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String serverAddress, String username, String password) {
        if (!isServerValid(serverAddress)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_server, null, null));
        }
        else if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        return username != null && !username.isEmpty();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty(); // && password.trim().length() > 5;
    }
    private boolean isServerValid(String serverAddress){
        return serverAddress != null && !serverAddress.isEmpty();
    }
}