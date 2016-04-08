package libsys;

import java.util.List;

public class DatabaseController {
	
	/**
	 * Puts the Account into the database.
	 * 
	 * @param a The Account to be placed into the database.
	 * @return true if the Account is successfully entered, false if not
	 */
	public boolean putAccount(Account a) {
		//TODO
		return false;
	}
	
	/**
	 * Gets a list of all Accounts in the system.
	 * 
	 * @return a list of all the Accounts in the system
	 */
	public List<Account> listUsers() {
		//TODO
		return null;
	}
	
	/**
	 * Gets the Account object from the database that has the given username.
	 * 
	 * @param username the username of the Account to be retrieved
	 * @return the Account in the database with the given username
	 */
	public Account getAccount(String username) {
		//TODO
		return null;
	}
	
	/**
	 * Removes the book with the given title and author from the database
	 * 
	 * @param title the title of the book to be removed
	 * @param author the author of the book to be removed
	 * @return true if the book has been successfully removed, false if not
	 */
	public boolean removeBook(String title, String author) {
		//TODO
		return false;
	}
	
	/**
	 * Puts the given Shelf object into the database
	 * 
	 * @param s the shelf to be inserted into the database
	 * @return true if the shelf has been successfully put into the database, false if not
	 */
	public boolean putShelf(Shelf s) {
		//TODO
		return false;
	}
	
	/**
	 * Puts the given Book object into the database
	 * 
	 * @param b the Book object to be put into the database
	 * @return true if the book has been successfully put into the database, false if not
	 */
	public boolean putBook(Book b) {
		//TODO
		return false;
	}
	
	/**
	 * Gets from the database the shelf with the given ID number
	 * 
	 * @param sid the ID number of the Shelf object to be gotten from the database
	 * @return the Shelf object with the given ID number
	 */
	public Shelf getShelf(int sid) {
		//TODO
		return null;
	}
	
}
