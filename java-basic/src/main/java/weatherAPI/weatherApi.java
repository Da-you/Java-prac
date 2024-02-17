package weatherAPI;






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
      System.out.println("temp = " + content);
      System.out.println("================================");
      con.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

}
