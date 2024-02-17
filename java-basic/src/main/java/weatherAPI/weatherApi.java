package weatherAPI;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class weatherApi {

  public static void main(String[] args) {
    String apiKey = "apiKey";
    String city = "Seoul";
    String urlString =
        "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey
            + "&units=metric";

    try {
      URL url = new URL(urlString);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Accept", "application/json"); // accept 에서 json 타입으로 데이터를 받는다.
      /**
       * con.getInputStream(): URLConnection에서 입력 스트림을 가져옵니다.
       * new InputStreamReader(con.getInputStream()): InputStreamReader를 사용하여 입력 스트림을 읽습니다.
       * new BufferedReader(new InputStreamReader(con.getInputStream())): InputStreamReader를
       * BufferedReader로 래핑하여 라인 단위로 읽기가 가능한 BufferedReader를 생성합니다.
       */

      BufferedReader br = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = br.readLine()) != null) {
        content.append(inputLine);
      }
      br.close();
      System.out.println("================================");
      System.out.println("content = " + content.toString());
      System.out.println("================================");

      /**
       *  content.toString()을 통해 API 응답을 문자열로 변환합니다. 이 문자열은 JSON 형식의 데이터를 포함하고 있습니다.
       *  JsonParser.parseString(content.toString())을 사용하여 Gson의 JsonParser를 통해 문자열을 JsonObject로 파싱합니다.
       *  이렇게 하면 API 응답을 자바에서 다루기 쉬운 JSON 객체로 변환됩니다.
       *  weatherData.getAsJsonObject("main")을 사용하여 weatherData 객체에서 "main"이라는 키를 갖는 JSON 객체를 추출합니다.
       *  이것은 일반적으로 OpenWeatherMap API 응답에서는 주요 정보를 담고 있는 객체입니다.
       *  mainData.get("temp").getAsDouble()을 사용하여 mainData 객체에서 "temp"라는 키를 갖는 값을 추출합니다.
       *  이것은 해당 도시의 온도를 나타내는데, 이 값을 getAsDouble() 메서드를 통해 double 타입으로 변환하여 가져옵니다
       *  .getAsJsonObject()는  Gson 라이브러리의 메서드로, JSON 객체에서 특정 키에 해당하는 값을 추출하기 위해 사용됩니다.
       */

      JsonObject weatherData = JsonParser.parseString(content.toString()).getAsJsonObject();
      JsonObject mainData = weatherData.getAsJsonObject("main");
      double temp = mainData.get("temp").getAsDouble();

      System.out.println("================================");
      System.out.println("temp = " + temp);
      System.out.println("================================");
      con.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

}
