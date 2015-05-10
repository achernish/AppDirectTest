package com.appdirect.integration.model;

import lombok.Getter;
import org.w3c.dom.Document;

/**
 * @author Anatoly Chernysh
 */
@Getter
public class SubscriptionCancelEvent {

    private Integer id;

    public SubscriptionCancelEvent(Document eventXmlDocument) {
        try {
            this.id = Integer.parseInt(eventXmlDocument.getElementsByTagName("accountIdentifier").item(0).getTextContent());
        } catch (NumberFormatException nfe) {
            this.id = 0;
        }
    }
}