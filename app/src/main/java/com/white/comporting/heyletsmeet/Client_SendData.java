package com.white.comporting.heyletsmeet;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;


public class Client_SendData extends Thread {


    private  String serverIp = "211.115.230.74";
    private boolean mConnected = false;
    private Handler mHandler = null;
    ArrayList<String> arrType;
    String Select_Subway;
    Client client;

    static class MessageTypeClass {
        public static final int SIMSOCK_CONNECTED = 1;
        public static final int SIMSOCK_DATA = 2;
        public static final int SIMSOCK_DISCONNECTED = 3;
    };
    public enum MessageType { SIMSOCK_CONNECTED, SIMSOCK_DATA, SIMSOCK_DISCONNECTED };

    private void makeMessage(MessageType what, Object obj)
    {
        Message msg = Message.obtain();
        msg.what = what.ordinal();
        msg.obj  = obj;
        mHandler.sendMessage(msg);
    }

	public String SendToServer(String Subway, ArrayList<String> arrType) {
        try{
//        	String serverIp = "127.0.0.1";

             Socket socket = new Socket(serverIp, 7777);
             
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
             
            OutputStream out = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);

            makeMessage(MessageType.SIMSOCK_CONNECTED, "");

            dos.writeUTF("가게");
            SendShop(dos,dis,Subway,arrType);


            makeMessage(MessageType.SIMSOCK_DISCONNECTED, "");
            dis.close();
            dos.close();           
            socket.close();

         }catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";

	}
    public Client_SendData(String addr, Handler handler,String Selct_Subway,ArrayList<String> arrType,Client client)
    {
        serverIp = addr;
        mHandler = handler;
        this.Select_Subway = Selct_Subway;
        this.arrType = arrType;
        this.client = client;
    }
    @Override
    public void run() {
        SendToServer(Select_Subway,arrType);
    }
	private void SendShop(DataOutputStream dos, DataInputStream dis, String Subway, ArrayList<String> arrType) throws Exception
	{

		dos.writeUTF(Subway);
		dos.writeInt(arrType.size());
		
		for(int i=0; i< arrType.size(); i++)
		{
			dos.writeUTF(arrType.get(i));
		}
		
		String Adress = dis.readUTF();
        Intent myIntent;
        myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Adress));
        client.startActivity(myIntent);

   
	}

}

