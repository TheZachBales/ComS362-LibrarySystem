package libsystemfinal;

public class Request {

	public static final int PENDING = 0;
	public static final int ACCEPTED = 1;
	public static final int DENIED = 2;
	
	private int rid;
	private int status;
	private Book book;
	private Account account;

	/**
	 * Creates a request with the given info
	 * 
	 * @param id
	 *            request id
	 * @param a
	 *            account that made the request
	 * @param b
	 *            book that was requested
	 * @param status
	 *            status of the request
	 */
	public Request(int id, Account a, Book b, int status) {

		rid = id;
		status = 0;
		account = a;
		book = b;

	}

	/**
	 * accepts the request
	 */
	public void accept() {

		status = 1;

	}

	/**
	 * denys the request
	 */
	public void deny() {

		status = 2;
	}
	
	/**
	 * gets the request id
	 * 
	 * @return the id of the request 
	 */
	public int getID() {

		return rid;
	}

	/**
	 * gets the account
	 * @return customer account
	 */
	public Account getCustomer() {

		return account;
	}

	/**
	 * gets the book object
	 * @return the book object
	 */
	public Book getBook() {

		return book;
	}

	/**
	 * gets the status of the request
	 * @return 0 or 1 if request is approved
	 */
	public int getStatus() {

		return status;
	}

}
