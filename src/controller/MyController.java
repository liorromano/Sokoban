package controller;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controller.commands.Command;
import controller.commands.DataBaseCommand;
import controller.commands.DisplayCommand;
import controller.commands.ExitCommand;
import controller.commands.FinishCommand;
import controller.commands.LoadCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveCommand;
import controller.commands.SolveCommand;
import controller.server.ClientHandler;
import controller.server.MyServer;
import entities.DBmanager;
import view.View;
import model.Model;

public class MyController implements Observer,Controller{

	private View view;
	private Model model;
	private HashMap<String,Command> commands;
	private GeneralController controller;
	//private ClientHandler clientHandler=null;
	//private MyServer theServer=null;
	private Socket socket=null;
	private DBmanager manager;

	/**
	 * This is the c'tor of the controller. The function initialize the view,model, DBmanager.
	 * also it's start the General controller that managed the commands.
	 * @param view- this is the view of the game.
	 * @param model-this is the model that doing all the functions of the game.
	 * @param manager-this is the db manager.
	 */
	public MyController(View view, Model model,DBmanager manager){
		this.model=model;
		this.view=view;
		this.manager=manager;
		initCommands();
		controller = new GeneralController();
		controller.start();
	}

/**
 * This is the c'tor of the controller. The function initialize the view,model, DBmanager.
 * also it's start the General controller that managed the commands.
 * and also start the client server.
 * @param view- this is the view of the game.
 * @param model-this is the model that doing all the functions of the game.
 * @param manager-this is the db manager.
 * @param clientHandler-this handle the client requests from the server.
 * @param port-the port that the server working on.
 */
	public MyController(View view, Model model,DBmanager manager, Socket socket){
		this.model=model;
		this.view=view;
		this.manager=manager;
		//this.clientHandler=clientHandler;
		//this.theServer=new MyServer(port, this.clientHandler);
		//this.theServer.start();
		this.socket=socket;
		initCommands();
		controller = new GeneralController();
		controller.start();
	}

/**
 * The function initialize the commands types.
 */
	protected void initCommands() {
		commands = new HashMap<String, Command>();
		commands.put("move", new MoveCommand(model));
		commands.put("display", new DisplayCommand(model, view));
		commands.put("load", new LoadCommand(model));
		commands.put("save", new SaveCommand(model));
		commands.put("exit", new ExitCommand(view,this,socket));
		commands.put("finish", new FinishCommand(view,model));
		commands.put("SaveToDataBase", new DataBaseCommand(view,model,manager));
		commands.put("solve", new SolveCommand(model,socket));
	}

	/**
	 * This function is the function of the Observer.
	 * It's insert every command to the general controller.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg!=null)
		{

			@SuppressWarnings("unchecked")
			List<String> params = (List<String>) arg;
			String commandKey = params.remove(0);
			//System.out.println(commandKey);
			Command c = commands.get(commandKey);
			if (c == null) {
				System.out.println("Command " + commandKey + " not found");
				return;
			}
			c.setParams(params);
			controller.insertCommand(c);
		}
	}

	/**
	 * Function to get the General controller.
	 * @return- the general controller.
	 */
	public GeneralController getController() {
		return controller;
	}

}
