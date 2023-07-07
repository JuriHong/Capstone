package com.example.capstone111;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.capstone111.databinding.FragmentFindpwBinding;

public class FindPWFragment extends Fragment {

    FragmentFindpwBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFindpwBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.FindPWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.navigation_newpw);
            }
        });

        return root;
    }

    public void onDestroy() {
        Log.d("FindPWFragment", "onDestroy()");
        super.onDestroy();
        binding = null;
    }

}
