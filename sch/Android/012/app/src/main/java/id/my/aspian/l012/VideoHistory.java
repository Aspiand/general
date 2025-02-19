package id.my.aspian.l012;

public class VideoHistory extends Video {
    String timestamp;

    public VideoHistory(String id, String path, String title, String fileName, String size, String duration, String timestamp) {
        super(id, path, title, fileName, size, duration);
        this.timestamp = timestamp;
    }
}
