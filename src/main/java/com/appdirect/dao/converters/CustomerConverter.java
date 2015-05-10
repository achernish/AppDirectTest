package com.appdirect.dao.converters;

import com.appdirect.dao.repositories.entities.Customer;
import com.appdirect.dto.CustomerDTO;

/**
 * @author Anatoly Chernysh
 */
public class CustomerConverter {

    public CustomerDTO convert(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setOpenId(customer.getOpenId());
        customerDTO.setEdition(customer.getEdition());
        return customerDTO;
    }
}
