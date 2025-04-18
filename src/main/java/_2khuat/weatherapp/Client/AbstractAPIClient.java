/* (C) 2025 */
package _2khuat.weatherapp.Client;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class AbstractAPIClient {
    protected final String _apiKeyOpenWeather = "c7c6feca22d0f169d76feacae1e95ecd";

    protected JsonElement makeApiCall(String apiCall) {
        JsonElement jsonElement =
                new JsonElement() {
                    @Override
                    public JsonElement deepCopy() {
                        return null;
                    }
                };
        try {
            URL url = new URL(apiCall);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream inputStream = conn.getInputStream();
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            jsonElement = JsonParser.parseReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonElement;
    }
}
