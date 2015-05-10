package com.appdirect.dao.repositories.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Anatoly Chernysh
 */
@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue()
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String openId;

    @Getter
    @Setter
    private String edition;
}
