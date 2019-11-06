
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
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "register", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.Register")
    @ResponseWrapper(localName = "registerResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.RegisterResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/registerRequest", output = "http://entrypoint.webservices.library.fr/IConnection/registerResponse")
    public Long register(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

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
     */
    @WebMethod
    @RequestWrapper(localName = "sendResetPasswordLink", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.SendResetPasswordLink")
    @ResponseWrapper(localName = "sendResetPasswordLinkResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.SendResetPasswordLinkResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/sendResetPasswordLinkRequest", output = "http://entrypoint.webservices.library.fr/IConnection/sendResetPasswordLinkResponse")
    public void sendResetPasswordLink(
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
     * @param arg1
     * @param arg0
     * @throws JWTCheckingException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "resetPassword", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.ResetPassword")
    @ResponseWrapper(localName = "resetPasswordResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.ResetPasswordResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/resetPasswordRequest", output = "http://entrypoint.webservices.library.fr/IConnection/resetPasswordResponse", fault = {
        @FaultAction(className = JWTCheckingException_Exception.class, value = "http://entrypoint.webservices.library.fr/IConnection/resetPassword/Fault/JWTCheckingException")
    })
    public void resetPassword(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws JWTCheckingException_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "sendMail", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.SendMail")
    @ResponseWrapper(localName = "sendMailResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.SendMailResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/sendMailRequest", output = "http://entrypoint.webservices.library.fr/IConnection/sendMailResponse")
    public void sendMail(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.connect.UpdateUserResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IConnection/updateUserRequest", output = "http://entrypoint.webservices.library.fr/IConnection/updateUserResponse")
    public void updateUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

}
