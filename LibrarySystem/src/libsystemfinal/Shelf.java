package libsystemfinal;

import java.util.ArrayList;
import java.util.List;

public class Shelf {

	private int cap;
	private String genre;
	private int id;
	private DatabaseController dc;
	List<Book> books;

	/**
	 * creates a shelf with the given info
	 * 
	 * @param shelfID
	 *            id of the shelf
	 * @param genre
	 *            genre of the shelf
	 * @param capacity
	 *            shelf capacity
	 * @param books
	 *            list of books on the shelf
	 */
	public Shelf(int shelfID, String genre, int capacity, List<Book> books) {

		cap = capacity;
		this.genre = genre;
		id = shelfID;
		this.books = books;
	}

	/**
	 * Puts the given Book object on this shelf.
	 * 
	 * @param b
	 *            the Book to be put on this shelf
	 * @return true if the Book object has been successfully added to this
	 *         shelf, false if not
	 */
	public boolean addBookToShelf(Book b) {
		return books.add(b);
	}

	public List<Book> getBooks() {

		return books;

	}

	/**
	 * returns the shelf capacity
	 * 
	 * @return shelf capacity
	 */
	public int getCapacity() {

		return cap;
	}

	/**
	 * returns the genre of the shelf
	 * 
	 * @return genre of shelf
	 */
	public String getGenre() {

		return genre;
	}

	/**
	 * returns id of shelf
	 * 
	 * @return shelf if
	 */
	public int getID() {

		return id;
	}
	
	public void setBookList(List<Book> l) {
		this.books = l;
	}

}
