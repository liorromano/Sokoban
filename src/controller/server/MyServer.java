package controller.server;


 import java.net.ServerSocket;
 import java.net.Socket;
 import java.net.SocketTimeoutException;
import java.util.Observable;
import java.util.Observer;


 public class MyServer extends Observable implements Observer {

 	private int port;
 	private ClientHandler ch;
 	private volatile boolean stop;

/**
 * This is the server c'tor.
 * @param port-the port of the server.
 * @param ch- the client handler that works with the server.
 */
 	public MyServer(int port, ClientHandler ch) {
 		this.port=port;
 		this.ch=ch;
 		stop=false;
 	}

 	public void start(){
 		new Thread(new Runnable() {
 			@Override
 			public void run() {
 				try{
 					ServerSocket server=new ServerSocket(port);
 			 		System.out.println("Server is alive");
 			 		server.setSoTimeout(5000);

 			 		while(!stop){
 			 			try{
 			 				Socket aClient=server.accept(); // blocking call
 			 				System.out.println("The client is connected");
 			 				ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
 			 				//aClient.getInputStream().close();
 			 				//aClient.getOutputStream().close();
 			 				aClient.close();
 			 			}catch(SocketTimeoutException e) {continue;}
 			 		}

 			 		server.close();
 				}catch (Exception e){e.printStackTrace();}
 			}
 		}).start();
 	}


/**
 * This function stops the server.
 */
	public void stopServer() {
		stop=true;

	}

/**
 * This function notify the observers.
 */
	public void update(Observable observable, Object object) {
		setChanged();
		notifyObservers(object);
	}

 }
