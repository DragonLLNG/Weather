package edu.uncc.weather;

import java.util.ArrayList;

public class Forecast {
    Main main;
    ArrayList<Weathers> weather;
    String dt_txt;

    public Forecast(){

    }
    public Forecast(Main main, ArrayList<Weathers> weather, String dt_txt){
        this.main = main;
        this.weather = weather;
        this.dt_txt = dt_txt;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWeather(ArrayList<Weathers> weather) {
        this.weather = weather;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public Main getMain() {
        return main;
    }

    public ArrayList<Weathers> getWeather() {
        return weather;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "main=" + main +
                ", weather=" + weather +
                ", dt_txt='" + dt_txt + '\'' +
                '}';
    }
}
