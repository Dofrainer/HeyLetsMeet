package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class client {

		 public static void main(String[] args) {
		        try {
		            String serverIP = "127.0.0.1"; // 127.0.0.1 & localhost 본인
		            System.out.println("서버에 연결중입니다. 서버 IP : " + serverIP);
		             
		            // 소켓을 생성하여 연결을 요청한다.
		            Socket socket = new Socket(serverIP, 5000);
		          
		            //
		            
		           
		            
		            ///
		         // 소켓의 출력스트림을 얻는다.
	                OutputStream out = socket.getOutputStream(); 
	                DataOutputStream dos = new DataOutputStream(out); // 기본형 단위로 처리하는 보조스트림
	                 
	                // 원격 소켓(remote socket)에 데이터를 보낸다.
	                dos.writeUTF("asb");
	                System.out.println(getTime() + " 데이터를 전송했습니다.");
		             
		            // 스트림과 소켓을 닫는다.
		            dos.close();
		            socket.close();
		        } catch (ConnectException ce) {
		            ce.printStackTrace();
		        } catch (IOException ie) {
		            ie.printStackTrace();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } // try - catch
		    } // main
		 
		  static String getTime() {
		        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		        return f.format(new Date());
		  }
	} // TcpClientTest

		 
		