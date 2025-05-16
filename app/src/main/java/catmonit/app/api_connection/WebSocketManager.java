package catmonit.app.api_connection;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketManager extends WebSocketListener {
    private final MutableLiveData<String> messageLiveData = new MutableLiveData<>();
    private WebSocket webSocket;

    public LiveData<String> getMessageLiveData() {
        return messageLiveData;
    }

    @Override
    public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        Log.println(Log.ERROR, "", t.getMessage());
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
        super.onMessage(webSocket, text);
    }
}
