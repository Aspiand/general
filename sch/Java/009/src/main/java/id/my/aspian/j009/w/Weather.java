package id.my.aspian.j009.w;

// Response

public class Weather {

    CurrentWeather current_weather;
    double latitude, longitude;
    String timezone, location;

    public String getLocation() {
        return this.location;
    }

    public String getTime() {
        return this.current_weather.time;

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
    
    public String getWindSpeed() {
        return String.valueOf(this.current_weather.windspeed);
    }

    public Weather setLocation(String location) {
        this.location = location;
        return this;
    }

    static class CurrentWeather {
        double temperature;
        double windspeed;
        String time;
    }
}
