package foodcpu.shared;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataUser   {

	@Id Long id;
	String username;
	String password;
	
	public DataUser()
	{
	}
	
	public DataUser (String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
