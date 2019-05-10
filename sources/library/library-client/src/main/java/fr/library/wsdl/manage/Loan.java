
package fr.library.wsdl.manage;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Classe Java pour loan complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="loan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beginDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="doc" type="{http://entrypoint.webservices.library.fr/}document" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="status" type="{http://entrypoint.webservices.library.fr/}status" minOccurs="0"/>
 *         &lt;element name="user" type="{http://entrypoint.webservices.library.fr/}user" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loan", propOrder = {
    "beginDate",
    "doc",
    "endDate",
    "id",
    "status",
    "user"
})
public class Loan {

    @XmlSchemaType(name = "dateTime")
    protected Date beginDate;
    protected Document doc;
    @XmlSchemaType(name = "dateTime")
    protected Date endDate;
    protected Long id;
    @XmlSchemaType(name = "string")
    protected Status status;
    protected User user;

    /**
     * Obtient la valeur de la propri�t� beginDate.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * D�finit la valeur de la propri�t� beginDate.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setBeginDate(Date value) {
        this.beginDate = value;
    }

    /**
     * Obtient la valeur de la propri�t� doc.
     * 
     * @return
     *     possible object is
     *     {@link Document }
     *     
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * D�finit la valeur de la propri�t� doc.
     * 
     * @param value
     *     allowed object is
     *     {@link Document }
     *     
     */
    public void setDoc(Document value) {
        this.doc = value;
    }

    /**
     * Obtient la valeur de la propri�t� endDate.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * D�finit la valeur de la propri�t� endDate.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setEndDate(Date value) {
        this.endDate = value;
    }

    /**
     * Obtient la valeur de la propri�t� id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * D�finit la valeur de la propri�t� id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propri�t� status.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * D�finit la valeur de la propri�t� status.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Obtient la valeur de la propri�t� user.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * D�finit la valeur de la propri�t� user.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

}
