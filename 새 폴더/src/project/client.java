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
		            String serverIP = "127.0.0.1"; // 127.0.0.1 & localhost ����
		            System.out.println("������ �������Դϴ�. ���� IP : " + serverIP);
		             
		            // ������ �����Ͽ� ������ ��û�Ѵ�.
		            Socket socket = new Socket(serverIP, 5000);
		          
		            //
		            
		           
		            
		            ///
		         // ������ ��½�Ʈ���� ��´�.
	                OutputStream out = socket.getOutputStream(); 
	                DataOutputStream dos = new DataOutputStream(out); // �⺻�� ������ ó���ϴ� ������Ʈ��
	                 
	                // ���� ����(remote socket)�� �����͸� ������.
	                dos.writeUTF("asb");
	                System.out.println(getTime() + " �����͸� �����߽��ϴ�.");
		             
		            // ��Ʈ���� ������ �ݴ´�.
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

		 
		