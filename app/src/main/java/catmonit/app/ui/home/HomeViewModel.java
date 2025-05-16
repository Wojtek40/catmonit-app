package catmonit.app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import catmonit.app.data.LoginRepository;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        LoginRepository lr = LoginRepository.getInstance();
        if (lr != null) {
            mText.setValue(lr.getLoggedInUser().getDisplayName());
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}