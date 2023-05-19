package com.example.capstone;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.capstone.databinding.ActivityMain2Binding;
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

public class MainActivity2 extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback{


    private ActivityMain2Binding binding;
    static NavController navController;

    String []REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int hasFINELocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCorseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(hasFINELocationPermission == PackageManager.PERMISSION_GRANTED && hasCorseLocationPermission == PackageManager.PERMISSION_GRANTED){
            // 위치 업데이트 문 작성
            Toast.makeText(this, "권한이 허용 되어있습니다", Toast.LENGTH_SHORT).show();
        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])){
                Toast.makeText(this, "이 앱 실행 시 위치 접근 권한 필요", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }else{
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }
        BottomNavigationView navView = findViewById(R.id.nav_view); // 엑티비티 메인에 있는 네비게이션 뷰
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder( // 바텀 버튼 세 개
                R.id.navigation_Map, R.id.navigation_List, R.id.navigation_Setting)
                .build();//상단 탭 버튼 관리 최상위 뷰 넣어주면 뒤로가기 버튼 안생김
        // 인자를 비워두면 모든 인터페이스에 뒤로가기 버튼 생성
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2); // 엑티비티 메인에 있는 빈 프래그먼트 id
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration); // 컨트롤러랑 네비 바 연
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_Map:
                        navController.navigate(R.id.navigation_Map);
                        break;
                    case R.id.navigation_List:
                        navController.navigate(R.id.navigation_List);
                        break;
                    case R.id.navigation_Setting:
                        navController.navigate(R.id.navigation_Setting);
                        break;
                }
                return true;
            }
        });

    }
    // 상단 탭 이름 변경은 네비xml 파일에서 label 변경


    @Override
    public boolean onSupportNavigateUp() {
        navController.popBackStack();

        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(navController.getCurrentDestination().getId() == R.id.navigation_SelectList){
            navController.popBackStack();
        }else {
            Toast.makeText(this, "HOME 키를 이용해서 종료해주세요.", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.length == REQUIRED_PERMISSIONS.length) {
            boolean check_result = true;

            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            if (check_result) {
                // 퍼미션 둘 다 허용 확인 했으니까 위치 업데이트문 작성
                Toast.makeText(this, "권한이 허용 되었습니다!!!", Toast.LENGTH_SHORT).show();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(this, "권한이 거부 되었습니다. 앱을 다시 실행 해주세요1", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "설정에서 권한을 허용 해주세요.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

}