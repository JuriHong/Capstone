package com.example.capstone111;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.capstone111.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    FirebaseUser mUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mUser = FirebaseAuth.getInstance().getCurrentUser();

        if(mUser != null){
            binding.UIDTextView.setText(mUser.getUid());
        }

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).popBackStack();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        Log.d("NotificationsFragment", "onDestroy()");
        super.onDestroy();
        binding = null;
    }
}