package catmonit.app.models;

public class DeviceInfo {
    protected String deviceName;
    protected String ipAddress;
    protected String icon;
    protected DiscInfo[] discInfo;

    public DeviceInfo(String deviceName, String ipAddress, String icon, DiscInfo[] discInfo) {
        this.deviceName = deviceName;
        this.ipAddress = ipAddress;
        this.icon = icon;
        this.discInfo = discInfo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getIcon() {
        return icon;
    }

    public DiscInfo[] getDiscInfo() {
        return discInfo;
    }
}
