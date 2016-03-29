import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by geoff on 25/03/2016.
 */
public class App {

    public static void main(String[] args) {

        JSONObject obj = null;
        URL url = null;
        InputStream in = null;
        try {
            url = new URL("http://stream.meetup.com/2/rsvps");
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "Geoff's Test Java App");
            in = httpcon.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;

            while ((line = reader.readLine()) != null) {
                obj = new JSONObject(line);
                String venueName = "Null";
                String eventName = obj.getJSONObject("event").getString("event_name");
                String memberName = obj.getJSONObject("member").getString("member_name");
                if (obj.has("venue")) {
                    venueName = obj.getJSONObject("venue").getString("venue_name");
                }
                System.out.println("Member " + memberName + " will meet at " + venueName + " for the event " + eventName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

