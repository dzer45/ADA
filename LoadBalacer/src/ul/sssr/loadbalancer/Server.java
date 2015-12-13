package ul.sssr.loadbalancer;

import java.net.*;
import java.io.*;

import com.amazonaws.services.sqs.AmazonSQS;
 
public class Server { 

    static int portNumber = 8080;
    static AmazonSQS sqs;
    public static void main(String[] args) throws IOException {
 
	ServerSocket socket;
	boolean listening = true;
         
        try {
	    socket = new ServerSocket(portNumber);
            while (listening) {
                new ServerThread(socket.accept()).start();
            }
	    socket.close();
	} catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ServerThread extends Thread {
	private Socket socket = null;
 
	public ServerThread(Socket socket) {
	    super("ServerThread");
	    this.socket = socket;
	}
	
	public void run() {
	    
	    try {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String inputLine, outputLine;
		out.println("Hello"); 
		socket.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
