package model.data.generalObjects;

import java.io.Serializable;

public class Box extends GeneralObjects implements Serializable {

	private static final long serialVersionUID = 1L;

public Box(int x, int y) {
		super(x,y);
	}

public String getObject(){
	return ("Box");
}
public Box() {
	
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
}
