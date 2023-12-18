package udp;

import java.net.*;
import java.io.*;

public class Server {
private DatagramSocket datagramSocket;


public Server (DatagramSocket datagramSocket) {
	this.datagramSocket = datagramSocket;
}


public void reciveThenSend () {
	
	while(true) {
		try {
			byte[] receiveData = new byte[1024];
			
			
		DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
			datagramSocket.receive(datagramPacket);
	
			InetAddress inetAddress =  datagramPacket.getAddress();
			int port = datagramPacket.getPort();
			
			String messageFromClient = new String (datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.println("Message from client: " + messageFromClient);
			
			String modificirana = messageFromClient.toUpperCase();
			System.out.print(modificirana);
			
			if(modificirana.equalsIgnoreCase("kraj")) {
				System.out.println("KRAAAAAAAAJJJJJ");
			break;
			}
			else {
			datagramPacket = new DatagramPacket (modificirana.getBytes(), modificirana.length(), inetAddress, port);
			
				datagramSocket.send(datagramPacket);
			
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			break;
			
			
		}
	}
}

public static void main (String[] args) throws SocketException {
	
	DatagramSocket datagramSocket = new DatagramSocket(1233); //1234 e broj na porta
	Server server = new Server(datagramSocket);
	server.reciveThenSend();
	
}

}
