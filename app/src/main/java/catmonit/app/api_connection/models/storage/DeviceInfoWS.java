package catmonit.app.api_connection.models.storage;

import com.google.gson.annotations.SerializedName;

public class DeviceInfoWS {
    @SerializedName("lastUpdated")
    String lastUpdated;

    @SerializedName("hostname")
    String hostname;
    @SerializedName("ipAddress")
    String ipAddress;
    @SerializedName("mask")
    int mask;
    @SerializedName("uuid")
    String uuid;

    @SerializedName("os")
    String os;

    public DeviceInfoWS(String lastUpdated, String hostname, String ipAddress, int mask, String uuid, String os) {
        this.lastUpdated = lastUpdated;
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.uuid = uuid;
        this.mask = mask;
        this.os = os;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getHostname() {
        return hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUuid() {
        return uuid;
    }

    public String getOs() {
        return os;
    }

    public int getMask() {
        return mask;
    }
}
