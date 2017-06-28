package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import entities.HibernateUtil;
import entities.ScoresManager;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Level;
import view.commands.exitTypes.RegularExit;
import org.hibernate.query.Query;
import view.View;

public class MainWindowController extends Observable implements View, Initializable {

	@FXML
	Displayer SokobanDisplayer;
	@FXML
	Text steps;
	@FXML
	Text timer;

	private boolean resetTimerFlag = true; // reset the time flag
	private int count = 0; // time count
	private StringProperty counter; // timer string
	Timer timerThread = null; // timer
	MediaPlayer mediaPlayer = null; // media player
	private Stage primaryStage;
	private int time = 0;
	private int step = 0;
	private String playerName;
	private String levelName;
	private String searchText;
	public MainWindowController() {
		backgroundMusic();

	}

/**
 * This function is for the background music.
 */
	public static void backgroundMusic() {
		// start the background music
		{
			MediaPlayer mediaPlayer = null; // media player
			String musicFile = "./resources/music.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.play();
		}

	}
/**
 * This function initialize the gui.
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		counter = new SimpleStringProperty();

		timerThread = new Timer();
		timerThread.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				counter.set("Timer: " + (count++));
			}
		}, 0, 1000);

		SokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> SokobanDisplayer.requestFocus());
		SokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.UP)
					notifyCommand("move up");
				if (event.getCode() == KeyCode.RIGHT)
					notifyCommand("move right");
				if (event.getCode() == KeyCode.LEFT)
					notifyCommand("move left");
				if (event.getCode() == KeyCode.DOWN)
					notifyCommand("move down");
				SokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> SokobanDisplayer.requestFocus());
			}
		});
	}


	public void start() {

		String command = "display";
		LinkedList<String> params = new LinkedList<String>();
		params.add(command);

		this.setChanged();
		this.notifyObservers(params);

	}
/**
 * This function save a level.
 */
	public void saveFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Save SokoBan's Level File");
		fc.setInitialDirectory(new File("./resources/levels"));
		File chosen = fc.showSaveDialog(null);
		if (chosen != null) {
			notifyCommand("save " + chosen.getPath());
		}
	}
/**
 * This function opens file.
 */
	public void openFile() {

		FileChooser fc = new FileChooser();
		fc.setTitle("Open SokoBan's Level File");
		fc.setInitialDirectory(new File("./resources/levels"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text files", "*.txt"),
				new ExtensionFilter("XML files", "*.XML"), new ExtensionFilter("Object files", "*.obj"));
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			resetTimerFlag = true;
			// resetTimer();
			notifyCommand("load " + chosen.getPath());
		}
	}
/**
 * This function notify observers.
 * @param commandLine- the command to notify.
 */
	private void notifyCommand(String commandLine) {
		String[] arr = commandLine.split(" ", 2);
		List<String> params = new LinkedList<String>();

		for (String s : arr) {
			params.add(s);
		}

		setChanged();
		notifyObservers(params);
	}
/**
 * This function displays level.
 */
	public void displayData(Level level, String command) {

		if (resetTimerFlag == true) {
			this.resetTimer();
			resetTimerFlag = false;
		}
		SokobanDisplayer.setSokobanData(level);
		levelName = level.getName();

		if (level.getCountBoxOnTarget() == level.getNumOfTargets()) {
			notifyCommand("finish");
		} else {
			setChanged();
			notifyObservers();
		}

		this.setStepsText("Steps: " + Integer.toString(level.getSteps()));

	}
/**
 * This function notify the command exit.
 */
	public void stop() {
		notifyCommand("exit");
	}

	/**
	 * This function closes the game.
	 */
	@Override
	public void exit() {

		timerThread.cancel();
		Platform.exit();
		RegularExit exit = new RegularExit();
		exit.exit();
	}

	/**
	 * This function resets the game timer.
	 */
	public void resetTimer() // reset the game timer
	{
		count = 0;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.textProperty().bind(counter);
		timer.setVisible(true);
	}

	private void setStepsText(String text) {
		steps.setText(text);
	}
