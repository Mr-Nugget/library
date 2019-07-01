package org.library.model;


/**
 * Describe a loan state, in progress, clotured, extended or late
 * @author Titouan
 *
 */
public enum Status {
	CLOTURED,
	IN_PROGRESS,
	EXTENDED,
	LATE;

	//get a status by its ID
	public static Status getEnumByInt(int x) {
		switch(x) {
		case 0:
			return CLOTURED;
		case 1:
			return IN_PROGRESS;
		case 2:
			return EXTENDED;
		case 3:
			return LATE;
		default :
			return CLOTURED;
		}
	}
	
	public int getId() {
		if(this == CLOTURED) return 0;
		if(this == IN_PROGRESS) return 1;
		if(this == EXTENDED) return 2;
		if(this == LATE) return 3;
		
		return -1;
		
	}
}
