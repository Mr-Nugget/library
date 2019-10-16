
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
    private final static QName _WaitingList_QNAME = new QName("http://entrypoint.webservices.library.fr/", "WaitingList");
    private final static QName _GetAllWaitingResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "getAllWaitingResponse");
    private final static QName _AddUserToList_QNAME = new QName("http://entrypoint.webservices.library.fr/", "addUserToList");
    private final static QName _AddUserToListResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "addUserToListResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.library.wsdl.waiting
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WaitingList }
     * 
     */
    public WaitingList createWaitingList() {
        return new WaitingList();
    }

    /**
     * Create an instance of {@link WaitingList.UsersPositions }
     * 
     */
    public WaitingList.UsersPositions createWaitingListUsersPositions() {
        return new WaitingList.UsersPositions();
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
     * Create an instance of {@link GetAllWaiting }
     * 
     */
    public GetAllWaiting createGetAllWaiting() {
        return new GetAllWaiting();
    }

    /**
     * Create an instance of {@link AddUserToListResponse }
     * 
     */
    public AddUserToListResponse createAddUserToListResponse() {
        return new AddUserToListResponse();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link Type }
     * 
     */
    public Type createType() {
        return new Type();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link WaitingList.UsersPositions.Entry }
     * 
     */
    public WaitingList.UsersPositions.Entry createWaitingListUsersPositionsEntry() {
        return new WaitingList.UsersPositions.Entry();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link WaitingList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "WaitingList")
    public JAXBElement<WaitingList> createWaitingList(WaitingList value) {
        return new JAXBElement<WaitingList>(_WaitingList_QNAME, WaitingList.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserToListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "addUserToListResponse")
    public JAXBElement<AddUserToListResponse> createAddUserToListResponse(AddUserToListResponse value) {
        return new JAXBElement<AddUserToListResponse>(_AddUserToListResponse_QNAME, AddUserToListResponse.class, null, value);
    }

}
