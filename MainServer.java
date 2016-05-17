package sampleServer;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	public static void main(String[] args) {
		StServer stserver = new StServer();
		stserver.start();
		PrServer prserver = new PrServer();
		prserver.start();
	}
}
