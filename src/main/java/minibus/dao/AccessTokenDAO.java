package minibus.dao;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minibus.entities.Token;

@Repository
@Transactional
public final class AccessTokenDAO implements TokenDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Token getByToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Token.class);
		cr.add(Restrictions.eq("accessToken", token));
		return (Token) cr.uniqueResult();
	}

	@Override
	public Token getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Token.class);
		cr.add(Restrictions.eq("id", id));
		return (Token) cr.uniqueResult();
	}

	@Override
	public void addToken(Token token) {
		sessionFactory.getCurrentSession().save(token);
	}

	@Override
	public void removeToken(Token token) {
		sessionFactory.getCurrentSession().remove(token);
	}

	@Override
	public Token getByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Token.class);
		cr.add(Restrictions.eq("userId", userId));
		return (Token) cr.uniqueResult();
	}

}
