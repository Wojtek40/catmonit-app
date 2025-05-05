package catmonit.app.ui.storage;

public class StorageState {
    private final int total_space_bytes;
    private final int used_space_bytes;

    public StorageState(int total_space_bytes, int used_space_bytes) {
        this.total_space_bytes = total_space_bytes;
        this.used_space_bytes = used_space_bytes;
    }

    public int getTotal_space_bytes() {
        return total_space_bytes;
    }

    public int getUsed_space_bytes() {
        return used_space_bytes;
    }

}
