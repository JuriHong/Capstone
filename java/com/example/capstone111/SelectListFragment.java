package com.example.capstone111;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.capstone111.databinding.FragmentSelectlistBinding;
import com.example.capstone111.datamodel.MyPojo;
import com.example.capstone111.datamodel.Path;
import com.example.capstone111.datamodel.TrafficInformation;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectListFragment extends Fragment {

    FragmentSelectlistBinding binding;

    RecyclerView recyclerView2;
    TrafficInfoAdapter adapter2;

    String urlInfo;

    // 오디세이 API 키
    private String apiKey = " 발급받은 API KEY 입력 ";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSelectlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // volley 라이브러리를 이용한 리퀘스트를 담을 리퀘스트 큐 생성
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(root.getContext());
        }

        // 경로탐색 결과가 여러가지 나오기 때문에 리사이클러 뷰로 표현
        recyclerView2 = root.findViewById(R.id.recyclerView2);

        // 리사이클러 뷰 설정
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        adapter2 = new TrafficInfoAdapter(root.getContext());

        // SelectListFragmemnt로 넘어온 번들이 있으면 값 추출
        Bundle bundle = new Bundle();
        bundle = getArguments();

        if(bundle != null){ // SelectListFragmemnt로 넘어온 번들이 있는 경우

            // 시작점, 도착점 정보 추출
            TrevelDataModel StartInfo = bundle.getParcelable("StartInfo");
            TrevelDataModel EndInfo = bundle.getParcelable("EndInfo");

            // 출발지 Start(X, Y), End(X, Y) 값 저장
            String SY = String.valueOf(StartInfo.getLat());
            String SX= String.valueOf(StartInfo.getLag());
            String EY = String.valueOf(EndInfo.getLat());
            String EX = String.valueOf(EndInfo.getLag());

            try {
                // 오디세이 API로 요청 할 URL 설정
                urlInfo = "https://api.odsay.com/v1/api/searchPubTransPathT?SX="+SX+"&SY="+SY+"&EX="+EX+"&EY="+EY+"&apiKey="
                        + URLEncoder.encode(apiKey, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            // 네트워크 요청 할 리퀘스트 생성
            sendRequest();

        }else{
            // 리스트 페이지에서 SelectListFragmemnt로 넘어온 경우 검색

            Toast.makeText(binding.getRoot().getContext(), "리스트 페이지에서 접속", Toast.LENGTH_SHORT).show();
        }



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        Log.d("SelectListFragment", "onDestroy()");
        super.onDestroy();
        binding = null;
    }

    public void processResponse(String response){
        Gson gson = new Gson();
        // response 데이터는 Json 형식으로 MyPojo 클래스로 변환
        // * MyPojo 클래스는 경로 탐색 정보를 담고 있음
        MyPojo data  = gson.fromJson(response, MyPojo.class);

        // 경로 탐색 결과는 여러개가 나올 수 있음
        // data.getResult().getPath()[i]
        // getPath()[i] - i 에 따라 다른 정보가 있음
        int times = data.getResult().getPath().length;

        // 탐색 결과 개수에 따라 어댑터에 아이템 추가
        for(int i = 0; i < times; i++){
            adapter2.addItem(data.getResult().getPath()[i]);
        }

        // 설정한 어댑터를 리사이클러 뷰에 추가
        recyclerView2.setAdapter(adapter2);


    }


    // 네트워크 요청 할 리퀘스트 생성
    public void sendRequest(){

        StringRequest request = new StringRequest(
                Request.Method.GET, // post 방식 사용하면 request 클래스 재정의
                urlInfo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //                  ^  응답 성공하면 response 데이터 받아옴
                        // response 데이터를 인자로 processResponse() 호출
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 실패했을 때 Log 출력
                        Log.d("Odsay API", "요청실패");
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        //이전 정보 있더라도 새로 요청해서 응답함
        request.setShouldCache(false);

        // 리퀘스트 큐에 생성한 리퀘스트 추가
        AppHelper.requestQueue.add(request);
        Log.d("Odsay API", "요청보냄");
    }

}
