package server;


import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
	private Socket socket;
	private PrintWriter clientOut;
	private Server server;
	public String id;

	public ServerThread(Server server, Socket socket) {
		
		this.server = server;
		this.socket = socket;
	}
	private PrintWriter getWriter(){
		return clientOut;
	}
	/**
	 * while the socket is open ,keep getting input,save the id of the user .
	 * check the input ,if there is a private message , message to all ,or the user's request for disconnection and close the socket.
	 */
	@Override
	public void run() {
		try {
			this.clientOut = new PrintWriter(socket.getOutputStream(), false);
			Scanner in = new Scanner(socket.getInputStream());

			// start communicating
			while(!socket.isClosed()){
				if(in.hasNextLine()){
					String input = in.nextLine();
					// NOTE: if you want to check server can read input, uncomment next line and check server file console.
					// System.out.println(input);
					
					System.out.println(input);
					if(this.id==null)
					{
						this.id=input.substring(0,input.indexOf(">"));

					} 
					
					//the user wants to disconnect
					if(input.contains("disconnect")) {
						
						String ID2=input.substring(0,input.indexOf(">"));
						int counter=0;
						for(ServerThread thatClient : server.getClients()){

							if(thatClient.id.equals(ID2)) {
								break;
							}else counter++;

						}
						server.getClients().remove(counter);
						socket.close();
					}

					//private massage
					
					
					if(input.contains("(")) {
						System.out.println("done");
						String sendId=input.substring(input.indexOf("(")+1, input.indexOf(")"));
						System.out.println("do "+sendId);
						input=input.substring(input.indexOf(")")+1);
						for(ServerThread thatClient : server.getClients()){
							if(thatClient.id.equals(sendId)) {
								PrintWriter thatClientOut = thatClient.getWriter();
								thatClientOut.write("Private message from "+this.id+":"+input + "\r\n");
								thatClientOut.flush();
							}
							if(thatClient.id.equals(this.id)) {
								PrintWriter thatClientOut = thatClient.getWriter();
								thatClientOut.write("Private message from you to "+sendId+":"+input + "\r\n");
								thatClientOut.flush();
							}
						}
						//request for showing online users , print only for the user whom sends the request.
					}else if (input.contains("Show online users"))
					{    ServerThread Id2 = null;
					String ID2=input.substring(0,input.indexOf(">"));

					for(ServerThread thatClient : server.getClients()){
						// PrintWriter thatClientOut = thatClient.getWriter();
						if(thatClient.id.equals(ID2)) {
							System.out.println(thatClient.id);
							Id2=thatClient;
						}
					}
					String onlineusers="";
					PrintWriter thatClientOut = Id2.getWriter();
					for(ServerThread thatClient : server.getClients()){
						System.out.println(thatClient.id);
						onlineusers=onlineusers+thatClient.id+" . ";
					}
					thatClientOut.write("The online users are: "+onlineusers+ "\r\n");
					thatClientOut.flush();
					}


					//from broadcast ,send everyone.
					else{ 
						server.frame.textArea.append(input+"\n");
						for(ServerThread thatClient : server.getClients()){
							PrintWriter thatClientOut = thatClient.getWriter();
							if(thatClientOut != null){
								thatClientOut.write(input + "\r\n");
								thatClientOut.flush();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
