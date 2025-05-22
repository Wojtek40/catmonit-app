package catmonit.app.ui.networking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import catmonit.app.R;
import catmonit.app.data.LoginRepository;
import catmonit.app.databinding.FragmentNetworkingBinding;
import catmonit.app.ui.login.LoginActivity;

public class NetworkingFragment extends Fragment {

    private FragmentNetworkingBinding binding;
    private LineData networkThroughputData;
    private LineDataSet networkThroughputDataSet;
    private int entryIndex;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (!LoginRepository.getInstance().isLoggedIn()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            requireActivity().finish();
        }
        NetworkingViewModel networkingViewModel =
                new ViewModelProvider(this).get(NetworkingViewModel.class);

        binding = FragmentNetworkingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        setupChart(networkingViewModel.getEntries());
        networkingViewModel.getCurrentNetworkThroughput().observe(getViewLifecycleOwner(), this::addNetworkThroughput);
        return root;
    }

    private void setupChart(List<Long> entries) {
        ArrayList<Entry> parsed = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            parsed.add(new Entry(i, entries.get(i)));
        }
        networkThroughputDataSet = new LineDataSet(parsed, getString(catmonit.app.R.string.networkThroughput));
        entryIndex = parsed.size();
        networkThroughputDataSet.setLineWidth(2f);
        networkThroughputDataSet.setColor(requireContext().getColor(android.R.color.holo_blue_light));
        networkThroughputDataSet.setDrawCircles(false);
        networkThroughputDataSet.setDrawValues(false);

        networkThroughputData = new LineData(networkThroughputDataSet);
        binding.networkThroughput.setData(networkThroughputData);
        binding.networkThroughput.getDescription().setEnabled(false);
        binding.networkThroughput.getAxisRight().setEnabled(false);

        XAxis xAxis = binding.networkThroughput.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = binding.networkThroughput.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(requireContext().getColor(R.color.gray));

        binding.networkThroughput.setTouchEnabled(false);
        binding.networkThroughput.setScaleEnabled(false);
        binding.networkThroughput.getLegend().setTextColor(requireContext().getColor(R.color.gray));
        binding.networkThroughput.moveViewToX(entryIndex);
    }


    private void addNetworkThroughput(Long networkThroughput) {
        networkThroughputDataSet.addEntry(new Entry(entryIndex++, networkThroughput));
        networkThroughputData.notifyDataChanged();
        binding.networkThroughput.notifyDataSetChanged();
        binding.networkThroughput.setVisibleXRangeMaximum(150);
        binding.networkThroughput.moveViewToX(entryIndex);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}