package catmonit.app.ui.fileshares;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.util.ArrayList;

import catmonit.app.api_connection.WebSocketManager;
import catmonit.app.api_connection.models.fileshares.FilesharesWSResponse;
import catmonit.app.models.DeviceInfo;
import catmonit.app.models.DiscInfo;

public class FilesharesViewModel extends ViewModel {
    private final MutableLiveData<FilesharesState> filesharesState;
    private final LiveData<FilesharesWSResponse> rawData;
    private final MediatorLiveData<FilesharesState> liveStateBuilder;
    private final WebSocketManager webSocketManager;

    public FilesharesViewModel() {
        webSocketManager = new WebSocketManager();
        webSocketManager.startListeningFileshares();
        filesharesState = new MutableLiveData<>(new FilesharesState(new DeviceInfo[]{}));
        rawData = webSocketManager.getFilesharesLiveData();
        liveStateBuilder = new MediatorLiveData<>();
        liveStateBuilder.addSource(rawData, devicesWSResponse -> {
            Log.d("SVM", "StorageViewModel: ");
            ArrayList<DeviceInfo> devices = new ArrayList<>();
            devicesWSResponse.getAutoDevices().forEach((s, deviceFSWS) -> {
                if (deviceFSWS == null || deviceFSWS.getDeviceInfo() == null) return;
                devices.add(new DeviceInfo(deviceFSWS.getDeviceInfo().getHostname(),
                        deviceFSWS.getDeviceInfo().getIpAddress(),
                        deviceFSWS.getDeviceInfo().getOs(),
                        deviceFSWS.getFilesharesInfo().stream().map(discInfoWS -> new DiscInfo(discInfoWS.getSharePath(), discInfoWS.getUsage(), discInfoWS.getCapacity())).toArray(DiscInfo[]::new)));
            });
            filesharesState.setValue(new FilesharesState(devices.toArray(new DeviceInfo[0])));
            Log.d("SVM", "StorageViewModel: " + new Gson().toJson(filesharesState.getValue()));
        });


    }

    public MutableLiveData<FilesharesState> getFilesharesState() {
        return filesharesState;
    }

    public MediatorLiveData<FilesharesState> getLiveStateBuilder() {
        return liveStateBuilder;
    }
}