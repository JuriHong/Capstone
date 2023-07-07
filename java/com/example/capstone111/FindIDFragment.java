package com.example.capstone111;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.capstone111.databinding.FragmentFindidBinding;

public class FindIDFragment extends Fragment {

    FragmentFindidBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFindidBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        return root;
    }

    public void onDestroy() {
        Log.d("FindIDFragment", "onDestroy()");
        super.onDestroy();
        binding = null;
    }

}
