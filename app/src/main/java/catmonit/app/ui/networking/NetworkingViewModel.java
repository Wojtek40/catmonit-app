package catmonit.app.ui.networking;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NetworkingViewModel extends ViewModel {
    private final MutableLiveData<Long> currentNetworkThroughput;
    private final Handler mockHandler = new Handler(Looper.getMainLooper());
    private final Random random = new Random();
    private final List<Long> entries;

    public NetworkingViewModel(){
        currentNetworkThroughput = new MutableLiveData<>();
        entries = new ArrayList<>();
        startMockingBandwidth();
    }
    public void startMockingBandwidth() {
        mockHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long value = (long) (50 + random.nextFloat() * 450);
                currentNetworkThroughput.setValue(value);
                entries.add(value);
                mockHandler.postDelayed(this, 100); // update every second
            }
        }, 100);
    }

    public MutableLiveData<Long> getCurrentNetworkThroughput() {
        return currentNetworkThroughput;
    }

    public List<Long> getEntries() {
        return entries;
    }
}