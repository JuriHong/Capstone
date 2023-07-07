package com.example.capstone111;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

// *************  위치 정보 저장 용 클래스
// implements Parcelable 하는 이유는
// 객체를 다른 컴포넌트에 전달하려면 직렬화 해주어야 함
public class TrevelDataModel implements Parcelable {
    double Lat;
    double Lag;
    String address;



    public TrevelDataModel(double lat, double lag, String address) {
        Lat = lat;
        Lag = lag;
        this.address = address;
    }

    public TrevelDataModel(Parcel src) {
        Lat = src.readDouble();
        Lag = src.readDouble();
        address = src.readString();
    }

    public TrevelDataModel() {
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TrevelDataModel createFromParcel(Parcel in) {
            return new TrevelDataModel(in);
        }

        public TrevelDataModel[] newArray(int size) {
            return new TrevelDataModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeDouble(Lat);
        parcel.writeDouble(Lag);
        parcel.writeString(address);
    }


    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLag() {
        return Lag;
    }

    public void setLag(double lag) {
        Lag = lag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
