package catmonit.app.api_connection.models.storage;

import com.google.gson.annotations.SerializedName;

public class DiscInfoWS {
    @SerializedName("mountPoint")
    protected final String label;
    @SerializedName("usage")
    protected final long usedBytes;
    @SerializedName("capacity")
    protected final long totalBytes;

    public DiscInfoWS(String label, long usedBytes, long totalBytes) {
        this.label = label;
        this.usedBytes = usedBytes;
        this.totalBytes = totalBytes;
    }

    public String getLabel() {
        return label;
    }

    public long getUsedBytes() {
        return usedBytes;
    }

    public long getTotalBytes() {
        return totalBytes;
    }
}
