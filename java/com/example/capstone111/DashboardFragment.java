package com.example.capstone111;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.capstone111.databinding.FragmentDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    String tag = "Cycle";

    ListAdapter adapter; // 리사이클러뷰 관리용
    DatabaseReference mDatabase;
    FirebaseUser mUser;

    RecyclerView recyclerView;
    private String UserUID;  // 사용자 고유 UID 저장 용 변수

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(tag, "attach");
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.d(tag, "onCreateView");

        // xml 파일에 있는 리사이클러뷰를 실제 객체로 생성
        recyclerView = root.findViewById(R.id.recyclerView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        // 현재 사용자 정보 저장
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser != null){
            // 현재 사용자 고유 UID 저장
            UserUID = mUser.getUid();
        }

        // 리사이클러 뷰 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(root.getContext());

        // 파이어베이스에 내용이 변경될 시 동작 정의
        // 리스트 페이지에 리스트 출력
        mDatabase.child("Info").child(UserUID).child("Point").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(root.getContext(), "onDataChange", Toast.LENGTH_SHORT).show();

                TrevelDataModel list;

                // 어댑터 초기화
                adapter.Clear();

                // 변경된 내용을 하나씩 ds에 저장
                for(DataSnapshot ds : snapshot.getChildren()) {

                    // ds에 저장된 데이터를 TrevelDataMode 타입 List 객체에 저장
                    list = ds.getValue(TrevelDataModel.class);

                    //list 라는 아이템을 어댑터 추가
                    adapter.addItem(list);
                }

                // 변경된 어댑터를 리사이클러 뷰에 저장
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Read DB", "94Line Fail");
            }
        });

        // 경로 찾기 버튼
        binding.SearchCorseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 경로 출력 페이지인 navigation_SelectList 화면으로 이동
                Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_SelectList);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(tag, "destroyview");
    }

    public void onDestroy() {
        Log.d(tag, "onDestroy()");
        super.onDestroy();
        binding = null;
    }

}