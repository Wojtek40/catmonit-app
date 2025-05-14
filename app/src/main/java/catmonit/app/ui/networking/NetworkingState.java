package catmonit.app.ui.networking;

import catmonit.app.models.Warning;

public class NetworkingState {
    private final Warning[] errors;
    private final Warning[] warnings;
    private final long[] networkThroughputHistory;

    public NetworkingState(Warning[] errors, Warning[] warnings, long[] networkThroughputHistory) {
        this.errors = errors;
        this.warnings = warnings;
        this.networkThroughputHistory = networkThroughputHistory;
    }

    public Warning[] getErrors() {
        return errors;
    }

    public Warning[] getWarnings() {
        return warnings;
    }

    public long[] getNetworkThroughput() {
        return networkThroughputHistory;
    }
}
