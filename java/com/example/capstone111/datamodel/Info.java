package com.example.capstone111.datamodel;

public class Info
{
    private String totalTime;

     String totalWalkTime;

     String subwayTransitCount;

     String busStationCount;

     String trafficDistance;

    private String firstStartStation;

    private String mapObj;

    private String totalStationCount;

    private String totalWalk;

    private String payment;

    private String subwayStationCount;

    private String checkIntervalTimeOverYn;

    private String totalDistance;

    private String busTransitCount;

    private String lastEndStation;

    private String checkIntervalTime;

    public String getTotalTime ()
    {
        return totalTime;
    }

    public void setTotalTime (String totalTime)
    {
        this.totalTime = totalTime;
    }

    public String getTotalWalkTime ()
    {
        return totalWalkTime;
    }

    public void setTotalWalkTime (String totalWalkTime)
    {
        this.totalWalkTime = totalWalkTime;
    }

    public String getSubwayTransitCount ()
    {
        return subwayTransitCount;
    }

    public void setSubwayTransitCount (String subwayTransitCount)
    {
        this.subwayTransitCount = subwayTransitCount;
    }

    public String getBusStationCount ()
    {
        return busStationCount;
    }

    public void setBusStationCount (String busStationCount)
    {
        this.busStationCount = busStationCount;
    }

    public String getTrafficDistance ()
    {
        return trafficDistance;
    }

    public void setTrafficDistance (String trafficDistance)
    {
        this.trafficDistance = trafficDistance;
    }

    public String getFirstStartStation ()
    {
        return firstStartStation;
    }

    public void setFirstStartStation (String firstStartStation)
    {
        this.firstStartStation = firstStartStation;
    }

    public String getMapObj ()
    {
        return mapObj;
    }

    public void setMapObj (String mapObj)
    {
        this.mapObj = mapObj;
    }

    public String getTotalStationCount ()
    {
        return totalStationCount;
    }

    public void setTotalStationCount (String totalStationCount)
    {
        this.totalStationCount = totalStationCount;
    }

    public String getTotalWalk ()
    {
        return totalWalk;
    }

    public void setTotalWalk (String totalWalk)
    {
        this.totalWalk = totalWalk;
    }

    public String getPayment ()
    {
        return payment;
    }

    public void setPayment (String payment)
    {
        this.payment = payment;
    }

    public String getSubwayStationCount ()
    {
        return subwayStationCount;
    }

    public void setSubwayStationCount (String subwayStationCount)
    {
        this.subwayStationCount = subwayStationCount;
    }

    public String getCheckIntervalTimeOverYn ()
    {
        return checkIntervalTimeOverYn;
    }

    public void setCheckIntervalTimeOverYn (String checkIntervalTimeOverYn)
    {
        this.checkIntervalTimeOverYn = checkIntervalTimeOverYn;
    }

    public String getTotalDistance ()
    {
        return totalDistance;
    }

    public void setTotalDistance (String totalDistance)
    {
        this.totalDistance = totalDistance;
    }

    public String getBusTransitCount ()
    {
        return busTransitCount;
    }

    public void setBusTransitCount (String busTransitCount)
    {
        this.busTransitCount = busTransitCount;
    }

    public String getLastEndStation ()
    {
        return lastEndStation;
    }

    public void setLastEndStation (String lastEndStation)
    {
        this.lastEndStation = lastEndStation;
    }

    public String getCheckIntervalTime ()
    {
        return checkIntervalTime;
    }

    public void setCheckIntervalTime (String checkIntervalTime)
    {
        this.checkIntervalTime = checkIntervalTime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [totalTime = "+totalTime+", totalWalkTime = "+totalWalkTime+", subwayTransitCount = "+subwayTransitCount+", busStationCount = "+busStationCount+", trafficDistance = "+trafficDistance+", firstStartStation = "+firstStartStation+", mapObj = "+mapObj+", totalStationCount = "+totalStationCount+", totalWalk = "+totalWalk+", payment = "+payment+", subwayStationCount = "+subwayStationCount+", checkIntervalTimeOverYn = "+checkIntervalTimeOverYn+", totalDistance = "+totalDistance+", busTransitCount = "+busTransitCount+", lastEndStation = "+lastEndStation+", checkIntervalTime = "+checkIntervalTime+"]";
    }
}