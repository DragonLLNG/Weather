package edu.uncc.weather;


public class Weathers {
        int id;
        String main, description, icon;
    public Weathers(){

    }
    public Weathers(int id, String main, String description, String icon){
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

    public void setId(int id) {
            this.id = id;
        }

    public void setMain(String main) {
            this.main = main;
        }

    public void setDescription(String description) {
            this.description = description;
        }

    public void setIcon(String icon) {
            this.icon = icon;
        }

    public int getId() {
            return id;
        }

    public String getMain() {
            return main;
        }

    public String getDescription() {
            return description;
        }

    public String getIcon() {
            return icon;
        }
}

