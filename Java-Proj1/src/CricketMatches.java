import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class CricketMatches {
    public static void main(String[] args) {
        try {
            // Setup the API URL
            String apiUrl = "https://api.cuvora.com/car/partner/cricket-data";
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("apiKey", "test-creds@2320");

            // Read the API response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            
            // Store response in a string
            String jsonResponse = content.toString();
            
            // Print the raw API response for debugging
            System.out.println("API Response: " + jsonResponse);
            
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);
            
            // Check if the "data" key exists
            if (jsonObject.has("data")) {
                JSONArray matches = jsonObject.getJSONArray("data");
                
                // Process each match
                for (int i = 0; i < matches.length(); i++) {
                    JSONObject match = matches.getJSONObject(i);
                    System.out.println("Match ID: " + match.getString("id"));
                    System.out.println("Date: " + match.getString("dateTimeGMT"));
                    System.out.println("Match Type: " + match.getString("matchType"));
                    System.out.println("Status: " + match.getString("status"));
                    System.out.println("Team 1: " + match.getString("t1"));
                    System.out.println("Team 2: " + match.getString("t2"));
                    System.out.println("Score 1: " + match.getString("t1s"));
                    System.out.println("Score 2: " + match.getString("t2s"));
                    System.out.println("Team 1 Image: " + match.getString("t1img"));
                    System.out.println("Team 2 Image: " + match.getString("t2img"));
                    System.out.println();
                }
            } else {
                System.out.println("\"data\" field not found in the API response.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


