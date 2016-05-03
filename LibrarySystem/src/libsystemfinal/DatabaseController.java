package libsystemfinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
	
	/**
	 * This class assumes tables in SQL database
	 * created with the following commands:
	 * 
	 * CREATE TABLE Accounts
	 * (
	 * Username VARCHAR(100),
	 * Password VARCHAR(100),
	 * Type TINYINT,
	 * CurrentBookTitle VARCHAR(100),
	 * CurrentBookAuthor VARCHAR(100),
	 * );
	 * 
	 * CREATE TABLE Books
	 * (
	 * Title VARCHAR(100),
	 * Author VARCHAR(100),
	 * Genre VARCHAR(100),
	 * ISBN VARCHAR(100),
	 * ShelfID INT,
	 * Status TINYINT,
	 * CurrentCustomer VARCHAR(100)
	 * );
	 * 
	 * CREATE TABLE Shelves
	 * (
	 * ShelfID INT,
	 * Genre VARCHAR(100),
	 * Capacity INT
	 * );
	 * 
	 * CREATE TABLE Request
	 * (
	 * Customer VARCHAR(100),
	 * BookTitle VARCHAR(100),
	 * BookAuthor VARCHAR(100),
	 * Status TINYINT
	 * );
	 * 
	 */
	
	Connection conn;
	
	/**
	 * Main public constructor for creating a DatabaseController.
	 */
	public DatabaseController(){
		this.conn = createConnection();
	}
	
	/**
	 * Puts the Account into the database.
	 * 
	 * @param a The Account to be placed into the database.
	 * @return true if the Account is successfully entered, false if not
	 */
	public boolean putAccount(Account a) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet user = stmt.executeQuery("SELECT * FROM Accounts WHERE Username='" + a.getUsername() + "'");
			
			if(user.next()){
				Statement stmt2 = conn.createStatement();
				
				String title = (a.getBookTitle() != null) ? ("'" + a.getBookTitle() + "'") : "NULL" ;
				String author = (a.getBookAuthor() != null) ? ("'" + a.getBookAuthor() + "'") : "NULL" ;
				
				stmt2.executeUpdate("UPDATE Accounts SET Username='" + a.getUsername() + "',Password='" + a.getPassword() + "',Type='" + a.getType() + "',CurrentBookTitle=" + title + ",CurrentBookAuthor=" + author + " WHERE Username='" + a.getUsername() + "'");
			}
			else {
				Statement stmt2 = conn.createStatement();
				
				String title = (a.getBookTitle() != null) ? ("'" + a.getBookTitle() + "'") : "NULL" ;
				String author = (a.getBookAuthor() != null) ? ("'" + a.getBookTitle() + "'") : "NULL" ;
				
				stmt2.executeUpdate("INSERT INTO Accounts VALUES ('" + a.getUsername() + "','" + a.getPassword() + "','" + a.getType() + "'," + title + "," + author + ")");
			}
			
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Gets a list of all Accounts in the system.
	 * 
	 * @return a list of all the Accounts in the system
	 */
	public List<Account> listUsers() {
		try {
			List<Account> userList = new ArrayList<Account>();
			Statement stmt = conn.createStatement();
			ResultSet users = stmt.executeQuery("SELECT * FROM Accounts");
			
			while(users.next()) {
				Account a = new Account(users.getString("Username"), users.getString("Password"), users.getInt("Type"), users.getString("CurrentBookTitle"), users.getString("CurrentBookAuthor"));
				userList.add(a);
			}
			return userList;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets the Account object from the database that has the given username.
	 * 
	 * @param username the username of the Account to be retrieved
	 * @return the Account in the database with the given username
	 */
	public Account getAccount(String username) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet user = stmt.executeQuery("SELECT * FROM Accounts WHERE Username='" + username + "'");
			if(user.next()) {
				Account a = new Account(user.getString("Username"), user.getString("Password"), user.getInt("Type"), user.getString("CurrentBookTitle"), user.getString("CurrentBookAuthor"));
				return a;
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Puts the given Shelf object into the database
	 * 
	 * @param s the shelf to be inserted into the database
	 * @return true if the shelf has been successfully put into the database, false if not
	 */
	public boolean putShelf(Shelf s) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet shelf = stmt.executeQuery("SELECT * FROM Shelves WHERE ShelfID='" + s.getID() + "'");
			if(shelf.next()) {
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("UPDATE Shelves SET ShelfID='" + s.getID() + "',Genre='" + s.getGenre() + "',Capacity='" + s.getCapacity() + "' WHERE ShelfID='" + s.getID() + "'");
			}
			else {
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("INSERT INTO Shelves VALUES ('" + s.getID() + "','" + s.getGenre() + "','" + s.getCapacity() + "')");
			}
			List<Book> books = s.getBooks();
			for(int i = 0; i < books.size(); i++) {
				putBook(books.get(i));
			}
			return true;
		}
		catch(SQLException e) {
			return false;
		}
	}
	
	/**
	 * Puts the given Book object into the database
	 * 
	 * @param b the Book object to be put into the database
	 * @return true if the book has been successfully put into the database, false if not
	 */
	public boolean putBook(Book b) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet bookCheck = stmt.executeQuery("SELECT * FROM Books WHERE Title='" + b.getTitle() + "' AND Author='" + b.getAuthor() + "'");
			
			if(bookCheck.next()) {
				String shelfID = "" + ((b.getShelf() != null) ? ("'" + b.getShelf().getID() + "'") : "NULL");
				String customerName = ((b.getCheckOutAccount() != null) ? ("'" + b.getCheckOutAccount() + "'") : "NULL");
				
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("UPDATE Books SET Title='" + b.getTitle() + "',Author='" + b.getAuthor() + "',Genre='" + b.getGenre() + "',ISBN='" + b.getISBN() + "',ShelfID=" + shelfID + ",Status='" + b.getStatus() + "',CurrentCustomer=" + customerName + " WHERE Title='" + b.getTitle() + "' AND Author='" + b.getAuthor() + "'");
			}
			else {
				String shelfID = "" + ((b.getShelf() != null) ? ("'" + b.getShelf().getID() + "'") : "NULL");
				String customerName = "" + ((b.getCheckOutAccount() != null) ? ("'" + b.getCheckOutAccount() + "'") : "NULL");
				
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("INSERT INTO Books VALUES ('" + b.getTitle() + "','" + b.getAuthor() + "','" + b.getGenre() + "','" + b.getISBN() + "'," + shelfID + ",'" + b.getStatus() + "'," + customerName + ")");
			}
			
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Gets from the database the shelf with the given ID number
	 * 
	 * @param sid the ID number of the Shelf object to be gotten from the database
	 * @return the Shelf object with the given ID number
	 */
	public Shelf getShelf(int sid) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet shelf = stmt.executeQuery("SELECT * FROM Shelves WHERE ShelfID='" + sid + "'");
			if(shelf.next()) {
				Shelf s = new Shelf(shelf.getInt("ShelfID"), shelf.getString("Genre"), shelf.getInt("Capacity"), null);
				List<Book> books = getBooksByShelf(s);
				s.setBookList(books);
				return s;
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets a list of books with the given title.
	 * 
	 * @param title
	 * @return list of books with the given title
	 */
	public List<Book> getBookByTitle(String title) {
		try {
			List<Book> l = new ArrayList<Book>();
			Statement stmt = conn.createStatement();
			ResultSet books = stmt.executeQuery("SELECT * FROM Books WHERE Title='" + title + "'");
			while(books.next()) {
				Book b = new Book(books.getString("Title"), books.getString("Author"), books.getString("Genre"), books.getString("ISBN"), books.getInt("ShelfID"), books.getInt("Status"), books.getString("CurrentCustomer"));
				l.add(b);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets a list of books with the given author.
	 * 
	 * @param author
	 * @return the list of books with tht given author
	 */
	public List<Book> getBookByAuthor(String author) {
		try {
			List<Book> l = new ArrayList<Book>();
			Statement stmt = conn.createStatement();
			ResultSet books = stmt.executeQuery("SELECT * FROM Books WHERE Author='" + author + "'");
			while(books.next()) {
				Book b = new Book(books.getString("Title"), books.getString("Author"), books.getString("Genre"), books.getString("ISBN"), books.getInt("ShelfID"), books.getInt("Status"), books.getString("CurrentCustomer"));
				l.add(b);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets a list of shelves based on the genre.
	 * 
	 * @param genre
	 * @return a list of shelves with tht given genre
	 */
	public List<Shelf> getShelf(String genre) {
		try {
			List<Shelf> l = new ArrayList<Shelf>();
			Statement stmt = conn.createStatement();
			ResultSet shelves = stmt.executeQuery("SELECT * FROM Shelves WHERE Genre='" + genre + "'");
			while(shelves.next()) {
				Shelf s = new Shelf(shelves.getInt("ShelfID"), shelves.getString("Genre"), shelves.getInt("Capacity"), null);
				List<Book> books = getBooksByShelf(s);
				s.setBookList(books);
				l.add(s);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets the book with the given title and author.
	 * 
	 * @param title
	 * @param author
	 * @return book with the given title an author
	 */
	public Book getBook(String title, String author) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet book = stmt.executeQuery("SELECT * FROM Books WHERE Title='" + title + "' AND Author='" + author + "'");
			
			if(book.next()) {
				Book b = new Book(book.getString("Title"), book.getString("Author"), book.getString("Genre"), book.getString("ISBN"), book.getInt("ShelfID"), book.getInt("Status"), book.getString("CurrentCustomer"));
				return b;
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets a list off all shelves
	 * 
	 * @return a list of all shelves
	 */
	public List<Shelf> getAllShelves() {
		try {
			List<Shelf> l = new ArrayList<Shelf>();
			Statement stmt = conn.createStatement();
			ResultSet shelves = stmt.executeQuery("SELECT * FROM Shelves");
			while(shelves.next()) {
				Shelf s = new Shelf(shelves.getInt("ShelfID"), shelves.getString("Genre"), shelves.getInt("Capacity"), null);
				List<Book> books = getBooksByShelf(s);
				s.setBookList(books);
				l.add(s);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets all books in the library.
	 * 
	 * @return A list of books in the library
	 */
	public List<Book> listLibraryBooks() {
		try {
			List<Book> l = new ArrayList<Book>();
			Statement stmt = conn.createStatement();
			ResultSet books = stmt.executeQuery("SELECT * FROM Books WHERE Status='" + Book.LIBRARY + "' OR Status='" + Book.CHECKED_OUT + "' OR Status='" + Book.RESERVED + "'");
			while(books.next()) {
				Book b = new Book(books.getString("Title"), books.getString("Author"), books.getString("Genre"), books.getString("ISBN"), books.getInt("ShelfID"), books.getInt("Status"), books.getString("CurrentCustomer"));
				l.add(b);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets a list of all books  in the catalog
	 * 
	 * @return a list of all books in the catalog
	 */
	public List<Book> listCatalogBooks() {
		try {
			List<Book> l = new ArrayList<Book>();
			Statement stmt = conn.createStatement();
			ResultSet books = stmt.executeQuery("SELECT * FROM Books WHERE Status='" + Book.CATALOG + "'");
			while(books.next()) {
				Book b = new Book(books.getString("Title"), books.getString("Author"), books.getString("Genre"), books.getString("ISBN"), books.getInt("ShelfID"), books.getInt("Status"), books.getString("CurrentCustomer"));
				l.add(b);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Put a Request object into the database
	 * 
	 * @param r
	 * @return true if the Request object was successfully put into the database, false otherwise
	 */
	public boolean putRequest(Request r) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet request = stmt.executeQuery("SELECT * FROM Requests WHERE RequestID='" + r.getID() + "'");
			
			if(request.next()) {
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("UPDATE Requests SET Customer='" + r.getCustomer().getUsername() + "',BookTitle='" + r.getBook().getTitle() + "',BookAuthor='" + r.getBook().getAuthor() + "',Status='" + r.getStatus() + "' WHERE RequestID='" + r.getID() + "'");
			}
			else {
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("INSERT INTO Requests VALUES ('" + r.getID() + "','" + r.getCustomer().getUsername() + "','" + r.getBook().getTitle() + "','" + r.getBook().getAuthor() + "','" + r.getStatus() + "')");
			}
			
			return true;
		}
		catch(SQLException e) {
			return false;
		}
	}
	
	/**
	 * Get a Request object from the database
	 * 
	 * @param rid
	 * @return the Request object with the given request id
	 */
	public Request getRequest(int rid) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet request = stmt.executeQuery("SELECT * FROM Requests WHERE RequestID='" + rid + "'");
			if(request.next()) {
				Account a = getAccount(request.getString("Customer"));
				Book b = getBook(request.getString("BookTitle"), request.getString("BookAuthor"));
				Request r = new Request(request.getInt("RequestID"), a, b, request.getInt("Status"));
				return r;
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets a list of all requests.
	 * 
	 * @return a list of all requests in the system
	 */
	public List<Request> viewRequests() {
		try {
			List<Request> l = new ArrayList<Request>();
			
			Statement stmt = conn.createStatement();
			ResultSet requests = stmt.executeQuery("SELECT * FROM Requests");
			
			while(requests.next()) {
				Account a = getAccount(requests.getString("Customer"));
				Book b = getBook(requests.getString("BookTitle"), requests.getString("BookAuthor"));
				Request r = new Request(requests.getInt("RequestID"), a, b, requests.getInt("Status"));
				l.add(r);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets a list of all checked out books.
	 * 
	 * @return a list of all checked out books
	 */
	public List<Book> getCheckouts() {
		try {
			List<Book> l = new ArrayList<Book>();
			Statement stmt = conn.createStatement();
			ResultSet books = stmt.executeQuery("SELECT * FROM Books WHERE Status='" + Book.CHECKED_OUT + "'");
			while(books.next()) {
				Book b = new Book(books.getString("Title"), books.getString("Author"), books.getString("Genre"), books.getString("ISBN"), books.getInt("ShelfID"), books.getInt("Status"), books.getString("CurrentCustomer"));
				l.add(b);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Gets a list of books by their shelf id number
	 * 
	 * @param sid
	 * @return a list of books stored on the given shelf
	 */
	private List<Book> getBooksByShelf(Shelf s) {
		try {
			List<Book> l = new ArrayList<Book>();
			Statement stmt = conn.createStatement();
			ResultSet books = stmt.executeQuery("SELECT * FROM Books WHERE ShelfID='" + s.getID() + "'");
			while(books.next()) {
				Book b = new Book(books.getString("Title"), books.getString("Author"), books.getString("Genre"), books.getString("ISBN"), books.getInt("ShelfID"), books.getInt("Status"), books.getString("CurrentCustomer"));
				l.add(b);
			}
			
			return l;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * Creates a connection to the database
	 * 
	 * @return a Connection object that is connected to the database
	 */
	private Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		String dbUrl = "jdbc:mysql://173.17.63.77:3306/Library_db";
		String user = "team7";
		String password = "^h;>8jp.";
		try {
			conn = DriverManager.getConnection(dbUrl, user, password);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}