package com.example.capstone111.datamodel;

public class Stations
{
    private String x;

    private String index;

    private String y;

    private String stationName;

    private String stationID;

    public String getX ()
    {
        return x;
    }

    public void setX (String x)
    {
        this.x = x;
    }

    public String getIndex ()
    {
        return index;
    }

    public void setIndex (String index)
    {
        this.index = index;
    }

    public String getY ()
    {
        return y;
    }

    public void setY (String y)
    {
        this.y = y;
    }

    public String getStationName ()
    {
        return stationName;
    }

    public void setStationName (String stationName)
    {
        this.stationName = stationName;
    }

    public String getStationID ()
    {
        return stationID;
    }

    public void setStationID (String stationID)
    {
        this.stationID = stationID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [x = "+x+", index = "+index+", y = "+y+", stationName = "+stationName+", stationID = "+stationID+"]";
    }
}