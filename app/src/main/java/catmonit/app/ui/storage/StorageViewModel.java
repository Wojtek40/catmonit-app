package catmonit.app.ui.storage;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import catmonit.app.api_connection.WebSocketManager;
import catmonit.app.api_connection.models.storage.DevicesWSResponse;
import catmonit.app.models.DeviceInfo;
import catmonit.app.models.DiscInfo;
import catmonit.app.models.Warning;

public class StorageViewModel extends ViewModel {

    private final MutableLiveData<StorageState> storageState;
    private final LiveData<DevicesWSResponse> rawData;
    private final MediatorLiveData<StorageState> liveStateBuilder;
    private final WebSocketManager webSocketManager;

    public StorageViewModel() {
        webSocketManager = new WebSocketManager();
        webSocketManager.startListeningStorage();
        Log.w("StorageViewModel", "StorageViewModel: connected WS");
        storageState = new MutableLiveData<>(new StorageState(new Warning[]{}, new Warning[]{}, new DeviceInfo[]{}));
        rawData = webSocketManager.getStorageLiveData();

        liveStateBuilder = new MediatorLiveData<>();
        liveStateBuilder.addSource(rawData, devicesWSResponse -> {
            ArrayList<DeviceInfo> devices = new ArrayList<>();
            devicesWSResponse.getAutoDevices().forEach((s, deviceWS) -> {
                if (deviceWS == null || deviceWS.getDeviceInfo() == null) return;
                devices.add(new DeviceInfo(deviceWS.getDeviceInfo().getHostname(),
                        deviceWS.getDeviceInfo().getIpAddress(),
                        deviceWS.getDeviceInfo().getOs(),
                        deviceWS.getDiscInfo().stream().map(discInfoWS -> new DiscInfo(discInfoWS.getLabel(), discInfoWS.getUsedBytes(), discInfoWS.getTotalBytes())).toArray(DiscInfo[]::new)));
            });
            storageState.setValue(new StorageState(new Warning[]{}, new Warning[]{}, devices.toArray(new DeviceInfo[0])));
        });
    }

    public LiveData<StorageState> getLiveStateBuilder() {
        return liveStateBuilder;
    }

    public LiveData<StorageState> getStorageState() {
        return storageState;
    }
}