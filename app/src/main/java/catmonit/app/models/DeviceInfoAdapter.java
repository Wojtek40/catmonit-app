package catmonit.app.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import catmonit.app.R;

public class DeviceInfoAdapter extends RecyclerView.Adapter<DeviceInfoAdapter.DeviceInfoHolder> {
    private final DeviceInfo[] deviceInfo;

    public DeviceInfoAdapter(DeviceInfo[] deviceInfo) {
        this.deviceInfo = deviceInfo;
    }


    @NonNull
    @Override
    public DeviceInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_display, parent, false);
        return new DeviceInfoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceInfoHolder holder, int position) {
        DeviceInfo di = deviceInfo[position];
        DiscInfoAdapter discInfoAdapter = new DiscInfoAdapter(di.getDiscInfo());
        holder.discsInfo.setAdapter(discInfoAdapter);
        holder.discsInfo.setLayoutManager(new LinearLayoutManager(null));
        holder.deviceName.setText(di.getDeviceName());
        holder.ipAddress.setText(di.getIpAddress());
    }

    @Override
    public int getItemCount() {
        return deviceInfo.length;
    }

    public static class DeviceInfoHolder extends RecyclerView.ViewHolder {
        TextView deviceName;
        TextView ipAddress;
        RecyclerView discsInfo;


        public DeviceInfoHolder(@NonNull View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.device_name_tv);
            ipAddress = itemView.findViewById(R.id.ip_address_tv);
            discsInfo = itemView.findViewById(R.id.discs_recyclerView);
        }
    }
}
