package view;

import java.net.Socket;
import java.util.List;

import controller.MyController;
import controller.server.MyClientHandler;
import entities.SokobanDBManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;



public class Main extends Application {
	private SokobanDBManager manager;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();

			MyModel m=new MyModel();
			MainWindowController v=(MainWindowController)loader.getController();
			MyController theController;

			manager=new SokobanDBManager();
			theController= new MyController(v, m,manager);

			m.addObserver(theController);
			v.addObserver(theController);
			manager.addObserver(theController);
			v.setPrimaryStage(primaryStage);

			primaryStage.setTitle("Sokoban Game");
			Scene scene = new Scene(root,900,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}
