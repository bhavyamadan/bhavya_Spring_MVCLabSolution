package com.greatlearning.customerManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.customerManagement.model.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {
	
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		try {
			session=sessionFactory.getCurrentSession();
		}catch (HibernateException e) {
			session=sessionFactory.openSession(); 
		}
	}

	@Override
	public List<Customer> findAll() {
		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer").list();
		tx.commit();
		return customers;
	}

	@Transactional
	public Customer findById(int id) {
		Customer customer = new Customer();
		Transaction tx = session.beginTransaction();
		customer= session.get(Customer.class, id);
		tx.commit();
		return customer;
	}

	@Transactional
	public void save(Customer customer) {
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(customer);

		tx.commit();

		
	}

	@Transactional
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer customer = session.get(Customer.class, id);

		// delete record
		session.delete(customer);

		tx.commit();
		
	}

}
