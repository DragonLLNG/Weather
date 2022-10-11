package edu.uncc.weather;

import java.util.ArrayList;

public class Weather {
    //Coordinate coord;
    ArrayList<Weathers> weather;
    //String base;
    Main main;
    //int visibility;
    Wind wind;
    //Rain rain;
    Cloud clouds;
    //int dt;
    //Sys sys;
    //int timezone, id, name, cod;

    public Weather(){

    }
    public Weather( ArrayList<Weathers> weather,Main main,
                   Wind wind, Cloud clouds){
        //this.coord = coord;
        this.weather = weather;
        //this.base = base;
        this.main = main;
        //this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        //this.dt = dt;
        //this.sys = sys;
        //this.timezone = timezone;
        //this.id = id;
        //this.name = name;
        //this.cod = cod;
    }

    /*
    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

     */

    public void setWeather(ArrayList<Weathers> weather) {
        this.weather = weather;
    }

    /*
    public void setBase(String base) {
        this.base = base;
    }

     */

    public void setMain(Main main) {
        this.main = main;
    }

    /*
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
     */

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setClouds(Cloud clouds) {
        this.clouds = clouds;
    }

/*
    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Coordinate getCoord() {
        return coord;
    }

 */

    public ArrayList<Weathers> getWeather() {
        return weather;
    }

    /*
    public String getBase() {
        return base;
    }

     */

    public Main getMain() {
        return main;
    }


    /*
    public int getVisibility() {
        return visibility;
    }

     */

    public Wind getWind() {
        return wind;
    }

    public Cloud getClouds() {
        return clouds;
    }

    /*
    public int getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    private class Rain {

        double h;
    }

     */

}
