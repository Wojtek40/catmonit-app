package catmonit.app.ui.system;

import catmonit.app.models.SystemDeviceInfo;
import catmonit.app.models.Warning;

public class SystemState {
    private final Warning[] warnings;
    private final Warning[] errors;
    private final SystemDeviceInfo[] systemDeviceInfo;


    public SystemState(Warning[] warnings, Warning[] errors, SystemDeviceInfo[] systemDeviceInfo) {
        this.warnings = warnings;
        this.errors = errors;
        this.systemDeviceInfo = systemDeviceInfo;
    }

    public Warning[] getWarnings() {
        return warnings;
    }

    public Warning[] getErrors() {
        return errors;
    }

    public SystemDeviceInfo[] getSystemDeviceInfo() {
        return systemDeviceInfo;
    }


}
