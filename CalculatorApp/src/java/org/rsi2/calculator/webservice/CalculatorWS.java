package org.rsi2.calculator.webservice;

import com.sun.xml.ws.api.SOAPVersion;
import com.sun.xml.ws.api.addressing.AddressingVersion;
import com.sun.xml.ws.api.addressing.WSEndpointReference;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.JAXWSProperties;
import com.sun.xml.ws.developer.WSBindingProvider;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.Addressing;
import org.rsi2.calculator.util.MailUtils;
import org.rsi2.calculator.util.SleepUtil;

/**
 * CalculatorWS
 */
@WebService(serviceName = "CalculatorService")
@Addressing(enabled = true, required = true)
public class CalculatorWS implements ICalculatorWS {
    
    /**
     * Logger logger
     */
    private static final Logger logger = Logger.getLogger(CalculatorWS.class.getName());
    
    
    /**
     * WebServiceContext
     */
    @Resource
    WebServiceContext context;

    /**
     * dodaj
     * @param a
     * @param b 
     */
    @Override
    @WebMethod
    @Oneway
    public void dodaj(double a, double b) {
        // log
        logger.log(Level.SEVERE, "dodaj start");

        // headerList
        HeaderList headerList = (HeaderList) context.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
 
        // replyTo
        WSEndpointReference replyTo = headerList.getReplyTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);
    
        // log
        logger.log(Level.SEVERE, "replyTo.getAddress: {0}", replyTo.getAddress());
        
