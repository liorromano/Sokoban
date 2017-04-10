package model.data.generalObjects;

import java.io.Serializable;

public class Wall extends GeneralObjects implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Wall(int x, int y) {
		super(x,y);
	}
public String getObject(){
	return ("Wall");
}
public Wall() {
	
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
}
