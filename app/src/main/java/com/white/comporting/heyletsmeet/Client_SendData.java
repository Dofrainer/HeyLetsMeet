package com.white.comporting.heyletsmeet;

import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class Client_SendData {

	public static String SendToServer(String Subway, ArrayList<String> arrType,Client typeList) {
        try{
//        	String serverIp = "127.0.0.1";
            String serverIp = "169.254.75.211";
            Toast.makeText(typeList.getApplicationContext(), "서버에 연결중입니다. 서버IP : " + serverIp, Toast.LENGTH_SHORT).show();

              Socket socket = new Socket(serverIp, 7777);
             
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
             
            OutputStream out = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
                            


            dos.writeUTF("가게");
            String URL = SendShop(dos,dis,Subway,arrType);
            
            dis.close();
            dos.close();           
            socket.close();
            return URL;
         }catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";

	}

	private static String SendShop(DataOutputStream dos, DataInputStream dis , String Subway, ArrayList<String> arrType) throws Exception
	{

		dos.writeUTF(Subway);
//		ArrayList<String> arrType = new ArrayList<String>();
//		arrType.add("��ü");
		dos.writeInt(arrType.size());
		
		for(int i=0; i< arrType.size(); i++)
		{
			dos.writeUTF(arrType.get(i));
		}
		
		String Adress = dis.readUTF();
		return Adress;
   
	}

}
