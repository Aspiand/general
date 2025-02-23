package id.my.aspian.l012;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static ArrayList<Video> getVideos(Context context, String selection, String[] selectionArgs) {
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

        Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sort);
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

    public static ArrayList<Video> getAllVideo(Context context) {
        return getVideos(context, null, null);
    }

    public static ArrayList<Video> getAllVideoByDirectory(Context context, String directory) {
        String selection = MediaStore.Video.Media.DATA + " LIKE ?";
        String[] selectionArgs = new String[]{directory + "%"};

        return getVideos(context, selection, selectionArgs);
    }

    public static ArrayList<Directory> getAllVideoDirectory(ArrayList<Video> videos) {
        // ./0/a.txt
        // ./0/b.txt
        // ./0/c.txt
        // ./0/d.txt
        // ./0/e.txt
        // ./1/a.txt
        // ./1/b.txt
        // ./1/c.txt
        // ./1/d.txt
        // ./1/e.txt
        // ./2/a.txt
        // ./2/b.txt
        // ./2/c.txt
        // ./2/d.txt
        // ./2/e.txt

        // to

        //.
        //├── 0
        //│   ├── a.txt
        //│   ├── b.txt
        //│   ├── c.txt
        //│   ├── d.txt
        //│   └── e.txt
        //├── 1
        //│   ├── a.txt
        //│   ├── b.txt
        //│   ├── c.txt
        //│   ├── d.txt
        //│   └── e.txt
        //└── 2
        //    ├── a.txt
        //    ├── b.txt
        //    ├── c.txt
        //    ├── d.txt
        //    └── e.txt

        ArrayList<Directory> data = new ArrayList<>();
        Map<String, List<Video>> groupedVideos = videos.stream()
                .collect(Collectors.groupingBy(
                                video -> new File(video.getPath()).getParent()
                        )
                );

        groupedVideos.forEach((path, listVideo) -> {
            String name = new File(path).getName();
            int count = listVideo.size();
            long size = listVideo.stream().mapToLong(Video::getSize).sum();
            data.add(new Directory(name, path, count, size, listVideo));
        });

        data.sort(Comparator.comparing(Directory::getName));
        return data;
    }

//    public static ArrayList<Directory> getAllVideoDirectory(Context context) {
//        return getAllVideoDirectory(getAllVideo(context));
//    }

//    public static ArrayList<Video> getAllVideoFromDirectory(String directoryName) {
//        return new ArrayList<>();
//    }

    // https://stackoverflow.com/a/5599842/29457100
    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB", "PB", "EB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static String readableFileSize(String size) {
        return readableFileSize(Long.parseLong(size));
    }

//    https://stackoverflow.com/a/3758880/29457100
    public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }

        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }
}