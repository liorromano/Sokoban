package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import model.Level;


public class MyClientHandler extends Observable implements ClientHandler {

	private BufferedReader fromClient;
	private PrintWriter toClient;
/**
 * This function handles with the client that is connected to the server.
 */
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {

		fromClient = new BufferedReader(new InputStreamReader(inFromClient));
		toClient = new PrintWriter(outToClient, true);

		String inputLine = null;
		String outputLine = new String("");
		boolean stopPlay = false;

		// Sending terms to the client
		toClient.println("Commands-server:\n1.Load filename.{txt ,XML, obj}\n2.Display\n3.Move {up, down, left, right}\n4.Save filename.{txt, XML, obj}\n5.Exit");

		while(stopPlay != true)
		{
			outputLine = "Enter the next command please.";
			toClient.println(outputLine);

			try
			{
				inputLine = fromClient.readLine();
				String[] arr = inputLine.split(" ");
				List<String> params = new LinkedList<String>();
				for (String s: arr) {
					System.out.println("get command "+s);
					params.add(s);
				}
				if(inputLine.equalsIgnoreCase("exit"))
				{
					stopPlay = true;
				}
				setChanged();
				notifyObservers(params);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			fromClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		toClient.close();
	}

	/**
	 * This function sends the level to the client.
	 */
	public void sendLevel(Level level)
	{
		if (level!=null)
		{
			String str=level.getLevelString();
			toClient.println(str);
		}
		else
			toClient.println("load file first");
	}

}



