package libsys;

public class Request {

	private int rid;
	private Account customer;
	private Book book;
	private int status;
	
	public static final int PENDING = 0;
	public static final int ACCEPTED = 1;
	public static final int DENIED = 2;
	
	public Request(int rid, Account customer, Book book, int status) {
		this.rid = rid;
		this.customer = customer;
		this.book = book;
		this.status = status;
	}
	
	public int getID() {
		return this.rid;
	}
	
	public Account getCustomer() {
		return this.customer;
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public int getStatus() {
		return this.status;
	}
	
}