        // to
        String to = headerList.getTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);

        // log
        logger.log(Level.SEVERE, "To: {0}", to);

        if (!replyTo.isNone()) {
            // log
            logger.log(Level.SEVERE, "rozpoczynam dodawanie liczb {0} i {1}.", new Object[]{a, b});

            // sleep between 3 and 7 sec
            SleepUtil.randomSleep();

            // obliczenie wyniku
            double result = a + b;

            // log
            logger.log(Level.SEVERE, "zakończyłem dodawnie liczb {0} i {1}.", new Object[]{a, b});

            // send email message
            if (to.contains("mailto")) {
                // log
                logger.log(Level.SEVERE, "wykryto adres email w polu reply.");
                
                // log
                logger.log(Level.SEVERE, "wysyłam email.");
    
                // send mail
                MailUtils.sendMail(to.substring(7), "wynik dodawania " + a + " i " + b + " wynosi " + result + " ");
            } else {
                // log
                logger.log(Level.SEVERE, "wysylam odpowiedz");

                // get messageId
                String messageId = headerList.getMessageID(AddressingVersion.W3C, SOAPVersion.SOAP_11);

                // create webservice client
                org.rsi2.calculator.clients.CallbackService callbackService = new org.rsi2.calculator.clients.CallbackService();
                org.rsi2.calculator.clients.CallbackWS callbackWS = callbackService.getCallbackWSPort();

                // bind address
                WSBindingProvider wSBindingProvider = (WSBindingProvider) callbackWS;
                wSBindingProvider.setAddress(replyTo.getAddress());
                wSBindingProvider.setOutboundHeaders(Headers.create(AddressingVersion.W3C.relatesToTag, messageId));

                // wywolanie metody w serwisie
                callbackWS.callback("wynik dodawania " + a + " i " + b + " wynosi " + result + " ");
            }
        } else {
            // log
            logger.log(Level.SEVERE, "Nie podano adresu zwrotnego.");
        }
        
        // log
        logger.log(Level.SEVERE, "dodaj stop");
    }

    /**
     * odejmij
     * @param a
     * @param b 
     */
    @Override
    @WebMethod
    @Oneway
    public void odejmij(double a, double b) {
        // log
        logger.log(Level.SEVERE, "odejmij start");

        // headerList
        HeaderList headerList = (HeaderList) context.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
 
        // replyTo
        WSEndpointReference replyTo = headerList.getReplyTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);
    
        // log
        logger.log(Level.SEVERE, "replyTo.getAddress: {0}", replyTo.getAddress());
        
        // to
        String to = headerList.getTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);

        // log
        logger.log(Level.SEVERE, "To: {0}", to);

        if (!replyTo.isNone()) {
            // log
            logger.log(Level.SEVERE, "rozpoczynam odejmowanie liczb {0} i {1}.", new Object[]{a, b});

            // sleep between 3 and 7 sec
            SleepUtil.randomSleep();

            // obliczenie wyniku
            double result = a - b;

            // log
            logger.log(Level.SEVERE, "zakończyłem odejmowanie liczb {0} i {1}.", new Object[]{a, b});

            // send email message
            if (to.contains("mailto")) {
                // log
                logger.log(Level.SEVERE, "wykryto adres email w polu reply.");
                
                // log
                logger.log(Level.SEVERE, "wysyłam email.");
    
                // send mail
                MailUtils.sendMail(to.substring(7), "wynik odejmowania " + a + " i " + b + " wynosi " + result + " ");
            } else {
                // log
                logger.log(Level.SEVERE, "wysylam odpowiedz");

                // get messageId
                String messageId = headerList.getMessageID(AddressingVersion.W3C, SOAPVersion.SOAP_11);

                // create webservice client
                org.rsi2.calculator.clients.CallbackService callbackService = new org.rsi2.calculator.clients.CallbackService();
                org.rsi2.calculator.clients.CallbackWS callbackWS = callbackService.getCallbackWSPort();

                // bind address
                WSBindingProvider wSBindingProvider = (WSBindingProvider) callbackWS;
                wSBindingProvider.setAddress(replyTo.getAddress());
                wSBindingProvider.setOutboundHeaders(Headers.create(AddressingVersion.W3C.relatesToTag, messageId));

                // wywolanie metody w serwisie
                callbackWS.callback("wynik odejmowania " + a + " i " + b + " wynosi " + result + " ");
            }
        } else {
            // log
            logger.log(Level.SEVERE, "Nie podano adresu zwrotnego.");
        }
        
        // log
        logger.log(Level.SEVERE, "odejmij stop");
    }

    /**
     * pomnoz
     * @param a
     * @param b 
     */
    @Override
    @WebMethod
    @Oneway
    public void pomnoz(double a, double b) {
        // log
        logger.log(Level.SEVERE, "pomnoz start");

        // headerList
        HeaderList headerList = (HeaderList) context.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
 
        // replyTo
        WSEndpointReference replyTo = headerList.getReplyTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);
    
        // log
        logger.log(Level.SEVERE, "replyTo.getAddress: {0}", replyTo.getAddress());
        
        // to
        String to = headerList.getTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);

        // log
        logger.log(Level.SEVERE, "To: {0}", to);

        if (!replyTo.isNone()) {
            // log
            logger.log(Level.SEVERE, "rozpoczynam mnozenie liczb {0} i {1}.", new Object[]{a, b});

            // sleep between 3 and 7 sec
            SleepUtil.randomSleep();

            // obliczenie wyniku
            double result = a * b;

            // log
            logger.log(Level.SEVERE, "zakończyłem mnozenie liczb {0} i {1}.", new Object[]{a, b});

            // send email message
            if (to.contains("mailto")) {
                // log
                logger.log(Level.SEVERE, "wykryto adres email w polu reply.");
                
                // log
                logger.log(Level.SEVERE, "wysyłam email.");
    
                // send mail
                MailUtils.sendMail(to.substring(7), "wynik mnozenia " + a + " i " + b + " wynosi " + result + " ");
            } else {
                // log
                logger.log(Level.SEVERE, "wysylam odpowiedz");

                // get messageId
                String messageId = headerList.getMessageID(AddressingVersion.W3C, SOAPVersion.SOAP_11);

                // create webservice client
                org.rsi2.calculator.clients.CallbackService callbackService = new org.rsi2.calculator.clients.CallbackService();
                org.rsi2.calculator.clients.CallbackWS callbackWS = callbackService.getCallbackWSPort();

                // bind address
                WSBindingProvider wSBindingProvider = (WSBindingProvider) callbackWS;
                wSBindingProvider.setAddress(replyTo.getAddress());
                wSBindingProvider.setOutboundHeaders(Headers.create(AddressingVersion.W3C.relatesToTag, messageId));

                // wywolanie metody w serwisie
                callbackWS.callback("wynik mnozenia " + a + " i " + b + " wynosi " + result + " ");
            }
        } else {
            // log
            logger.log(Level.SEVERE, "Nie podano adresu zwrotnego.");
        }
        
        // log
        logger.log(Level.SEVERE, "pomnoz stop");
    }

    /**
     * podziel
     * @param a
     * @param b 
     */
    @Override
    @WebMethod
    @Oneway
    public void podziel(double a, double b) {
        // log
        logger.log(Level.SEVERE, "podziel start");

        // headerList
        HeaderList headerList = (HeaderList) context.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
 
        // replyTo
        WSEndpointReference replyTo = headerList.getReplyTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);
    
        // log
        logger.log(Level.SEVERE, "replyTo.getAddress: {0}", replyTo.getAddress());
        
        // to
        String to = headerList.getTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);

        // log
        logger.log(Level.SEVERE, "To: {0}", to);

        if (!replyTo.isNone()) {
            // log
            logger.log(Level.SEVERE, "rozpoczynam dzielenie liczb {0} i {1}.", new Object[]{a, b});

            // sleep between 3 and 7 sec
            SleepUtil.randomSleep();

            // obliczenie wyniku
            double result = 0;

            if (b == 0) {
                // log
                logger.log(Level.SEVERE, "dzielenie przez 0!");
            } else {
                result = a / b;
            }

            // log
            logger.log(Level.SEVERE, "zakończyłem dzielenie liczb {0} i {1}.", new Object[]{a, b});

            // send email message
            if (to.contains("mailto")) {
                // log
                logger.log(Level.SEVERE, "wykryto adres email w polu reply.");
                
                // log
                logger.log(Level.SEVERE, "wysyłam email.");
    
                // send mail
                MailUtils.sendMail(to.substring(7), "wynik dzielenia " + a + " i " + b + " wynosi " + result + " ");
            } else {
                // log
                logger.log(Level.SEVERE, "wysylam odpowiedz");

                // get messageId
                String messageId = headerList.getMessageID(AddressingVersion.W3C, SOAPVersion.SOAP_11);

                // create webservice client
                org.rsi2.calculator.clients.CallbackService callbackService = new org.rsi2.calculator.clients.CallbackService();
                org.rsi2.calculator.clients.CallbackWS callbackWS = callbackService.getCallbackWSPort();

                // bind address
                WSBindingProvider wSBindingProvider = (WSBindingProvider) callbackWS;
                wSBindingProvider.setAddress(replyTo.getAddress());
                wSBindingProvider.setOutboundHeaders(Headers.create(AddressingVersion.W3C.relatesToTag, messageId));

                // wywolanie metody w serwisie
                callbackWS.callback("wynik dzielenia " + a + " i " + b + " wynosi " + result + " ");
            }
        } else {
            // log
            logger.log(Level.SEVERE, "Nie podano adresu zwrotnego.");
        }
        
        // log
        logger.log(Level.SEVERE, "podziel stop");
    }
}
