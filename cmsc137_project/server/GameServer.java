import java.net.*;
import java.io.*;
import java.util.*;

public class GameServer extends Thread {
	/*public static void main(String[] args) throws IOException {
		new GamerServerThread().start();
	}*/

	ServerSocket serverSocket = null;

	public GameServer(int port) throws IOException {
		try {
			serverSocket = new ServerSocket(port);
			//serverSocket.setSoTimeout(50000);
		}catch(IOException e) {
			System.err.println("Could not listen on port: " + port);
			System.exit(-1);
		}catch(Exception e) {
			System.out.println("error");
		}
	}

	public void run() {
		boolean connected = true;
		Scanner input = new Scanner(System.in);
		while(connected) {
			try{
				System.out.print("How many players? (min. of 3 & max. of 13): ");
				String noOfPlayersInit = input.nextLine();
				int noOfPlayers = Integer.parseInt(noOfPlayersInit);
				
				for(int i=0; i < noOfPlayers; i++){

					System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
					/* Start accepting data from the ServerSocket */
	                //waits or accepts connection from client
	                Socket client = serverSocket.accept();
	                
	                DataOutputStream out = new DataOutputStream(client.getOutputStream());
	                out.writeUTF("What is your name? " + client.getRemoteSocketAddress());
	                
	                System.out.println("Just connected to " + client.getRemoteSocketAddress());
	                DataInputStream in = new DataInputStream(client.getInputStream());
                	System.out.println(in.readUTF());
	                client.close();
           		}
                // obtaining input and out streams
                /*DataInputStream in = new DataInputStream(client.getInputStream());
                System.out.println(in.readUTF()); //readUTF waits for input

                DataOutputStream out = new DataOutputStream(client.getOutputStream());*/
                


                //connected = false
                
			}catch(SocketTimeoutException s) {
				System.out.println("Socket timed out!");
                break;
			}catch(IOException e) {
                e.printStackTrace();
                System.out.println("Input/Output Error!");
                break;
            }
		}
	}

	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[0]);
			Thread t = new GameServer(port);
			t.start();
		}catch(IOException e) {
			System.out.println("Usage: java GameServer <port no.>\n"+
                    "Make sure to use valid ports.");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: java GameServer <port no.>\n"+
                    "Insufficient arguments given.");
		}
	}

}