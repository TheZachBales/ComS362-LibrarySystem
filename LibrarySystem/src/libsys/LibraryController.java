package libsys;

import java.util.List;

public class LibraryController {
	
	/**
	 * Creates an Account object with the given username and password.
	 * 
	 * @param username the username for the new Account
	 * @param password the password for the new Account
	 * @return true if the Account object is successfully created, false if not
	 */
	public boolean createAccount(String username, String password) {
		//TODO
		return false;
	}
	
	/**
	 * Changes the type of Account to the type provided.
	 * 
	 * @param username the username of the Account to be changed
	 * @param at account type to convert the Account to
	 * @return true if the Account is successfully upgraded, false if not
	 */
	public boolean changeAccount(String username, int at) {
		//TODO
		return false;
	}
	
	/**
	 * Logs the user into the Account with the given username.
	 * 
	 * @param username the username of the Account to log in to
	 * @param password the password for the Customer account
	 * @return type of Account that has been logged in to
	 */
	public int login(String username, String password) {
		//TODO
		return 0;
	}
	
	/**
	 * Returns a list of all Accounts in the system
	 * 
	 * @return list of all Accounts in the system
	 */
	public List<Account> listUsers() {
		//TODO
		return null;
	}
	
	/**
	 * Creates a shelf to hold books of the specified genre and being
	 * able to hold up to the number of books specified by the capacity.
	 * 
	 * @param genre the name of the genre of books that will be put on this shelf
	 * @param capacity the maximum number of books that will be able to be put on this shelf
	 * @return true if the shelf is created successfully, false if not
	 */
	public boolean createShelf(String genre, int capacity) {
		//TODO
		return false;
	}
	
	/**
	 * Adds the book with the given title, author, and ISBN number into the library from the catalog.
	 * 
	 * @param title the title of the book to be added
	 * @param author the author of the book to be added
	 * @param ISBN the ISBN code of the book to be added
	 * @return true if the book has been successfully added, false if not
	 */
	public boolean addBook(String title, String author, String ISBN) {
		//TODO
		return false;
	}
	
	/**
	 * Removes the book with the given title and author from the library.
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
	 * Puts the book with the specified title and author onto a shelf with the matching genre.
	 * 
	 * @param title the title of the book to be added to a shelf
	 * @param author the author of the book to be added to a shelf
	 * @return true if the book has been successfully added to a shelf, false if not
	 */
	public boolean addBookToShelf(String title, String author) {
		//TODO
		return false;
	}
	
}
