package com.example.capstone111.datamodel;

public class Lane
{

    private String name;

    private String subwayCode;



    private String subwayCityCode;
    private String busID;

    private String busLocalBlID;

    private String busNo;

    private String busCityCode;

    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubwayCode() {
        return subwayCode;
    }

    public void setSubwayCode(String subwayCode) {
        this.subwayCode = subwayCode;
    }

    public String getSubwayCityCode() {
        return subwayCityCode;
    }

    public void setSubwayCityCode(String subwayCityCode) {
        this.subwayCityCode = subwayCityCode;
    }
    private String busProviderCode;

    public String getBusID ()
    {
        return busID;
    }

    public void setBusID (String busID)
    {
        this.busID = busID;
    }

    public String getBusLocalBlID ()
    {
        return busLocalBlID;
    }

    public void setBusLocalBlID (String busLocalBlID)
    {
        this.busLocalBlID = busLocalBlID;
    }

    public String getBusNo ()
    {
        return busNo;
    }

    public void setBusNo (String busNo)
    {
        this.busNo = busNo;
    }

    public String getBusCityCode ()
    {
        return busCityCode;
    }

    public void setBusCityCode (String busCityCode)
    {
        this.busCityCode = busCityCode;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getBusProviderCode ()
    {
        return busProviderCode;
    }

    public void setBusProviderCode (String busProviderCode)
    {
        this.busProviderCode = busProviderCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [busID = "+busID+", busLocalBlID = "+busLocalBlID+", busNo = "+busNo+", busCityCode = "+busCityCode+", type = "+type+", busProviderCode = "+busProviderCode+"]";
    }
}