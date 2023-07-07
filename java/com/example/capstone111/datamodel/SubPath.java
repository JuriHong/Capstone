package com.example.capstone111.datamodel;

public class SubPath
{
    private String endStationCityCode;

    private String distance;

    private String startLocalStationID;

    private String startStationProviderCode;

    private String trafficType;

    private String startArsID;

    private String endArsID;

    private String sectionTime;

    private String endY;

    private String endX;

    private String startStationCityCode;

    private String endID;

    private String stationCount;

    private String endName;

    private String startID;

    private String endStationProviderCode;

    private String endLocalStationID;

    private PassStopList passStopList;

    private String startY;

    private String startX;

    private Lane[] lane;

    private String startName;

    public String getEndStationCityCode ()
    {
        return endStationCityCode;
    }

    public void setEndStationCityCode (String endStationCityCode)
    {
        this.endStationCityCode = endStationCityCode;
    }

    public String getDistance ()
    {
        return distance;
    }

    public void setDistance (String distance)
    {
        this.distance = distance;
    }

    public String getStartLocalStationID ()
    {
        return startLocalStationID;
    }

    public void setStartLocalStationID (String startLocalStationID)
    {
        this.startLocalStationID = startLocalStationID;
    }

    public String getStartStationProviderCode ()
    {
        return startStationProviderCode;
    }

    public void setStartStationProviderCode (String startStationProviderCode)
    {
        this.startStationProviderCode = startStationProviderCode;
    }

    public String getTrafficType ()
    {
        return trafficType;
    }

    public void setTrafficType (String trafficType)
    {
        this.trafficType = trafficType;
    }

    public String getStartArsID ()
    {
        return startArsID;
    }

    public void setStartArsID (String startArsID)
    {
        this.startArsID = startArsID;
    }

    public String getEndArsID ()
    {
        return endArsID;
    }

    public void setEndArsID (String endArsID)
    {
        this.endArsID = endArsID;
    }

    public String getSectionTime ()
    {
        return sectionTime;
    }

    public void setSectionTime (String sectionTime)
    {
        this.sectionTime = sectionTime;
    }

    public String getEndY ()
    {
        return endY;
    }

    public void setEndY (String endY)
    {
        this.endY = endY;
    }

    public String getEndX ()
    {
        return endX;
    }

    public void setEndX (String endX)
    {
        this.endX = endX;
    }

    public String getStartStationCityCode ()
    {
        return startStationCityCode;
    }

    public void setStartStationCityCode (String startStationCityCode)
    {
        this.startStationCityCode = startStationCityCode;
    }

    public String getEndID ()
    {
        return endID;
    }

    public void setEndID (String endID)
    {
        this.endID = endID;
    }

    public String getStationCount ()
    {
        return stationCount;
    }

    public void setStationCount (String stationCount)
    {
        this.stationCount = stationCount;
    }

    public String getEndName ()
    {
        return endName;
    }

    public void setEndName (String endName)
    {
        this.endName = endName;
    }

    public String getStartID ()
    {
        return startID;
    }

    public void setStartID (String startID)
    {
        this.startID = startID;
    }

    public String getEndStationProviderCode ()
    {
        return endStationProviderCode;
    }

    public void setEndStationProviderCode (String endStationProviderCode)
    {
        this.endStationProviderCode = endStationProviderCode;
    }

    public String getEndLocalStationID ()
    {
        return endLocalStationID;
    }

    public void setEndLocalStationID (String endLocalStationID)
    {
        this.endLocalStationID = endLocalStationID;
    }

    public PassStopList getPassStopList ()
    {
        return passStopList;
    }

    public void setPassStopList (PassStopList passStopList)
    {
        this.passStopList = passStopList;
    }

    public String getStartY ()
    {
        return startY;
    }

    public void setStartY (String startY)
    {
        this.startY = startY;
    }

    public String getStartX ()
    {
        return startX;
    }

    public void setStartX (String startX)
    {
        this.startX = startX;
    }

    public Lane[] getLane ()
    {
        return lane;
    }

    public void setLane (Lane[] lane)
    {
        this.lane = lane;
    }

    public String getStartName ()
    {
        return startName;
    }

    public void setStartName (String startName)
    {
        this.startName = startName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [endStationCityCode = "+endStationCityCode+", distance = "+distance+", startLocalStationID = "+startLocalStationID+", startStationProviderCode = "+startStationProviderCode+", trafficType = "+trafficType+", startArsID = "+startArsID+", endArsID = "+endArsID+", sectionTime = "+sectionTime+", endY = "+endY+", endX = "+endX+", startStationCityCode = "+startStationCityCode+", endID = "+endID+", stationCount = "+stationCount+", endName = "+endName+", startID = "+startID+", endStationProviderCode = "+endStationProviderCode+", endLocalStationID = "+endLocalStationID+", passStopList = "+passStopList+", startY = "+startY+", startX = "+startX+", lane = "+lane+", startName = "+startName+"]";
    }
}