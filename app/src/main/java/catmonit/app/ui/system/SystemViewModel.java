package catmonit.app.ui.system;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import catmonit.app.api_connection.WebSocketManager;
import catmonit.app.models.SystemDeviceInfo;
import catmonit.app.models.Warning;

public class SystemViewModel extends ViewModel {
    private final MediatorLiveData<SystemState> systemState;
    private final WebSocketManager webSocketManager;

    public SystemViewModel() {
        webSocketManager = new WebSocketManager();
        webSocketManager.startListeningSystem();

        systemState = new MediatorLiveData<>();
        systemState.addSource(webSocketManager.getSystemLiveData(), systemWSResponse -> {
            ArrayList<SystemDeviceInfo> devices = new ArrayList<>();
            systemWSResponse.getAutoDevices().forEach((s, deviceWS) -> {
                devices.add(new SystemDeviceInfo(deviceWS.getDeviceInfo().getHostname(),
                        deviceWS.getDeviceInfo().getIpAddress(),
                        deviceWS.getDeviceInfo().getOs(),
                        deviceWS.getSystemInfo().getCpuUsagePercent(),
                        deviceWS.getSystemInfo().getRamUsedBytes(),
                        deviceWS.getSystemInfo().getRamTotalBytes(),
                        deviceWS.getSystemInfo().getPagefileUsedBytes(),
                        deviceWS.getSystemInfo().getPagefileTotalBytes(),
                        deviceWS.getSystemInfo().getLastBootTimestamp()
                ));
            });
            systemState.setValue(new SystemState(new Warning[]{}, new Warning[]{}, devices.toArray(new SystemDeviceInfo[0])));
        });
    }

    public LiveData<SystemState> getSystemState() {
        return systemState;
    }
}