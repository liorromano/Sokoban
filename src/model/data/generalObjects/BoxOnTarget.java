package model.data.generalObjects;

import java.io.Serializable;

public class BoxOnTarget extends GeneralObjects implements Serializable {

	private static final long serialVersionUID = 1L;

public BoxOnTarget(int x, int y) {
		super(x,y);
	}

public String getObject(){
	return ("BoxOnTarget");
}
public BoxOnTarget() {
	
}
public static long getSerialversionuid() {
	return serialVersionUID;
}

}

