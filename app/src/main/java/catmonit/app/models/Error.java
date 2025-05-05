package catmonit.app.models;

public class Error {
    protected String text;
    protected String deviceId;

    public Error(String text, String deviceId) {
        this.text = text;
        this.deviceId = deviceId;
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

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
