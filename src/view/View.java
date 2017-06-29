package view;

import java.net.Socket;

import javafx.stage.Stage;
import model.Level;

public interface View{

 	public void displayData(Level level, String command);

	public void exit();

	public void FinishLevel(String LevelName);

	public void stop();

	public void exitPrimaryStage();

	public void setPrimaryStage(Stage primaryStage);

	public int getStep();

	public int getTime();

	public String getPlayerName();

	void solve();

	public String getStartplayerName();
}
