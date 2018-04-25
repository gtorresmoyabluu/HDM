/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.rest.dao.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gonzalo Torres
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements IGenericDAO<T, ID> {

    private Class<T> clazz;
    @Autowired
    private SessionFactory sessionFactory;

    public GenericDAOImpl(Class<T> persistenceClass) {
	this.clazz = persistenceClass;
    }

    public Class<T> getClazz() {
	return clazz;
    }

    public void setClazz(Class<T> clazz) {
	this.clazz = clazz;
    }

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public static <T> List<T> castGenerics(Class<? extends T> clazz, Collection<?> collections) {
	List<T> list = new ArrayList<T>(collections.size());
	for (Object object : collections) {
	    list.add(clazz.cast(object));
	}
	return list;
    }

    @Override
    @Transactional(readOnly = true)
    public T save(T entity) {
	if (entity != null) {
	    getSessionFactory()
		    .getCurrentSession()
		    .persist(entity);
	    return entity;
	}
	return null;
    }

    @Override
    public Optional<T> findById(ID id) {
	Object object = getSessionFactory()
		.getCurrentSession()
		.get(getClazz(), id);

	if (getClazz().isInstance(object)) {
	    T entity = getClazz().cast(object);
	    return Optional.of(entity);
	}

	return null;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean deleteById(ID id) {
	try {
	    Object object = getSessionFactory().getCurrentSession().get(getClazz(), id);

	    if (getClazz().isInstance(object)) {
		T entity = getClazz().cast(object);
		getSessionFactory().getCurrentSession().delete(entity);
		return true;
	    }
	} catch (HibernateException e) {
	    e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean isExist(ID id) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
