package model.data.generalObjects;

import java.io.Serializable;

public class Target extends GeneralObjects implements Serializable {

	private static final long serialVersionUID = 1L;
	public Target(int x, int y) {
		super(x,y);
	}
	public String getObject(){
		return ("Target");
	}
	public Target() {

	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
