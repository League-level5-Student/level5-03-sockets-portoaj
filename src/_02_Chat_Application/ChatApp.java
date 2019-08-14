package _02_Chat_Application;

import java.awt.HeadlessException;
import java.net.UnknownHostException;

import javax.swing.*;


/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
	JButton button = new JButton("sendmessage");
	JTextField text; 
	
	ChatAppServer server;
	ChatAppClient client;
	int response;
	public static void main(String[] args) throws HeadlessException, UnknownHostException {
		new ChatApp();
	}
	ChatApp() throws HeadlessException, UnknownHostException
	{
		text = new JTextField(30);
		add(text);
		text.addActionListener(e -> {
			if(server != null)
				server.setText(text.getText());
		});
		response  = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION)
		{
			server = new ChatAppServer(1235);
			setTitle("Server");
			JOptionPane.showMessageDialog(null, "Server started at ip: " + server.getIPAddress() + "\nPort: " + server.getPort());
			//button.addActionListener(e -> server.sendClick());
			add(button);
			setVisible(true);
			setSize(400,300);
			server.start();
		}
		else
		{
			setTitle("CLIENT");
			String IP = JOptionPane.showInputDialog("Enter the IP Adress of the server");
			int port = Integer.parseInt(JOptionPane.showInputDialog("Enter the server's port"));
			client = new ChatAppClient(IP, port);
			//button.addActionListener(e -> client.sendClick());
			add(button);
			setVisible(true);
			setSize(400,300);
			client.start();
		}
		
	}
	
}