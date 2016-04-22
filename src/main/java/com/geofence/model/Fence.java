package com.geofence.model;

import org.json.simple.JSONObject;

import java.math.BigDecimal;

/**
 * Created by wmonks on 4/22/2016.
 */
public class Fence {
    String name, type;
    BigDecimal north, south, east, west;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getNorth() {
        return north;
    }

    public void setNorth(BigDecimal north) {
        this.north = north;
    }

    public BigDecimal getSouth() {
        return south;
    }

    public void setSouth(BigDecimal south) {
        this.south = south;
    }

    public BigDecimal getEast() {
        return east;
    }

    public void setEast(BigDecimal east) {
        this.east = east;
    }

    public BigDecimal getWest() {
        return west;
    }

    public void setWest(BigDecimal west) {
        this.west = west;
    }

    public JSONObject getJSON() {
        JSONObject obj = new JSONObject();

        obj.put("name", getName());
        obj.put("type", getType());

        return obj;
    }
}
