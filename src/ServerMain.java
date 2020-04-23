/*
  Erik Safford
  Java Chat Utility
  Spring 2018
*/

import java.awt.AWTException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//ServerMain Class
public class ServerMain { //Servermain starts server, accepts client socket, initializes threads
	
	ReadFromClient readC; //ReadFromclient type object
	Socket clientSock;  //clientsock will hold client socket
	
	public static void main(String[] args) throws IOException, AWTException {
		ServerMain sm = new ServerMain(); //java
		ServerSocket ss = new ServerSocket(1201);  //create new server socket
		
		while(true) { //all in an infinite loop
			System.out.println("Searching for a connection...");
			sm.clientSock = ss.accept(); //Waits until connection is accepted
			System.out.println("Got connection from " + sm.clientSock); 
			sm.readC = new ReadFromClient(sm.clientSock); //create new ReadFromClient thread that accepts client socket
			sm.readC.start(); //start it.                 
			while(sm.readC.flag != 1){ //while loop until flag is changed to 1,
				System.out.print(""); //indicating client is closing
			}
			System.out.println("\nClient is closing...");
			System.out.println("Please press Enter to continue.");
			while(sm.readC.cWriter.flags == 0) { //need to press enter to get out of
				System.out.print("");            //while loop inside writetoclient thread
			}
			sm.clientSock.close();	//close client socket
	   }
	}
}

//Write to Client Thread
class WriteToClient extends Thread{ //Reads stdin and prints it to client
	
	Scanner stdin;
	PrintWriter toClient; //used to write to client
	int flags = 0; //used to indicate that it is stuck in while
	boolean flag = true; //used to indicate from servermain to exit while loop in thread
	
	public WriteToClient(Socket c) throws IOException { //initialize printwirte and scanner
		toClient = new PrintWriter(c.getOutputStream());
		stdin = new Scanner(System.in);
	}
	
	public void run() { //run in background
		while(flag) {
			String text = stdin.nextLine(); //Get input from stdin
			toClient.println(text);		//print to client
			toClient.flush();  //flush to update automatically.
		}
		flags = 1;  //set flag to 1 indicating loop is broken
		toClient.close(); //close stuff
		return;
  }
}

//Read from Client Thread
class ReadFromClient extends Thread{ //reads from client, prints to server
	WriteToClient cWriter; //creates and starts writeto client thread.
	Scanner fromClient;
	int flag = 0;
	
	public ReadFromClient(Socket c) throws IOException { //initialize
		cWriter = new WriteToClient(c);
		cWriter.start();
		fromClient = new Scanner(c.getInputStream());
	}
	
	public void run() {
		while(fromClient.hasNextLine()) { //while client hasnextline
			String text = fromClient.nextLine(); //save
			System.out.println("From Client: \"" + text + "\""); //print out
		}
		cWriter.flag = false; //indicate thread is out of while loop
		flag = 1;
		fromClient.close();
		return;
	}

}
