package entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

 @Entity(name="Scores")
 public class ScoresManager implements Serializable {


 	private static final long serialVersionUID = 1L;
 	@Id
 	@Column(name="FullName")
 	private String fullname;
 	@Id
 	@Column(name="LevelName")
 	private String levelname;
 	@Column(name="Times")
 	private int time;
 	@Column(name="Steps")
 	private int steps;

 	public ScoresManager() {
		// TODO Auto-generated constructor stub
	}




 	public String getFullname() {
		return fullname;
	}




	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

public String getLevelname() {
	return levelname;
}
public void setLevelname(String levelname) {
	this.levelname = levelname;
}



	public int getTime() {
 		return time;
 	}


 	public void setTime(int time) {
 		this.time = time;
 	}



 	public int getSteps() {
 		return steps;
 	}



 	public void setSteps(int steps) {
 		this.steps = steps;
 	}

/**
 * This is the c'tor for the ScoresManager.
 * @param username- this is an input of the user name.
 * @param levelname-the level name to be saved.
 * @param time-the time that the user finished the level.
 * @param steps-counter of how much steps it takes the user to finish the level.
 */
 	public ScoresManager(String username , String levelname ,int time , int steps)
 	{
 		this.fullname = username;
 		this.levelname =levelname;
 		this.time=time;
 		this.steps=steps;
 	}

 	/**
 	 * This function override the "toString" function.
 	 */
 	@Override
 	public String toString() {
 	return "Scores [UserName=" + fullname + "LevelName=" + levelname + "Steps="+steps +"Time="+time + "]";
 	}



 }
