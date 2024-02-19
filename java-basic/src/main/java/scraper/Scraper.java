package scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {

  public static void main(String[] args) throws IOException {
    String url = "https://ncov.kdca.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";
    try {
      // 웹 페이지에서 HTML 문서를 가져온다.
      Document doc = Jsoup.connect(url).get();
      // 일자 정보를 가져옵니다.
      String date = doc.select("div.timetable > p").first().text();

      // 원하는 데이터를 포함하고 있는 테이블을 찾는다.
      Element table = doc.select("table.num").first();
      Elements rows = table.select("tbody > tr");

      List<CovidStatus> covidStatusList = new ArrayList<>();

      for (Element row : rows) {
        // 각 셀의 값을 추출한다.
        String region = row.select("th").text();
        int total = Integer.parseInt(row.select("td:nth-child(2)").text().replaceAll(",",
            ""));
        int domestic = Integer.parseInt(row.select("td:nth-child(3)").text().replaceAll(",",
            ""));
        int abroad = Integer.parseInt(row.select("td:nth-child(4)").text().replaceAll(",",
            ""));
        int confirmed = Integer.parseInt(row.select("td:nth-child(5)").text().replaceAll(",",
            ""));
        int deaths = Integer.parseInt(row.select("td:nth-child(6)").text().replaceAll(",",
            ""));
        String rateStr = row.select("td:nth-child(7)").text().replaceAll(",",
            "");
        double rate = rateStr.equals("-") ? 0.0 : Double.parseDouble(rateStr);
        covidStatusList.add(
            new CovidStatus(region, total, domestic, abroad, confirmed, deaths, rate));
      }
      System.out.println("일일 바이러스 감영 현황 (" + date + ")");
      System.out.println("시도 | 합계 | 국내발생 | 해외유입 | 확진환자 | 사망자 | 발생률");

      for (CovidStatus covidStatus : covidStatusList) {
        System.out.println(covidStatus);
      }
      String excelFIleName = "covid_status_" + date.replace(" ", "_" ).replace(":", "_") + ".xlsx";
      ExcelExporter.exportToExcel(date, covidStatusList, excelFIleName);
      System.out.println("파일 저장완료: " + excelFIleName);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
