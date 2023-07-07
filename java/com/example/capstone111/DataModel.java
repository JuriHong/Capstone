package com.example.capstone111;

public class DataModel {
    String name;
    String count;
    String Key;

    public DataModel(String name, String count) {
        this.name = name;
        this.count = count;
    }

    public DataModel(String name, String count, String Key) {
        this.name = name;
        this.count = count;
        this.Key = Key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
