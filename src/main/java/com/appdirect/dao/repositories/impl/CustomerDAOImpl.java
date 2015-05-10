package com.appdirect.dao.repositories.impl;

import com.appdirect.dao.repositories.CustomerDAO;
import com.appdirect.dao.repositories.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anatoly Chernysh
 */
@Repository
@Slf4j
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(customer);
        log.info("Customer saved successfully, Customer Details = " + customer);
    }

    @Override
    public void updateCustomer(Customer c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        log.info("Customer updated successfully, Customer Details = " + c);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> listCustomers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Customer> customers = session.createQuery("from Customer").list();
        for (Customer customer : customers) {
            log.info("Customer List::" + customer);
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Customer c = (Customer) session.get(Customer.class, new Integer(id));
        log.info("Customer loaded successfully, Customer Details=" + c);
        return c;
    }

    @Override
    public Customer getCustomerByOpenId(String openId) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Customer> customers = session.createQuery("from Customer where openId = :openId")
                .setString("openId", openId).list();
        if (customers.isEmpty()) {
            return null;
        }
        log.info("Customer loaded successfully, Customer Details = " + customers.get(0));
        return customers.get(0);
    }

    @Override
    public void removeCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Customer customer = (Customer) session.get(Customer.class, new Integer(id));
        if (customer != null) {
            session.delete(customer);
        }
        log.info("Customer deleted successfully, Customer Details = " + customer);
    }
}