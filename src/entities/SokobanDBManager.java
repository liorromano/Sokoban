package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


 public class SokobanDBManager extends Observable implements DBmanager{
	 private static SessionFactory factory;
	 private Configuration configuration;
	private Socket socket;
	private ObjectOutputStream outToServer;
	 /**
	  * This is the c'tor of SokobanDBManager.
	  */
	 public SokobanDBManager() {
		 Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}

		/**
		 * This function checks if there is already a score in the data base.
		 * If there is, it checks if we need to save a new score or not.
		 */
		public void checkIfsave(String name,String levelName, int step, int time) {
			//send to server the parameters

			DbBin db=new DbBin();
			db.setName(name);
 			db.setLevelName(levelName);
 			db.setStep(step);
 			db.setTime(time);
			try {
				this.socket=new Socket("localhost",5555);
				outToServer = new ObjectOutputStream(socket.getOutputStream());
				outToServer.flush();
				outToServer.writeObject(db);
				outToServer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

 }
