package id.my.aspian.j009.w;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Client {
    String url = "https://api.open-meteo.com/v1/forecast?latitude=%.4f&longitude=%.4f&current_weather=true";
    HttpClient client;
    Gson gson;

    public Client() {
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public Weather fetch(String location, double latitude, double longitude) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, latitude, longitude)))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), Weather.class).setLocation(location);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Weather> fetchAll(ArrayList<Location> src) {
        ArrayList<Weather> weathers = new ArrayList<>();

        src.forEach((t) -> {
            weathers.add(this.fetch(
                    t.location,
                    t.latitude,
                    t.longitude
            ));
        });

        return weathers;
    }
}
