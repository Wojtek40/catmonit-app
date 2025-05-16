package catmonit.app.models;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import catmonit.app.R;

public class DeviceInfoAdapter extends RecyclerView.Adapter<DeviceInfoAdapter.DeviceInfoHolder> implements Filterable {
    private final ArrayList<DeviceInfo> filteredDeviceInfo;
    private final DeviceInfo[] allDeviceInfo;
    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DeviceInfo> filtered = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtered.addAll(Arrays.asList(allDeviceInfo));
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DeviceInfo deviceInfo : allDeviceInfo) {
                    if (deviceInfo.deviceName.toLowerCase().contains(filterPattern) ||
                            deviceInfo.ipAddress.toLowerCase().contains(filterPattern)) {
                        filtered.add(deviceInfo);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredDeviceInfo.clear();
            filteredDeviceInfo.addAll((List<DeviceInfo>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public DeviceInfoAdapter(DeviceInfo[] deviceInfo) {
        this.filteredDeviceInfo = new ArrayList<>(Arrays.asList(deviceInfo));
        this.allDeviceInfo = deviceInfo;
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
        DeviceInfo di = filteredDeviceInfo.get(position);
        String usedText = Formatter.formatShortFileSize(holder.itemView.getContext(), di.getUsedSpace());
        String totalText = Formatter.formatShortFileSize(holder.itemView.getContext(), di.getTotalSpace());

        DiscInfoAdapter discInfoAdapter = new DiscInfoAdapter(di.getDiscInfo());
        holder.discsInfo.setAdapter(discInfoAdapter);
        holder.discsInfo.setLayoutManager(new LinearLayoutManager(null));

        holder.deviceName.setText(di.getDeviceName());
        holder.ipAddress.setText(di.getIpAddress());
        holder.total.setText(holder.itemView.getContext().getString(R.string.storage_space_used, usedText, totalText));
        drawChart(di.getUsedSpace(), di.getTotalSpace(), holder.itemView.getContext(), holder.chart);
    }

    @Override
    public int getItemCount() {
        return filteredDeviceInfo.size();
    }

    private void drawChart(long used, long total, Context context, PieChart chart) {
        long available = total - used;

        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(used, "Used"));
        data.add(new PieEntry(available, "Free"));

        PieDataSet dataSet = new PieDataSet(data, "");

        dataSet.setColors(context.getColor(R.color.midnight_green), context.getColor(R.color.vanilla));
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);

        dataSet.setValueTextColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Formatter.formatShortFileSize(context, (long) value);
            }
        });
        pieData.setDrawValues(false);

        chart.getLegend().setTextColor(context.getColor(R.color.gray));
        chart.setDrawEntryLabels(false);
        chart.setDrawCenterText(false);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(false);
        chart.setTransparentCircleRadius(0f);
        chart.setDrawHoleEnabled(false);
        chart.getLegend().setEnabled(false);

        chart.setData(pieData);
        chart.invalidate();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public static class DeviceInfoHolder extends RecyclerView.ViewHolder {
        TextView deviceName;
        TextView ipAddress;
        RecyclerView discsInfo;
        PieChart chart;
        TextView total;

        public DeviceInfoHolder(@NonNull View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.device_name_tv);
            ipAddress = itemView.findViewById(R.id.ip_address_tv);
            discsInfo = itemView.findViewById(R.id.discs_recyclerView);
            chart = itemView.findViewById(R.id.chart_storage_used);
            total = itemView.findViewById(R.id.total_used);
        }
    }
}
