package com.appdirect.integration.model;

import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Anatoly Chernysh
 */
@Getter
public class SubscriptionOrderEvent {

    private String openId;

    private String firstName;

    private String lastName;

    private String email;

    private String edition;

    public SubscriptionOrderEvent(Document eventXmlDocument) {
        NodeList creatorNode = eventXmlDocument.getElementsByTagName("creator");
        for (int i = 0; i < creatorNode.getLength(); i++) {
            for (int j = 0; j < creatorNode.item(i).getChildNodes().getLength(); j++) {
                Node xmlNode = creatorNode.item(i).getChildNodes().item(j);
                String nodeName = xmlNode.getNodeName();
                if (nodeName.equals("email")) {
                    this.email = xmlNode.getTextContent();
                } else if (nodeName.equals("firstName")) {
                    this.firstName = xmlNode.getTextContent();
                } else if (nodeName.equals("lastName")) {
                    this.lastName = xmlNode.getTextContent();
                }
            }
        }

        this.openId = eventXmlDocument.getElementsByTagName("openId").item(0).getTextContent();
        this.edition = eventXmlDocument.getElementsByTagName("editionCode").item(0).getTextContent();
    }
}
