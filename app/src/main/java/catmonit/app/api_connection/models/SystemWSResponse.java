package catmonit.app.api_connection.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class SystemWSResponse {

    @SerializedName("responseTime")
    private String responseTime;

    @SerializedName("monitoredDevices")
    private Map<String, SystemDeviceWS> monitoredDevices;

    @SerializedName("autoDevices")
    private Map<String, SystemDeviceWS> autoDevices;

    @SerializedName("warnings")
    private Map<String, Object> warnings; // Assuming warnings can have varied structures

    @SerializedName("errors")
    private Map<String, Object> errors; // Assuming errors can have varied structures

    @SerializedName("totalWarningCount")
    private int totalWarningCount;

    @SerializedName("totalErrorCount")
    private int totalErrorCount;

    // Getters
    public String getResponseTime() {
        return responseTime;
    }

    public Map<String, SystemDeviceWS> getMonitoredDevices() {
        return monitoredDevices;
    }

    public Map<String, SystemDeviceWS> getAutoDevices() {
        return autoDevices;
    }

    public Map<String, Object> getWarnings() {
        return warnings;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public int getTotalWarningCount() {
        return totalWarningCount;
    }

    public int getTotalErrorCount() {
        return totalErrorCount;
    }

    public static class SystemDeviceWS {
        @SerializedName("deviceInfo")
        private DeviceInfo deviceInfo;

        @SerializedName("systemInfo")
        private SystemInfo systemInfo;

        // Getters
        public DeviceInfo getDeviceInfo() {
            return deviceInfo;
        }

        public SystemInfo getSystemInfo() {
            return systemInfo;
        }
    }

    public static class DeviceInfo {
        @SerializedName("lastUpdated")
        private String lastUpdated;
        @SerializedName("hostname")
        private String hostname;
        @SerializedName("ipAddress")
        private String ipAddress;
        @SerializedName("mask")
        private int mask;
        @SerializedName("uuid")
        private String uuid;
        @SerializedName("os")
        private String os;

        // Getters
        public String getLastUpdated() {
            return lastUpdated;
        }

        public String getHostname() {
            return hostname;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public int getMask() {
            return mask;
        }

        public String getUuid() {
            return uuid;
        }

        public String getOs() {
            return os;
        }
    }

    public static class SystemInfo {
        @SerializedName("cpuUsagePercent")
        private float cpuUsagePercent;
        @SerializedName("ramTotalBytes")
        private long ramTotalBytes;
        @SerializedName("ramUsedBytes")
        private long ramUsedBytes;
        @SerializedName("pagefileTotalBytes")
        private long pagefileTotalBytes;
        @SerializedName("pagefileUsedBytes")
        private long pagefileUsedBytes;
        @SerializedName("lastBootTimestamp")
        private String lastBootTimestamp;

        // Getters
        public float getCpuUsagePercent() {
            return cpuUsagePercent;
        }

        public long getRamTotalBytes() {
            return ramTotalBytes;
        }

        public long getRamUsedBytes() {
            return ramUsedBytes;
        }

        public long getPagefileTotalBytes() {
            return pagefileTotalBytes;
        }

        public long getPagefileUsedBytes() {
            return pagefileUsedBytes;
        }

        public String getLastBootTimestamp() {
            return lastBootTimestamp;
        }
    }
}
