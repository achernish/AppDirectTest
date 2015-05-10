package com.appdirect.dao.service.impl;

import com.appdirect.dao.repositories.CustomerDAO;
import com.appdirect.dao.repositories.entities.Customer;
import com.appdirect.dao.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Anatoly Chernysh
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public void addCustomer(Customer c) {
        this.customerDAO.addCustomer(c);
    }

    @Override
    @Transactional
    public void updateCustomer(Customer c) {
        this.customerDAO.updateCustomer(c);
    }

    @Override
    @Transactional
    public List<Customer> listCustomers() {
        return this.customerDAO.listCustomers();
    }

    @Override
    @Transactional
    public Customer getCustomerById(int id) {
        return this.customerDAO.getCustomerById(id);
    }

    @Override
    public Customer getCustomerByOpenId(String openId) {
        return this.customerDAO.getCustomerByOpenId(openId);
    }

    @Override
    @Transactional
    public void removeCustomer(int id) {
        this.customerDAO.removeCustomer(id);
    }
}