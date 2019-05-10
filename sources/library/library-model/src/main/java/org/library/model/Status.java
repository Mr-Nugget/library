package org.library.model;

/*
 * Describe a loan state, in progress, clotured or extended
 */

public enum Status {
	CLOTURED,
	IN_PROGRESS,
	EXTENDED;

	//get a status by its ID
	public static Status getEnumByInt(int x) {
		switch(x) {
		case 0:
			return CLOTURED;
		case 1:
			return IN_PROGRESS;
		case 2:
			return EXTENDED;
		default :
			return CLOTURED;
		}
	}
	
	public int getId() {
		if(this == CLOTURED) return 0;
		if(this == IN_PROGRESS) return 1;
		if(this == EXTENDED) return 2;
		
		return -1;
		
	}
}
