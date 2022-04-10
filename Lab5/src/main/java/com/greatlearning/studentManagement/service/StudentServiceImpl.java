package com.greatlearning.studentManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.studentManagement.model.Student;

@Repository
public class StudentServiceImpl implements StudentService {
	
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		try {
			session=sessionFactory.getCurrentSession();
		}catch (HibernateException e) {
			session=sessionFactory.openSession(); 
		}
	}

	@Override
	public List<Student> findAll() {
		Transaction tx = session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();
		tx.commit();
		return students;
	}

	@Transactional
	public Student findById(int id) {
		Student student = new Student();
		Transaction tx = session.beginTransaction();
		student = session.get(Student.class, id);
		tx.commit();
		return student;
	}

	@Transactional
	public void save(Student student) {
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(student);

		tx.commit();

		
	}

	@Transactional
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();

		// get transaction
		Student student = session.get(Student.class, id);

		// delete record
		session.delete(student);

		tx.commit();
		
	}

}
