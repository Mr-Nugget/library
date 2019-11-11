package org.library.model;


/**
 * Describe a loan state, in progress, clotured, extended, late or awaiting
 * @author Titouan
 *
 */
public enum Status {
	CLOTURED,
	IN_PROGRESS,
	EXTENDED,
	LATE,
	AWAITING;

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
		case 4:
			return AWAITING;
		default :
			return CLOTURED;
		}
	}
	
	public int getId() {
		if(this == CLOTURED) return 0;
		if(this == IN_PROGRESS) return 1;
		if(this == EXTENDED) return 2;
		if(this == LATE) return 3;
		if(this == AWAITING) return 4;
		
		return -1;
		
	}
}
