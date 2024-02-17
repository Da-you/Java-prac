package excel;

import static org.apache.poi.ss.usermodel.WorkbookFactory.create;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadExcel {

  public static void main(String[] args) {
    try {
      FileInputStream file =  new FileInputStream(new File("example.xlsx"));
      //메모리에 가상의 엑셀파일 올린다 (workBook)
      Workbook workbook  = create(file); // inputStream으로 만든 파일을 workbook으로 만든다
      Sheet sheet = workbook.getSheetAt(0); // 0번 시트부터
      for (Row cells : sheet) {
        for (Cell cell : cells) {
//          case NUMERIC :
//            if (DateUtil.isCellDateFormatted(cell)){
//              Date dateValue = cell.getDateCellValue();
//              DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//              String formatDate = dateFormat.format(dateValue);
//              System.out.println(formatDate+"\t");
//        }

          System.out.print(cell.toString()+"\t");
        }
        System.out.println(); // 줄바꿈
      }
      file.close();
      System.out.println("엑셀에서 데이터 읽기 성공 ");
    }catch (IOException e){
      e.printStackTrace();
    }
  }

}
