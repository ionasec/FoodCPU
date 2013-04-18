package foodcpu.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataDrink {

	@Id Long id;
	String name;
	String quantity;
	String location;
	String price;
	
	public DataDrink(String name, String quantity, String location, String price)
	{
		this.name = name;
		this.quantity = quantity;
		this.location = location;
		this.price = price;
	}
}
