package minibus.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import minibus.UserRole;

@Entity
public final class AdminUser implements User {
	
	public AdminUser() {
		setRole(UserRole.Admin);
	}
	
	@Id
	@GeneratedValue
	int id;
	
	private String fistName, lastName, address, email, phone;
	private UserRole role;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getFirstName() {
		return fistName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public UserRole getRole() {
		return role;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setFirstName(String firstName) {
		this.fistName = firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setRole(UserRole userRole) {
		this.role = userRole;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
