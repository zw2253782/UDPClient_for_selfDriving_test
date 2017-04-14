package main;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("start UDP client");
		
		UDPClient udpclient = new UDPClient();	
		(new Thread(udpclient)).start();

		
		
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			udpclient.send("send to server");
			//Thread.yield();
		}
		
	}
	
}

