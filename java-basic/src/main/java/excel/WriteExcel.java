package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Member {

  private String name;
  private int age;
  private String birthdate;
  private String phone;
  private String address;
  private boolean isMarried;

  public Member() {
  }

  public Member(String name, int age, String birthdate, String phone, String address,
      boolean isMarried) {
    this.name = name;
    this.age = age;
    this.birthdate = birthdate;
    this.phone = phone;
    this.address = address;
    this.isMarried = isMarried;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isMarried() {
    return isMarried;
  }

  public void setMarried(boolean married) {
    isMarried = married;
  }

  @Override
  public String toString() {
    return "Member{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", birthdate='" + birthdate + '\'' +
        ", phone='" + phone + '\'' +
        ", address='" + address + '\'' +
        ", isMarried=" + isMarried +
        '}';
  }
}

public class WriteExcel {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Member> members = new ArrayList<>();
    while (true) {
      System.out.print("이름을 입력하세요 :");
      String name = scanner.nextLine();
      if (name.equals("quit")) {
        break;
      }
      System.out.print("나이를 입력하세요 :");
      int age = scanner.nextInt();
      scanner.nextLine(); // 개행문자 제거
      System.out.print("생년월일을 입력하세요 :");
      String birthdate = scanner.nextLine();
      System.out.print("전화번호를 입력하세요 :");
      String phone = scanner.nextLine();
      System.out.print("주소를 입력하세요 :");
      String address = scanner.nextLine();
      System.out.print("결혼여부를 입력하세요 (true/false):");
      boolean isMarried = scanner.nextBoolean();
      scanner.nextLine(); // 개행문자 제거
      Member member = new Member(name, age, birthdate, phone, address, isMarried);
      members.add(member);
    }
    scanner.close();
    try {
      XSSFWorkbook workbook = new XSSFWorkbook(); // xlsx 파일 생성
      Sheet sheet = workbook.createSheet("회원 정보");
// 헤더 생성
      Row headerRow = sheet.createRow(0);
      headerRow.createCell(0).setCellValue("이름");
      headerRow.createCell(1).setCellValue("나이");
      headerRow.createCell(2).setCellValue("생년월일");
      headerRow.createCell(3).setCellValue("전화번호");
      headerRow.createCell(4).setCellValue("주소");
      headerRow.createCell(5).setCellValue("결혼여부");
// 데이터 생성
      for (int i = 0; i < members.size(); i++) {
        Member member = members.get(i);
        Row row = sheet.createRow(i + 1);
        row.createCell(0).setCellValue(member.getName());
        row.createCell(1).setCellValue(member.getAge());
        row.createCell(2).setCellValue(member.getBirthdate());
        row.createCell(3).setCellValue(member.getPhone());
        row.createCell(4).setCellValue(member.getAddress());
        Cell marriedCell = row.createCell(5);
        marriedCell.setCellValue(member.isMarried());
      }
// 엑셀 파일 저장
      String filename = "members.xlsx";
      FileOutputStream outputStream = new FileOutputStream(new File(filename));
      workbook.write(outputStream);
      workbook.close();
      System.out.println("엑셀 파일이 저장되었습니다 : " + filename);
    } catch (IOException e) {
      System.out.println("엑셀 파일 저장 중 오류가 발생했습니다 .");
      e.printStackTrace();

    }
  }
}
