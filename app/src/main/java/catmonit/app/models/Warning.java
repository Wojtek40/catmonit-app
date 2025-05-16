package catmonit.app.models;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;

public class Warning {
    public static final int WARNING = 0;
    public static final int ERROR = 1;
    @Severity
    protected int severity;
    protected String deviceId;
    protected String text;
    protected String deviceName;
    protected String ipAddress;

    public Warning(int severity, String deviceId, String text, String deviceName, String ipAddress) {
        this.severity = severity;
        this.deviceId = deviceId;
        this.text = text;
        this.deviceName = deviceName;
        this.ipAddress = ipAddress;
    }

    public int getSeverity() {
        return severity;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getText() {
        return text;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    @Retention(SOURCE)
    @IntDef({WARNING, ERROR})
    public @interface Severity {
    }

}
