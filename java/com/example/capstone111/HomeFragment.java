package com.example.capstone111;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.example.capstone111.databinding.FragmentHomeBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapMarkerItem2;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.poi_item.TMapPOIItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    String LLOG = "HomeFragment"; // Log 확인용 태그
    DatabaseReference mDatabase;
    FirebaseUser mUser;
    String UserUID;

    TMapView tMapView;

    TMapMarkerItem markerItem1; // 검색창에 목적지 입력 시 생성 할 마커 묶음
    TMapMarkerItem DestinationMarkerItem; // 저장 용도 마커

    BottomSheetBehavior<LinearLayout> bottomSheetBehavior;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(LLOG, "Attach");
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // FirebaseAuth.getInstance() 호출하면
        // 현재 Firebase 프로젝트에 대한 인증 인스턴스가 반환
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // 현재 접속 계정 정보 저장
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser != null){
            // 접속한 계정 정보가 Null이 아니면 고유 UID값을 문자열로 저장
            UserUID = mUser.getUid();
        }

        // 지도 화면 설정
        tMapView = new TMapView(root.getContext());
        tMapView.setSKTMapApiKey("6lwAW08l7P6gVjHK4QYVf3DmgUSVWW8R2RAChDIz");
        // LinearLayout에 티맵뷰 넣어주기
        binding.mapView.addView(tMapView);

        // 목적지 입력 버튼
        // 버튼을 누르면 해당 표에 마커 생성
        binding.searchGeoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AsyncTask를 이용한 네트워크 통신 요청 및 받아온 정보로 동작 정의
                GeocordingTask poiSearchTask = new GeocordingTask(new GeocordingTask.OnPoiSearchCompleteListener() {
                    @Override
                    // 통신 요청으로 받아온 정보로 동작 정의
                    public void onPoiSearchComplete(ArrayList<TMapPOIItem> poiItems) {
                        // 검색 결과 처리             ^
                        //                          ^ 입력한 도착지 정보를 TMapPOIItem타입 위치정보(좌표, 주소 등)로 변환, ArrayList타입

                        if (poiItems != null) { // 위치정보가 Null이 아닌 때

                            boolean flag = true;

                            // 사용자 위치추적 모드 OFF
                            tMapView.setTrackingMode(false);
                            // 현재 지도에 표시된 마커 전체 지우기
                            tMapView.removeAllMarkerItem();

                            // 하나의 목적지만 검색해도 여러개가 나올 수 있음
                            // ex) 동명대 검색 시 동명대 1정, 동명대 공학관, 동명대 대학원 등
                            // poiItems는 ArrayList 이므로 하나씩 작업함
                            for (TMapPOIItem poiItem : poiItems) {

                                // 받아온 좌표로 상세 주소 얻기, 마커 생성 메서드
                                ReverseGeocoding(poiItem);

                                // 플래그에 따라 현재 지도 카메라 위치 설정
                                // 첫번째 마커를 기준으로 카메라 고정
                                if (flag){
                                    tMapView.setCenterPoint( poiItem.getPOIPoint().getLongitude(), poiItem.getPOIPoint().getLatitude() );

                                    flag = false;
                                }

                            }

                        }
                    }
                });

                // 입력한 도착지 정보를 인자로
                // AsyncTask를 이용한 네트워크 통신 요청
                // 입력한 도착지 정보를 좌표로 변환하는 작업
                poiSearchTask.execute(binding.searchGeoEditText.getText().toString());
            }
        });

        // 마커 클릭 시 나오는 정보창의 버튼
        tMapView.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
                //                                      ^
                //                                      ^  클릭한 마커의 위치정보가 포함되어있음
                // 길 찾기 또는 목적지 저장 묻는 창 띄우기
                bottomSheetBehavior = BottomSheetBehavior.from(binding.BottomSheetLayout);
                binding.GoDestinationEdit.setVisibility(View.INVISIBLE);
                binding.GoDestinationBtn2.setVisibility(View.INVISIBLE);

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                // 현재 마커의 정보를 DestinationMarkerItem 객체에 저장
                DestinationMarkerItem = tMapMarkerItem;
            }
        });

        // 길 찾기 버튼1 클릭 시 동작
        binding.GoDestinationBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 가려둔 출발지 EditView와 출발지 입력 Button 띄우기
                binding.GoDestinationEdit.setVisibility(View.VISIBLE);
                binding.GoDestinationBtn2.setVisibility(View.VISIBLE);

            }
        });

        // 길 찾기 버튼2 클릭 시 동작
        binding.GoDestinationBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // AsyncTask를 이용한 네트워크 통신 요청 및 받아온 정보로 동작 정의
                GeocordingTask poiSearchTask2 = new GeocordingTask(new GeocordingTask.OnPoiSearchCompleteListener() {
                    @Override
                    // 통신 요청으로 받아온 정보로 동작 정의
                    public void onPoiSearchComplete(ArrayList<TMapPOIItem> poiItems) {
                        // 검색 결과 처리                     // ^
                                                            // ^ 요청으로 받아온 위치정보 ArrayList
                        if (poiItems != null) { // 받아온 정보가 Null이 아닐 때

                            // 사용자 위치추적 모드 OFF
                            tMapView.setTrackingMode(false);
                            // 현재 지도에 표시된 마커 전체 지우기
                            tMapView.removeAllMarkerItem();

                            // 출발지 좌표 정보를 StartTMapPoint에 저장
                            TMapPoint StartTMapPoint = poiItems.get(0).getPOIPoint();
                            // 도착지 좌표 정보를 EndTMapPoint에 저장
                            TMapPoint EndTMapPoint = DestinationMarkerItem.getTMapPoint();

                            // TrevelDataModel는 좌표와 주소 문자열 가지고 있는 클래스
                            // 화면 간 데이터 전달에 이용
                            // 출발지, 도착지 정보를 이용해 TrevelDataModel 타입 객체 2개 생성
                            TrevelDataModel StartInfo = new TrevelDataModel(StartTMapPoint.getLatitude()
                                    , StartTMapPoint.getLongitude(), poiItems.get(0).getPOIName());
                            TrevelDataModel EndInfo = new TrevelDataModel(EndTMapPoint.getLatitude()
                                    ,EndTMapPoint.getLongitude(), DestinationMarkerItem.getCalloutTitle());

                            // TrevelDataModel 타입 객체 2개를 bundle에 저장
                            Bundle Single_Traffic = new Bundle();
                            Single_Traffic.putParcelable("StartInfo", StartInfo);
                            Single_Traffic.putParcelable("EndInfo", EndInfo);
                            // 경로 출력 페이지인 navigation_SelectList 화면으로 저장 한 정보와 함께 이동
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_SelectList, Single_Traffic);

                        }
                    }
                });

                // 입력한 출발지 정보를 인자로
                // AsyncTask를 이용한 네트워크 통신 요청
                // 입력한 출발지 정보를 좌표로 변환하는 작업
                poiSearchTask2.execute(binding.GoDestinationEdit.getText().toString());

            }
        });

        // 목적지 파이어베이스에 저장 버튼
        binding.SaveDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TMapPoint 은 좌표 저장용 클래스
                // 현재 클릭 한 마커 정보에 포한된 좌표를 SaveTMapPoint에 저장
                TMapPoint SaveTMapPoint = DestinationMarkerItem.getTMapPoint();

                // Info -  UserUID - Point 디렉토리에 좌표와 목적지 이름 저장
                // child() 는 하위 항목 생성 시 사용
                // push().setValue()는 추가로 데이터 넣기
                // setValue()만 쓰면 덮어 씀
                mDatabase.child("Info").child(UserUID).child("Point")
                        .push().setValue(new TrevelDataModel(SaveTMapPoint.getLatitude()
                                ,SaveTMapPoint.getLongitude(), DestinationMarkerItem.getCalloutTitle()));

            }
        });

        // 길 찾기 또는 목적지 저장 묻는 창 닫기
        binding.CloseBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }
        });

        // 화면 확대 버튼
        binding.zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tMapView.MapZoomIn();
            }
        });

        // 화면 축소 버튼
        binding.zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tMapView.MapZoomOut();
            }
        });

        return root;
    }

    // 좌표를 주소로 변환하는 메서드
    public void ReverseGeocoding(TMapPOIItem poiItem){

        // AsyncTask를 이용한 네트워크 통신 요청 및 받아온 정보로 동작 정의
        ReverseGeocodingTask reverseGeocodingTask = new ReverseGeocodingTask(new ReverseGeocodingTask.ReverseGeocodingListener() {
            @Override
            public void onAddressFound(String address) {
                //                      ^
                //                      ^ 리버스 지오코딩으로 변환된 상세주소

                // 마커 생성
                markerItem1 = new TMapMarkerItem();
                // 마커 이미지를 bitmap으로 변환
                Bitmap bitmap = BitmapFactory.decodeResource(binding.getRoot().getContext().getResources(), R.drawable.map_pin_red);

                // 마커 아이콘 지정
                markerItem1.setIcon(bitmap);
                // 마커의 좌표 지정
                markerItem1.setTMapPoint( poiItem.getPOIPoint() );
                // 마커의 타이틀 지정
                markerItem1.setName(poiItem.getPOIName());
                // 마커 클릭 시 말풍선 표시 ON
                markerItem1.setCanShowCallout(true);
                // 말풍선 제목
                markerItem1.setCalloutTitle(poiItem.getPOIName());
                // 말풍선 소제목
                markerItem1.setCalloutSubTitle(address);
                // 말풍선에 버튼 생성
                markerItem1.setCalloutRightButtonImage(bitmap);

                // 지도에 마커 추가
                tMapView.addMarkerItem(poiItem.getPOIName(), markerItem1); // 지도에 마커 추가

            }
        });

        // 도착지 정보를 인자로
        // AsyncTask를 이용한 네트워크 통신 요청
        // 좌표를 주소로 변환하는 작업
        reverseGeocodingTask.execute(poiItem.getPOIPoint());

    }


    @Override
    public void onResume() {
        Log.d(LLOG, "Resume");
        super.onResume();
        }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onLowMemory() {
        Log.d(LLOG, "onLowMemory");
        super.onLowMemory();
    }
    @Override
    public void onDestroyView() {
        Log.d(LLOG, "onDestroyView()");
        super.onDestroyView();


    }

    @Override
    public void onDestroy() {
        Log.d(LLOG, "onDestroy()");
        super.onDestroy();
        binding = null;
    }



}