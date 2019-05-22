
package fr.library.wsdl.connect;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IConnection", targetNamespace = "http://entrypoint.webservices.library.fr/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IConnection {


    /**
     * 
     * @param arg0
     * @return
     *     returns fr.library.wsdl.connect.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "userExist", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.UserExist")
    @ResponseWrapper(localName = "userExistResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.UserExistResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/userExistRequest", output = "http://entrypoint.webservices.library.fr/IConnection/userExistResponse")
    public User userExist(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns fr.library.wsdl.connect.User
     * @throws JWTCheckingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.GetUserResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/getUserRequest", output = "http://entrypoint.webservices.library.fr/IConnection/getUserResponse", fault = {
        @FaultAction(className = JWTCheckingException_Exception.class, value = "http://entrypoint.webservices.library.fr/IConnection/getUser/Fault/JWTCheckingException")
    })
    public User getUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws JWTCheckingException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.LoginResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/loginRequest", output = "http://entrypoint.webservices.library.fr/IConnection/loginResponse")
    public String login(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @throws JWTCheckingException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "logout", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.Logout")
    @ResponseWrapper(localName = "logoutResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.LogoutResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/logoutRequest", output = "http://entrypoint.webservices.library.fr/IConnection/logoutResponse", fault = {
        @FaultAction(className = JWTCheckingException_Exception.class, value = "http://entrypoint.webservices.library.fr/IConnection/logout/Fault/JWTCheckingException")
    })
    public void logout(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws JWTCheckingException_Exception
    ;

}
