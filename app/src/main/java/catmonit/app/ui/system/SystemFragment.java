package catmonit.app.ui.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import catmonit.app.data.LoginRepository;
import catmonit.app.databinding.FragmentSystemBinding;
import catmonit.app.models.SystemDeviceAdapter;
import catmonit.app.ui.login.LoginActivity;

public class SystemFragment extends Fragment {

    private SystemViewModel mViewModel;
    private FragmentSystemBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (!LoginRepository.getInstance().isLoggedIn()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            requireActivity().finish();
        }
        mViewModel = new ViewModelProvider(this).get(SystemViewModel.class);

        binding = FragmentSystemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getSystemState().observe(getViewLifecycleOwner(), this::updateDisplay);
        return root;
    }

    private void updateDisplay(SystemState systemState) {
        SystemDeviceAdapter systemDeviceAdapter = new SystemDeviceAdapter(systemState.getSystemDeviceInfo());
        binding.devicesRecyclerView.setAdapter(systemDeviceAdapter);
        binding.devicesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}