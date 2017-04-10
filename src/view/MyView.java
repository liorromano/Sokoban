package view;

import java.util.Observable;
import java.util.Observer;

import javafx.stage.Stage;
import view.commands.display.DisplayLevel;
import view.commands.exitTypes.RegularExit;
import model.Level;

public class MyView extends Observable implements View,Observer{

	private CLI cli;

	public MyView() {
;
		cli=new CLI();
		cli.addObserver(this);

	}

	public void displayData(Level level,String command)
	{
		DisplayLevel display=new DisplayLevel();
		display.setCmd(command);
		display.setLevel(level);
		display.start();
		setChanged();
		notifyObservers();
	}
	public void exit()
	{
	cli.stop();
	RegularExit exit=new RegularExit();
	exit.exit();
	}


	public void getUserCommand()
	{
		cli.start();
	}

	@Override
	public void update(Observable observer, Object object) {
		setChanged();
		notifyObservers(object);
	}


	public CLI getCli() {
		return cli;
	}


	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitPrimaryStage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPrimaryStage(Stage primaryStage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void FinishLevel(String LevelName) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getStep() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return null;
	}




}
