
package fr.library.wsdl.search;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "JWTCheckingException", targetNamespace = "http://entrypoint.webservices.library.fr/")
public class JWTCheckingException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private JWTCheckingException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public JWTCheckingException_Exception(String message, JWTCheckingException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public JWTCheckingException_Exception(String message, JWTCheckingException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: fr.library.wsdl.search.JWTCheckingException
     */
    public JWTCheckingException getFaultInfo() {
        return faultInfo;
    }

}
