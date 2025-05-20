package catmonit.app.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import catmonit.app.data.LoginRepository;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    LoginRepository lr;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        lr = LoginRepository.getInstance();
        if (lr != null) {
            mText.setValue(lr.getLoggedInUser().getDisplayName());
        }
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void logout(Context context) {
        if (lr != null) {
            lr.logout(context);
        }
    }
}