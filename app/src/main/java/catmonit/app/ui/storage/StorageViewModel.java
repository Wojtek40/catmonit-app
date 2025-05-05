package catmonit.app.ui.storage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import catmonit.app.ui.storage.StorageState;

public class StorageViewModel extends ViewModel {

    private final MutableLiveData<StorageState> storageState;

    public StorageViewModel() {
        storageState = new MutableLiveData<>();
        storageState.setValue(new StorageState(0,0));
    }

    public LiveData<StorageState> getStorageState() {
        return storageState;
    }

    private void getStorageData(){

    }
}