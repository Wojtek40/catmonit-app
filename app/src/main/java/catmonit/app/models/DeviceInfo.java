package catmonit.app.models;

public class DeviceInfo {
    protected String deviceName;
    protected String ipAddress;
    protected String icon;
    protected DiscInfo[] discInfo;
    protected long usedSpace;
    protected long totalSpace;

    public DeviceInfo(String deviceName, String ipAddress, String icon, DiscInfo[] discInfo, long usedSpace, long totalSpace) {
        this.deviceName = deviceName;
        this.ipAddress = ipAddress;
        this.icon = icon;
        this.discInfo = discInfo;
        this.usedSpace = usedSpace;
        this.totalSpace = totalSpace;
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

    public long getUsedSpace() {
        return usedSpace;
    }

    public long getTotalSpace() {
        return totalSpace;
    }
}
