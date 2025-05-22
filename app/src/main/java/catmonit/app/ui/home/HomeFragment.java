package catmonit.app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import catmonit.app.R;
import catmonit.app.data.LoginRepository;
import catmonit.app.databinding.FragmentHomeBinding;
import catmonit.app.ui.login.LoginActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (!LoginRepository.getInstance().isLoggedIn()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            requireActivity().finish();
        }
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), mText -> textView.setText(getString(R.string.welcome, mText)));
        binding.buttonLogout.setOnClickListener(view -> {
            homeViewModel.logout(getContext());
            startActivity(new Intent(getContext(), LoginActivity.class));
            requireActivity().finish();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}