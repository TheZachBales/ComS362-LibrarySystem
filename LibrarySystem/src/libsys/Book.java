package libsys;

public class Book {

	private String title;
	private String author;
	private String genre;
	private String ISBN;
	private Shelf shelf;
	private int status;
	private Account currentCustomer;
	
	public static final int CATALOG = 0;
	public static final int LIBRARY = 1;
	public static final int CHECKED_OUT = 2;
	
	public Book(String title, String author, String genre, String ISBN) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.ISBN = ISBN;
		this.shelf = null;
		this.status = Book.CATALOG;
		this.currentCustomer = null;
	}
	
	public Book(String title, String author, String genre, String ISBN, Shelf shelf, int status, Account currentCustomer) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.ISBN = ISBN;
		this.shelf = shelf;
		this.status = Book.CATALOG;
		this.currentCustomer = currentCustomer;
	}
	
	/**
	 * Associates this Book object with the Shelf object given.
	 * 
	 * @param s the Shelf object to attach to this book
	 * @return true if the Shelf has been successfully set, false if not
	 */
	public boolean putBookOnShelf(Shelf s) {
		//TODO
		return false;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public String getISBN() {
		return this.ISBN;
	}
	
	public Shelf getShelf() {
		return this.shelf;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public Account getCurrentCustomer() {
		return this.currentCustomer;
	}
	
}
