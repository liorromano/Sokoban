package model.data.generalObjects;

import java.io.Serializable;

public abstract class GeneralObjects implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Point point;

public GeneralObjects(int x,int y) {
	this.point = new Point(x, y);	
}
public Point getPoint() {
	return point;
}public void setPoint(Point point) {
	this.point = point;
}
public GeneralObjects() {
	
}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
