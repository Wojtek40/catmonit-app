package catmonit.app.models;

public class Warning {
    protected String text;
    protected String deviceId;

    public Warning(String text, String device_id) {
        this.text = text;
        this.deviceId = device_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String device_id) {
        this.deviceId = device_id;
    }
}
