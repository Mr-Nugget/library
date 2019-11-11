
package fr.library.wsdl.waiting;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour waitingList complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="waitingList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="doc" type="{http://entrypoint.webservices.library.fr/}document" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="lastPosition" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="usersPositions" type="{http://entrypoint.webservices.library.fr/}user" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "waitingList", propOrder = {
    "doc",
    "id",
    "lastPosition",
    "usersPositions"
})
public class WaitingList {

    protected Document doc;
    protected Long id;
    protected Integer lastPosition;
    @XmlElement(nillable = true)
    protected List<User> usersPositions;

    /**
     * Obtient la valeur de la propriété doc.
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
     * Définit la valeur de la propriété doc.
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
     * Obtient la valeur de la propriété id.
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
     * Définit la valeur de la propriété id.
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
     * Obtient la valeur de la propriété lastPosition.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLastPosition() {
        return lastPosition;
    }

    /**
     * Définit la valeur de la propriété lastPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLastPosition(Integer value) {
        this.lastPosition = value;
    }

    /**
     * Gets the value of the usersPositions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usersPositions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsersPositions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getUsersPositions() {
        if (usersPositions == null) {
            usersPositions = new ArrayList<User>();
        }
        return this.usersPositions;
    }

}
