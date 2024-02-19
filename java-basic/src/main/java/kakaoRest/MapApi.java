package kakaoRest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class MapApi {

  private static final String key = "fd0cef618b7563588af6c3fe7ea898b4";
  private static final String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
  private static final Gson gson = new Gson();


  public static double[] getAddressCoordinate(String address) throws IOException, ParseException {
    String encodeAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
    String requestUrl = apiUrl + "?query=" + encodeAddress;
    System.out.println(requestUrl);

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(requestUrl);
    httpGet.setHeader("Authorization", "KakaoAK " + key);

    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
      String responseBody = EntityUtils.toString(response.getEntity());
      JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
      JsonArray documents = jsonObject.getAsJsonArray("documents");

      if (documents.size() > 0) {
        JsonObject document = documents.get(0).getAsJsonObject();
        double lat = document.get("y").getAsDouble();
        double lon = document.get("x").getAsDouble();
        return new double[]{lat, lon};
      } else {
        return null;
      }
    }

  }


}
