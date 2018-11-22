package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.ServerThread;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Color;

public class GUIclient extends JFrame implements ActionListener{

	private JPanel contentPane;
	public JTextField usernameField;
	JTextArea chatTextArea;
	client Client;
	JButton connectButton;
	JButton sendButton;
	JTextArea inputTextArea;
	public String name = "";
	public List<String> clients;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 new GUIclient();
	}

	/**
	 * Create the frame.
	 */
	public GUIclient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		chatTextArea.setBackground(new Color(192, 192, 192));
		chatTextArea.setWrapStyleWord(true);
		chatTextArea.setLineWrap(true);
		chatTextArea.setBounds(10, 51, 474, 261);
		contentPane.add(chatTextArea);
		
		usernameField = new JTextField();
		usernameField.setText("enter your name");
		usernameField.setBounds(113, 11, 228, 34);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(10, 16, 93, 24);
		contentPane.add(lblUsername);
		
		 connectButton = new JButton("connect");
		
		connectButton.addActionListener(this);
;
		connectButton.setBounds(351, 14, 123, 28);
		contentPane.add(connectButton);
		
		JButton disconnectButton = new JButton("disconnect");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Client.ClientThread.stop();
				Client.sendMes(" "+Client.userName+" disconnect");
				chatTextArea.append("goodbye :) ");
				
			}
		});
		disconnectButton.setBounds(496, 16, 125, 24);
		contentPane.add(disconnectButton);
		
		inputTextArea = new JTextArea();
		inputTextArea.setBounds(10, 324, 380, 70);
		contentPane.add(inputTextArea);
		
		 sendButton = new JButton("send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inputTextArea.getText()!=null)
					Client.sendMes(inputTextArea.getText());
				inputTextArea.setText(" ");
			}
		});
		sendButton.setBounds(396, 332, 93, 62);
		contentPane.add(sendButton);
		
		JButton onlineUsersButton = new JButton("Show online users");
		onlineUsersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.sendMes("Show online users");			}
		});
		onlineUsersButton.setBounds(494, 98, 137, 93);
		contentPane.add(onlineUsersButton);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object t = e.getSource();
		if(t == connectButton) {
			name =usernameField.getText();
			
			if (name.trim().equals("")) {
				chatTextArea.append("Invalid. Please enter again:");
			}
			else {
				//String adress=adfieldText.getText();,insert to the new client
				Client = new client( name,  "localhost", 4444,this);
				Thread clientT=new Thread(Client);
				clientT.start();
			}
			usernameField.setText("Start chatting");
		}

	}
}
