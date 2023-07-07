package com.example.capstone111;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.capstone111.databinding.FragmentLoginBinding;
import com.example.capstone111.databinding.FragmentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {

    FragmentSignupBinding binding;

    FirebaseAuth mAuth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        binding.SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ID = binding.InsertEmailEditView.getText().toString().trim();
                String PW = binding.InsertPasswordEditView.getText().toString().trim();
                String CheckPW = binding.CheckPasswordEditView.getText().toString().trim();

                CreateAccount(ID, PW, CheckPW, view);

            }
        });



        return root;
    }

    public void CreateAccount(String ID, String PW, String CheckPW, View view){

        if (!ID.equals("") && !PW.equals("")){
            if(PW.equals(CheckPW)){

                mAuth.createUserWithEmailAndPassword(ID, PW).
                        addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "회원가입 성공했습니다", Toast.LENGTH_SHORT).show();

                                    Navigation.findNavController(view).popBackStack();


                                } else {
                                    try{ // 이메일 중복 방지 코드
                                        task.getResult();
                                    }catch (Exception e) {
                                        e.printStackTrace();
                                        Log.d("Fail_register_email",e.getMessage());
                                        Toast.makeText(getActivity(), "중복 이메일 형식입니다 다시 입력해주세요", Toast.LENGTH_LONG).show();
                                    }
                                }


                            }
                        });


            }else {
                Toast.makeText(getActivity(), "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getActivity(), "정보를 입력 해주세요.", Toast.LENGTH_SHORT).show();
        }

    }

    public void onDestroy() {
        Log.d("SignUpFragment", "onDestroy()");
        super.onDestroy();
        binding = null;
    }
}
