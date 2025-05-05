package catmonit.app.ui.networking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import catmonit.app.databinding.FragmentNetworkingBinding;

public class NetworkingFragment extends Fragment {

    private FragmentNetworkingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NetworkingViewModel networkingViewModel =
                new ViewModelProvider(this).get(NetworkingViewModel.class);

        binding = FragmentNetworkingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.bandwidthUsedText;
        networkingViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}