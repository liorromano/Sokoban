package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public void start(String ip, int port){
		try{
		Socket theServer=new Socket(ip, port);
		System.out.println("connected to server");
		BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
		BufferedReader serverInput=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
		PrintWriter outToServer=new PrintWriter(theServer.getOutputStream());
		PrintWriter outToScreen=new PrintWriter(System.out);
		Thread t1= aSyncReadInputsAndSend(userInput,outToServer,"bye bye"); // different thread
		Thread t2= aSyncReadInputsAndSend(serverInput,outToScreen,"bye"); // different thread
		t1.join(); t2.join(); // wait for threads to end
		userInput.close();
		serverInput.close();
		outToServer.close();
		outToScreen.close();
		theServer.close();
		} catch(UnknownHostException e) {/*...*/}
		catch(IOException e) {/*...*/}
		catch(InterruptedException  e) {/*...*/}
		}

	private void readInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
		try {
 			String line;
			while(!(line=in.readLine()).equals(exitStr)){
				out.println(line);
			out.flush();
			}
		} catch (IOException e) { e.printStackTrace();}
	}
/**
 * This function open a thread and start the function start.
 * The thread gets input from the client in the server and send massages.
 * @param in- it gets the lines from the client.
 * @param out- prints back to client.
 * @param exitStr- string of the exit.
 * @return- return a new thread.
 */
 	private Thread aSyncReadInputsAndSend(final BufferedReader in, final PrintWriter out,final String exitStr){
 		Thread t=new Thread(new Runnable() {
			public void run() {readInputsAndSend(in, out, exitStr);}
 		});
	    t.start();
		return t;
 	}
}
