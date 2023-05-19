package com.example.capstone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.example.capstone.databinding.FragmentHomeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private FragmentHomeBinding binding;

    private FusedLocationProviderClient mFusedLocationProviderClient; // Deprecated된 FusedLocationApi를 대체
    private LocationRequest locationRequest;
    public Location mCurrentLocatiion;

    private Marker currentMarker = null;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000 * 60 * 1;  // 1분 단위 시간 갱신
    private static final int FASTEST_UPDATE_INTERVAL_MS = 1000 * 30 ; // 30초 단위로 화면 갱신

    LatLng currentPosition;
    boolean currentFlag = true;
    LatLng SearchPosition;


    private final LatLng mDefaultLocation = new LatLng(37.56, 126.97);
    private static final int DEFAULT_ZOOM = 15;

    private GoogleMap mMap;
    private FragmentActivity myContext;
    MainActivity mainActivity;
    String LLOG = "LLOG";
    String SearchAddress;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(LLOG, "Attach");
        super.onAttach(context);
        myContext=(FragmentActivity) context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Log.d(LLOG, "onCreateView");

        binding.mapview.onCreate(savedInstanceState);

        binding.mapview.getMapAsync(this);

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("tag", 12);
                // Navigation.findNavController(view).navigate(R.id.action_navigation_Map_to_navigation_Road);
                //Navigation.findNavController(root).navigate(R.id.action_navigation_Map_to_navigation_Road, bundle);
                Toast.makeText(binding.getRoot().getContext(), "다른 프래그먼트로 이동", Toast.LENGTH_SHORT).show();
            }
        });


        locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY) // 정확도를 최우선적으로 고려
                .setInterval(UPDATE_INTERVAL_MS) // 위치가 Update 되는 주기
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS); // 위치 획득후 업데이트되는 주기

        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);

        // FusedLocationProviderClient 객체 생성
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(myContext);

        return root;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        Log.d(LLOG, "onMapReady");
        mMap = googleMap;

        //setDefaultLocation();
        updateLocationUI();
        getDeviceLocation();






        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address_edit = binding.searchGeo.getText().toString();
                List<Address> addresses = null;
                Geocoder geocoder = new Geocoder(myContext, Locale.getDefault());
                double AddressInfo[] = new double[2];
                try {
                    addresses = geocoder.getFromLocationName(address_edit, 10);

                    if(addresses != null){

                        if(addresses.size() > 0){

                            AddressInfo[0] = addresses.get(0).getLatitude();
                            AddressInfo[1] = addresses.get(0).getLongitude();

                            SearchPosition = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(SearchPosition, 15);
                            SearchAddress = addresses.get(0).getAddressLine(0);

                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(SearchPosition);
                            markerOptions.title(SearchAddress);
                            markerOptions.snippet("....");
                            markerOptions.draggable(true);
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                            mMap.clear();
                            mMap.addMarker(markerOptions);
                            mMap.moveCamera(cameraUpdate);
                            mMap.setOnInfoWindowClickListener(infoWindowClickListener);
                        }else{
                            Toast.makeText(myContext, "올바른 주소 입력", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (IOException e){
                    Toast.makeText(myContext, "위치로부터 주소 인식할 수 없음.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            AlertDialog.Builder menu = new AlertDialog.Builder(myContext);
            menu.setIcon(R.mipmap.ic_launcher);
            menu.setTitle("목저지 추가"); // 제목
            menu.setMessage("목적지를 추가 하겠습니까?"); // 문구


            // 확인 버튼
            menu.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(binding.getRoot().getContext(), "다른 프래그먼트로 이동", Toast.LENGTH_SHORT).show();
                }
            });

            // 취소 버튼
            menu.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // dialog 제거
                    dialog.dismiss();
                }
            });

            menu.show();
        }
    };
    private void setDefaultLocation() {
        Log.d(LLOG, "setDefaultLocation");

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(mDefaultLocation);
        markerOptions.title("위치정보 가져올 수 없음");
        markerOptions.snippet("위치 퍼미션과 GPS 활성 여부 확인하세요");
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mDefaultLocation, 15);
        mMap.moveCamera(cameraUpdate);
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            Log.d(LLOG, "onLocationResult");
            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                Location location = locationList.get(locationList.size() - 1);

                currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

                if(currentFlag == true){
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentPosition, 15);
                    mMap.moveCamera(cameraUpdate);
                    currentFlag = false;
                }

            }
        }

    };
    private void getDeviceLocation() {
        Log.d(LLOG, "getDeviceLocation");
        try {
                mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }



    private void updateLocationUI() {
        Log.d(LLOG, "updateLocationUI");
        if (mMap == null) {
            Log.d(LLOG, "updateLocationmMapNull");
            return;
        }
        try {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    @Override
    public void onResume() {
        Log.d(LLOG, "Resume");
        binding.mapview.onResume();
        super.onResume();
        }

    @Override
    public void onStop() {
        super.onStop();

        if(mFusedLocationProviderClient != null){
            Log.d(LLOG, "onStop(), mMap이 null 아니기 때문에 mFusedLocationProviderClient 삭제");
            mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    @Override
    public void onLowMemory() {
        Log.d(LLOG, "onLowMemory");
        super.onLowMemory();
        binding.mapview.onLowMemory();
    }
    @Override
    public void onDestroyView() {
        Log.d(LLOG, "onDestroyView");
        super.onDestroyView();
        binding = null;

    }
}