package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class server {
	public static void main(String[] args) {
        ServerSocket serverSocket = null;
         
        try {
            // ���������� �����ϰ� 5000�� ��Ʈ�� ����(bind) ��Ų��.
            serverSocket = new ServerSocket(5000);
            System.out.println(getTime() + " ������ �غ�Ǿ����ϴ�.");
        } catch (IOException e) {
            e.printStackTrace();
        } // try - catch
         
        while (true) {
            try {
                System.out.println(getTime() + " �����û�� ��ٸ��ϴ�.");
                // ���������� Ŭ���̾�Ʈ�� �����û�� �� ������ ������ ���߰� ��� ��ٸ���.
                // Ŭ���̾�Ʈ�� �����û�� ���� Ŭ���̾�Ʈ ���ϰ� ����� ���ο� ������ �����Ѵ�.
                Socket socket = serverSocket.accept();
                System.out.println(getTime() + socket.getInetAddress() + " �κ��� �����û�� ���Խ��ϴ�.");
                 ///
                
                // ������ �Է½�Ʈ���� ��´�.
	            InputStream in = socket.getInputStream();
	            DataInputStream dis = new DataInputStream(in);  // �⺻�� ������ ó���ϴ� ������Ʈ��
	             
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
	  		  
	  		try {
	 		   Class.forName("com.mysql.jdbc.Driver");
	 		   // ��� ����
	 		   conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	 		   
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
	            
			     String id = dis.readUTF();
			     /*String name = dis.readUTF();
			     String pw = dis.readUTF();
			     String age = dis.readUTF();
			     String major = dis.readUTF();
			     */
			     sql = "insert into person values(?,?,?,?,?,?)";
			     pstmt = conn.prepareStatement(sql);
			     pstmt.setInt(1, num);
			     pstmt.setString(2, id);
			     pstmt.setString(3, id);
			     pstmt.setString(4, id);
			     pstmt.setInt(5, 6543);
			     pstmt.setString(6, id);
			     pstmt.executeUpdate();
			     System.out.println();
	  		} catch (Exception e) {
	 		   e.printStackTrace();
	 		  } finally{
	 		   if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
	 		   if(conn!=null) try{conn.close();}catch(SQLException ex){}
	 		   if(rs!=null) try{conn.close();}catch(SQLException ex){}
	 		  }
	  		
	            
	            
	            
	            // �������� ���� ���� �����͸� ����Ѵ�.
	            //System.out.println("�����κ��� ���� �޼��� : " + dis.readUTF());
	            
	            
	            
	            System.out.println("������ �����մϴ�.");
                 
                //
                
                // ��Ʈ���� ������ �޾��ش�.
                dis.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } // try - catch
        } // while
    } // main
     
    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    } // getTime
} // TcpServerTest
 
