package com.example.capstone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.capstone.databinding.FragmentLoginBinding;
import com.example.capstone.databinding.FragmentSignupBinding;

public class SignUpFragment extends Fragment {

    FragmentSignupBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        return root;
    }
}
