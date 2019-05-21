
package fr.library.wsdl.manage;

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
@WebServiceClient(name = "ManageImplService", targetNamespace = "http://entrypoint.webservices.library.fr/", wsdlLocation = "http://51.68.230.132:8080/library-webservice/manage?wsdl")
public class ManageImplService
    extends Service
{

    private final static URL MANAGEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException MANAGEIMPLSERVICE_EXCEPTION;
    private final static QName MANAGEIMPLSERVICE_QNAME = new QName("http://entrypoint.webservices.library.fr/", "ManageImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://51.68.230.132:8080/library-webservice/manage?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MANAGEIMPLSERVICE_WSDL_LOCATION = url;
        MANAGEIMPLSERVICE_EXCEPTION = e;
    }

    public ManageImplService() {
        super(__getWsdlLocation(), MANAGEIMPLSERVICE_QNAME);
    }

    public ManageImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MANAGEIMPLSERVICE_QNAME, features);
    }

    public ManageImplService(URL wsdlLocation) {
        super(wsdlLocation, MANAGEIMPLSERVICE_QNAME);
    }

    public ManageImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MANAGEIMPLSERVICE_QNAME, features);
    }

    public ManageImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ManageImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IManage
     */
    @WebEndpoint(name = "ManageImplPort")
    public IManage getManageImplPort() {
        return super.getPort(new QName("http://entrypoint.webservices.library.fr/", "ManageImplPort"), IManage.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IManage
     */
    @WebEndpoint(name = "ManageImplPort")
    public IManage getManageImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://entrypoint.webservices.library.fr/", "ManageImplPort"), IManage.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MANAGEIMPLSERVICE_EXCEPTION!= null) {
            throw MANAGEIMPLSERVICE_EXCEPTION;
        }
        return MANAGEIMPLSERVICE_WSDL_LOCATION;
    }

}
