package catmonit.app.ui.storage;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

import catmonit.app.R;
import catmonit.app.databinding.FragmentStorageBinding;
import catmonit.app.models.DeviceInfoAdapter;
import catmonit.app.models.Warning;
import catmonit.app.models.WarningAdapter;


public class StorageFragment extends Fragment {

    private FragmentStorageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StorageViewModel storageViewModel =
                new ViewModelProvider(this).get(StorageViewModel.class);

        binding = FragmentStorageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        storageViewModel.getStorageState().observe(getViewLifecycleOwner(), this::updateDisplay);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void updateDisplay(StorageState storageState){
        drawChart(storageState.getUsed_space_bytes(), storageState.getTotal_space_bytes());
        binding.numberErrorsText.setText(String.valueOf(storageState.getErrors().length));
        binding.numberWarningsText.setText(String.valueOf(storageState.getWarnings().length));

        updateMonitView(storageState.getErrors(), binding.errorsRecyclerView);
        updateMonitView(storageState.getWarnings(), binding.warningRecyclerView);

        DeviceInfoAdapter deviceInfoAdapter = new DeviceInfoAdapter(storageState.getDeviceInfo());
        binding.devicesRecyclerView.setAdapter(deviceInfoAdapter);
        binding.devicesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateMonitView(Warning[] warnings, RecyclerView recyclerView){
        WarningAdapter warningAdapter = new WarningAdapter(warnings);
        recyclerView.setAdapter(warningAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void drawChart(long used, long total){
        long available = total - used;

        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(used, "Used"));
        data.add(new PieEntry(available, "Free"));

        PieDataSet dataSet = new PieDataSet(data, "");
        dataSet.setColors(requireContext().getColor(R.color.midnight_green), requireContext().getColor(R.color.vanilla));
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);

        dataSet.setValueTextColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Formatter.formatShortFileSize(getContext(), (long) value);
            }
        });
        pieData.setValueTextSize(14f);

        String usedText = Formatter.formatShortFileSize(getContext(), used);
        String totalText = Formatter.formatShortFileSize(getContext(), total);

        PieChart chart = binding.chartStorageUsed;

        chart.getLegend().setTextColor(requireContext().getColor(R.color.gray));
        chart.setDrawEntryLabels(false);
        chart.setCenterText(getString(R.string.storage_space_used, usedText, totalText));
        chart.setDrawCenterText(true);
        chart.getDescription().setEnabled(false);

        chart.setData(pieData);
        chart.invalidate();
    }
}