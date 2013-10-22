package org.rsi2.calculator.webservice;

import com.sun.xml.ws.api.SOAPVersion;
import com.sun.xml.ws.api.addressing.AddressingVersion;
import com.sun.xml.ws.api.addressing.WSEndpointReference;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;
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
        HeaderList headerList = (HeaderList) context.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
        WSEndpointReference replyTo = headerList.getReplyTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);
        String to = headerList.getTo(AddressingVersion.W3C, SOAPVersion.SOAP_11);
        
        logger.log(Level.SEVERE, "replyTo.getAddress: {0}", replyTo.getAddress());
        logger.log(Level.SEVERE, "To: {0}", to);

        // log
        logger.log(Level.SEVERE, "rozpoczynam dodawanie liczb {1} i {2}.", new Object[]{a, b});

        // sleep between 3 and 7 sec
        SleepUtil.randomSleep();

        // obliczenie wyniku
        double result = a + b;

        // log
        logger.log(Level.SEVERE, "zakończyłem dodawnie liczb {1} i {2}.", new Object[]{a, b});

        if (!replyTo.isNone()) {
//            String messageId = headerList.getMessageID(AddressingVersion.W3C, SOAPVersion.SOAP_11);
//            client.CalculatorImplService service = new client.CalculatorImplService();
//            client.CalculatorImpl portType = service.getCalculatorImplPort();
//            WSBindingProvider bp = (WSBindingProvider) portType;
//            bp.setAddress(replyTo.getAddress());
//            bp.setOutboundHeaders(Headers.create(AddressingVersion.W3C.relatesToTag, messageId));
//            portType.callbackMessage(result, "dodawanie");

            // send email message
            if (to.contains("mailto")) {
                MailUtils.sendMail(email.substring(7), "wynik dodawania " + a + " i " + b + " jest " + result + " ");
            }
        } else {
            // log
            logger.log(Level.SEVERE, "Nie podano adresu zwrotnego.");
        }
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
    }
}
