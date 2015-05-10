package com.appdirect.dao.repositories;

import com.appdirect.dao.repositories.entities.Customer;

import java.util.List;

/**
 * @author Anatoly Chernysh
 */
public interface CustomerDAO {

    public void addCustomer(Customer c);

    public void updateCustomer(Customer c);

    public List<Customer> listCustomers();

    public Customer getCustomerById(int id);

    public Customer getCustomerByOpenId(String openID);

    public void removeCustomer(int id);
}
