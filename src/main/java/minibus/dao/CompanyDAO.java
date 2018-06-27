package minibus.dao;

import java.util.List;

import minibus.entities.Company;

public interface CompanyDAO {
	Company getById(int id);
	List<Company> getByOwnerId(int ownerId);
	
	void addCompany(Company company);
	void removeCompany(Company company);
}
