package com.example.entity;

/**
 * Created by Administrator on 2016/6/23.
 */

public class IpInfo {
    private String country;
    private String country_id;
    private String ip;
    private String area;
    private String county;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "IpInfo{" +
                "country='" + country + '\'' +
                ", country_id='" + country_id + '\'' +
                ", ip='" + ip + '\'' +
                ", area='" + area + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
