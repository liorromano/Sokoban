package controller;

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
	private ClientHandler clientHandler=null;
	private MyServer theServer=null;
	private DBmanager manager;

	public MyController(View view, Model model,DBmanager manager){
		this.model=model;
		this.view=view;
		this.manager=manager;
		initCommands();
		controller = new GeneralController();
		controller.start();
	}


	public MyController(View view, Model model,DBmanager manager,ClientHandler clientHandler,int port){
		this.model=model;
		this.view=view;
		this.manager=manager;
		this.clientHandler=clientHandler;
		this.theServer=new MyServer(port, this.clientHandler);
		this.theServer.start();

		initCommands();
		controller = new GeneralController();
		controller.start();
	}


	protected void initCommands() {
		commands = new HashMap<String, Command>();
		commands.put("move", new MoveCommand(model));
		commands.put("display", new DisplayCommand(model, view,this.clientHandler));
		commands.put("load", new LoadCommand(model));
		commands.put("save", new SaveCommand(model));
		commands.put("exit", new ExitCommand(view,this,theServer));
		commands.put("finish", new FinishCommand(view,model));
		commands.put("SaveToDataBase", new DataBaseCommand(view,model,manager));
	}

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

	public GeneralController getController() {
		return controller;
	}

}
