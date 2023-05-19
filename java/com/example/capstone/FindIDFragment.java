package com.example.capstone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.capstone.databinding.FragmentFindidBinding;

public class FindIDFragment extends Fragment {

    FragmentFindidBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFindidBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        return root;
    }

}
