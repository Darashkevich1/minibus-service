package minibus.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class TaxiCompany implements Company {

	@Id
	@GeneratedValue
	private int id;
	
	private int ownerId;
	private String name;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getOwnerId() {
		return ownerId;
	}

	@Override
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
