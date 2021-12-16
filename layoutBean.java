package layouttable;

public class layoutBean {

	String floor;
	int capacity;
	String type;
	int Occupied;
	int Amount;
	public layoutBean()
	{}
	public layoutBean(String floor, int capacity, String type, int occupied, int amount) {
		super();
		this.floor = floor;
		this.capacity = capacity;
		this.type = type;
		Occupied = occupied;
		Amount = amount;
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
		return Occupied;
	}
	public void setOccupied(int occupied) {
		Occupied = occupied;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	
	
}
