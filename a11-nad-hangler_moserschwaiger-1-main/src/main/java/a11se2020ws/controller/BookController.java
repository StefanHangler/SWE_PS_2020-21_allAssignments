package a11se2020ws.controller;

import a11se2020ws.model.Book;
import a11se2020ws.model.BookModel;

/**
 * Represents the controller in the MVC-Pattern
 * Is called by the view
 * The corresponding methods from the model will be called
 */
public class BookController {

    private final BookModel bookModel;

    /**
     * constructs a {@link BookController} object
     * @param bookModel the corresponding model class
     */
    public BookController(BookModel bookModel) {
        this.bookModel = bookModel;
    }

    /**
     * create a new book object and
     * call the addBook-methode from the model class
     * @param title of the book
     * @param author of the book
     * @param year of the book
     * @param isbn of the book
     */
    public void addBook(String title, String author, int year, long isbn){
        if(isbn == 0)
            throw new RuntimeException("ISBN has to be a valid number and not 0");

        if(title == null || title.equals(""))
            throw new RuntimeException("A book must have a title, because a book with no title makes no sense");

        this.bookModel.addBook(new Book(title,author,year,isbn));
    }

    /**
     * call the removeBook-methode from the model class
     * @param isbn of the book
     */
    public void removeBook(long isbn){
        this.bookModel.removeBook(isbn);
    }

    /**
     * create a new book object and
     * call the editBook-methode from the model class
     * @param title of the book
     * @param author of the book
     * @param year of the book
     * @param isbn of the book
     */
    public void editBook(String title, String author, int year, long isbn){
        this.bookModel.editBook(new Book(title,author,year,isbn));
    }
}
