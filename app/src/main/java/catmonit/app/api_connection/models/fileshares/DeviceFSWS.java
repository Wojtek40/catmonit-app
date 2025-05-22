package catmonit.app.api_connection.models.fileshares;

import java.util.List;

import catmonit.app.api_connection.models.storage.DeviceInfoWS;

public class DeviceFSWS {
    private final DeviceInfoWS deviceInfo;
    private final List<FilesharesInfoWS> sharesInfo;


    public DeviceFSWS(DeviceInfoWS deviceInfo, List<FilesharesInfoWS> sharesInfo) {
        this.deviceInfo = deviceInfo;
        this.sharesInfo = sharesInfo;
    }

    public List<FilesharesInfoWS> getFilesharesInfo() {
        return sharesInfo;
    }

    public DeviceInfoWS getDeviceInfo() {
        return deviceInfo;
    }
}
