package com.example.capstone111.datamodel;

public class TrafficInformation {

    String Total_Time;
    String Start_Name;
    String End_Name;
    String Walk_Distance;
    String []Bus_No;
    String Subway_Line_Name;

    public TrafficInformation() {
    }

    public TrafficInformation(String total_Time, String start_Name,
                              String end_Name, String walk_Distance,
                              String[] bus_No, String subway_Line_Name) {
        Total_Time = total_Time;
        Start_Name = start_Name;
        End_Name = end_Name;
        Walk_Distance = walk_Distance;
        Bus_No = bus_No;
        Subway_Line_Name = subway_Line_Name;
    }

    public TrafficInformation(String total_Time, String start_Name,
                              String end_Name, String walk_Distance,
                              String[] bus_No) {
        Total_Time = total_Time;
        Start_Name = start_Name;
        End_Name = end_Name;
        Walk_Distance = walk_Distance;
        Bus_No = bus_No;
    }

    public String getTotal_Time() {
        return Total_Time;
    }

    public void setTotal_Time(String total_Time) {
        Total_Time = total_Time;
    }

    public String getStart_Name() {
        return Start_Name;
    }

    public void setStart_Name(String start_Name) {
        Start_Name = start_Name;
    }

    public String getEnd_Name() {
        return End_Name;
    }

    public void setEnd_Name(String end_Name) {
        End_Name = end_Name;
    }

    public String getWalk_Distance() {
        return Walk_Distance;
    }

    public void setWalk_Distance(String walk_Distance) {
        Walk_Distance = walk_Distance;
    }

    public String[] getBus_No() {
        return Bus_No;
    }

    public void setBus_No(String[] bus_No) {
        Bus_No = bus_No;
    }

    public String getSubway_Line_Name() {
        return Subway_Line_Name;
    }

    public void setSubway_Line_Name(String subway_Line_Name) {
        Subway_Line_Name = subway_Line_Name;
    }
}
