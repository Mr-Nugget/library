
package fr.library.wsdl.search;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.library.wsdl.search package. 
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

    private final static QName _SearchResponse_QNAME = new QName("http://entrypoint.webservices.library.fr/", "searchResponse");
    private final static QName _Search_QNAME = new QName("http://entrypoint.webservices.library.fr/", "search");
    private final static QName _JWTCheckingException_QNAME = new QName("http://entrypoint.webservices.library.fr/", "JWTCheckingException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.library.wsdl.search
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
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "searchResponse")
    public JAXBElement<SearchResponse> createSearchResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchResponse_QNAME, SearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Search }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "search")
    public JAXBElement<Search> createSearch(Search value) {
        return new JAXBElement<Search>(_Search_QNAME, Search.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JWTCheckingException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entrypoint.webservices.library.fr/", name = "JWTCheckingException")
    public JAXBElement<JWTCheckingException> createJWTCheckingException(JWTCheckingException value) {
        return new JAXBElement<JWTCheckingException>(_JWTCheckingException_QNAME, JWTCheckingException.class, null, value);
    }

}
