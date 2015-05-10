package com.appdirect.integration.model;

import lombok.Getter;
import org.w3c.dom.Document;

/**
 * @author Anatoly Chernysh
 */
@Getter
public class SubscriptionChangeEvent {

    private Integer id;

    private String edition;

    public SubscriptionChangeEvent(Document eventXmlDocument) {
        try {
            this.id = Integer.parseInt(eventXmlDocument.getElementsByTagName("accountIdentifier").item(0).getTextContent());
        } catch (NumberFormatException nfe) {
            this.id = 0;
        }
        this.edition = eventXmlDocument.getElementsByTagName("editionCode").item(0).getTextContent();
    }
}