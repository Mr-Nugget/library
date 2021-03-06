
package fr.library.wsdl.connect;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.library.wsdl.connect package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegisterResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "registerResponse");
    private final static QName _ResetPasswordResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "resetPasswordResponse");
    private final static QName _Logout_QNAME = new QName("http://entrypoint.webservices.library.fr/", "logout");
    private final static QName _UserExistResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "userExistResponse");
    private final static QName _Register_QNAME = new QName("http://entrypoint.webservices.library.fr/", "register");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "updateUserResponse");
    private final static QName _UserExist_QNAME = new QName("http://entrypoint.webservices.library.fr/", "userExist");
    private final static QName _SendResetPasswordLinkResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "sendResetPasswordLinkResponse");
    private final static QName _SendMailResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "sendMailResponse");
    private final static QName _GetUser_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getUser");
    private final static QName _LoginResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "loginResponse");
    private final static QName _LogoutResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "logoutResponse");
    private final static QName _JWTCheckingException_QNAME = new QName("http://entrypoint.webservices.library.fr/", "JWTCheckingException");
    private final static QName _SendResetPasswordLink_QNAME = new QName("http://entrypoint.webservices.library.fr/", "sendResetPasswordLink");
    private final static QName _GetUserResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getUserResponse");
    private final static QName _ResetPassword_QNAME = new QName("http://entrypoint.webservices.library.fr/", "resetPassword");
    private final static QName _Login_QNAME = new QName("http://entrypoint.webservices.library.fr/", "login");
    private final static QName _SendMail_QNAME = new QName("http://entrypoint.webservices.library.fr/", "sendMail");
    private final static QName _UpdateUser_QNAME = new QName("http://entrypoint.webservices.library.fr/", "updateUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.library.wsdl.connect
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JWTCheckingException }
     * 
     */
    public JWTCheckingException createJWTCheckingException() {
        return new JWTCheckingException();
    }

    /**
     * Create an instance of {@link SendResetPasswordLink }
     * 
     */
    public SendResetPasswordLink createSendResetPasswordLink() {
        return new SendResetPasswordLink();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link ResetPassword }
     * 
     */
    public ResetPassword createResetPassword() {
        return new ResetPassword();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link SendMail }
     * 
     */
    public SendMail createSendMail() {
        return new SendMail();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link ResetPasswordResponse }
     * 
     */
    public ResetPasswordResponse createResetPasswordResponse() {
        return new ResetPasswordResponse();
    }

    /**
     * Create an instance of {@link SendMailResponse }
     * 
     */
    public SendMailResponse createSendMailResponse() {
        return new SendMailResponse();
    }

    /**
     * Create an instance of {@link SendResetPasswordLinkResponse }
     * 
     */
    public SendResetPasswordLinkResponse createSendResetPasswordLinkResponse() {
        return new SendResetPasswordLinkResponse();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link UserExist }
     * 
     */
    public UserExist createUserExist() {
        return new UserExist();
    }

    /**
     * Create an instance of {@link UserExistResponse }
     * 
     */
    public UserExistResponse createUserExistResponse() {
        return new UserExistResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "resetPasswordResponse")
    public JAXBElement<ResetPasswordResponse> createResetPasswordResponse(ResetPasswordResponse value) {
        return new JAXBElement<ResetPasswordResponse>(_ResetPasswordResponse_QNAME, ResetPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserExistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "userExistResponse")
    public JAXBElement<UserExistResponse> createUserExistResponse(UserExistResponse value) {
        return new JAXBElement<UserExistResponse>(_UserExistResponse_QNAME, UserExistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "userExist")
    public JAXBElement<UserExist> createUserExist(UserExist value) {
        return new JAXBElement<UserExist>(_UserExist_QNAME, UserExist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendResetPasswordLinkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "sendResetPasswordLinkResponse")
    public JAXBElement<SendResetPasswordLinkResponse> createSendResetPasswordLinkResponse(SendResetPasswordLinkResponse value) {
        return new JAXBElement<SendResetPasswordLinkResponse>(_SendResetPasswordLinkResponse_QNAME, SendResetPasswordLinkResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "sendMailResponse")
    public JAXBElement<SendMailResponse> createSendMailResponse(SendMailResponse value) {
        return new JAXBElement<SendMailResponse>(_SendMailResponse_QNAME, SendMailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JWTCheckingException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "JWTCheckingException")
    public JAXBElement<JWTCheckingException> createJWTCheckingException(JWTCheckingException value) {
        return new JAXBElement<JWTCheckingException>(_JWTCheckingException_QNAME, JWTCheckingException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendResetPasswordLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "sendResetPasswordLink")
    public JAXBElement<SendResetPasswordLink> createSendResetPasswordLink(SendResetPasswordLink value) {
        return new JAXBElement<SendResetPasswordLink>(_SendResetPasswordLink_QNAME, SendResetPasswordLink.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "resetPassword")
    public JAXBElement<ResetPassword> createResetPassword(ResetPassword value) {
        return new JAXBElement<ResetPassword>(_ResetPassword_QNAME, ResetPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "sendMail")
    public JAXBElement<SendMail> createSendMail(SendMail value) {
        return new JAXBElement<SendMail>(_SendMail_QNAME, SendMail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

}
