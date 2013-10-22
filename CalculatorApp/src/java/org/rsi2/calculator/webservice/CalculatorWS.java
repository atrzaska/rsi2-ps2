package org.rsi2.calculator.webservice;

import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

@WebService(serviceName = "CalculatorService")
@Addressing(enabled = true, required = true)
public class CalculatorWS implements ICalculatorWS {

    public void dodaj(double a, double b) {
     }

    public void odejmij(double a, double b) {
    }

    public void pomnoz(double a, double b) {
     }

    public void podziel(double a, double b) {
    }
}
