package catmonit.app.ui.storage;

import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import catmonit.app.R;
import catmonit.app.databinding.FragmentStorageBinding;


public class StorageFragment extends Fragment {

    private FragmentStorageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StorageViewModel storageViewModel =
                new ViewModelProvider(this).get(StorageViewModel.class);

        binding = FragmentStorageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textStorageUsed;
        storageViewModel.getStorageState().observe(getViewLifecycleOwner(), storageState -> {
            String used = Formatter.formatShortFileSize(getContext(), storageState.getUsed_space_bytes());
            String total = Formatter.formatShortFileSize(getContext(), storageState.getTotal_space_bytes());
            textView.setText(getString(R.string.storage_space_used, used, total));
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}