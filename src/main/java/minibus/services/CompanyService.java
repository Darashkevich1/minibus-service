package minibus.services;

import minibus.entities.Company;

public interface CompanyService {
	Company addCompany(String name, int ownerId, String accessToken);
	boolean deleteCompany(int id, String accessToken);
}
