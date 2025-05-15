import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CookieAuthClient {
    public static void main(String[] args) {
        try {
            // Step 1: Login to get session cookie
            URL loginUrl = new URL("https://example.com/login");
            HttpURLConnection loginConn = (HttpURLConnection) loginUrl.openConnection();
            loginConn.setRequestMethod("POST");
            loginConn.setDoOutput(true);
            loginConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String postData = "username=demo&password=p@55w0rd";
            try (OutputStream os = loginConn.getOutputStream()) {
                os.write(postData.getBytes(StandardCharsets.UTF_8));
            }

            String cookie = loginConn.getHeaderField("Set-Cookie");
            if (cookie == null) {
                System.out.println("No cookie received. Login failed.");
                return;
            }
            System.out.println("Received cookie: " + cookie);

            // Step 2: Use cookie for protected request
            URL protectedUrl = new URL("https://example.com/protected");
            HttpURLConnection protectedConn = (HttpURLConnection) protectedUrl.openConnection();
            protectedConn.setRequestMethod("GET");
            protectedConn.setRequestProperty("Cookie", cookie);

            int responseCode = protectedConn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == 200) {
                System.out.println("Success! Session cookie worked.");
            } else {
                System.out.println("Failed. Check cookie or endpoint.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}