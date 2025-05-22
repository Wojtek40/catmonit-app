package catmonit.app.api_connection.models.storage;

import java.util.Map;

public class DevicesWSResponse {
    private final String responseTime;
    private final Map<String, DeviceWS> monitoredDevices;
    private final Map<String, DeviceWS> autoDevices;

    public DevicesWSResponse(String responseTime, Map<String, DeviceWS> monitoredDevices, Map<String, DeviceWS> autoDevices) {
        this.responseTime = responseTime;
        this.monitoredDevices = monitoredDevices;
        this.autoDevices = autoDevices;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public Map<String, DeviceWS> getMonitoredDevices() {
        return monitoredDevices;
    }

    public Map<String, DeviceWS> getAutoDevices() {
        return autoDevices;
    }
}
