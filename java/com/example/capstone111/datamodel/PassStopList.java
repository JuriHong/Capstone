package com.example.capstone111.datamodel;

public class PassStopList
{
    private Stations[] stations;

    public Stations[] getStations ()
    {
        return stations;
    }

    public void setStations (Stations[] stations)
    {
        this.stations = stations;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [stations = "+stations+"]";
    }
}
