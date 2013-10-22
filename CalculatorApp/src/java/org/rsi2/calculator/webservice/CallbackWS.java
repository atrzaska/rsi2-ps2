package org.rsi2.calculator.webservice;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

/**
 * CallbackWS
 */
@WebService(serviceName = "CallbackService")
public class CallbackWS implements ICallbackWS {
    private static final Logger logger = Logger.getLogger(CallbackWS.class.getName());
    
    /**
     * callback
     * @param msg 
     */
    @Override
    public void callback(String msg) {
        logger.log(Level.INFO, msg);
    }
}
