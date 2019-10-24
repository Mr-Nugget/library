package fr.library.helpers;

import java.util.List;

import fr.library.wsdl.connect.User;

public abstract class UserPosition {

	public static Integer getUserPosition(List<fr.library.wsdl.waiting.User> list, User userJWT) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(0) != null) {
				if(userJWT.getId().equals(list.get(0).getId())) {
					return i;
				}
			}
		}
		return -1;
	}
}
