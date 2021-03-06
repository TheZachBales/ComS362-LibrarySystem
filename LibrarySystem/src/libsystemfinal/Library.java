package libsystemfinal;

import java.util.ArrayList;
import java.util.List;

public class Library {

	//private Account account;
	private DatabaseController dc;
	private int cap;

	/**
	 * construsts a library with a given capacity
	 * 
	 * @param capacity
	 *            max capacity of book
	 */
	public Library(int capacity) {

		cap = capacity;
		dc = new DatabaseController();
	}

	/**
	 * Creates an Account object with the given username and password.
	 * 
	 * @param username
	 *            the username for the new Account
	 * @param password
	 *            the password for the new Account
	 * @return true if the Account object is successfully created, false if not
	 */
	public boolean createAccount(String username, String password) {

		Account account = new Account(username, password, 0);
		dc.putAccount(account);
		return true;
	}

	/**
	 * Changes the type of Account to the type provided.
	 * 
	 * @param username
	 *            the username of the Account to be changed
	 * @param at
	 *            account type to convert the Account to
	 * @return true if the Account is successfully upgraded, false if not
	 */
	public boolean changeAccount(String username, int at) {

		Account account = dc.getAccount(username);
		account.changeAccount(at);
		dc.putAccount(account);
		return true;
	}

	/**
	 * Logs the user into the Account with the given username.
	 * 
	 * @param username
	 *            the username of the Account to log in to
	 * @param password
	 *            the password for the Customer account
	 * @return type of Account that has been logged in to
	 */
	public int login(String username, String password) {

		Account a = dc.getAccount(username);

		return a.validateAndReturnType(password);
	}

	/**
	 * Returns a list of all Accounts in the system
	 * 
	 * @return list of all Accounts in the system
	 */
	public List<Account> listUsers() {

		return dc.listUsers();
	}

	public boolean logout(String username) {

		return true;
	}

	/**
	 * Creates a shelf to hold books of the specified genre and being able to
	 * hold up to the number of books specified by the capacity.
	 * 
	 * @param genre
	 *            the name of the genre of books that will be put on this shelf
	 * @param capacity
	 *            the maximum number of books that will be able to be put on
	 *            this shelf
	 * @return true if the shelf is created successfully, false if not
	 */
	public boolean createShelf(String genre, int capacity) {

		List<Shelf> shelf = dc.getAllShelves();
		int sid = 0;
		for (int i = 0; i < shelf.size(); i++) {
			if (sid < shelf.get(i).getID()) {
				sid = shelf.get(i).getID();
			}
		}
		sid++;

		Shelf f = new Shelf(sid, genre, capacity, new ArrayList<Book>());
		dc.putShelf(f);
		return true;
	}

	/**
	 * Adds the book with the given title, author, and ISBN number into the
	 * library from the catalog.
	 * 
	 * @param title
	 *            the title of the book to be added
	 * @param author
	 *            the author of the book to be added
	 * @param ISBN
	 *            the ISBN code of the book to be added
	 * @return true if the book has been successfully added, false if not
	 */
	public boolean addBook(String title, String author, String genre,
			String ISBN) {

		Book b = new Book(title, author, genre, ISBN);
		dc.putBook(b);
		return true;
	}

	/**
	 * Removes the book with the given title and author from the library.
	 * 
	 * @param title
	 *            the title of the book to be removed
	 * @param author
	 *            the author of the book to be removed
	 * @return true if the book has been successfully removed, false if not
	 */
	public boolean removeBook(String title, String author) {

		Book b = dc.getBook(title, author);
		if(b != null) {
			b.updateStatus(Book.CATALOG);
			b.setAccount(null);
			b.setShelf(0);
			return dc.putBook(b);
		}
		else {
			return false;
		}
	}

