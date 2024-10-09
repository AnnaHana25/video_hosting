import java.util.ArrayList;
import java.util.List;

interface IPlayable {
    void play();
    void rate(int rating);
}

abstract class User {
    protected String username = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public abstract void displayInfo();
}

class Viewer extends User {

    @Override
    public void displayInfo() {
        System.out.println("Viewer: " + username);
    }

    public void watchVideo(Video video) {
        video.play();
        System.out.println(username + " is watching the video.");
    }

    public void evaluateVideo(Video video, int rating) {
        video.rate(rating);
        System.out.println(username + " rated the video with " + rating + " stars.");
    }
}

class Producer extends User {

    @Override
    public void displayInfo() {
        System.out.println("Producer: " + username);
    }

    public void uploadVideo(Video video) {
        System.out.println(username + " uploaded a new video: " + video.getTitle());
    }

    public void editVideoMetadata(Video video, String newTitle) {
        video.setTitle(newTitle);
        System.out.println(username + " edited the video title to: " + newTitle);
    }
}

class Video implements IPlayable {
    private String title = "";
    private int rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + title);
    }

    @Override
    public void rate(int rating) {
        this.rating = rating;
        System.out.println("Video rated with " + rating + " stars.");
    }

    public int getRating() {
        return rating;
    }
}

class RecommendationEngine {
    public List<Video> generateRecommendations(Viewer viewer) {
        // Simulated recommendations
        List<Video> recommendations = new ArrayList<>();
        recommendations.add(new Video() {{
            setTitle("Recommended Video 1");
        }});
        recommendations.add(new Video() {{
            setTitle("Recommended Video 2");
        }});
        return recommendations;
    }
}

class ContentManagementSystem {
    public void manageContent() {
        System.out.println("Managing content...");
    }
}

public class Main {
    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.setUsername("Producer1");

        Viewer viewer = new Viewer();
        viewer.setUsername("Viewer1");

        Video video = new Video();
        video.setTitle("My First Video");

        producer.uploadVideo(video);
        viewer.watchVideo(video);
        viewer.evaluateVideo(video, 5);
        producer.editVideoMetadata(video, "My First Video - Edited");

        RecommendationEngine recommendationEngine = new RecommendationEngine();
        List<Video> recommendations = recommendationEngine.generateRecommendations(viewer);
        System.out.println("Recommendations for Viewer:");
        for (Video rec : recommendations) {
            System.out.println(rec.getTitle());
        }

        ContentManagementSystem cms = new ContentManagementSystem();
        cms.manageContent();
    }
}