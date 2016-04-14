

package project;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class dbserver1 {
	public static void menu() {
		  System.out.println("1. 데이터 삽입");
		  System.out.println("2. 데이터 검색");
		  System.out.println("3. 끝내기");
		  System.out.print(">> ");
	}

	public static void main(String[] args) {
		  // MySQL 접속 경로, 기본 포트는 3306, DB명 
		  String jdbcUrl = "jdbc:mysql://localhost:3306/project1";
		  // MySQL 계정
		  String dbId = "root";
		  // MySQL 계정 비밀번호
		  String dbPw = "1234";
		  
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  String sql = "";
		  int num = 0;
		  int select = 0;
		  
		  Scanner scanner = new Scanner(System.in);
		  
		  try {
		   Class.forName("com.mysql.jdbc.Driver");
		   // 디비 연결
		   conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
		   
		   while(true) {
		    menu();
		    
		    select = scanner.nextInt();
		    
		    if(select == 1) {
		     // 테이블에 들어간 num 값 중 최대값 검색
		     sql = "select max(num) from person";
		     pstmt = conn.prepareStatement(sql);
		     rs = pstmt.executeQuery();
		     
		     if(rs.next()) {
		      // 값이 있으면 해당 값에 +1
		      num = rs.getInt(1) + 1;
		     } else {
		      // 값이 없으면 비어있는 것으로 1
		      num = 1;
		     }
		     scanner.nextLine();
		     
		     System.out.print("아이디 입력 : ");
		     String id = scanner.nextLine();
		     System.out.print("이름 입력 : ");
		     String name = scanner.nextLine();
		     System.out.print("비밀번호 입력 : ");
		     String pw = scanner.nextLine();
		     System.out.print("나이 입력 : ");
		     int age = scanner.nextInt();
		     scanner.nextLine();
		     System.out.print("전공 입력 : ");
		     String major = scanner.nextLine().trim();
		     
		     // insert 는 데이터 삽입 DML 구문
		     // insert into 테이블명(컬럼 나열) values(삽입할 값)
		     // 해당 테이블의 모든 컬럼을 다 넣을 경우 컬럼 나열은 하지
		     // 않아도 됨.
		     sql = "insert into person values(?,?,?,?,?,?)";
		     pstmt = conn.prepareStatement(sql);
		     pstmt.setInt(1, num);
		     pstmt.setString(2, id);
		     pstmt.setString(3, name);
		     pstmt.setString(4, pw);
		     pstmt.setInt(5, age);
		     pstmt.setString(6, major);
		     pstmt.executeUpdate();
		     System.out.println();
		    } else if(select == 2) {
		     // select문은 DML 구문으로 검색 질의
		     // *은 asterisk로 모든 컬럼 검색
		     // from 절 뒤에는 테이블명
		     // order by절은 정렬로 asc나 없으면 오름차순 정렬
		     // desc 내림차순 정렬
		     sql = "select * from person order by num desc";
		     pstmt = conn.prepareStatement(sql);
		     rs = pstmt.executeQuery();
		     
		     System.out.println("번호\t아이디\t이름\t비밀번호\t나이\t전공");
		     while(rs.next()) {
		      System.out.println(rs.getInt("num")+"\t"+rs.getString("id")+"\t"+rs.getString("name")+"\t"+rs.getString("pw")+"\t"+rs.getInt("age")+"\t"+rs.getString("major"));
		     }
		     System.out.println();
		    } else if(select == 3) {
		     System.out.println("프로그램 종료...");
		     break;
		    }
		   }
		   
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally{
		   if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		   if(conn!=null) try{conn.close();}catch(SQLException ex){}
		   if(rs!=null) try{conn.close();}catch(SQLException ex){}
		  }
		 }
		}


