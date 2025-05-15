import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BearerAuthClient {
    public static void main(String[] args) {
        try {
            String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."; // Replace with your token
            URL url = new URL("https://api.example.com/protected-resource");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == 200) {
                System.out.println("Access granted! Token worked.");
            } else {
                System.out.println("Failed. Check token or endpoint.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}