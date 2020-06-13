package com.example.air_guardiansproject;

public class BoxFavourite{
    private String name;
    private String category;
    private String aqi;
    private String co;
    private String no2;
    private String o3;
    private String pm10;
    private String pm25;

    public BoxFavourite(String name, String category, String aqi, String co, String no2, String o3, String pm10, String pm25) {
        this.name = name;
        this.category = category;
        this.aqi = "Aqi: " + aqi;
        this.co = "CO: " + co;
        this.no2 = "NO2: " + no2;
        this.o3 = "O3: " + o3;
        this.pm10 = "PM10: " + pm10;
        this.pm25 = "PM25: " + pm25;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAqi() {
        return aqi;
    }

    public String getCo() {
        return co;
    }

    public String getNo2() {
        return no2;
    }

    public String getO3() {
        return o3;
    }

    public String getPm10() {
        return pm10;
    }

    public String getPm25() {
        return pm25;
    }
}