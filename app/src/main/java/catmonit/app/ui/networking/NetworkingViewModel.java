package catmonit.app.ui.networking;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class NetworkingViewModel extends ViewModel {
    private final MutableLiveData<Long> currentNetworkThroughput;
    private final Handler mockHandler = new Handler(Looper.getMainLooper());
    private final Random random = new Random();

    public NetworkingViewModel(){
        currentNetworkThroughput = new MutableLiveData<>();
        startMockingBandwidth();
    }
    public void startMockingBandwidth() {
        mockHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentNetworkThroughput.setValue((long)(50 + random.nextFloat() * 450));
                mockHandler.postDelayed(this, 100); // update every second
            }
        }, 100);
    }

    public MutableLiveData<Long> getCurrentNetworkThroughput() {
        return currentNetworkThroughput;
    }
}