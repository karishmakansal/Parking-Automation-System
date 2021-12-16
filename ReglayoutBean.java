package Registrationlayout;

public class ReglayoutBean {
String Mobile;
String Name;
String Address;
String City;
String Pic;
public ReglayoutBean(){}
public ReglayoutBean(String mobile, String name, String address, String city, String pic) {
	super();
	this.Mobile = mobile;
	this.Name = name;
	this.Address = address;
	this.City = city;
	this.Pic = pic;
}
public String getMobile() {
	return Mobile;
}
public void setMobile(String mobile) {
	Mobile = mobile;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getCity() {
	return City;
}
public void setCity(String city) {
	City = city;
}
public String getPic() {
	return Pic;
}
public void setPic(String pic) {
	Pic = pic;
}

}
