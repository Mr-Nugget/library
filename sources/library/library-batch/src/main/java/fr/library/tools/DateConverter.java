package fr.library.tools;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * A date converter class
 * @author Titouan
 *
 */
public abstract class DateConverter {
	
	/**
	 * Convert XMLGregorianCalendar to java.util.Date
	 * @param xmlDate
	 * @return Date
	 */
	public static Date xmlGregorianToDate(XMLGregorianCalendar xmlDate) {
		if(xmlDate == null) {
			return null;
		}else {
			return xmlDate.toGregorianCalendar().getTime();
		}
	}
}
