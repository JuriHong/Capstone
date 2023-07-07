package com.example.capstone111;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.capstone111.databinding.FragmentFindpwBinding;
import com.example.capstone111.databinding.FragmentNewpwBinding;

public class NewPWFragment extends Fragment {

    FragmentNewpwBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewpwBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public void onDestroy() {
        Log.d("NewPwFragment", "onDestroy()");
        super.onDestroy();
        binding = null;
    }

}
