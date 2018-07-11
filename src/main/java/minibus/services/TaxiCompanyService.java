package minibus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minibus.UserRole;
import minibus.dao.CompanyDAO;
import minibus.dao.TokenDAO;
import minibus.dao.UserDAO;
import minibus.entities.Company;
import minibus.entities.TaxiCompany;
import minibus.entities.Token;
import minibus.entities.User;

@Service
public final class TaxiCompanyService implements CompanyService {
	
	@Autowired
	CompanyDAO taxiCompanyDAO;
	
	@Autowired
	UserDAO userDataAcceess;
	
	@Autowired
	TokenDAO accessTokenDAO;
	
	@Override
	public Company addCompany(String name, int ownerId, String accessToken) {
		User user = userDataAcceess.getById(ownerId);
		Token token = accessTokenDAO.getByToken(accessToken);
		if(user != null && token != null) {
			User user_by_token = userDataAcceess.getById(token.getUserId());
			if(user_by_token != null && (user_by_token.getRole() == UserRole.Admin && user.getRole() == UserRole.Leader || 
					token.getUserId() == user.getId() && user_by_token.getRole() == UserRole.Leader)) {
				Company c = new TaxiCompany();
				c.setName(name);
				c.setOwnerId(ownerId);
				taxiCompanyDAO.addCompany(c);
				List<Company> companies = taxiCompanyDAO.getByOwnerId(ownerId);
				return companies.get(companies.size()-1);
			}
		}
		return null;
	}

	@Override
	public boolean deleteCompany(int id, String accessToken) {
		Token token = accessTokenDAO.getByToken(accessToken);
		Company company = taxiCompanyDAO.getById(id);
		if(token != null && company != null) {
			User user_by_token = userDataAcceess.getById(token.getUserId());
			if(user_by_token != null && (user_by_token.getRole() == UserRole.Admin || company.getOwnerId() == user_by_token.getId())) {
				taxiCompanyDAO.removeCompany(company);
				return true;
			}
		}
		return false;
	}

}
