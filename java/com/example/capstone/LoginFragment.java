package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.capstone.databinding.FragmentLoginBinding;
import com.example.capstone.databinding.FragmentNotificationsBinding;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root.getContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

        binding.SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.navigation_signup);
            }
        });

        binding.findIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.navigation_findid);
            }
        });

        binding.findPWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.navigation_findpw);
            }
        });


        return root;
    }
}
