package com.example.air_guardiansproject;

public class BoxFavourite{
    private String name;
    private String category_aqi;
    private String co_no2_o3;
    private String pm10_pm25;
    private String trees;
    private String healthRecomandation;

    public BoxFavourite(String name, String category, String aqi, String co, String no2, String o3, String pm10, String pm25) {
        /*AICI SCRIU TEXTUL */
        this.name = name;
        this.category_aqi = "The AQI value is " + aqi + " so there is a " + category + ".";
        this.co_no2_o3 = "CO: " + co + "   NO2: " + no2 + "   O3: " + o3;
        this.pm10_pm25 = "PM10: " + pm10 + " PM2.5: " + pm25;
        if(category.equals("Low Air Quality"))
            this.healthRecomandation = "Reduce intense activities outdoors or postpone them to the early morning when ozone levels tend to be lower.";
        if(category.equals("Medium Air Quality"))
            this.healthRecomandation = "If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. " +
                    "Try to limit the time you spend near busy roads, construction sites, industrial emission stacks, open fires and other sources of smoke.";
        if(category.equals("Good Air Quality"))
            this.healthRecomandation = "It is the best moment for a walk or a bike ride";
        if(category.equals("Excellent Air Quality"))
            this.healthRecomandation = "It is the best moment for a walk or a bike ride";

        if(name.equals("Bucuresti"))
            this.trees = "If you want to make an impovement on air quality you can plant: oak, linden tree or locust.";
        if(name.equals("Vaslui"))
            this.trees = "If you want to make an impovement on air quality you can plant: oak, linden tree or locust.";
        if(name.equals("Iasi"))
            this.trees = "If you want to make an impovement on air quality you can plant: oak, linden tree or locust.";
        if(name.equals("Constanta"))
            this.trees = "If you want to make an impovement on air quality you can plant: oak, linden tree or locust.";
        if(name.equals("Drobeta - Turnu Severin"))
            this.trees = "If you want to make an impovement on air quality you can plant: oak, linden tree or locust.";
        if(name.equals("Cluj"))
            this.trees = "If you want to make an impovement on air quality you can plant: oak, linden tree or locust.";
        if(name.equals("Targu Mures"))
            this.trees = "If you want to make an impovement on air quality you can plant: oak, beech or common oak.";
        if(name.equals("Brasov"))
            this.trees = "If you want to make an impovement on air quality you can plant: oak, fir tree or spruce tree or pine.";
    }

    public String getName() {
        return name;
    }

    public String getCategory_aqi() {
        return category_aqi;
    }

    public String getCo_no2_o3() {
        return co_no2_o3;
    }

    public String getHealthRecomandation() {
        return healthRecomandation;
    }

    public String getPm10_pm25() {
        return pm10_pm25;
    }

    public String getTrees() {
        return trees;
    }
}