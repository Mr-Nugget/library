
package fr.library.wsdl.waiting;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.library.wsdl.waiting package. 
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

    private final static QName _GetAllWaiting_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getAllWaiting");
    private final static QName _ReturnDocument_QNAME = new QName("http://entrypoint.webservices.library.fr/", "returnDocument");
    private final static QName _UpdateListAfterTwoDaysResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "updateListAfterTwoDaysResponse");
    private final static QName _CancelAReservation_QNAME = new QName("http://entrypoint.webservices.library.fr/", "cancelAReservation");
    private final static QName _CancelAReservationResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "cancelAReservationResponse");
    private final static QName _UpdateListAfterTwoDays_QNAME = new QName("http://entrypoint.webservices.library.fr/", "updateListAfterTwoDays");
    private final static QName _GetAllWaitingResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getAllWaitingResponse");
    private final static QName _AddUserToList_QNAME = new QName("http://entrypoint.webservices.library.fr/", "addUserToList");
    private final static QName _UserNotInTheListException_QNAME = new QName("http://entrypoint.webservices.library.fr/", "UserNotInTheListException");
    private final static QName _AddUserToListResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "addUserToListResponse");
    private final static QName _ReturnDocumentResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "returnDocumentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.library.wsdl.waiting
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllWaitingResponse }
     * 
     */
    public GetAllWaitingResponse createGetAllWaitingResponse() {
        return new GetAllWaitingResponse();
    }

    /**
     * Create an instance of {@link AddUserToList }
     * 
     */
    public AddUserToList createAddUserToList() {
        return new AddUserToList();
    }

    /**
     * Create an instance of {@link UpdateListAfterTwoDays }
     * 
     */
    public UpdateListAfterTwoDays createUpdateListAfterTwoDays() {
        return new UpdateListAfterTwoDays();
    }

    /**
     * Create an instance of {@link CancelAReservation }
     * 
     */
    public CancelAReservation createCancelAReservation() {
        return new CancelAReservation();
    }

    /**
     * Create an instance of {@link CancelAReservationResponse }
     * 
     */
    public CancelAReservationResponse createCancelAReservationResponse() {
        return new CancelAReservationResponse();
    }

    /**
     * Create an instance of {@link GetAllWaiting }
     * 
     */
    public GetAllWaiting createGetAllWaiting() {
        return new GetAllWaiting();
    }

    /**
     * Create an instance of {@link ReturnDocument }
     * 
     */
    public ReturnDocument createReturnDocument() {
        return new ReturnDocument();
    }

    /**
     * Create an instance of {@link UpdateListAfterTwoDaysResponse }
     * 
     */
    public UpdateListAfterTwoDaysResponse createUpdateListAfterTwoDaysResponse() {
        return new UpdateListAfterTwoDaysResponse();
    }

    /**
     * Create an instance of {@link UserNotInTheListException }
     * 
     */
    public UserNotInTheListException createUserNotInTheListException() {
        return new UserNotInTheListException();
    }

    /**
     * Create an instance of {@link AddUserToListResponse }
     * 
     */
    public AddUserToListResponse createAddUserToListResponse() {
        return new AddUserToListResponse();
    }

    /**
     * Create an instance of {@link ReturnDocumentResponse }
     * 
     */
    public ReturnDocumentResponse createReturnDocumentResponse() {
        return new ReturnDocumentResponse();
    }

    /**
     * Create an instance of {@link Loan }
     * 
     */
    public Loan createLoan() {
        return new Loan();
    }

    /**
     * Create an instance of {@link WaitingList }
     * 
     */
    public WaitingList createWaitingList() {
        return new WaitingList();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllWaiting }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "getAllWaiting")
    public JAXBElement<GetAllWaiting> createGetAllWaiting(GetAllWaiting value) {
        return new JAXBElement<GetAllWaiting>(_GetAllWaiting_QNAME, GetAllWaiting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "returnDocument")
    public JAXBElement<ReturnDocument> createReturnDocument(ReturnDocument value) {
        return new JAXBElement<ReturnDocument>(_ReturnDocument_QNAME, ReturnDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateListAfterTwoDaysResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "updateListAfterTwoDaysResponse")
    public JAXBElement<UpdateListAfterTwoDaysResponse> createUpdateListAfterTwoDaysResponse(UpdateListAfterTwoDaysResponse value) {
        return new JAXBElement<UpdateListAfterTwoDaysResponse>(_UpdateListAfterTwoDaysResponse_QNAME, UpdateListAfterTwoDaysResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelAReservation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "cancelAReservation")
    public JAXBElement<CancelAReservation> createCancelAReservation(CancelAReservation value) {
        return new JAXBElement<CancelAReservation>(_CancelAReservation_QNAME, CancelAReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelAReservationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "cancelAReservationResponse")
    public JAXBElement<CancelAReservationResponse> createCancelAReservationResponse(CancelAReservationResponse value) {
        return new JAXBElement<CancelAReservationResponse>(_CancelAReservationResponse_QNAME, CancelAReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateListAfterTwoDays }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "updateListAfterTwoDays")
    public JAXBElement<UpdateListAfterTwoDays> createUpdateListAfterTwoDays(UpdateListAfterTwoDays value) {
        return new JAXBElement<UpdateListAfterTwoDays>(_UpdateListAfterTwoDays_QNAME, UpdateListAfterTwoDays.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllWaitingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "getAllWaitingResponse")
    public JAXBElement<GetAllWaitingResponse> createGetAllWaitingResponse(GetAllWaitingResponse value) {
        return new JAXBElement<GetAllWaitingResponse>(_GetAllWaitingResponse_QNAME, GetAllWaitingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserToList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "addUserToList")
    public JAXBElement<AddUserToList> createAddUserToList(AddUserToList value) {
        return new JAXBElement<AddUserToList>(_AddUserToList_QNAME, AddUserToList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNotInTheListException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "UserNotInTheListException")
    public JAXBElement<UserNotInTheListException> createUserNotInTheListException(UserNotInTheListException value) {
        return new JAXBElement<UserNotInTheListException>(_UserNotInTheListException_QNAME, UserNotInTheListException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserToListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "addUserToListResponse")
    public JAXBElement<AddUserToListResponse> createAddUserToListResponse(AddUserToListResponse value) {
        return new JAXBElement<AddUserToListResponse>(_AddUserToListResponse_QNAME, AddUserToListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "returnDocumentResponse")
    public JAXBElement<ReturnDocumentResponse> createReturnDocumentResponse(ReturnDocumentResponse value) {
        return new JAXBElement<ReturnDocumentResponse>(_ReturnDocumentResponse_QNAME, ReturnDocumentResponse.class, null, value);
    }

}
