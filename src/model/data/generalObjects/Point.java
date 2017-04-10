package model.data.generalObjects;

import java.io.Serializable;

public class Point implements Serializable {

private static final long serialVersionUID = 1L;
private int x;
private int y;



public Point (int x, int y){
	this.setX(x);
	this.setY(y);
}
public Point() {

}
public int getX() {
	return x;
}public int getY() {
	return y;
}public void setX(int x) {
	this.x = x;
}public void setY(int y) {
	this.y = y;
}
@Override
public int hashCode()
{
    return 31 * x + y;
}

@Override
public boolean equals(final Object o)
{
    // No object instance is equal to null
    if (o == null)
        return false;
    // If the object is the same, this is true
    if (this == o)
        return true;
    // If not the same object class, false
    if (getClass() != o.getClass())
        return false;

    final Point other = (Point) o; // safe to cast since getClass() == o.getClass()
    return x == other.x && y == other.y; // test instance member equality
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

}
