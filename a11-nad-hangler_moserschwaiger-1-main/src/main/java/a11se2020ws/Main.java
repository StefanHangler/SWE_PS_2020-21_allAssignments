package a11se2020ws;

import a11se2020ws.model.Book;
import a11se2020ws.model.BookModel;
import a11se2020ws.presentation.MainFX;
import javafx.application.Platform;


public class Main {

	public static void main(String[] args) throws InterruptedException {

		Thread t = new Thread(new Runnable() {
			public void run() {
				MainFX.launch(MainFX.class, args);
			}
		});

		t.start();
		Thread.sleep(5000);

		BookModel model = BookModel.getInstance();

		Book b1 = new Book("Harry Potter", "J.K.Rowling", 1997, 978190754);
		Book b2 = new Book("Catching Fire", "Suzanne Collins", 2010, 9780439);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				model.addBook(b1); //add Book to model
			}
		});

		System.out.println("Added first book");
		Thread.sleep(5000);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				model.addBook(b2); //add Book to model
			}
		});

		System.out.println("Added second book");
		Thread.sleep(5000);

		Book b3 = new Book("The Hunger Games", b2.getAuthor(), 2008, b2.getIsbn());
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				model.editBook(b3); //edit Book
			}
		});

		System.out.println("Edit second book");

		Thread.sleep(5000);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				model.removeBook(b1.getIsbn()); //delete Book from model
			}
		});

		System.out.println("Remove first book");
	}
}
