package catmonit.app.ui.storage;

import catmonit.app.models.DeviceInfo;
import catmonit.app.models.Warning;

public class StorageState {
    private final Warning[] warnings;
    private final Warning[] errors;
    private final DeviceInfo[] deviceInfo;
    public StorageState(Warning[] warnings, Warning[] errors, DeviceInfo[] deviceInfo) {
        this.warnings = warnings;
        this.errors = errors;
        this.deviceInfo = deviceInfo;
    }

    public Warning[] getWarnings() {
        return warnings;
    }

    public Warning[] getErrors() {
        return errors;
    }

    public DeviceInfo[] getDeviceInfo() {
        return deviceInfo;
    }
}
