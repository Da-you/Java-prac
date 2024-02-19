package pdf;


import static com.itextpdf.kernel.font.PdfFontFactory.createFont;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Year;
import java.util.HashMap;


public class BookInfoToPDF {

  public static void main(String[] args) throws IOException {

    HashMap<String, String> bookInfo = new HashMap<>();
    bookInfo.put("title", "한글 자바");
    bookInfo.put("author", "JO");
    bookInfo.put("publisher", "출판");
    bookInfo.put("year", String.valueOf(Year.now().getValue()));
    bookInfo.put("price", "25000");
    String name = "한글 자바.pdf";
    try {
      // PDF 생성을 위한 pdfWriter 객채 생성
      PdfWriter writer = new PdfWriter(new FileOutputStream(name));
      // PdfWriter 객체를 사용하여 PdfDocument 객체 생성
      PdfDocument pdf = new PdfDocument(writer);
      // Document 객체 생성
      Document document = new Document(pdf);
      // pdf파일의 폰트를 생성하고 설정
      PdfFont font = createFont("NANUMGOTHICLIGHT.TTF", PdfEncodings.IDENTITY_H,
          true); // 폰트는 따로 다운 받아서 리소스 패키지에 넣어야함
      document.setFont(font);
      // 책정보가 담긴 map을 문단으로 생성 document에 추가
      for (String key : bookInfo.keySet()) {
        Paragraph paragraph = new Paragraph(key + ": " + bookInfo.get(key));
        document.add(paragraph);
      }
      document.close();
      System.out.println("파일 생성 완료");

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}