package catmonit.app.ui.storage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import catmonit.app.R;
import catmonit.app.data.LoginRepository;
import catmonit.app.databinding.FragmentStorageBinding;
import catmonit.app.models.DeviceInfoAdapter;
import catmonit.app.models.Warning;
import catmonit.app.models.WarningAdapter;
import catmonit.app.ui.login.LoginActivity;


public class StorageFragment extends Fragment {

    private FragmentStorageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (!LoginRepository.getInstance().isLoggedIn()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            requireActivity().finish();
        }
        StorageViewModel storageViewModel =
                new ViewModelProvider(this).get(StorageViewModel.class);

        binding = FragmentStorageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        storageViewModel.getStorageState().observe(getViewLifecycleOwner(), this::updateDisplay);
        storageViewModel.getLiveStateBuilder().observe(getViewLifecycleOwner(), x -> {
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateDisplay(StorageState storageState) {
        binding.numberErrorsText.setText(String.valueOf(storageState.getErrors().length));
        binding.numberWarningsText.setText(String.valueOf(storageState.getWarnings().length));

        updateMonitView(storageState.getErrors(), binding.errorsRecyclerView, R.layout.error_display);
        updateMonitView(storageState.getWarnings(), binding.warningRecyclerView, R.layout.warning_display);

        DeviceInfoAdapter deviceInfoAdapter = new DeviceInfoAdapter(storageState.getDeviceInfo());
        binding.devicesRecyclerView.setAdapter(deviceInfoAdapter);
        binding.devicesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.deviceSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                deviceInfoAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

    private void updateMonitView(Warning[] warnings, RecyclerView recyclerView, @LayoutRes int layout) {
        WarningAdapter warningAdapter = new WarningAdapter(warnings, layout);
        recyclerView.setAdapter(warningAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}