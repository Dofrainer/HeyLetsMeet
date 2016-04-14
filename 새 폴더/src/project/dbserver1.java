

package project;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class dbserver1 {
	public static void menu() {
		  System.out.println("1. ������ ����");
		  System.out.println("2. ������ �˻�");
		  System.out.println("3. ������");
		  System.out.print(">> ");
	}

	public static void main(String[] args) {
		  // MySQL ���� ���, �⺻ ��Ʈ�� 3306, DB�� 
		  String jdbcUrl = "jdbc:mysql://localhost:3306/project1";
		  // MySQL ����
		  String dbId = "root";
		  // MySQL ���� ��й�ȣ
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
		   // ��� ����
		   conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
		   
		   while(true) {
		    menu();
		    
		    select = scanner.nextInt();
		    
		    if(select == 1) {
		     // ���̺� �� num �� �� �ִ밪 �˻�
		     sql = "select max(num) from person";
		     pstmt = conn.prepareStatement(sql);
		     rs = pstmt.executeQuery();
		     
		     if(rs.next()) {
		      // ���� ������ �ش� ���� +1
		      num = rs.getInt(1) + 1;
		     } else {
		      // ���� ������ ����ִ� ������ 1
		      num = 1;
		     }
		     scanner.nextLine();
		     
		     System.out.print("���̵� �Է� : ");
		     String id = scanner.nextLine();
		     System.out.print("�̸� �Է� : ");
		     String name = scanner.nextLine();
		     System.out.print("��й�ȣ �Է� : ");
		     String pw = scanner.nextLine();
		     System.out.print("���� �Է� : ");
		     int age = scanner.nextInt();
		     scanner.nextLine();
		     System.out.print("���� �Է� : ");
		     String major = scanner.nextLine().trim();
		     
		     // insert �� ������ ���� DML ����
		     // insert into ���̺��(�÷� ����) values(������ ��)
		     // �ش� ���̺��� ��� �÷��� �� ���� ��� �÷� ������ ����
		     // �ʾƵ� ��.
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
		     // select���� DML �������� �˻� ����
		     // *�� asterisk�� ��� �÷� �˻�
		     // from �� �ڿ��� ���̺��
		     // order by���� ���ķ� asc�� ������ �������� ����
		     // desc �������� ����
		     sql = "select * from person order by num desc";
		     pstmt = conn.prepareStatement(sql);
		     rs = pstmt.executeQuery();
		     
		     System.out.println("��ȣ\t���̵�\t�̸�\t��й�ȣ\t����\t����");
		     while(rs.next()) {
		      System.out.println(rs.getInt("num")+"\t"+rs.getString("id")+"\t"+rs.getString("name")+"\t"+rs.getString("pw")+"\t"+rs.getInt("age")+"\t"+rs.getString("major"));
		     }
		     System.out.println();
		    } else if(select == 3) {
		     System.out.println("���α׷� ����...");
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


