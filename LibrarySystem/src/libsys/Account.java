package libsys;

public class Account {

	private String username;
	private String password;
	private int type;
	private Book currentBook;
	
	public Account(String username, String password, int type) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.currentBook = null;
	}
	
	public Account(String username, String password, int type, Book b) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.currentBook = b;
	}
	
	/**
	 * Checks if the password given is correct and returns the type of the Account if it is.
	 * 
	 * @param password the password to be validated for this Account
	 * @return the account type if the password is valid, -1 if it is not
	 */
	public int validateAndReturnType(String password) {
		//TODO
		return -1;
	}
	
	/**
	 * Changes this Account to the type given.
	 * 
	 * @param at the type of Account to change this to
	 * @return true if the Account type has been successfully changed
	 */
	public boolean changeAccount(int at) {
		//TODO
		return false;
	}
	
	public boolean checkOut(Book b) {
		//TODO
		return false;
	}
	
	public boolean returnBook() {
		//TODO
		return false;
	}
	
	public int getAccountType() {
		//TODO
		return -1;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getType() {
		return this.type;
	}
	
	public Book getCurrentBook() {
		return this.currentBook;
	}
	
}
