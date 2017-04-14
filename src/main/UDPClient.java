package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UDPClient implements Runnable {
	public DatagramSocket serverSocket = null;
	public InetAddress clientIPAddress = null;
	public int clientPort = 55555;
	public int serverPort = 4444;
	String IPName = "192.168.1.101";

	
	
	public UDPClient() {
		try {
			serverSocket = new DatagramSocket(serverPort);
			//serverSocket.setReuseAddress(true);
			clientIPAddress = InetAddress.getByName(IPName);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String data) {
		System.out.println("send " + data);
		byte[] sendData = new byte[1024];
		sendData = data.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
		try {
			serverSocket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void run() {
		// TODO Auto-generated method stub
		byte[] receiveData = new byte[1024];
		while(true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				System.out.println("RECEIVED: " + sentence);
				clientIPAddress = receivePacket.getAddress();
				clientPort = receivePacket.getPort();
				
				System.out.println(clientIPAddress.toString());
				System.out.println(clientPort);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}	