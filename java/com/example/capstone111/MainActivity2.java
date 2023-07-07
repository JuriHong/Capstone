package com.example.capstone111;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.capstone111.databinding.ActivityMain2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.capstone111.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity2 extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback{


    private ActivityMain2Binding binding;
    static NavController navController;

    // 부여 할 권한 묶음 생성( 앱이 사용자의 위치 정보에 액세스할 수 있는 권한)
    String []REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 권한이 허용되었는지 스스로 체크 후 허용 여부 반환
        int hasFINELocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCorseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        // 두 가지 권한 모두 허용 된 경우
        if(hasFINELocationPermission == PackageManager.PERMISSION_GRANTED && hasCorseLocationPermission == PackageManager.PERMISSION_GRANTED){

            Toast.makeText(this, "권한이 허용 되어있습니다", Toast.LENGTH_SHORT).show();
        }else{ // 아닌 경우
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])){

                Toast.makeText(this, "이 앱 실행 시 위치 접근 권한 필요", Toast.LENGTH_SHORT).show();

                // 권한 허용 다이얼로그 띄우기
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }else{

                // 권한 허용 다이얼로그 띄우기
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }
        // 하단에 있는 바텀 내비게이션 뷰 정의
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // 상단 탭 버튼 관리
        // 인자를 비워두면 모든 인터페이스에 뒤로가기 버튼 생성
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder( // 바텀 버튼 세 개
                R.id.navigation_Map, R.id.navigation_List, R.id.navigation_Setting)
                .build();

        // 화면 전환용 내비 컨트롤러와 상단 탭 버튼, 하단의 바텀 내비게이션 연결
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2); // 엑티비티 메인에 있는 빈 프래그먼트 id
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration); // 컨트롤러랑 네비 바 연
        NavigationUI.setupWithNavController(navView, navController);

    }

    // 상단 탭 이름 변경은 네비xml 파일에서 label 변경


    @Override
    public boolean onSupportNavigateUp() {
        navController.popBackStack();

        Toast.makeText(this, "뒤로가기 화살표 클릭", Toast.LENGTH_SHORT).show();

        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(navController.getCurrentDestination().getId() == R.id.navigation_Map){
            Toast.makeText(this, "HOME 버튼으로 종료해주세요.", Toast.LENGTH_SHORT).show();
        }else{
            super.onBackPressed();
        }
    }


    // 권한 허용 다이얼 로그에서 허용 또는 거절을 눌렀을 때 실행되는 콜백 메서드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //권한이 허용 되었는지 체크
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.length == REQUIRED_PERMISSIONS.length) {
            boolean check_result = true;

            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            // 권한 허용 여부에 따른 동작
            if (check_result) {
                // 퍼미션 둘 다 허용 확인 했으니까 위치 업데이트문 작성
                Toast.makeText(this, "권한이 허용 되었습니다!!!", Toast.LENGTH_SHORT).show();
            } else { // 거절 했을 때 앱 종료
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

    public void onDestroy() {
        Log.d("MainActivity", "onDestroy()");
        super.onDestroy();
        binding = null;
    }
}