package catmonit.app.api_connection;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import catmonit.app.api_connection.models.fileshares.FilesharesWSResponse;
import catmonit.app.api_connection.models.storage.DevicesWSResponse;
import catmonit.app.data.LoginRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketManager extends WebSocketListener {
    private final LoginRepository loginRepository = LoginRepository.getInstance();

    private final MutableLiveData<FilesharesWSResponse> webSocketMessageFS = new MutableLiveData<>();

    private final MutableLiveData<DevicesWSResponse> webSocketMessage = new MutableLiveData<>();
    private final MutableLiveData<String> webSocketStatus = new MutableLiveData<>("Disconnected");

    public MutableLiveData<FilesharesWSResponse> getFilesharesLiveData() {
        return webSocketMessageFS;
    }
    private final OkHttpClient client;
    private WebSocket webSocket;
    private final Gson gson;

    public WebSocketManager() {
        Log.d("WSM", "Initialised");
        this.client = APIClient.getOkHttpClient().readTimeout(0, TimeUnit.MILLISECONDS).build();
        this.gson = new Gson();
    }

    public LiveData<DevicesWSResponse> getStorageLiveData() {
        return webSocketMessage;
    }


    public void startListeningStorage() {
        String endpoint = "disks/";
        Log.d("WSM", "startListening: ");
        String serverUrl = loginRepository.getLoggedInUser().getServer();
        String token = loginRepository.getLoggedInUser().getJWT();

        if (serverUrl == null || token == null) {
            Log.e("WebSocketManager", "Server URL or token is null");
            return;
        }

        if (!serverUrl.startsWith("wss://")) {
            if (serverUrl.startsWith("https://")) {
                serverUrl = serverUrl.replaceFirst("https://", "wss://");
            } else if (serverUrl.startsWith("http://")) {
                serverUrl = serverUrl.replaceFirst("http://", "wss://");
            } else if (serverUrl.startsWith("ws://")) {
                serverUrl = serverUrl.replaceFirst("ws://", "wss://");
            } else {
                serverUrl = "wss://" + serverUrl;
            }
        }
        Log.d("WSM", "startListening: " + serverUrl);
        Request request = new Request.Builder()
                .url(serverUrl + endpoint + "/?Authentication=" + token)
                .build();
        webSocket = client.newWebSocket(request, new StorageWebSocketListener());
        webSocketStatus.postValue("Connecting");

    }

    public void startListeningFileshares() {
        String endpoint = "shares/";
        Log.d("WSM", "startListening: ");
        String serverUrl = loginRepository.getLoggedInUser().getServer();
        String token = loginRepository.getLoggedInUser().getJWT();

        if (serverUrl == null || token == null) {
            Log.e("WebSocketManager", "Server URL or token is null");
            return;
        }

        if (!serverUrl.startsWith("wss://")) {
            if (serverUrl.startsWith("https://")) {
                serverUrl = serverUrl.replaceFirst("https://", "wss://");
            } else if (serverUrl.startsWith("http://")) {
                serverUrl = serverUrl.replaceFirst("http://", "wss://");
            } else if (serverUrl.startsWith("ws://")) {
                serverUrl = serverUrl.replaceFirst("ws://", "wss://");
            } else {
                serverUrl = "wss://" + serverUrl;
            }
        }
        Log.d("WSM", "startListening: " + serverUrl);
        Request request = new Request.Builder()
                .url(serverUrl + endpoint + "/?Authentication=" + token)
                .build();
        webSocket = client.newWebSocket(request, new FilesharesWebSocketListener());
        webSocketStatus.postValue("Connecting");

    }

    public void stopListening() {
        if (webSocket != null) {
            webSocket.close(1000, null);
            webSocket = null;
        }
        client.dispatcher().executorService().shutdown();
        webSocketStatus.postValue("Disconnected");
    }

    private class StorageWebSocketListener extends WebSocketListener {
        @Override
        public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            webSocketStatus.postValue("Disconnected");
        }

        @Override
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
            webSocketStatus.postValue("Disconnected");
            String responseText = "";
            if (response != null) {
                responseText = response.message();
            }
            Log.e("WebSocketManager", "onFailure:  (in WSM) " + responseText, t);
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            DevicesWSResponse devicesWSResponse = gson.fromJson(text, DevicesWSResponse.class);
            Log.d("WebSocketManager", "onMessage: " + text);
            webSocketMessage.postValue(devicesWSResponse);
        }

        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            webSocketStatus.postValue("Connected");
            Log.d("WebSocketManager", "onOpen: Connected");
            webSocket.send("{\"message\":\"start\", \"devices\":[],\"auto\":10}");
        }
    }

    private class FilesharesWebSocketListener extends WebSocketListener {
        @Override
        public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            webSocketStatus.postValue("Disconnected");
        }

        @Override
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
            webSocketStatus.postValue("Disconnected");
            String responseText = "";
            if (response != null) {
                responseText = response.message();
            }
            Log.e("WebSocketManager", "onFailure:  (in WSM) " + responseText, t);
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            FilesharesWSResponse devicesWSResponse = gson.fromJson(text, FilesharesWSResponse.class);
            Log.d("WebSocketManager", "onMessage: (raw) " + text);
            Log.d("WebSocketManager", "onMessage: (parsed) " + gson.toJson(devicesWSResponse));
            webSocketMessageFS.postValue(devicesWSResponse);
        }

        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            webSocketStatus.postValue("Connected");
            Log.d("WebSocketManager", "onOpen: Connected");
            webSocket.send("{\"message\":\"start\", \"devices\":[],\"auto\":10}");
        }
    }
}
