package catmonit.app.models;

import com.google.gson.annotations.SerializedName;

public class DiscInfo {
    @SerializedName("MountPoint")
    protected String label;
    @SerializedName("Usage")
    protected long usedBytes;
    @SerializedName("Capacity")
    protected long totalBytes;

    public DiscInfo(String label, long usedBytes, long totalBytes) {
        this.label = label;
        this.usedBytes = usedBytes;
        this.totalBytes = totalBytes;
    }
}
