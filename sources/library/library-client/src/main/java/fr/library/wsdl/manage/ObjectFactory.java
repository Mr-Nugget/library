
package fr.library.wsdl.manage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.library.wsdl.manage package. 
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

    private final static QName _LoanExpired_QNAME = new QName("http://entrypoint.webservices.library.fr/", "loanExpired");
    private final static QName _GetArchivedLoansResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getArchivedLoansResponse");
    private final static QName _GetCurrentLoans_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getCurrentLoans");
    private final static QName _LoanExpiredResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "loanExpiredResponse");
    private final static QName _JWTCheckingException_QNAME = new QName("http://entrypoint.webservices.library.fr/", "JWTCheckingException");
    private final static QName _GetCurrentLoansResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getCurrentLoansResponse");
    private final static QName _ExtendLoan_QNAME = new QName("http://entrypoint.webservices.library.fr/", "extendLoan");
    private final static QName _ExtendLoanResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "extendLoanResponse");
    private final static QName _GetArchivedLoans_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getArchivedLoans");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.library.wsdl.manage
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
     * Create an instance of {@link GetCurrentLoansResponse }
     * 
     */
    public GetCurrentLoansResponse createGetCurrentLoansResponse() {
        return new GetCurrentLoansResponse();
    }

    /**
     * Create an instance of {@link GetCurrentLoans }
     * 
     */
    public GetCurrentLoans createGetCurrentLoans() {
        return new GetCurrentLoans();
    }

    /**
     * Create an instance of {@link LoanExpiredResponse }
     * 
     */
    public LoanExpiredResponse createLoanExpiredResponse() {
        return new LoanExpiredResponse();
    }

    /**
     * Create an instance of {@link GetArchivedLoansResponse }
     * 
     */
    public GetArchivedLoansResponse createGetArchivedLoansResponse() {
        return new GetArchivedLoansResponse();
    }

    /**
     * Create an instance of {@link LoanExpired }
     * 
     */
    public LoanExpired createLoanExpired() {
        return new LoanExpired();
    }

    /**
     * Create an instance of {@link GetArchivedLoans }
     * 
     */
    public GetArchivedLoans createGetArchivedLoans() {
        return new GetArchivedLoans();
    }

    /**
     * Create an instance of {@link ExtendLoanResponse }
     * 
     */
    public ExtendLoanResponse createExtendLoanResponse() {
        return new ExtendLoanResponse();
    }

    /**
     * Create an instance of {@link ExtendLoan }
     * 
     */
    public ExtendLoan createExtendLoan() {
        return new ExtendLoan();
    }

    /**
     * Create an instance of {@link Loan }
     * 
     */
    public Loan createLoan() {
        return new Loan();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link Type }
     * 
     */
    public Type createType() {
        return new Type();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoanExpired }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "loanExpired")
    public JAXBElement<LoanExpired> createLoanExpired(LoanExpired value) {
        return new JAXBElement<LoanExpired>(_LoanExpired_QNAME, LoanExpired.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArchivedLoansResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "getArchivedLoansResponse")
    public JAXBElement<GetArchivedLoansResponse> createGetArchivedLoansResponse(GetArchivedLoansResponse value) {
        return new JAXBElement<GetArchivedLoansResponse>(_GetArchivedLoansResponse_QNAME, GetArchivedLoansResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentLoans }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "getCurrentLoans")
    public JAXBElement<GetCurrentLoans> createGetCurrentLoans(GetCurrentLoans value) {
        return new JAXBElement<GetCurrentLoans>(_GetCurrentLoans_QNAME, GetCurrentLoans.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoanExpiredResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "loanExpiredResponse")
    public JAXBElement<LoanExpiredResponse> createLoanExpiredResponse(LoanExpiredResponse value) {
        return new JAXBElement<LoanExpiredResponse>(_LoanExpiredResponse_QNAME, LoanExpiredResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentLoansResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "getCurrentLoansResponse")
    public JAXBElement<GetCurrentLoansResponse> createGetCurrentLoansResponse(GetCurrentLoansResponse value) {
        return new JAXBElement<GetCurrentLoansResponse>(_GetCurrentLoansResponse_QNAME, GetCurrentLoansResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtendLoan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "extendLoan")
    public JAXBElement<ExtendLoan> createExtendLoan(ExtendLoan value) {
        return new JAXBElement<ExtendLoan>(_ExtendLoan_QNAME, ExtendLoan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtendLoanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "extendLoanResponse")
    public JAXBElement<ExtendLoanResponse> createExtendLoanResponse(ExtendLoanResponse value) {
        return new JAXBElement<ExtendLoanResponse>(_ExtendLoanResponse_QNAME, ExtendLoanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArchivedLoans }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "getArchivedLoans")
    public JAXBElement<GetArchivedLoans> createGetArchivedLoans(GetArchivedLoans value) {
        return new JAXBElement<GetArchivedLoans>(_GetArchivedLoans_QNAME, GetArchivedLoans.class, null, value);
    }

}
