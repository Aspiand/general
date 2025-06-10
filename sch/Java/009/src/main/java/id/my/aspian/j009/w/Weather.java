package id.my.aspian.j009.w;

// Response

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Weather {

    CurrentWeather current_weather;
    double latitude, longitude;
    String timezone, location;

    public String getLocation() {
        return this.location;
    }

    public String getTime() {
        return parseTime(this.current_weather.time);

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

    public String getWindDirection() {
        return String.valueOf(this.current_weather.winddirection);
    }

    public Weather setLocation(String location) {
        this.location = location;
        return this;
    }

    static class CurrentWeather {

        double temperature, windspeed, winddirection;
        String time;
    }

    private String parseTime(String time) {
        LocalDateTime utcTime = LocalDateTime.parse(time);

        ZonedDateTime wita = utcTime.atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("Asia/Makassar"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd MMMM yyyy HH:mm", new Locale("id", "ID")
        );

        return wita.format(formatter);
    }
}
