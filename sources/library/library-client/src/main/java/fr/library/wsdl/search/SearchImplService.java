
package fr.library.wsdl.search;

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
@WebServiceClient(name = "SearchImplService", targetNamespace = "http://entrypoint.webservices.library.fr/", wsdlLocation = "http://localhost:8283/library-webservice/search?wsdl")
public class SearchImplService
    extends Service
{

    private final static URL SEARCHIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException SEARCHIMPLSERVICE_EXCEPTION;
    private final static QName SEARCHIMPLSERVICE_QNAME = new QName("http://entrypoint.webservices.library.fr/", "SearchImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8283/library-webservice/search?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SEARCHIMPLSERVICE_WSDL_LOCATION = url;
        SEARCHIMPLSERVICE_EXCEPTION = e;
    }

    public SearchImplService() {
        super(__getWsdlLocation(), SEARCHIMPLSERVICE_QNAME);
    }

    public SearchImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SEARCHIMPLSERVICE_QNAME, features);
    }

    public SearchImplService(URL wsdlLocation) {
        super(wsdlLocation, SEARCHIMPLSERVICE_QNAME);
    }

    public SearchImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SEARCHIMPLSERVICE_QNAME, features);
    }

    public SearchImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SearchImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ISearch
     */
    @WebEndpoint(name = "SearchImplPort")
    public ISearch getSearchImplPort() {
        return super.getPort(new QName("http://entrypoint.webservices.library.fr/", "SearchImplPort"), ISearch.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ISearch
     */
    @WebEndpoint(name = "SearchImplPort")
    public ISearch getSearchImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://entrypoint.webservices.library.fr/", "SearchImplPort"), ISearch.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SEARCHIMPLSERVICE_EXCEPTION!= null) {
            throw SEARCHIMPLSERVICE_EXCEPTION;
        }
        return SEARCHIMPLSERVICE_WSDL_LOCATION;
    }

}
