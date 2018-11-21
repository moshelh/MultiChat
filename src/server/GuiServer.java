package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GuiServer extends JFrame {

	private JPanel contentPane;
	private Server server;
	JTextArea textArea;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public GuiServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServer = new JLabel("server");
		lblServer.setFont(new Font("Arial", Font.BOLD, 35));
		lblServer.setToolTipText("");
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setBounds(138, 11, 239, 30);
		contentPane.add(lblServer);
		
		JButton startButton = new JButton("start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Server.setFlag(true);
			}
		});
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startButton.setBounds(56, 281, 186, 23);
		contentPane.add(startButton);
		
		JButton stopButton = new JButton("stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server.setFlag(false);
				textArea.append("stop connecting\n");
				
			}
		});
		stopButton.setBounds(294, 281, 206, 23);
		contentPane.add(stopButton);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 45, 494, 216);
		contentPane.add(textArea);
	}

	public void textArea(String input) {
		// TODO Auto-generated method stub
		
	}
}
