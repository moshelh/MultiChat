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
import java.awt.Color;
import javax.swing.JScrollPane;

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
		lblServer.setBounds(138, 11, 239, 30);
		lblServer.setFont(new Font("Arial", Font.BOLD, 35));
		lblServer.setToolTipText("");
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblServer);
		
		JButton startButton = new JButton("start");
		startButton.setBounds(56, 272, 186, 32);
		startButton.setBackground(Color.GREEN);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Server.setFlag(true);
			}
		});
		startButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(startButton);
		
		JButton stopButton = new JButton("stop");
		stopButton.setBounds(294, 272, 206, 32);
		stopButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		stopButton.setBackground(Color.RED);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server.setFlag(false);
				textArea.append("stop connecting\n");
				
			}
		});
		contentPane.add(stopButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 52, 528, 195);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
		textArea.setForeground(new Color(255, 255, 0));
		textArea.setBackground(Color.BLACK);
	}

	public void textArea(String input) {
		// TODO Auto-generated method stub
		
	}
}
