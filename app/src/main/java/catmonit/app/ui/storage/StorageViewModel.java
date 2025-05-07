package catmonit.app.ui.storage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StorageViewModel extends ViewModel {

    private final MutableLiveData<StorageState> storageState;

    public StorageViewModel() {
        storageState = new MutableLiveData<>();
        storageState.setValue(new StorageState(10009900,100000));
    }

    public LiveData<StorageState> getStorageState() {
        return storageState;
    }

}