/**
 * This function opens the finish level screen.
 */
	public void FinishLevel(String levelName) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Finish Level");
				alert.setHeaderText("You Finished the level");
				ButtonType buttonTypeOne = new ButtonType("Save Score");
				ButtonType buttonTypeTwo = new ButtonType("Close Window");
				try {

					ImageView finish = new ImageView(new Image(new FileInputStream("./resources/levelCompleted.png")));
					finish.setFitWidth(70);
					finish.setFitHeight(80);
					alert.setGraphic(finish);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				time = Integer.valueOf(getLast(timer.getText()));
				step = Integer.valueOf(getLast(steps.getText()));
				alert.setContentText("Time: " + time + "\n" + "Steps: " + step);
				alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne) {
					playerName = openNameDialog();
					System.out.println("time:" + time + " steps:" + step);
					System.out.println(playerName);
					notifyCommand("SaveToDataBase");

				} else if (result.get() == buttonTypeTwo) {

				}
			}
		});
	}

	@Override
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		exitPrimaryStage();
	}

	@Override
	public void exitPrimaryStage() {
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				notifyCommand("exit");
			}
		});
	}

	private String getLast(String cmd) {
		int i = cmd.indexOf(" ");
		if (i == (-1)) {
			return null;
		} else {
			String word = cmd.substring(i + 1, cmd.length());
			return word;
		}
	}

	private String openNameDialog() {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Look, a Text Input Dialog");
		dialog.setContentText("Please enter your name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();

		// The Java 8 way to get the response value (with lambda expression).
		return result.get();
	}

/**
 * This function shows the leader boards from db by level.
 */
	public void showLeaderBoardsBylevel() {


		TableView<ScoresManager> usertable=initiliazetable();

		usertable.setItems(getUsers(levelName));


		Stage table = new Stage();
		table.setTitle("Leaderboards By Current Level");
		VBox vBox = new VBox();
		vBox.getChildren().addAll(usertable);


		Scene scene = new Scene(vBox);
		table.setScene(scene);
		table.show();

		usertable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
				{
					if (usertable.getSelectionModel().getSelectedItem()!=null)
					{
						showPlayerTable(usertable.getSelectionModel().getSelectedItem());
						usertable.getSelectionModel().getSelectedItem().toString();
					}
				}
			}
		});

	}
/**
 * This function initialize the table.
 * @return- return the users table.
 */
	private TableView<ScoresManager> initiliazetable()
	{
		TableView<ScoresManager> usertable;

		TableColumn<ScoresManager, Integer> LevelNameColumn = new TableColumn<>("LevelName");
		LevelNameColumn.setMinWidth(100);
		LevelNameColumn.setCellValueFactory(new PropertyValueFactory<>("levelname"));

		TableColumn<ScoresManager, String> FirstNameColumn = new TableColumn<>("FullName");
		FirstNameColumn.setMinWidth(150);
		FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));

		TableColumn<ScoresManager, Integer> FinishTimeColumn = new TableColumn<>("Finish Time");
		FinishTimeColumn.setMinWidth(100);
		FinishTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

		TableColumn<ScoresManager, Integer> StepsColumn = new TableColumn<>("Steps");
		StepsColumn.setMinWidth(100);
		StepsColumn.setCellValueFactory(new PropertyValueFactory<>("Steps"));

		usertable = new TableView<>();

		usertable.getColumns().addAll(LevelNameColumn, FirstNameColumn, FinishTimeColumn, StepsColumn);
		usertable.getSortOrder().add(LevelNameColumn);
		usertable.getSortOrder().add(FinishTimeColumn);
		usertable.getSortOrder().add(StepsColumn);

		return usertable;
	}

	/**
	 * This function shows all scores from db.
	 * @param levelName- the name of the level that we want to see all the scores.
	 * @return- return a table.
	 */
	public ObservableList<ScoresManager> getUsers(String levelName) {
		ObservableList<ScoresManager> users = FXCollections.observableArrayList();
		if(levelName!=null)
		{
			Query<ScoresManager> query = HibernateUtil.getSessionFactory().openSession().createQuery("from Scores S where S.levelname='"+levelName+"'");
			query.setMaxResults(10);
			List<ScoresManager> list = query.list();
			if (!list.isEmpty()) {

				for (ScoresManager u : list) {
					users.add(u);
				}
				for (ScoresManager user : users)
					user.toString();
				return users;
			}
		}
				return users;
	}
