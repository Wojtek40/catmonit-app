package catmonit.app.ui.storage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import catmonit.app.models.Warning;

public class StorageViewModel extends ViewModel {

    private final MutableLiveData<StorageState> storageState;

    public StorageViewModel() {
        storageState = new MutableLiveData<>();
        storageState.setValue(new StorageState(1000000009900L,564744453000L, new Warning[] {new Warning(Warning.WARNING, "0", "Ostrzeżenie", "Test device 0/1", "192.168.0.12")}, new Warning[]{new Warning(Warning.ERROR, "01", "Krytyczny błąd", "Drukarka", "192.168.0.113" )}));
    }

    public LiveData<StorageState> getStorageState() {
        return storageState;
    }

}