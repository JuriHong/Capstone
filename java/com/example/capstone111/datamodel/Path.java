package com.example.capstone111.datamodel;

public class Path
{
     SubPath[] subPath;

     String pathType;

     Info info;

    public SubPath[] getSubPath ()
    {
        return subPath;
    }

    public void setSubPath (SubPath[] subPath)
    {
        this.subPath = subPath;
    }

    public String getPathType ()
    {
        return pathType;
    }

    public void setPathType (String pathType)
    {
        this.pathType = pathType;
    }

    public Info getInfo ()
    {
        return info;
    }

    public void setInfo (Info info)
    {
        this.info = info;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [subPath = "+subPath+", pathType = "+pathType+", info = "+info+"]";
    }
}