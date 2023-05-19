package com.example.capstone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.capstone.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

public class MainActivity extends AppCompatActivity{


    private ActivityMainBinding binding;
    static NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder( // 바텀 버튼 세 개
                R.id.navigation_login)
                .build();//상단 탭 버튼 관리 최상위 뷰 넣어주면 뒤로가기 버튼 안생김
        // 인자를 비워두면 모든 인터페이스에 뒤로가기 버튼 생성
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main); // 엑티비티 메인에 있는 빈 프래그먼트 id
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration); // 컨트롤러랑 네비 바 연


    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.popBackStack();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(navController.getCurrentDestination().getId() == R.id.navigation_login){
            Toast.makeText(this, "HOME 키를 이용해서 종료해주세요.", Toast.LENGTH_SHORT).show();

        }else {
            navController.popBackStack();
        }
    }

}