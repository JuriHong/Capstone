package com.example.capstone111;

import android.content.Intent;
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
import com.example.capstone111.databinding.FragmentNotificationsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;

    FirebaseAuth mAuth;

    // 파이어 베이스에서 인증된 이메일 확인용
    FirebaseUser mUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // FirebaseAuth.getInstance() 호출하면
        // 현재 Firebase 프로젝트에 대한 인증 인스턴스가 반환
        mAuth = FirebaseAuth.getInstance();

        // 로그인 버튼 클릭 시
        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditView에서 아이디, 비밀번호 값 가져오기
                String ID = binding.IdEditView.getText().toString().trim();
                String PW = binding.PasswordEditView.getText().toString().trim();

                // 사용자 인증 함수 실행
                // 밑에 정의
                Login(ID, PW);

            }
        });

        // 회원가입 버튼 눌렀을 때
        binding.SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 화면 전환용 메서드 navigate() 안에 이동할 프래그먼트 ID 넣기
                Navigation.findNavController(root).navigate(R.id.navigation_signup);
            }
        });

        // 아이디 찾기 버튼
        binding.findIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.navigation_findid);
            }
        });

        // 비밀번호 찾기 버튼
        binding.findPWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.navigation_findpw);
            }
        });


        return root;
    }

    public void Login(String ID, String PW){

        // 아이디, 비밀번호 공백 체크
        if(!ID.equals("") && !PW.equals("")){

            // 파이어베이스에 저장 되어있는 계정인지 확인
            mAuth.signInWithEmailAndPassword(ID, PW)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override // 확인 성공 했을 때
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {// 인증 성공했을 때

                                // 현재 접속 계정 정보 저장
                                mUser = FirebaseAuth.getInstance().getCurrentUser();

                                if(mUser.isEmailVerified()){ // 현재 접속한 계정의 이메일이 인증 된 상태 일 때
                                    Toast.makeText(getContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                                    // 지도 화면으로 전환
                                    Intent intent = new Intent(getContext(), MainActivity2.class);
                                    startActivity(intent);
                                }else { // 현재 접속한 계정의 이메일이 인증 된 상태가 아닌 때
                                    // 인증 이메일 전송 코드
                                    mUser.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) { // 인증 이메일 전송 여부
                                                        Toast.makeText(getContext(), "-인증 이메일 전송-", Toast.LENGTH_SHORT).show();

                                                    }else{
                                                        Toast.makeText(getContext(), "-인증 이메일 전송 실패-", Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });
                                }


                            } else {// 확인 실패했을 때
                                Toast.makeText(getContext(), "로그인 오류", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else { // 아이디 또는 비밀번호 EditView가 공백 일 때
            Toast.makeText(getContext(), "로그인 정보를 입력 해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onDestroy() {
        Log.d("LoginFragment", "onDestroy()");
        super.onDestroy();
        binding = null;
    }
}
