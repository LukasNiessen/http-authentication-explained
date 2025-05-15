import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthClient {
    public static void main(String[] args) {
        try {
            String username = "demo";
            String password = "p@55w0rd";
            String credentials = username + ":" + password;
            String encodedCredentials = Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

            URL url = new URL("https://api.example.com/protected");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Basic " + encodedCredentials);

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == 200) {
                System.out.println("Success! Access granted.");
            } else {
                System.out.println("Failed. Check credentials or endpoint.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}