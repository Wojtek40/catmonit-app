package catmonit.app.api_connection.models.storage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceWS {
    @SerializedName("deviceInfo")
    private final DeviceInfoWS deviceInfo;
    @SerializedName("disksInfo")
    private final List<DiscInfoWS> discInfo;

    public DeviceWS(DeviceInfoWS deviceInfo, List<DiscInfoWS> discInfo) {
        this.deviceInfo = deviceInfo;
        this.discInfo = discInfo;
    }

    public DeviceInfoWS getDeviceInfo() {
        return deviceInfo;
    }

    public List<DiscInfoWS> getDiscInfo() {
        return discInfo;
    }
}