	/**
	 * Puts the book with the specified title and author onto a shelf with the
	 * matching genre.
	 * 
	 * @param title
	 *            the title of the book to be added to a shelf
	 * @param author
	 *            the author of the book to be added to a shelf
	 * @return true if the book has been successfully added to a shelf, false if
	 *         not
	 */
	public boolean addBookToShelf(String title, String author, int sid) {

		Shelf s = dc.getShelf(sid);
		Book b = dc.getBook(title, author);
		if(b != null && s != null) {
			if(s.addBookToShelf(b) && b.putBookOnShelf(s)) {
				return dc.putShelf(s) && dc.putBook(b);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * returns a list of books that match the given title
	 * 
	 * @param title
	 *            title of books to return
	 * @return list of books
	 */
	public ArrayList<Book> getBookByTitle(String title) {

		return (ArrayList<Book>) dc.getBookByTitle(title);

	}

	/**
	 * returns a list of books that match the author
	 * 
	 * @param author
	 *            author of book to match
	 * @return list of books
	 */
	public ArrayList<Book> getBookByAuthor(String author) {

		return (ArrayList<Book>) dc.getBookByAuthor(author);
	}

	/**
	 * returns a list of books that match the genre
	 * 
	 * @param genre
	 *            genre to match books with
	 * @return list of books
	 */
	public List<Book> searchByGenre(String genre) {

		ArrayList<Book> books = new ArrayList<Book>();
		ArrayList<Shelf> f = (ArrayList<Shelf>) dc.getAllShelves();
		for (int i = 0; i < f.size(); i++) {
			if (f.get(i).getGenre().equals(genre)) {
				books.addAll(f.get(i).getBooks());
			}
		}
		return books;
	}

	/**
	 * checks out a book based on the information given
	 * 
	 * @param username
	 *            user that is checking out the book
	 * @param title
	 *            title of book
	 * @param author
	 *            author of book
	 * @return true on success
	 */
	public boolean checkOutBook(String username, String title, String author) {

		Book b = dc.getBook(title, author);
		Account a = dc.getAccount(username);
		b.checkOut(a);
		a.checkOut(b);
		dc.putBook(b);
		dc.putAccount(a);
		return true;
	}

	/**
	 * returns a book to the library
	 * 
	 * @param title
	 *            title of book
	 * @param author
	 *            author of book
	 * @return true on success
	 */
	public boolean returnBook(String title, String author) {

		Book b = dc.getBook(title, author);
		Account a = b.getCheckOutAccount();
		b.returnToLibrary();
		a.returnBook(b);
		dc.putBook(b);
		dc.putAccount(a);
		return true;
	}

	/**
	 * returns the library's capacity
	 * 
	 * @return library's capacity
	 */
	public int checkLibraryCapacity() {

		// ArrayList<Shelf> s = (ArrayList<Shelf>) dc.getAllShelves();
		// int cap = 0;
		// for (int i = 0; i < s.size(); i++) {
		// cap += s.get(i).getCapacity();
		// }
		return cap;
	}

	/**
	 * returns a list of all books in library
	 * 
	 * @return list of books
	 */
	public ArrayList<Book> listLibraryBooks() {

		return (ArrayList<Book>) dc.listLibraryBooks();
	}

	/**
	 * returns a list of all books in catalog
	 * 
	 * @return list of books
	 */
	public ArrayList<Book> listCatalogBooks() {

		return (ArrayList<Book>) dc.listCatalogBooks();
	}

	/**
	 * accepts a request
	 * 
	 * @param rid
	 *            id of request
	 * @return true on sucess
	 */
	public boolean acceptRequest(int rid) {

		Request r = dc.getRequest(rid);
		r.accept();
		dc.putRequest(r);
		return true;
	}

	/**
	 * returns a list of all requests
	 * 
	 * @return list of requests
	 */
	public ArrayList<Request> viewRequests() {

		dc.viewRequests();
		return (ArrayList<Request>) dc.viewRequests();
	}

	/**
	 * denys a request
	 * 
	 * @param rid
	 *            id of request
	 * @return true on success
	 */
	public boolean denyRequest(int rid) {

		Request r = dc.getRequest(rid);
		r.deny();
		dc.putRequest(r);
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

		Book b = dc.getBook(title, author);
		b.reserve();
		dc.putBook(b);
		return true;
	}

	/**
	 * lists all checked out books
	 * 
	 * @return a list of books that are checked out
	 */
	public ArrayList<Book> viewAllCheckouts() {

		return (ArrayList<Book>) dc.getCheckouts();
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

		Book b = dc.getBook(title, author);
		b.reserve();
		dc.putBook(b);
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
	public int requestBookFromCatalog(String username, String title,
			String author) {

		List<Request> re = dc.viewRequests();
		int rid = 0;
		for (int i = 0; i < re.size(); i++) {
			if (rid < re.get(i).getID()) {
				rid = re.get(i).getID();
			}
		}
		rid++;

		Book b = dc.getBook(title, author);
		Account a = dc.getAccount(username);
		if (b != null && b.checkStatus() == Book.CATALOG) {
			int type = a.accountType();

			Request r = new Request(rid, a, b, 0);
			// int j = b.setRequest(a);
			dc.putRequest(r);
			// dc.putBook(b);
		}
		else {
			return -1;
		}
		return rid;

	}

}
