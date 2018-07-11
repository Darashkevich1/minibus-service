package minibus.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minibus.entities.Car;

@Repository
@Transactional
public final class TaxiCarDAO implements CarDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Car getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Car.class);
		cr.add(Restrictions.eq("id", id));
		return (Car) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getByDriverId(int driverId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Car.class);
		cr.add(Restrictions.eq("driverId", driverId));
		return cr.list();
	}

	@Override
	public Car getByRegisterNumber(String registerNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Car.class);
		cr.add(Restrictions.eq("registerNumber", registerNumber));
		return (Car) cr.uniqueResult();
	}

	@Override
	public void addCar(Car car) {
		sessionFactory.getCurrentSession().save(car);
	}

	@Override
	public void removeCar(Car car) {
		sessionFactory.getCurrentSession().remove(car);
	}

}
