package catmonit.app.api_connection.models.fileshares;

import java.util.Map;

public class FilesharesWSResponse {
    private final String responseTime;
    private final Map<String, DeviceFSWS> monitoredDevices;
    private final Map<String, DeviceFSWS> autoDevices;


    public FilesharesWSResponse(String responseTime, Map<String, DeviceFSWS> monitoredDevices, Map<String, DeviceFSWS> autoDevices) {
        this.responseTime = responseTime;
        this.monitoredDevices = monitoredDevices;
        this.autoDevices = autoDevices;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public Map<String, DeviceFSWS> getMonitoredDevices() {
        return monitoredDevices;
    }

    public Map<String, DeviceFSWS> getAutoDevices() {
        return autoDevices;
    }
}
