package id.my.aspian.j009.w;

import java.net.URI;
import java.net.http.*;
import java.util.*;
import com.google.gson.*;

class WeatherResponse {

    CurrentWeather current_weather;

    static class CurrentWeather {

        double temperature;
        double windspeed;
    }
}

public class App {

    static ArrayList<Location> locations = new ArrayList<>() {
        {
            add(new Location("Jakarta", -6.2, 106.8));
            add(new Location("Surabaya", -7.25, 112.75));
            add(new Location("Bandung", -6.91, 107.61));
        }
    };

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        for (var entry : locations) {
            String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%.4f&longitude=%.4f&current_weather=true", entry.latitude, entry.longitude);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                WeatherResponse weather = gson.fromJson(response.body(), WeatherResponse.class);
                double temp = weather.current_weather.temperature;
                double wind = weather.current_weather.windspeed;

//                        System.out.printf("Cuaca di %s: %.1f°C, angin %.1f km/jam%n", city, temp, wind);;
                System.out.println(response.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            break;
        }

//        Client client = new Client();
//        for (var w : client.fetchAll(locations)) {
//            System.out.println(w.location);
//            System.out.println(w.current_weather.temperature);
//        }
    }
}
