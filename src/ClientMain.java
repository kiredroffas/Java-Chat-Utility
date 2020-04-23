import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//ClientMain class
public class ClientMain { //connects to server, asks for ip address (localhost), starts threads
	Socket sock;    
	ClientMain cm;
	WriteToServer sReaderThread;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientMain cm = new ClientMain();
		System.out.println("Input ip to connect to Server:");
		Scanner input = new Scanner(System.in); //takes in ip address
		String text2 = input.nextLine();
		cm.sock = new Socket(text2, 1201); //establishes connection with server
		System.out.println("Welcome!");
		cm.sReaderThread = new WriteToServer(cm.sock);  //create thread
		cm.sReaderThread.start(); //start it
	}
}

//WriteToServer thread
class WriteToServer extends Thread{ //reads from client, sends to server
	Scanner stdin;
	PrintWriter toServer;
	ReadFromServer sReader;
	
	public WriteToServer(Socket s) throws IOException { //constructor
		stdin = new Scanner(System.in); //used to read stdin
		toServer = new PrintWriter(s.getOutputStream()); //used to write to server
		sReader = new ReadFromServer(s); //create thread to read from server
		sReader.start();	//start
	}
	
	public void run() {
		while(stdin.hasNextLine()) { //while hastnextline
			String text = stdin.nextLine();
			toServer.println(text);
			toServer.flush();
		}
		
		System.out.println("Exiting client. Bye!"); //if ctrl c is pressed we exit client
		toServer.close();
		stdin.close();
		System.exit(0); //bye bye
		return;
	}
}

//ReadFromServer Thread
class ReadFromServer extends Thread{ //reads from server and prints in client
	Scanner fromServer;
	
	public ReadFromServer(Socket s) throws IOException { 
		fromServer = new Scanner(s.getInputStream()); //read from server inpustream
	}
	
	public void run() {
		while(fromServer.hasNextLine()) { //while server hasnexline
			String text = fromServer.nextLine();
			System.out.println("From Server: \"" + text + "\""); //save and print input
		}
		System.out.println("Server disconnected"); //disconnected means close client.
		fromServer.close();
		System.exit(0);
		return;
	}
	
}