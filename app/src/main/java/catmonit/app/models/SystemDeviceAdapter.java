package catmonit.app.models;

import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.time.Duration;
import java.time.LocalDateTime;

import catmonit.app.R;

public class SystemDeviceAdapter extends RecyclerView.Adapter<SystemDeviceAdapter.SystemDeviceViewHolder> {
    private final SystemDeviceInfo[] dataSet;

    public SystemDeviceAdapter(SystemDeviceInfo[] dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public SystemDeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.system_device_display, parent, false);
        return new SystemDeviceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemDeviceViewHolder holder, int position) {
        SystemDeviceInfo si = dataSet[position];
        holder.deviceName.setText(si.getHostname());
        holder.ipAddress.setText(si.getIpAddress());
        holder.cpuUsage.setProgress((int) si.getCpuUsagePercent());
        holder.ramUsage.setProgress((int) (si.getRamUsed() * 100 / si.getRamTotal()));
        holder.swapUsage.setProgress((int) (si.getSwapUsed() * 100 / si.getSwapTotal()));

        holder.cpuUsageText.setText(holder.itemView.getContext().getString(R.string.cpu_usage_d, si.getCpuUsagePercent()));
        holder.ramUsageText.setText(holder.itemView.getContext().getString(R.string.ram_usage,
                Formatter.formatFileSize(holder.itemView.getContext(), si.getRamUsed()),
                Formatter.formatShortFileSize(holder.itemView.getContext(), si.getRamTotal())));

        holder.swapUsageText.setText(holder.itemView.getContext().getString(R.string.swap_usage,
                Formatter.formatShortFileSize(holder.itemView.getContext(), si.getSwapUsed()),
                Formatter.formatShortFileSize(holder.itemView.getContext(), si.getRamTotal())));

        try {
//            Instant boot = Instant.parse(si.getLastBootTimestamp());
//            Instant now = Instant.now();
            LocalDateTime boot = LocalDateTime.parse(si.getLastBootTimestamp());
            LocalDateTime now = LocalDateTime.now();
            Duration uptime = Duration.between(boot, now);
            holder.uptime.setText(holder.itemView.getContext().getString(R.string.uptime, uptime.toString()));
        } catch (Exception e) {
            Log.e("SystmDvcAdptr", "Error parsing / calculating dt", e);
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public class SystemDeviceViewHolder extends RecyclerView.ViewHolder {
        private final TextView deviceName;
        private final TextView ipAddress;
        private final LinearProgressIndicator cpuUsage;
        private final LinearProgressIndicator ramUsage;
        private final LinearProgressIndicator swapUsage;
        private final TextView uptime;
        private final TextView cpuUsageText;
        private final TextView ramUsageText;
        private final TextView swapUsageText;

        public SystemDeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.device_name_tv);
            ipAddress = itemView.findViewById(R.id.ip_address_tv);
            cpuUsage = itemView.findViewById(R.id.cpu_used_progress);
            ramUsage = itemView.findViewById(R.id.ram_used_progress);
            swapUsage = itemView.findViewById(R.id.swap_used_progress);
            uptime = itemView.findViewById(R.id.uptime_textview);
            cpuUsageText = itemView.findViewById(R.id.cpu_usage_text);
            ramUsageText = itemView.findViewById(R.id.ram_usage_text);
            swapUsageText = itemView.findViewById(R.id.swap_usage_text);
        }
    }
}
