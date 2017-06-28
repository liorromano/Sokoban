package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import model.data.loadersAndSavers.LoadLevel;
import model.data.loadersAndSavers.SaveLevel;
import model.policy.GeneralSokobanPolicy;
import model.policy.MySokobanPolicy;
import model.policy.move.GeneralMove;
import model.policy.move.MyMove;

public class MyModel extends Observable implements Model{

	private Level level;

	public MyModel() {

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
	@Override
	public void solve(Socket socket) {
		if(this.level!=null)
		{
			String direction="";
			String solution="";
			LevelBin level=new LevelBin();
			level.setName(this.level.getName());
			level.setLevelString(this.level.getLevelString());
			level.setUserName("lior");
			BufferedReader serverInput;
			try {
				ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
				outToServer.writeObject(level);
				serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				List<String> params = new LinkedList<String>();

				solution = serverInput.readLine();
				System.out.println("Solution received from server: " + solution);
				notifyCommand(solution);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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