package server;



import java.awt.EventQueue;
import java.awt.Frame;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	public static boolean flag;
	private static final int portNumber = 4444;
	private static int serverPort;
	public List<ServerThread> clients;
	
	public static GuiServer frame = null;
	public static void main(String[] args) {
		frame = new GuiServer();
		frame.setVisible(true);
		frame.textArea.setEditable(false); 
		while(true) {
			try {

				Thread.sleep(500);
				if (flag) {
					
					Server server =new Server (portNumber);
					server.startServer();
				}

			} catch (Exception e) {

			}
		}
	}

	public Server (int portNumber)
	{
		Server.serverPort=portNumber;
	}

	public static void setFlag(boolean flag) {
		Server.flag = flag;
	}
	public List<ServerThread> getClients(){
		return clients;
	}
	private void startServer() {
		clients = new ArrayList<ServerThread>();
		ServerSocket serverSocket =null;
		try {
			serverSocket= new ServerSocket(serverPort);
			acceptClients(serverSocket);
		} catch (Exception e) {
			frame.textArea.append("Could not listen on port: "+serverPort+"\n");
		}

	}
	private void acceptClients(ServerSocket serverSocket) {
		frame.textArea.append("server starts port = " + serverSocket.getLocalSocketAddress()+"\n");
		while(flag) {
			try {
				Socket socket =serverSocket.accept();
				frame.textArea.append("accepts : " + socket.getRemoteSocketAddress()+"\n");
				ServerThread client = new ServerThread(this, socket);
				Thread thread = new Thread(client);
				thread.start();
				clients.add(client);
			} catch (Exception e) {
				frame.textArea.append("Accept failed on : "+serverPort+"\n");
			}
		}

	}
}
