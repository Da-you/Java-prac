package kakaoRest;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BookSearchMain {

  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("도서 제목 입력:");
      String title = sc.nextLine();
      List<Book> books = KakaoBookApi.searchBooks(title);

      if (books.isEmpty()) {
        System.out.println("존재하지 않는 도서");
      } else {
        for (Book book : books) {
          System.out.println(book);
        }
      }

    } catch (IOException e) {
      System.out.println("에러 발생:" + e.getMessage());
    }
  }

}
