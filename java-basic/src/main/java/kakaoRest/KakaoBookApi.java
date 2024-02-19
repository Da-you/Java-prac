package kakaoRest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KakaoBookApi {

  private static final String apiKey = "fd0cef618b7563588af6c3fe7ea898b4";
  private static final String apuUrl = "https://dapi.kakao.com/v3/search/book";
  private static final OkHttpClient client = new OkHttpClient();
  private static final Gson gson = new Gson();

  // 책 검색 메서드
  public static List<Book> searchBooks(String title) throws IOException {
    HttpUrl.Builder urlBuilder = HttpUrl.parse(apuUrl).newBuilder();
    urlBuilder.addQueryParameter("query", title);

    Request request = new Request.Builder()
        .url(urlBuilder.build())
        .addHeader("Authorization", "KakaoAK " + apiKey)
        .build();

    try (Response response = client.newCall(request).execute()) {

      if (!response.isSuccessful()) {
        throw new IOException("Request fail:" + response);
      }

      JsonObject jsonResponse = gson.fromJson(response.body().charStream(), JsonObject.class);
      JsonArray documents = jsonResponse.getAsJsonArray("documents");
      List<Book> books = new ArrayList<>();

      for (JsonElement document : documents) {
        JsonObject bookJson = document.getAsJsonObject();
        Book book = new Book(
            bookJson.get("title").getAsString(),
            bookJson.get("authors").getAsJsonArray().toString(),
            bookJson.get("publisher").getAsString(),
            bookJson.get("thumbnail").getAsString()
        );
        books.add(book);
      }
      return books;
    }
  }
}
