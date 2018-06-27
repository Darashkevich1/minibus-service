package minibus.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import minibus.entities.Company;

@Repository
@Transactional
public final class TaxiCompanyDAO implements CompanyDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Company getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Company.class);
		cr.add(Restrictions.eq("id", id));
		return (Company) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getByOwnerId(int ownerId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Company.class);
		cr.add(Restrictions.eq("ownerId", ownerId));
		return cr.list();
	}

	@Override
	public void addCompany(Company company) {
		sessionFactory.getCurrentSession().save(company);
	}

	@Override
	public void removeCompany(Company company) {
		sessionFactory.getCurrentSession().remove(company);
	}

}
