package com.example.capstone111;

import android.os.AsyncTask;
import android.util.Log;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPoint;

public class ReverseGeocodingTask extends AsyncTask<TMapPoint, Void, String> {

    private static final String TAG = "ReverseGeocodingTask";

    private ReverseGeocodingListener listener;

    public interface ReverseGeocodingListener {
        void onAddressFound(String address);
    }


    public ReverseGeocodingTask(ReverseGeocodingListener listener) {
        this.listener = listener;
    }

    // HomeFragment에서 reverseGeocodingTask.execute(poiItem.getPOIPoint())
    // 호출 시 작동
    @Override
    protected String doInBackground(TMapPoint... tMapPoints) {
        TMapPoint tMapPoint = tMapPoints[0];// ^ HomeFragment에서 reverseGeocodingTask.execute(poiItem.getPOIPoint())
                                            // ^ poiItem.getPOIPoint()가 tMapPoints에 들어감

        TMapData tMapData = new TMapData();

        String address = "";

        try {
            // 좌표를 상세 주소로 변환
            address = tMapData.convertGpsToAddress(tMapPoint.getLatitude(), tMapPoint.getLongitude());

        } catch (Exception e) {
            Log.e(TAG, "Error reverse geocoding: " + e.getMessage());
        }

        // 변환 한 address onPostExecute에 반환
        return address;
    }

    @Override
    protected void onPostExecute(String address) {
        if (listener != null) {   // ^ doInBackground에서 반환 한 값
            // onAddressFound 함수의 정의는 HomeFragment에서 정의 해둠
            listener.onAddressFound(address);
        }
    }
}