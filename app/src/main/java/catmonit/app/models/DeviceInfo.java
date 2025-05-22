package catmonit.app.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class DeviceInfo {
    @SerializedName("hostname")
    protected String deviceName;
    protected String ipAddress;
    protected String os;
    @SerializedName("DisksInfo")
    protected DiscInfo[] discInfo;
    protected long usedSpace;
    protected long totalSpace;

    public DeviceInfo(String deviceName, String ipAddress, String os, DiscInfo[] discInfo) {
        this.deviceName = deviceName;
        this.ipAddress = ipAddress;
        this.os = os;
        this.discInfo = discInfo;

        this.usedSpace = Arrays.stream(discInfo).mapToLong(i -> i.usedBytes).sum();
        this.totalSpace = Arrays.stream(discInfo).mapToLong(i -> i.totalBytes).sum();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getOs() {
        return os;
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
