package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import server.GuiServer;
/**
 * The client process, is connected to the server’s system over a network.
 * The client process sends a request across the network to the server requesting service of
 * some form, e.g. reading or writing a file on the server’s system.
 * @author moshe and ariel
 *
 */
public class client implements Runnable{
	 public String userName=null;
	 
	 public InetAddress serverHost;
     public int serverPort;
	 private Scanner userInputScanner;
	 private GUIclient frame = null;
	 clientThread ClientThread;
	 public Socket socket;
	 public Scanner scan;
	 public Thread serverAccessThread ;
	  public client(String userName, InetAddress ip, int portNumber,GUIclient frame){
		  
	        this.userName = userName;
	        this.serverHost = ip;
	        this.serverPort = portNumber;
	        this.frame=frame;
	    }
	  public void sendMes(String s) {
          ClientThread.addNextMessage(s);
	  }
	  /**
	   * open a new socket and new thread for the client. 
	   */
	  public void startClient(){
		   try{
			   
	            socket = new Socket(serverHost, serverPort);
	            scan = new Scanner(frame.chatTextArea.getText());
	            ClientThread = new  clientThread(socket, userName,frame);
	            serverAccessThread = new Thread(ClientThread);
	            serverAccessThread.start();
	            while(serverAccessThread.isAlive()){
	                if(scan.hasNextLine()){
	                	 ClientThread.addNextMessage(scan.nextLine());
	                }

	            }
	            
	        }catch(IOException ex){
	        	frame.chatTextArea.append("Fatal Connection error!\n");
	            System.err.println("Fatal Connection error!");
	            ex.printStackTrace();

	    }
	  }
	  
	@Override
	public void run() {
		
		this.startClient();
	}
}
	  