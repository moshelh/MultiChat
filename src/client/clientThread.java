package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JTextArea;
/**
 *  creating a helper class for handling various requests.
 *  sending and getting the clients message
 * @author moshe and ariel
 *
 */
public class clientThread implements Runnable {
    private Socket socket;
    private String userName;
    private boolean isAlived;
    private JTextArea chatTextarea = new JTextArea();
    private final LinkedList<String> messagesToSend;
    private boolean hasMessages = false;
    GUIclient frame;
  
    
    
    public clientThread(Socket socket, String userName, GUIclient frame){
    	
    	this.frame = frame;
        this.socket = socket;
        this.userName = userName;
        messagesToSend = new LinkedList<String>();
        // 
    }
	public void addNextMessage(String message){
        synchronized (messagesToSend){
            hasMessages = true;
            messagesToSend.push(message);
        }
    }
	@Override
	public void run() {
		frame.chatTextArea.append("Welcome :" + userName+"\n");
		frame.chatTextArea.append("for sending a private message use (username)"+"\n");
	    frame.chatTextArea.append("Enjoy!"+"\n");

        try{
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), false);
            InputStream serverInStream = socket.getInputStream();
            Scanner serverIn = new Scanner(serverInStream);
        

            while(!socket.isClosed()){
                if(serverInStream.available() > 0){
                    if(serverIn.hasNextLine()){
                    	String m=serverIn.nextLine();

                        frame.chatTextArea.append(m+"\n");
                    }
                }
                if(hasMessages){
                    String nextSend = "";
                    synchronized(messagesToSend){
                        nextSend = messagesToSend.pop();
                        hasMessages = !messagesToSend.isEmpty();
                    }
              
                    //serverOut.println()
                    serverOut.println(userName + ">" + nextSend);
                    serverOut.flush();
                }
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }
	public void stop() {
		PrintWriter serverOut;
		try {
			serverOut = new PrintWriter(socket.getOutputStream(), false);
			 serverOut.write(userName+"disconnect");
		        serverOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
	}
		
	}


