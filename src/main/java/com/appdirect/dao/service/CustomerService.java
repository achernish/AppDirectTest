package com.appdirect.dao.service;

import com.appdirect.dao.repositories.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anatoly Chernysh
 */
@Service
public interface CustomerService {

    public void addCustomer(Customer c);

    public void updateCustomer(Customer c);

    public List<Customer> listCustomers();

    public Customer getCustomerById(int id);

    public Customer getCustomerByOpenId(String openId);

    public void removeCustomer(int id);

}