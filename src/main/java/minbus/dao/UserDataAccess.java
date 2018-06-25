package minbus.dao;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minibus.entities.User;

@Repository(value = "userDataAccess")
@Transactional
public class UserDataAccess implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		return user;
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public void removeUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(user);
	}
}
