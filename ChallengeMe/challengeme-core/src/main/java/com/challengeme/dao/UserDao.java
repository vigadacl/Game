package com.challengeme.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.challengeme.config.HibernateConfigurator;
import com.challengeme.config.TransaktionContainer;
import com.challengeme.entities.User;

public class UserDao {

	public void insert(String email, String name, String password) {
		Transaction tx = TransaktionContainer.getTransaktion();
		Session session = HibernateConfigurator.getInstance().getSession();
		try {
			User reg = new User();
			reg.setEmail(email);
			reg.setName(name);
			reg.setPassword(password);
			session.save(reg);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} 

	}
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(){
		Session session = HibernateConfigurator.getInstance().getSession();
		session.flush();
		Transaction tx = TransaktionContainer.getTransaktion();
		try {
			List<User> users=session.createQuery(" select u from User u ").list();
			tx.commit();
			return users;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} 
		return null;
	}
	public void insert(User employee) {
		Session session = HibernateConfigurator.getInstance().getSession();
		Transaction tx = TransaktionContainer.getTransaktion();
		try {
			session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null){
				tx.rollback();
				session.evict(employee);
			}
			e.printStackTrace();
		} 
		
	}

}
