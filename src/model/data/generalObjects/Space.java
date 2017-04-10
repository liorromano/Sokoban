package model.data.generalObjects;

import java.io.Serializable;

public class Space extends GeneralObjects implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Space(int x, int y) {
		super(x,y);
	}
	public String getObject(){
		return ("Space");
	}
	public Space() {
		
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
