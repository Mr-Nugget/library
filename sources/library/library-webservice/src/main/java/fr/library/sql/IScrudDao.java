package fr.library.sql;

import java.util.List;
/**
 * Basic methods for all the DAO the get access to tables
 * @author Titouan
 *
 * @param <T>
 */
public interface IScrudDao<T> {   //T can be a User, a Document or a Loan depending on the implementation
	//get an item by its id, return null if there is no item corresponding
	public T getById(Long id); 
	//delete item from the table by the item id
	public void deleteItem(T item);
	//update item from the table by item id, set every attributes of the item
	public void updateItem(T item);
	//return all the item of the table
	public List<T> findAll();
}
