package foodcpu.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataLocation {

	@Id Long id;
	String username;
	String password;
	
}
