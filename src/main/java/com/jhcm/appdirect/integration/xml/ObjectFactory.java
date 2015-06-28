
package com.jhcm.appdirect.integration.xml;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jhcm.appdirect.integration.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jhcm.appdirect.integration.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link Event.Payload }
     * 
     */
    public Event.Payload createEventPayload() {
        return new Event.Payload();
    }

    /**
     * Create an instance of {@link Event.Payload.User }
     * 
     */
    public Event.Payload.User createEventPayloadUser() {
        return new Event.Payload.User();
    }

    /**
     * Create an instance of {@link Event.Payload.User.Attributes }
     * 
     */
    public Event.Payload.User.Attributes createEventPayloadUserAttributes() {
        return new Event.Payload.User.Attributes();
    }

    /**
     * Create an instance of {@link Event.Marketplace }
     * 
     */
    public Event.Marketplace createEventMarketplace() {
        return new Event.Marketplace();
    }

    /**
     * Create an instance of {@link Event.Creator }
     * 
     */
    public Event.Creator createEventCreator() {
        return new Event.Creator();
    }

    /**
     * Create an instance of {@link Event.Payload.Account }
     * 
     */
    public Event.Payload.Account createEventPayloadAccount() {
        return new Event.Payload.Account();
    }

    /**
     * Create an instance of {@link Event.Payload.User.Attributes.Entry }
     * 
     */
    public Event.Payload.User.Attributes.Entry createEventPayloadUserAttributesEntry() {
        return new Event.Payload.User.Attributes.Entry();
    }

}
