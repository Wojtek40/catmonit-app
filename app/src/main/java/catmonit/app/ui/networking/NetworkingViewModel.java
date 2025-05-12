package catmonit.app.ui.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NetworkingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NetworkingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a problem for the next guys");
    }

    public LiveData<String> getText() {
        return mText;
    }
}