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
import java.awt.Font;

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
	public String IPAdress ="";
	private JTextField ipField;
	

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
		setBounds(100, 100, 657, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setText("enter your name");
		usernameField.setBounds(90, 11, 228, 34);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(0, 16, 93, 24);
		contentPane.add(lblUsername);
		
		 connectButton = new JButton("connect");
		 connectButton.setForeground(Color.BLACK);
		 connectButton.setBackground(Color.GREEN);
		 connectButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		connectButton.addActionListener(this);
;
		connectButton.setBounds(498, 54, 123, 28);
		contentPane.add(connectButton);
		
		JButton disconnectButton = new JButton("disconnect");
		disconnectButton.setBackground(Color.RED);
		disconnectButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Client.ClientThread.stop();
				Client.sendMes(" "+Client.userName+" disconnect");
				chatTextArea.append("goodbye :) ");
				
			}
		});
		disconnectButton.setBounds(494, 95, 125, 34);
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
		onlineUsersButton.setBounds(492, 149, 139, 93);
		contentPane.add(onlineUsersButton);
		
		ipField = new JTextField();
		ipField.setHorizontalAlignment(SwingConstants.CENTER);
		ipField.setText("localhost");
		ipField.setBounds(428, 14, 193, 29);
		contentPane.add(ipField);
		ipField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP ADRESS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(328, 16, 90, 24);
		contentPane.add(lblNewLabel);
		
		JTextArea txtrForPrivateMassage = new JTextArea();
		txtrForPrivateMassage.setFont(new Font("Courier New", Font.PLAIN, 11));
		txtrForPrivateMassage.setText("for private massage:");
		txtrForPrivateMassage.setBounds(483, 295, 148, 17);
		contentPane.add(txtrForPrivateMassage);
		
		JTextArea txtrnamemassage = new JTextArea();
		txtrnamemassage.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtrnamemassage.setText("(name)your massage");
		txtrnamemassage.setBounds(483, 310, 148, 24);
		contentPane.add(txtrnamemassage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 474, 261);
		contentPane.add(scrollPane);
		
		chatTextArea = new JTextArea();
		scrollPane.setViewportView(chatTextArea);
		chatTextArea.setEditable(false);
		chatTextArea.setBackground(new Color(192, 192, 192));
		chatTextArea.setWrapStyleWord(true);
		chatTextArea.setLineWrap(true);
		this.setVisible(true);
	}
/**
 * if connect button is pushed ,check if the username is valid ,if it is,open a new client.
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object t = e.getSource();
		if(t == connectButton) {
			name =usernameField.getText();
			IPAdress=ipField.getText();
			
			if (name.trim().equals("")) {
				chatTextArea.append("Invalid. Please enter again:");
			}
			else {
				//String adress=adfieldText.getText();,insert to the new client
				Client = new client( name,  IPAdress, 4444,this);
				Thread clientT=new Thread(Client);
				clientT.start();
			}
			usernameField.setText("Start chatting");
		}

	}
}
