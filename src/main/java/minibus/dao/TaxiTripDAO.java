package minibus.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minibus.entities.Trip;


@Repository
@Transactional
public final class TaxiTripDAO implements TripDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> getByDepartureArrival(String departure, String arrival) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Trip.class);
		cr.add(Restrictions.eq("departurePoint", departure));
		cr.add(Restrictions.eq("arrivalPoint", arrival));
		return cr.list();
	}

	@Override
	public Trip getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Trip.class);
		cr.add(Restrictions.eq("id", id));
		return (Trip) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> getByCarId(int carId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Trip.class);
		cr.add(Restrictions.eq("carId", carId));
		return cr.list();
	}

	@Override
	public void addTrip(Trip trip) {
		sessionFactory.getCurrentSession().save(trip);
	}

	@Override
	public void removeTrip(Trip trip) {
		sessionFactory.getCurrentSession().remove(trip);
	}

}
