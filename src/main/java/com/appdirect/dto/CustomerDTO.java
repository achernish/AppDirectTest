package com.appdirect.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Anatoly Chernysh
 */
@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String openId;

    private String edition;

}
