package _02_Chat_Application;

import java.awt.HeadlessException;
import java.net.UnknownHostException;

import javax.swing.*;


/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
	JButton button = new JButton("sendmessage");
	JButton refresh = new JButton("refresh");
	JTextField text; 
	JPanel panel;
	ChatAppServer server;
	ChatAppClient client;
	int response;
	int startTime;
	public static void main(String[] args) throws HeadlessException, UnknownHostException {
		new ChatApp();
	}
	ChatApp() throws HeadlessException, UnknownHostException
	{ 
		panel = new JPanel();
		add(panel);
		text = new JTextField(30);
		panel.add(refresh);
		panel.add(text);
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
			button.addActionListener(e -> server.sendClick(text.getText()));
			refresh.addActionListener(e -> {
				try {
					server.checkText();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			panel.add(button);
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
			button.addActionListener(e -> client.sendClick(text.getText()));
			refresh.addActionListener(e -> {
				try {
					client.checkText();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			panel.add(button);
			setVisible(true);
			setSize(400,300);
			client.start();
		}
		
	}
	
}