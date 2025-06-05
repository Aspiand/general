package id.my.aspian.j009.w;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Location {

    public String location;
    public double latitude, longitude;

    public static ArrayList<Location> locations = new ArrayList<>();

    public Location(String location, double latitude, double longitude) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Location newInstance(String location, String latitude, String longitude) {
        return new Location(location, Double.valueOf(latitude), Double.valueOf(longitude));
    }

    public static void write(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(locations, writer);
            System.out.println("Data saved to: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String path) {
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Location>>() {
            }.getType();

            locations = gson.fromJson(reader, listType);
            System.out.println("Data loaded from: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        write("./db.json");
    }
    
    public static void load() {
        read("./db.json");
    }

    @Override
    public String toString() {
        return String.format("%s (%.4f, %.4f)", location, latitude, longitude);
    }
}
