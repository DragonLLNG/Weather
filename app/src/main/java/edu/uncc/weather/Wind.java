package edu.uncc.weather;

public class Wind {

        private double speed, deg, gust;

        public Wind(){

        }
        public Wind(double speed, double deg, double gust){
            this.speed = speed;
            this. deg = deg;
            this.gust = gust;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public void setDeg(double deg) {
            this.deg = deg;
        }

        public void setGust(double gust) {
            this.gust = gust;
        }

        public double getSpeed() {
            return speed;
        }

        public double getDeg() {
            return deg;
        }

        public double getGust() {
            return gust;
        }

}
