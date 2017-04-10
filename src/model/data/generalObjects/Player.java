package model.data.generalObjects;

import java.io.Serializable;

public class Player extends GeneralObjects implements Serializable {

	private static final long serialVersionUID = 1L;

public Player(int x, int y) {
		super(x,y);
	}

public String getObject(){
	return ("Player");
}
public Player() {
	super();
}
public static long getSerialversionuid() {
	return serialVersionUID;
}

}