package catmonit.app.ui.storage;

import catmonit.app.models.Warning;

public class StorageState {
    private final long total_space_bytes;
    private final long used_space_bytes;
    private final Warning[] warnings;
    private final Warning[] errors;


    public StorageState(long total_space_bytes, long used_space_bytes, Warning[] warnings, Warning[] errors) {
        this.total_space_bytes = total_space_bytes;
        this.used_space_bytes = used_space_bytes;
        this.warnings = warnings;
        this.errors = errors;
    }

    public long getTotal_space_bytes() {
        return total_space_bytes;
    }

    public long getUsed_space_bytes() {
        return used_space_bytes;
    }

    public Warning[] getWarnings() {
        return warnings;
    }

    public Warning[] getErrors() {
        return errors;
    }
}
