package id.my.aspian.j009.w;

public class Weather {

    CurrentWeather current_weather;
    double latitude, longitude;
    String timezone, location;

    public Weather setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getLatitude() {
        return String.valueOf(this.latitude);
    }

    public String getLongitude() {
        return String.valueOf(this.longitude);
    }
    
    public String getTemperature() {
        return String.valueOf(this.current_weather.temperature);
    }

    static class CurrentWeather {

        double temperature;
        double windspeed;
        String time;
    }
}
