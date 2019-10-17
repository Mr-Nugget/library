
package fr.library.wsdl.waiting;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WaitingListImplService", targetNamespace = "http://entrypoint.webservices.library.fr/", wsdlLocation = "http://localhost:8283/library-webservice/waiting?wsdl")
public class WaitingListImplService
    extends Service
{

    private final static URL WAITINGLISTIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException WAITINGLISTIMPLSERVICE_EXCEPTION;
    private final static QName WAITINGLISTIMPLSERVICE_QNAME = new QName("http://entrypoint.webservices.library.fr/", "WaitingListImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8283/library-webservice/waiting?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WAITINGLISTIMPLSERVICE_WSDL_LOCATION = url;
        WAITINGLISTIMPLSERVICE_EXCEPTION = e;
    }

    public WaitingListImplService() {
        super(__getWsdlLocation(), WAITINGLISTIMPLSERVICE_QNAME);
    }

    public WaitingListImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WAITINGLISTIMPLSERVICE_QNAME, features);
    }

    public WaitingListImplService(URL wsdlLocation) {
        super(wsdlLocation, WAITINGLISTIMPLSERVICE_QNAME);
    }

    public WaitingListImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WAITINGLISTIMPLSERVICE_QNAME, features);
    }

    public WaitingListImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WaitingListImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IWaitingList
     */
    @WebEndpoint(name = "WaitingListImplPort")
    public IWaitingList getWaitingListImplPort() {
        return super.getPort(new QName("http://entrypoint.webservices.library.fr/", "WaitingListImplPort"), IWaitingList.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IWaitingList
     */
    @WebEndpoint(name = "WaitingListImplPort")
    public IWaitingList getWaitingListImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://entrypoint.webservices.library.fr/", "WaitingListImplPort"), IWaitingList.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WAITINGLISTIMPLSERVICE_EXCEPTION!= null) {
            throw WAITINGLISTIMPLSERVICE_EXCEPTION;
        }
        return WAITINGLISTIMPLSERVICE_WSDL_LOCATION;
    }

}