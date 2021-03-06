package libsystemfinal;

import java.util.ArrayList;

public class LibraryController {

	Library l = new Library(20);

	/**
	 * Creates a new account
	 * 
	 * @param username
	 *            unique name on account
	 * @param password
	 *            password for account
	 * @return true on success
	 */
	public boolean createAccount(String username, String password) {

		l.createAccount(username, password);
		return true;
	}

	/**
	 * changes the account type
	 * 
	 * @param username
	 *            given account name to change
	 * @param at
	 *            account type
	 * @return true on success
	 */
	public boolean changeAccount(String username, int at) {

		l.changeAccount(username, at);
		return true;
	}

	/**
	 * logs in a user
	 * 
	 * @param username
	 *            given user
	 * @param password
	 *            given password
	 * @return account type
	 */
	public int login(String username, String password) {

		return l.login(username, password);
	}

	/**
	 * Lists all users in the system
	 * 
	 * @return users in system
	 */
	public ArrayList<Account> listUsers() {

		return (ArrayList<Account>) l.listUsers();
	}

	/**
	 * Logs out user
	 * 
	 * @param username
	 *            given username
	 * @return true on success
	 */
	public boolean logout(String username) {

		l.logout(username);
		return true;
	}

	/**
	 * creates a new shelf
	 * 
	 * @param genre
	 *            given genre
	 * @param capacity
	 *            capacity of shelf
	 * @return true on success
	 */
	public boolean createShelf(String genre, int capacity) {

		l.createShelf(genre, capacity);
		return true;
	}

	/**
	 * Adds a book to the library
	 * 
	 * @param title
	 *            given title
	 * @param author
	 *            given author
	 * @param genre
	 *            given genre
	 * @param ISBN
	 *            given ISBN
	 * @return true on success
	 */
	public boolean addBook(String title, String author, String genre,
			String ISBN) {

		l.addBook(title, author, genre, ISBN);
		return true;
	}

	/**
	 * removes a book from the library
	 * 
	 * @param title
	 *            given title
	 * @param author
	 *            given author
	 * @return true on success
	 */
	public boolean removeBook(String title, String author) {

		return l.removeBook(title, author);
	}

	/**
	 * adds a book to the shelf
	 * 
	 * @param title
	 *            given title
	 * @param author
	 *            given author
	 * @param sid
	 *            given shelf id
	 * @return true on success
	 */
	public boolean addBookToShelf(String title, String author, int sid) {

		return l.addBookToShelf(title, author, sid);
	}

	/**
	 * lists all books by a given title
	 * 
	 * @param title
	 *            given title
	 * @return list of books
	 */
	public ArrayList<Book> getBookByTitle(String title) {

		return (ArrayList<Book>) l.getBookByTitle(title);

	}

	/**
	 * Lists all books by a given author
	 * 
	 * @param author
	 *            given author
	 * @return list of books
	 */
	public ArrayList<Book> getBookByAuthor(String author) {

		return l.getBookByAuthor(author);
	}

	/**
	 * lists all books by a genre
	 * 
	 * @param genre
	 *            given genre
	 * @return list of books
	 */
	public ArrayList<Book> searchByGenre(String genre) {

		return (ArrayList<Book>) l.searchByGenre(genre);
	}

	/**
	 * checks out a book under a given user
	 * 
	 * @param username
	 *            given user
	 * @param title
	 *            given title
	 * @param author
	 *            given author
	 * @return true on success
	 */
	public boolean checkOutBook(String username, String title, String author) {

		l.checkOutBook(username, title, author);
		return true;
	}

	/**
	 * Returns a book to the library
	 * 
	 * @param title
	 *            given title
	 * @param author
	 *            given author
	 * @return true on success
	 */
	public boolean returnBook(String title, String author) {

		l.returnBook(title, author);
		return true;
	}

	/**
	 * checks the capacity of the library
	 * 
	 * @return capacity of library
	 */
	public int checkLibraryCapacity() {

		return l.checkLibraryCapacity();
	}

	/**
	 * lists all the books in the library
	 * 
	 * @return books in the library
	 */
	public ArrayList<Book> listLibraryBooks() {

		return (ArrayList<Book>) l.listLibraryBooks();
	}

	/**
	 * returns a list of all books in catalog
	 * 
	 * @return list of books
	 */
	public ArrayList<Book> listCatalogBooks() {

		return (ArrayList<Book>) l.listCatalogBooks();
	}

	/**
	 * accepts a request
	 * 
	 * @param rid
	 *            id of request
	 * @return true on sucess
	 */
	public boolean acceptRequest(int rid) {

		l.acceptRequest(rid);
		return true;
	}

	/**
	 * returns a list of all requests
	 * 
	 * @return list of requests
	 */
	public ArrayList<Request> viewRequests() {

		return l.viewRequests();
	}

	/**
	 * denys a request
	 * 
	 * @param rid
	 *            id of request
	 * @return true on success
	 */
	public boolean denyRequest(int rid) {

		l.denyRequest(rid);
		return true;
	}

	/**
	 * reserves a book
	 * 
	 * @param title
	 *            title of book
	 * @param author
	 *            author of book
	 * @return true on success
	 */
	public boolean reserveBook(String title, String author) {

		l.reserveBook(title, author);
		return true;
	}

	/**
	 * lists all checked out books
	 * 
	 * @return a list of books that are checked out
	 */
	public ArrayList<Book> viewAllCheckouts() {

		return l.viewAllCheckouts();
	}

	/**
	 * unreserves a book
	 * 
	 * @param title
	 *            title of book
	 * @param author
	 *            author of book
	 * @return true on success
	 */
	public boolean unreserveBook(String title, String author) {

		l.unreserveBook(title, author);
		return true;
	}

	/**
	 * requests a book from the catalog to be put in the library
	 * 
	 * @param username
	 *            user that requested
	 * @param title
	 *            title of book
	 * @param author
	 *            author of book
	 * @return true on success
	 */
	public int requestBookFromCatelog(String username, String title,
			String author) {

		return l.requestBookFromCatalog(username, title, author);
	}

}