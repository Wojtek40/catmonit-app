package catmonit.app.api_connection.models.fileshares;

public class FilesharesInfoWS {
    private final String sharePath;
    private final long usage;
    private final long capacity;

    public FilesharesInfoWS(String sharePath, long usage, long capacity) {
        this.sharePath = sharePath;
        this.usage = usage;
        this.capacity = capacity;
    }

    public String getSharePath() {
        return sharePath;
    }

    public long getUsage() {
        return usage;
    }

    public long getCapacity() {
        return capacity;
    }
}