/**
 * This function shows a specific scores for player.
 * @param selectedScore- the score that the user click on.
 * @return- return a table.
 */
	public ObservableList<ScoresManager> getUser(ScoresManager selectedScore) {

		ObservableList<ScoresManager> users = FXCollections.observableArrayList();
		Query<ScoresManager> query = HibernateUtil.getSessionFactory().openSession()
				.createQuery("from Scores S where S.fullname='" + selectedScore.getFullname() + "'");
		List<ScoresManager> list = query.list();

		for (ScoresManager u : list) {
			users.add(u);
		}
		for (ScoresManager user : users)
			user.toString();

		return users;
	}

	public void showPlayerTable(ScoresManager selectedScore) {
		if (selectedScore!=null)
		{
			TableView<ScoresManager> usertable=initiliazetable();
			usertable.setItems(getUser(selectedScore));

			Stage table = new Stage();
			table.setTitle("Player Stats");

			VBox vBox = new VBox();
			vBox.getChildren().addAll(usertable);

			Scene scene = new Scene(vBox);
			table.setScene(scene);
			table.show();
		}

	}
/**
 * This function shows all leader board from db.
 */
	public void showLeaderBoards() {

		TableView<ScoresManager> usertable=initiliazetable();
		usertable.setItems(getAllScores());
		ObservableList<ScoresManager> users = usertable.getItems();

		Stage table = new Stage();
		table.setTitle("Leaderboards");
		VBox vBox = new VBox();
		RadioButton check1=new RadioButton("Search by player");
		RadioButton check2=new RadioButton("Search by level");
		final ToggleGroup group = new ToggleGroup();

		check1.setToggleGroup(group);
		check1.setSelected(true);

		check2.setToggleGroup(group);

		vBox.getChildren().addAll(check1,check2);
		TextField search = new TextField("search");
		Button button=new Button("Submit");
		vBox.getChildren().addAll(search,button);
		vBox.getChildren().addAll(usertable);


		Scene scene = new Scene(vBox);
		table.setScene(scene);
		table.show();

		usertable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
				{
					if (usertable.getSelectionModel().getSelectedItem()!=null)
					{
						showPlayerTable(usertable.getSelectionModel().getSelectedItem());
						usertable.getSelectionModel().getSelectedItem().toString();
					}
				}
			}
		});
		button.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<ScoresManager> users2 = FXCollections.observableArrayList();
				searchText=search.getText();
				List<ScoresManager> list =users.subList(0, users.size());
				if (event.isPrimaryButtonDown())
				{
						if(group.getSelectedToggle()==check1)
						{
							for (ScoresManager u : list) {
								if(u.getFullname().equals(searchText))
								{
									users2.add(u);
								}
							}
						}

						else if(group.getSelectedToggle()==check2)
						{
							for (ScoresManager u : list) {
								if(u.getLevelname().equals(searchText))
								{
									users2.add(u);
								}
							}
						}
						usertable.setItems(users2);
						table.show();
					}
				}

			});
		}
/**
 * This function shows all scores.
 * @return-return a table of the scores.
 */
	public ObservableList<ScoresManager> getAllScores() {
		ObservableList<ScoresManager> users = FXCollections.observableArrayList();
			Query<ScoresManager> query = HibernateUtil.getSessionFactory().openSession().createQuery("from Scores");
			query.setMaxResults(10);
			List<ScoresManager> list = query.list();
			if (!list.isEmpty()) {

				for (ScoresManager u : list) {
					users.add(u);
				}
				for (ScoresManager user : users)
					user.toString();
				return users;
			}
			return users;
		}


	public int getStep() {
		return step;
	}

	public int getTime() {
		return time;
	}

	public String getPlayerName() {
		return playerName;
	}


}




