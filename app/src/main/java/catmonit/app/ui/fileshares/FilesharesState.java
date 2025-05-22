package catmonit.app.ui.fileshares;

import catmonit.app.models.DeviceInfo;

public class FilesharesState {
    private final DeviceInfo[] deviceInfo;

    public FilesharesState(DeviceInfo[] deviceInfo) {

        this.deviceInfo = deviceInfo;
    }

    public DeviceInfo[] getDeviceInfo() {
        return deviceInfo;
    }
}
