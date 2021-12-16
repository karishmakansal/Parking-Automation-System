package entrytable;

public class entryBean {

	String CustomerType;
	String mobileno;
	String Vehicleno;
	String type;
	int availability;
	String date;
	String time;
	public entryBean(){}
	public entryBean(String customerType, String mobileno, String vehicleno, String type, int availability, String date,
			String time) {
		super();
		CustomerType = customerType;
		this.mobileno = mobileno;
		Vehicleno = vehicleno;
		this.type = type;
		this.availability = availability;
		this.date = date;
		this.time = time;
	}
	public String getCustomerType() {
		return CustomerType;
	}
	public void setCustomerType(String customerType) {
		CustomerType = customerType;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getVehicleno() {
		return Vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		Vehicleno = vehicleno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
