package udp;
import java.net.*;
import java.util.Scanner;
import java.io.*;


public class Client {
	
	
	private DatagramSocket datagramSocket;
	private InetAddress inetAddress;
	private byte[] buffer;
	
	
	public Client (DatagramSocket datagramSocket, InetAddress inetAddress) {
		
		this.datagramSocket=datagramSocket;
		this.inetAddress=inetAddress;
	}
	
	public void sendThenRecive () {
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				String messageTosend = scanner.nextLine();
				buffer = messageTosend.getBytes();
				DatagramPacket datagramPAcket = new DatagramPacket(buffer, buffer.length,inetAddress,1233);
			
				datagramSocket.send(datagramPAcket);
				datagramSocket.receive(datagramPAcket);
				String messageFromServer = new String(datagramPAcket.getData(),0,datagramPAcket.getLength());
				System.out.println("Server says you said :" +messageFromServer);

			} catch(IOException e){
				e.printStackTrace();
				break;
			}
		}
		
	}
		public static void main (String[] args) throws  IOException {
			
			DatagramSocket datagramSocket = new DatagramSocket();
			InetAddress inetAddress = InetAddress.getByName("localhost");
			Client client = new Client(datagramSocket, inetAddress);
			System.out.println("Send datagram packet to a server.");
			client.sendThenRecive();
		
		}
	
}
