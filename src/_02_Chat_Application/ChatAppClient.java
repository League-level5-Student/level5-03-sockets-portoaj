package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatAppClient {
	String text;
	ChatApp app;
	Socket sock;
	String IP;
	int port;
	DataInputStream is;
	DataOutputStream os;
	ChatAppClient(String IP, int port)
	{
		this.IP = IP;
		this.port = port;
	}
	public void sendClick(String text)
	{
		try {
			os = new DataOutputStream(sock.getOutputStream());
			os.writeUTF(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void start()
	{
		try {
			sock = new Socket(IP, port);
			is = new DataInputStream(sock.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void checkText() throws InterruptedException
	{
		try {
			text = is.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setApp(ChatApp app)
	{
		this.app = app;
	}
}
