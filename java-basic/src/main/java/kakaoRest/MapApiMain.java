package kakaoRest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.hc.core5.http.ParseException;

public class MapApiMain {

  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("주소를 입력하세요:");
      String address = reader.readLine();

      double[] coordinates = MapApi.getAddressCoordinate(address);

      if (coordinates != null){
        System.out.println("주소: " + address);
        System.out.println("위도: " + coordinates[0]);
        System.out.println("경도: " + coordinates[1]);
      }else {
        System.out.println("주소를 찾을 수 없다.");
      }
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
  }

}
