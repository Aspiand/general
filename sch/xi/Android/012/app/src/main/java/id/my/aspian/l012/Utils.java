package id.my.aspian.l012;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static ArrayList<Video> getAllVideo(Context context) {
        ArrayList<Video> tmp = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String sort = MediaStore.Video.Media.TITLE + " ASC";
        String[] projection = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DURATION,
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, sort);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                tmp.add(
                        new Video(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5)
                        )
                );
            } while (cursor.moveToNext());

            cursor.close();
        }

        return tmp;
    }

    public static ArrayList<Directory> getAllVideoDirectory(ArrayList<Video> videos) {
        ArrayList<Directory> data = new ArrayList<>();
        Map<String, List<Video>> groupedVideos = videos.stream()
                .collect(Collectors.groupingBy(
                                video -> new File(video.getPath()).getParent()
                        )
                );

        groupedVideos.forEach((path, listVideo) -> {
            String name = new File(path).getName();
            int count = listVideo.size();
            int size = listVideo.stream().mapToInt(Video::getSize).sum();
            data.add(new Directory(name, path, count, size, listVideo));
        });

        return data;
    }
}

//        Set<String> set = new HashSet<>();
//        for (Video video : videos) {
//            String path = video.getPath();
//
//            set.add(
//                    new File(path).getParent()
//            );
//        }
//
//        return set;
//        HashMap<String, HashMap<String, Integer>> data = new HashMap<>();
//        videos.forEach(video -> {
//            HashMap<String, Integer> value = new HashMap<>();
//            value.put("size");
//            data.put(video.getPath(), value);
//        });