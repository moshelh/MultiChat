package client;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class clientTest {
	GUIclient frame;
	 public InetAddress serverHost;
client c1=new client("joy",serverHost,4444,frame);



	@Test
	void clientport() {
		
	int portactual=c1.serverPort;
	int portexpected=4444;
	assertEquals(portexpected, portactual);
		
	}
	@Test
	void clientname() {
		String nameactual=c1.userName;
		String namenotexpected="";
		assertNotEquals(namenotexpected, nameactual);
		
		
	}
	@Test
	void clientIp() {
		try {
			InetAddress ipactual=c1.serverHost;
			InetAddress ipexpected=InetAddress.getByName("127.0.0.2");
			assertEquals(ipactual.getHostAddress(), ipexpected.getHostAddress());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
