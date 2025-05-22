package catmonit.app.ui.fileshares;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import catmonit.app.data.LoginRepository;
import catmonit.app.databinding.FragmentFilesharesBinding;
import catmonit.app.models.DeviceInfoAdapter;
import catmonit.app.ui.login.LoginActivity;

public class FilesharesFragment extends Fragment {

    private FilesharesViewModel mFilesharesViewModel;
    private FragmentFilesharesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (!LoginRepository.getInstance().isLoggedIn()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            requireActivity().finish();
        }
        mFilesharesViewModel = new ViewModelProvider(this).get(FilesharesViewModel.class);

        binding = FragmentFilesharesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mFilesharesViewModel.getFilesharesState().observe(getViewLifecycleOwner(), this::updateDisplay);
        mFilesharesViewModel.getLiveStateBuilder().observe(getViewLifecycleOwner(), x -> {
        });

        return root;
    }

    private void updateDisplay(FilesharesState filesharesState) {
        DeviceInfoAdapter deviceInfoAdapter = new DeviceInfoAdapter(filesharesState.getDeviceInfo());
        binding.devicesRecyclerViewfs.setAdapter(deviceInfoAdapter);
        binding.devicesRecyclerViewfs.setLayoutManager(new LinearLayoutManager(getActivity()));

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
}