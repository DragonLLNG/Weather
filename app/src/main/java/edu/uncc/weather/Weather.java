package edu.uncc.weather;

public class Weather {
    private double temperature, max_temp, min_temp, wind_speed, wind_degree;
    private int humidity, cloudiness;
    private String description;

    public Weather(){

    }

    public Weather(double temperature, double max_temp, double min_temp,
                   String description, int humidity, double wind_speed,
                   double wind_degree, int cloudiness){
        this.temperature = temperature;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
        this.description = description;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.wind_degree = wind_degree;
        this.cloudiness = cloudiness;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public double getMin_temp() {
        return min_temp;
    }

    public String getDescription() {
        return description;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_degree() {
        return wind_degree;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setWind_degree(double wind_degree) {
        this.wind_degree = wind_degree;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }
}
