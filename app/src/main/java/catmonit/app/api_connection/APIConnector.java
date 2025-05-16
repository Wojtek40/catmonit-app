package catmonit.app.api_connection;

import catmonit.app.models.Warning;

public class APIConnector {
    private static APIConnector apiConnector;
    private final String apiUrl;

    public APIConnector(String url) {
        this.apiUrl = url;
    }

    public APIConnector getApiConnector(String api_url) {
        if (apiConnector == null) {
            apiConnector = new APIConnector(api_url);
        }
        return apiConnector;
    }

    public APIConnector getApiConnector() {
        return apiConnector;
    }

    public int getBandWidthUsageBytes() {
        return 0; // mock
    }

    public int getTotalSpaceBytes() {
        return 0; // mock
    }

    public int getUsedSpaceBytes() {
        return 0; // mock
    }

    public Warning[] getNetworkingWarnings() {
        return new Warning[]{}; // mock
    }

    public Error[] getNetworkingErrors() {
        return new Error[]{}; // mock
    }

    public Warning[] getStorageWarnings() {
        return new Warning[]{}; // mock
    }

    public Error[] getStorageErrors() {
        return new Error[]{}; // mock
    }
}
