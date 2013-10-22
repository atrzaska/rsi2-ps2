package org.rsi2.calculator.util;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SleepUtil
 */
public class SleepUtil {

    /**
     * randomSleep
     */
    public static void randomSleep() {
        Random random = new Random();
        int delay = (random.nextInt(8) + 3) * 1000;

        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(SleepUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
