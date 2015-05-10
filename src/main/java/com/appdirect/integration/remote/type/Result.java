package com.appdirect.integration.remote.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Anatoly Chernysh
 */
@XmlRootElement(name = "result")
@Data
@NoArgsConstructor
public class Result implements Serializable {

    private boolean success;

    private ErrorCode errorCode;

    private String message;

    private String accountIdentifier;

    public Result(boolean success, ErrorCode errorCode, String message) {
        super();
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }

    public Result(boolean success, String message) {
        this(success, null, message);
    }
}