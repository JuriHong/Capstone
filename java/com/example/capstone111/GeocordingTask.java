package com.example.capstone111;

import android.os.AsyncTask;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.poi_item.TMapPOIItem;

import java.util.ArrayList;

// ******************* 검색한 목적지를 상세한 위치 정보로 변환하는 클래스
public class GeocordingTask extends AsyncTask<String, Void, ArrayList<TMapPOIItem>> {

    private OnPoiSearchCompleteListener mListener;

    public interface OnPoiSearchCompleteListener {
        void onPoiSearchComplete(ArrayList<TMapPOIItem> poiItems);
    }

    public GeocordingTask(OnPoiSearchCompleteListener listener) {
        mListener = listener;
    }

    // HomeFragment에서 poiSearchTask.execute(목적지) 호출 시 작동
    @Override
    protected ArrayList<TMapPOIItem> doInBackground(String... params) {
        String keyword = params[0];               // ^ HomeFragment에서 poiSearchTask.execute(목적지)
                                                  // ^ 목적지가 params에 들어감

        // TMapData 객체 생성
        TMapData tMapData = new TMapData();

        // POI 초기화
        ArrayList<TMapPOIItem> poiItems = null;
        try {
            // 목적지 이름을 주소 정보로 변환 후 poiItems에 저장
            poiItems = tMapData.findAllPOI(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 변환 한 poiItems을 onPostExecute에 반환
        return poiItems;
    }

    @Override
    protected void onPostExecute(ArrayList<TMapPOIItem> poiItems) {
        super.onPostExecute(poiItems);    // ^ doInBackground에서 반환 한 값

        // POI 검색 결과 처리
        if (mListener != null) {
            // onPoiSearchComplete 함수의 정의는 HomeFragment에서 정의 해둠
            mListener.onPoiSearchComplete(poiItems);
        }
    }
}
