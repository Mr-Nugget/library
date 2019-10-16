
package fr.library.wsdl.manage;

import java.util.List;
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
@WebService(name = "IManage", targetNamespace = "http://entrypoint.webservices.library.fr/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IManage {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<fr.library.wsdl.manage.Loan>
     * @throws JWTCheckingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCurrentLoans", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.GetCurrentLoans")
    @ResponseWrapper(localName = "getCurrentLoansResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.GetCurrentLoansResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IManage/getCurrentLoansRequest", output = "http://entrypoint.webservices.library.fr/IManage/getCurrentLoansResponse", fault = {
        @FaultAction(className = JWTCheckingException_Exception.class, value = "http://entrypoint.webservices.library.fr/IManage/getCurrentLoans/Fault/JWTCheckingException")
    })
    public List<Loan> getCurrentLoans(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws JWTCheckingException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<fr.library.wsdl.manage.Loan>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loanExpired", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.LoanExpired")
    @ResponseWrapper(localName = "loanExpiredResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.LoanExpiredResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IManage/loanExpiredRequest", output = "http://entrypoint.webservices.library.fr/IManage/loanExpiredResponse")
    public List<Loan> loanExpired();

    /**
     * 
     * @return
     *     returns java.util.List<fr.library.wsdl.manage.Loan>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "mailRecall", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.MailRecall")
    @ResponseWrapper(localName = "mailRecallResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.MailRecallResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IManage/mailRecallRequest", output = "http://entrypoint.webservices.library.fr/IManage/mailRecallResponse")
    public List<Loan> mailRecall();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws JWTCheckingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "extendLoan", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.ExtendLoan")
    @ResponseWrapper(localName = "extendLoanResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.ExtendLoanResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IManage/extendLoanRequest", output = "http://entrypoint.webservices.library.fr/IManage/extendLoanResponse", fault = {
        @FaultAction(className = JWTCheckingException_Exception.class, value = "http://entrypoint.webservices.library.fr/IManage/extendLoan/Fault/JWTCheckingException")
    })
    public boolean extendLoan(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Long arg1)
        throws JWTCheckingException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<fr.library.wsdl.manage.Loan>
     * @throws JWTCheckingException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getArchivedLoans", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.GetArchivedLoans")
    @ResponseWrapper(localName = "getArchivedLoansResponse", targetNamespace = "http://entrypoint.webservices.library.fr/", className = "fr.library.wsdl.manage.GetArchivedLoansResponse")
    @Action(input = "http://entrypoint.webservices.library.fr/IManage/getArchivedLoansRequest", output = "http://entrypoint.webservices.library.fr/IManage/getArchivedLoansResponse", fault = {
        @FaultAction(className = JWTCheckingException_Exception.class, value = "http://entrypoint.webservices.library.fr/IManage/getArchivedLoans/Fault/JWTCheckingException")
    })
    public List<Loan> getArchivedLoans(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws JWTCheckingException_Exception
    ;

}
