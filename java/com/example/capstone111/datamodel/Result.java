package com.example.capstone111.datamodel;

public class Result
{
     public Path[] path;

     String busCount;

     String searchType;

     String subwayCount;

    private String subwayBusCount;

    private String pointDistance;

    private String outTrafficCheck;

    private String startRadius;

    private String endRadius;

    public Path[] getPath ()
    {
        return path;
    }

    public void setPath (Path[] path)
    {
        this.path = path;
    }

    public String getBusCount ()
    {
        return busCount;
    }

    public void setBusCount (String busCount)
    {
        this.busCount = busCount;
    }

    public String getSearchType ()
    {
        return searchType;
    }

    public void setSearchType (String searchType)
    {
        this.searchType = searchType;
    }

    public String getSubwayCount ()
    {
        return subwayCount;
    }

    public void setSubwayCount (String subwayCount)
    {
        this.subwayCount = subwayCount;
    }

    public String getSubwayBusCount ()
    {
        return subwayBusCount;
    }

    public void setSubwayBusCount (String subwayBusCount)
    {
        this.subwayBusCount = subwayBusCount;
    }

    public String getPointDistance ()
    {
        return pointDistance;
    }

    public void setPointDistance (String pointDistance)
    {
        this.pointDistance = pointDistance;
    }

    public String getOutTrafficCheck ()
    {
        return outTrafficCheck;
    }

    public void setOutTrafficCheck (String outTrafficCheck)
    {
        this.outTrafficCheck = outTrafficCheck;
    }

    public String getStartRadius ()
    {
        return startRadius;
    }

    public void setStartRadius (String startRadius)
    {
        this.startRadius = startRadius;
    }

    public String getEndRadius ()
    {
        return endRadius;
    }

    public void setEndRadius (String endRadius)
    {
        this.endRadius = endRadius;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [path = "+path+", busCount = "+busCount+", searchType = "+searchType+", subwayCount = "+subwayCount+", subwayBusCount = "+subwayBusCount+", pointDistance = "+pointDistance+", outTrafficCheck = "+outTrafficCheck+", startRadius = "+startRadius+", endRadius = "+endRadius+"]";
    }
}