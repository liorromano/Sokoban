package model.data.generalObjects;

import java.io.Serializable;

public class PlayerOnTarget extends GeneralObjects implements Serializable {

	private static final long serialVersionUID = 1L;

	public PlayerOnTarget(int x, int y) {
		super(x,y);
	}

	public String getObject(){
		return ("PlayerOnTarget");
	}
	public PlayerOnTarget() {
		
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}