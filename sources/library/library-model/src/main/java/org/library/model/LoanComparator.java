package org.library.model;

import java.util.Comparator;

public class LoanComparator implements Comparator<Loan> {

	public int compare(Loan loan1, Loan loan2) {
		if(loan1.getDoc().getAuthor().equals(loan2.getDoc().getAuthor())) {
			return loan1.getDoc().getTitle().compareTo(loan2.getDoc().getTitle());
		}else {
			return loan1.getDoc().getAuthor().compareTo(loan2.getDoc().getAuthor());
		}
	}
}
