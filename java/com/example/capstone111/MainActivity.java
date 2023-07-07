package com.example.capstone111;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.capstone111.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    // findViewID() 대체, xml 파일과 자바 파일 연결 시킴, 위젯 아이디 바로 사용 가능
    private ActivityMainBinding binding;

    // 화면 전환 제어용
    static NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // xml 파일을 사용하여 실제 뷰 객체 생성
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //상단 탭 버튼 관리, 최상위 뷰 넣어주면 뒤로가기 버튼 안생김
        // 인자를 비워두면 모든 인터페이스 상단에 뒤로가기 버튼 생성
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder( // 바텀 버튼 세 개
                R.id.navigation_login)
                .build();

        // 화면 전환에 사용할 화면으로 엑티비티 메인에 있는 빈 프래그먼트 id 지정
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        // 컨트롤러랑 상단 탭 버튼 연결
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }

    // 어플 상단에 있는 뒤로가기 버튼 오버라이드, 뒤로가기 버튼 눌렀을 때 행동을 직접 지정
    @Override
    public boolean onSupportNavigateUp() {
        // 어플 상단에 있는 뒤로가기 버튼 누를 시 이전 화면 띄우기
        navController.popBackStack();
        return super.onSupportNavigateUp();
    }

    // 스마트폰 뒤로가기 버튼 콜백 메서드 오버라이드, 뒤로가기 버튼 눌렀을 때 행동을 직접 지정
    @Override
    public void onBackPressed() {
        if(navController.getCurrentDestination().getId() == R.id.navigation_login){
            // 현재화면이 navigation_login 화면이면 토스트 메세지 띄우기
            Toast.makeText(this, "HOME 키를 이용해서 종료해주세요.", Toast.LENGTH_SHORT).show();

        }else {
            // 현재화면이 navigation_login 화면이 아니면 이전 화면 띄우기
            navController.popBackStack();
        }
    }

    public void onDestroy() {
        Log.d("MainActivity", "onDestroy()");
        super.onDestroy();
        binding = null;
    }
}