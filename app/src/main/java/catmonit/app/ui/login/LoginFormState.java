package catmonit.app.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class LoginFormState {
    @Nullable
    private final Integer usernameError;
    @Nullable
    private final Integer passwordError;
    @Nullable
    private final Integer serverError;
    private final boolean isDataValid;

    LoginFormState(@Nullable Integer serverError, @Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.serverError = serverError;
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.serverError = null;
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }

    @Nullable
    public Integer getServerError() {
        return serverError;
    }

}