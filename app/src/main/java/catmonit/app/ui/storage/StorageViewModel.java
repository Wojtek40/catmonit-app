package catmonit.app.ui.storage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import catmonit.app.models.DeviceInfo;
import catmonit.app.models.DiscInfo;
import catmonit.app.models.Warning;

public class StorageViewModel extends ViewModel {

    private final MutableLiveData<StorageState> storageState;

    public StorageViewModel() {
        storageState = new MutableLiveData<>();
        storageState.setValue(new StorageState(
                new Warning[] {new Warning(Warning.WARNING, "0", "Ostrzeżenie", "Test device 0/1", "192.168.0.12")},
                new Warning[] {new Warning(Warning.ERROR, "01", "Krytyczny błąd", "Drukarka", "192.168.0.113" ),
                               new Warning(Warning.ERROR, "01", "Krytyczny błąd", "Drukarka", "192.168.0.113" )},
                new DeviceInfo[] {new DeviceInfo("Sample device", "192.0.0.78", "", new DiscInfo[] {new DiscInfo("/dev/nvme1", 1024L, 3197798L)}, 1024L, 3197798L),
                                  new DeviceInfo("Sample device", "192.0.0.78", "", new DiscInfo[] {new DiscInfo("/dev/nvme1", 120000L, 3197798L), new DiscInfo("/dev/nvme1", 1200000L, 3197798L)}, 1320000L, 6395596L)}
        ));
    }

    public LiveData<StorageState> getStorageState() {
        return storageState;
    }

}