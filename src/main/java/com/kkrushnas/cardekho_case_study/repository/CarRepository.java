package com.kkrushnas.cardekho_case_study.repository;

import java.lang.annotation.Target;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kkrushnas.cardekho_case_study.pojo.CarPOJO;

@Repository
public class CarRepository {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;

	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("mvc");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}

	private static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction != null) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public List<CarPOJO> findAllCars() {

		openConnection();
		transaction.begin();

		String jpql = "from CarPOJO";
		query = manager.createQuery(jpql);
		List<CarPOJO> cars = query.getResultList();

		transaction.commit();
		closeConnection();
		return cars;
	}

	public CarPOJO addCar(String name, String email, long contact, String address) {
		openConnection();
		transaction.begin();

		CarPOJO pojo = new CarPOJO();
		pojo.setName(name);
		pojo.setEmail(email);
		pojo.setContact(contact);
		pojo.setAddress(address);

		manager.persist(pojo);

		transaction.commit();
		closeConnection();
		return pojo;
	}

	public CarPOJO searchCar(int id) {
		openConnection();
		transaction.begin();
		
		CarPOJO pojo = manager.find(CarPOJO.class, id);

		transaction.commit();
		closeConnection();
		return pojo;
	}

}
