package foodcpu.shared;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataDrink implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id Long id;
	String name;
	String quantity;
	String location;
	String price;
	String username;
	Date date;
	
	public DataDrink()
	{
		
	}
	public DataDrink(String name, String quantity, String location, String price)
	{
		this.name = name;
		this.quantity = quantity;
		this.location = location;
		this.price = price;
		this.date = new Date(System.currentTimeMillis());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDate() {
	if(this.date !=null)
		return this.date;
	else 
		return new Date(0);
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
	}
	public void setCurrentDate() {
		this.date = new Date(System.currentTimeMillis());// TODO Auto-generated method stub
		System.out.println(date.toString());
	}
}
