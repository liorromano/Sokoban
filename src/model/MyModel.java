package model;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import controller.Client;
import model.data.loadersAndSavers.LoadLevel;
import model.data.loadersAndSavers.SaveLevel;
import model.policy.GeneralSokobanPolicy;
import model.policy.MySokobanPolicy;
import model.policy.move.GeneralMove;
import model.policy.move.MyMove;

public class MyModel extends Observable implements Model{

	private Level level;
	private Socket socket;
	private ObjectInputStream serverInput;
	private ObjectOutputStream outToServer;

	public MyModel() {

	}


	public void solve(Level level,String name) {
		if(level!=null)
 		{
 			String solution="";
 			String line="";
 			LevelBin levelBin=new LevelBin();
 			levelBin.setName(level.getName());
 			levelBin.setLevelString(level.getLevelString());
 			levelBin.setUserName(name);
			try {
				this.socket=new Socket("localhost",5555);
				outToServer = new ObjectOutputStream(socket.getOutputStream());
				outToServer.flush();
				//serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				serverInput=new ObjectInputStream(socket.getInputStream());
				outToServer.writeObject(levelBin);
				outToServer.flush();
				try {
					solution = (String)serverInput.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*solution=serverInput.readLine()+"\n";
				while ((line = serverInput.readLine()) != null) {
					solution=solution+"\n"+line;
			    }*/

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}



 				System.out.println(solution);
 				if(solution.equals("") || solution.equals("Unsolvable"))
 				{
 					System.out.println(solution);
 					//displayError("Level cannot be solved");
 				}
 				else
 					{
 					String [] moveCommands = solution.split("\n");
 					new Thread(new Runnable() {

 					@Override
 					public void run() {
 					for(String command : moveCommands)
 					{
 					System.out.println(command);
 					notifyCommand(command);
 					try {
 					Thread.sleep(500);
 					} catch (InterruptedException e) {
 									e.printStackTrace();
 					 							}
 											}
 										}
 				 				}).start();
 					}
 		}

 				//System.out.println("Solution received from server: " + solution);
 				//notifyCommand(solution);
}



	/**
	 * The load function gets "String last", this is the path to a file,
	 * and transfers it to "LoadLevel" to load it to a new level.
	 */
	@Override
	public void load(String last)
	{
		GeneralSokobanPolicy policy=new MySokobanPolicy();
		LoadLevel create= new LoadLevel(policy,last);
		this.setLevel(create.getLevel());
		this.setChanged();
		List<String> params = new LinkedList<String>();
		params.add("display");
		this.notifyObservers(params);
	}
	/**
	 * The save function gets "String last", this is the path of the file to be saved,
	 * and transfers it to "SaveLevel" to save it to a new file.
	 */
	@Override
	public void save(String last)
	{
		SaveLevel save= new SaveLevel();
		save.setFilename(last);
		save.setLevel(level);
		save.start();
		setChanged();
		notifyObservers();
}
	/**
	 * This function gets a string of direction to move player.
	 * It uses GeneralMove to move the player.
	 */
	@Override
	public void move(String last)
	{

		GeneralMove move=new MyMove(level);
		move.move(last);
		this.level=move.getLevel();
		this.setChanged();
		List<String> params = new LinkedList<String>();
		params.add("display");
		this.notifyObservers(params);

	}
	@Override
	public Level getLevel() {
		return level;
	}
	@Override
	public void setLevel(Level level) {
		if (level!=null)
			this.level = level;
	}

	private void notifyCommand(String commandLine) {
		String[] arr = commandLine.split(" ", 2);
		List<String> params = new LinkedList<String>();

		for (String s : arr) {
			params.add(s);
		}

		setChanged();
		notifyObservers(params);
	}





		}







