package com.example.capstone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.capstone.databinding.FragmentFindpwBinding;
import com.example.capstone.databinding.FragmentNewpwBinding;

public class NewPWFragment extends Fragment {

    FragmentNewpwBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewpwBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        return root;
    }
}
