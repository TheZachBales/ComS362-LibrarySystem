package libsystemfinal;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private String username;
	private String password;
	private int atype;
	private Book book;
	private List<Book> checkoutbooks = new ArrayList<Book>();
	private String title;
	private String author;
	private DatabaseController dc;

    /**
     *creates an account based on given info
     * @param username name of this account
     * @param password password on this account
     * @param accounttype type of account
     */
	public Account(String username, String password, int accountype) {

		this.username = username;
		this.password = password;
		atype = accountype;
		this.dc = new DatabaseController();
	}

	/**
	 * creates an account based on given info
	 * @param username name on this account
	 * @param password password of this user
	 * @param type account type
	 * @param title title of book
	 * @param author author of book
	 */
	public Account(String username, String password, int type, String title,
			String author) {

		this.username = username;
		this.password = password;
		atype = type;
		this.title = title;
		this.author = author;
		this.dc = new DatabaseController();
	}

	/**
	 * Checks if the password given is correct and returns the type of the
	 * Account if it is.
	 * 
	 * @param password
	 *            the password to be validated for this Account
	 * @return the account type if the password is valid, -1 if it is not
	 */
	public int validateAndReturnType(String password) {

		if (this.password.equals(password))
			return atype;

		return -1;
	}

	/**
	 * Changes this Account to the type given.
	 * 
	 * @param at
	 *            the type of Account to change this to
	 * @return true if the Account type has been successfully changed
	 */
	public boolean changeAccount(int at) {

		atype = at;

		return true;
	}

	/**
	 * Checks out book based on the book
	 * 
	 * @param b book to checkout
	 * @return true if book successfully checked out
	 */
	public boolean checkOut(Book b) {

		checkoutbooks.add(b);
		for (int k = 0; k < checkoutbooks.size(); k++) {
			if (checkoutbooks.get(k).equals(b)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns book
	 * 
	 * @return true if book is successfully returned
	 */
	public boolean returnBook(Book b) {

		checkoutbooks.remove(b);
		for (int k = 0; k < checkoutbooks.size(); k++) {
			if (checkoutbooks.get(k).equals(b)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Gives the account type base 
	 * @return 0 or 1 for customer and Librarian 
	 */
	public int accountType() {

		return atype;
	}
	/**
	 * Keeps the list of objects books checkouted books
	 * @return a list of checkout books
	 */
	public List<Book> checkoutList() {
		List<Book> books = new ArrayList<Book>();
		if(title != null && author != null) {
			books.add(dc.getBook(title, author));
		}
		return books;
	}

	/**
	 * Gets the current username in logged account
	 * @return the username
	 */
	public String getUsername() {

		return username;
	}

	/**
	 * Gets the current password of the username
	 * @return password
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * @return
	 */
	public String getBookTitle() {

		return title;
	}

	public String getBookAuthor() {

		return author;
	}

	public Book getBook() {

		return book;
	}

	/**
	 * Gets the current type of account base on Customer and Librarian
	 * @return Customer or Librarian 
	 */
	public int getType() {

		return atype;
	}

}
