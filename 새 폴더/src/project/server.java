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
            // 서버소켓을 생성하고 5000번 포트와 결합(bind) 시킨다.
            serverSocket = new ServerSocket(5000);
            System.out.println(getTime() + " 서버가 준비되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        } // try - catch
         
        while (true) {
            try {
                System.out.println(getTime() + " 연결요청을 기다립니다.");
                // 서버소켓은 클라이언트의 연결요청이 올 때까지 실행을 멈추고 계속 기다린다.
                // 클라이언트의 연결요청이 오면 클라이언트 소켓과 통신할 새로운 소켓을 생성한다.
                Socket socket = serverSocket.accept();
                System.out.println(getTime() + socket.getInetAddress() + " 로부터 연결요청이 들어왔습니다.");
                 ///
                
                // 소켓의 입력스트림을 얻는다.
	            InputStream in = socket.getInputStream();
	            DataInputStream dis = new DataInputStream(in);  // 기본형 단위로 처리하는 보조스트림
	             
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
	  		  
	  		try {
	 		   Class.forName("com.mysql.jdbc.Driver");
	 		   // 디비 연결
	 		   conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
	 		   
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
	  		
	            
	            
	            
	            // 소켓으로 부터 받은 데이터를 출력한다.
	            //System.out.println("서버로부터 받은 메세지 : " + dis.readUTF());
	            
	            
	            
	            System.out.println("연결을 종료합니다.");
                 
                //
                
                // 스트림과 소켓을 달아준다.
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
 
