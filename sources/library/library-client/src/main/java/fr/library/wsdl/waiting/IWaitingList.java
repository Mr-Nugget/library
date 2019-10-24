
package fr.library.wsdl.waiting;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IWaitingList", targetNamespace = "http://entrypoint.webservices.library.fr/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IWaitingList {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<fr.library.wsdl.waiting.WaitingList>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllWaiting", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.waiting.GetAllWaiting")
    @ResponseWrapper(localName = "getAllWaitingResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.waiting.GetAllWaitingResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IWaitingList/getAllWaitingRequest", output = "http://entrypoint.webservices.library.fr/IWaitingList/getAllWaitingResponse")
    public List<WaitingList> getAllWaiting(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addUserToList", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.waiting.AddUserToList")
    @ResponseWrapper(localName = "addUserToListResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.waiting.AddUserToListResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IWaitingList/addUserToListRequest", output = "http://entrypoint.webservices.library.fr/IWaitingList/addUserToListResponse")
    public Long addUserToList(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Long arg1);

}
