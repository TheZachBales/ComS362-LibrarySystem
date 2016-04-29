package libsys;

import java.util.List;

public class Main {

	public static void main(String[] args){
		//Initialize DatabaseController
		DatabaseController dbc = new DatabaseController();
		
		//Test putAccount
		System.out.println("putAccount");
		Account a1 = new Account("zlbales", "password", 0);
		if(dbc.putAccount(a1)) {
			System.out.println("Successfully put account!");
		}
		else {
			System.out.println("Failed to put account!");
		}
		System.out.println();
		
		//Test listUsers
		System.out.println("listUsers");
		List<Account> users = dbc.listUsers();
		if(users != null) {
			for(int i = 0; i < users.size(); i++) {
				System.out.println(users.get(i).getUsername());
			}
		}
		else {
			System.out.println("listUsers failed");
		}
		System.out.println();
		
		//Test getAccount
		System.out.println("getAccount");
		Account a2 = dbc.getAccount("zlbales");
		if(a2 != null) {
			System.out.println(a2.getUsername());
		}
		else {
			System.out.println("getAccount failed");
		}
		System.out.println();
		
		//Test putShelf
		System.out.println("putShelf");
		Shelf s1 = new Shelf(1, "Fantasy", 100);
		if(dbc.putShelf(s1)) {
			System.out.println("Shelf successfully put!");
		}
		else {
			System.out.println("Shelf put failed!");
		}
		System.out.println();
		
		//Test putBook
		System.out.println("putBook");
		Book b1 = new Book("A Game of Thrones", "George R. R. Martin", "Fantasy", "978-0553593716");
		if(dbc.putBook(b1)) {
			System.out.println("Book successfully put!");
		}
		else {
			System.out.println("Book put failed!");
		}
		System.out.println();
		
		//Test getShelf
		System.out.println("getShelf");
		Shelf s2 = dbc.getShelf(1);
		if(s2 != null) {
			System.out.println("Genre: " + s2.getGenre());
			System.out.println("Capacity: " + s2.getCapacity());
		}
		else {
			System.out.println("Shelf get failed!");
		}
		System.out.println();
		
		//Test getBook
		System.out.println("getBook");
		Book b2 = dbc.getBook("A Game of Thrones", "George R. R. Martin");
		if(b2 != null) {
			System.out.println("Title: " + b2.getTitle());
			System.out.println("Author: " + b2.getAuthor());
		}
		else {
			System.out.println("Book getting failed!");
		}
		System.out.println();
		
		//Test getAllShelves
		System.out.println("getAllShelves");
		List<Shelf> l1 = dbc.getAllShelves();
		if(l1 != null) {
			for(int i = 0; i < l1.size(); i++) {
				System.out.println("Genre: " + l1.get(i).getGenre());
				System.out.println("Capacity: " + l1.get(i).getCapacity());
			}
		}
		else {
			System.out.println("Getting all shelves failed!");
		}
		System.out.println();
		
		//Test listLibraryBooks
		System.out.println("listLibraryBooks");
		List<Book> l2 = dbc.listLibraryBooks();
		if(l2 != null) {
			for(int i = 0; i < l2.size(); i++) {
				System.out.println("Title: " + l2.get(i).getTitle());
				System.out.println("Author: " + l2.get(i).getAuthor());
			}
		}
		else {
			System.out.println("Listing library books failed!");
		}
		System.out.println();
		
		//Test listCatalogBooks
		System.out.println("listCatalogBooks");
		List<Book> l3 = dbc.listCatalogBooks();
		if(l3 != null) {
			for(int i = 0; i < l3.size(); i++) {
				System.out.println("Title: " + l3.get(i).getTitle());
				System.out.println("Author: " + l3.get(i).getAuthor());
			}
		}
		else {
			System.out.println("Listing catalog books failed!");
		}
		System.out.println();
		
		//Test putRequest
		System.out.println("putRequest");
		Request r1 = new Request(1, a1, b1, Request.PENDING);
		if(dbc.putRequest(r1)) {
			System.out.println("Request put successfully!");
		}
		else {
			System.out.println("Request put failed!");
		}
		System.out.println();
		
		//Test getRequest
		System.out.println("getRequest");
		Request r2 = dbc.getRequest(1);
		if(r2 != null) {
			System.out.println("Customer: " + r2.getCustomer().getUsername());
			System.out.println("Title: " + r2.getBook().getTitle());
		}
		else {
			System.out.println("Request get failed!");
		}
		System.out.println();
		
		//Test viewRequests
		System.out.println("viewRequests");
		List<Request> l4 = dbc.viewRequests();
		if(l4 != null) {
			for(int i = 0; i < l4.size(); i++) {
				System.out.println("Customer: " + l4.get(i).getCustomer().getUsername());
				System.out.println("Title: " + l4.get(i).getBook().getTitle());
			}
		}
		else {
			System.out.println("Viewing requests failed!");
		}
		System.out.println();
		
		//Test getCheckouts
		System.out.println("getCheckouts");
		List<Book> l5 = dbc.getCheckouts();
		if(l5 != null) {
			for(int i = 0; i < l5.size(); i++) {
				System.out.println("Title: " + l5.get(i).getTitle());
				System.out.println("Author: " + l5.get(i).getAuthor());
			}
		}
		else {
			System.out.println("Getting check outs failed!");
		}
		System.out.println();
		
		/*
		//Test getBooksByShelf
		System.out.println("getBooksByShelf");
		List<Book> l6 = dbc.getBooksByShelf(1);
		if(l5 != null) {
			for(int i = 0; i < l5.size(); i++) {
				System.out.println("Title: " + l6.get(i).getTitle());
				System.out.println("Author: " + l6.get(i).getAuthor());
				System.out.println("Shelf: " + l6.get(i).getShelf().getID());
			}
		}
		else {
			System.out.println("Getting books by shelf failed!");
		}
		System.out.println();
		*/
	}
	
}
