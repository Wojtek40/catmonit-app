package catmonit.app.models;

public class SystemDeviceInfo {
    private final String hostname;
    private final String ipAddress;
    private final String os;
    private final double cpuUsagePercent;
    private final long ramUsed;

    private final long ramTotal;
    private final long swapUsed;
    private final long swapTotal;
    private final String lastBootTimestamp;

    public SystemDeviceInfo(String hostname, String ipAddress, String os, double cpuUsagePercent, long ramUsed, long ramTotal, long swapUsed, long swapTotal, String lastBootTimestamp) {
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.os = os;
        this.cpuUsagePercent = cpuUsagePercent;
        this.ramUsed = ramUsed;
        this.ramTotal = ramTotal;
        this.swapUsed = swapUsed;
        this.swapTotal = swapTotal;
        this.lastBootTimestamp = lastBootTimestamp;
    }

    public String getHostname() {
        return hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getOs() {
        return os;
    }

    public double getCpuUsagePercent() {
        return cpuUsagePercent;
    }

    public long getRamUsed() {
        return ramUsed;
    }

    public String getLastBootTimestamp() {
        return lastBootTimestamp;
    }

    public long getRamTotal() {
        return ramTotal;
    }

    public long getSwapUsed() {
        return swapUsed;
    }

    public long getSwapTotal() {
        return swapTotal;
    }
}
