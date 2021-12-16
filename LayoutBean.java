package Tablelayout;
public class LayoutBean {

String floor;
int capacity;
String type;
int occupied;
public LayoutBean(){}
public LayoutBean(String floor, int capacity, String type, int occupied) {
	super();
	this.floor = floor;
	this.capacity = capacity;
	this.type = type;
	this.occupied = occupied;
}
public String getFloor() {
	return floor;
}
public void setFloor(String floor) {
	this.floor = floor;
}
public int getCapacity() {
	return capacity;
}
public void setCapacity(int capacity) {
	this.capacity = capacity;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getOccupied() {
	return occupied;
}
public void setOccupied(int occupied) {
	this.occupied = occupied;
}


}
