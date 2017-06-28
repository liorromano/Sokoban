package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

 @Entity(name="Levels")
 public class LevelsManager {

public LevelsManager() {
	// TODO Auto-generated constructor stub
}

 	@Id
 	@Column(name="LevelName")
 	private String levelname;


 	public String getLevelname() {
 		return levelname;
 	}


 	public void setLevelname(String levelname) {
 		this.levelname = levelname;
 	}


 	public LevelsManager(String levelname) {
 		this.levelname = levelname;

 	}
 	/**
 	 * This function override the "toString" function.
 	 */
 	@Override
 	public String toString() {
 	return "Levels [levelName=" + levelname+"]";
 	}




 }
