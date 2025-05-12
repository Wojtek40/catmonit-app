package catmonit.app.models;

public class DiscInfo {
    protected String label;
    protected long usedBytes;
    protected long totalBytes;

    public DiscInfo(String label, long usedBytes, long totalBytes) {
        this.label = label;
        this.usedBytes = usedBytes;
        this.totalBytes = totalBytes;
    }
}
