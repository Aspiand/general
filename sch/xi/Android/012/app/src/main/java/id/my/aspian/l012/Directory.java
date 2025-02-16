package id.my.aspian.l012;

import java.util.List;

public class Directory {
    private final String name, path;
    private final int count, size;
    private final List<Video> videoOnDirectory;

    public Directory(String name, String path, int count, int size, List<Video> videoOnDirectory) {
        this.name = name;
        this.path = path;
        this.count = count;
        this.size = size;
        this.videoOnDirectory = videoOnDirectory;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getCount() {
        return String.valueOf(count);
    }

    public String getSize() {
        return Utils.readableFileSize(size);
    }

    public List<Video> getVideoOnDirectory() {
        return videoOnDirectory;
    }
}
