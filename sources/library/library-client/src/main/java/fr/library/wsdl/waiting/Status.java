
package fr.library.wsdl.waiting;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour status.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="status">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CLOTURED"/>
 *     &lt;enumeration value="IN_PROGRESS"/>
 *     &lt;enumeration value="EXTENDED"/>
 *     &lt;enumeration value="LATE"/>
 *     &lt;enumeration value="AWAITING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "status")
@XmlEnum
public enum Status {

    CLOTURED,
    IN_PROGRESS,
    EXTENDED,
    LATE,
    AWAITING;

    public String value() {
        return name();
    }

    public static Status fromValue(String v) {
        return valueOf(v);
    }

}
