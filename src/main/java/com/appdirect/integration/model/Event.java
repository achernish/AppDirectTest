package com.appdirect.integration.model;

import com.appdirect.integration.remote.type.EventType;
import lombok.Getter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Anatoly Chernysh
 */
@Getter
public class Event {

    private Document eventXmlDocument;

    private EventType eventType;

    public Event(String eventXml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        this.eventXmlDocument = docBuilder.parse(new ByteArrayInputStream(eventXml.getBytes()));
        this.eventType = EventType.valueOf(this.eventXmlDocument.getElementsByTagName("type").item(0).getTextContent());
    }
}