package _02_Chat_Application;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatAppServer {
	String text;
	private int port;
	ChatApp app;
	ServerSocket ss;
	Socket sock;
	DataOutputStream os;
	ChatAppServer(int port)
	{
		this.port = port;
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setApp(ChatApp app)
	{
		this.app = app;
	}
	public String getIPAddress() throws UnknownHostException
	{
		return InetAddress.getLocalHost().getHostAddress();
	}
	public int getPort()
	{
		return port;
	}
	public void setText(String text)
	{
		this.text = text;
		try {
			sock = ss.accept();
			os = new DataOutputStream(sock.getOutputStream());
			os.writeUTF(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void start()
	{
		
	}
}
