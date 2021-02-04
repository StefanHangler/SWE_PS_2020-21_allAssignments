package a11se2020ws.model;

import a11se2020ws.presentation.IObserver;

import java.util.*;

/**
 * Represents the model in dem MVC-Pattern
 * stores the book-list and all registered views
 */
public class BookModel implements ISubject{

    private final HashSet<IObserver> views;
    private final HashMap<Long, Book> bookList;
    private static BookModel uniqueInstance = null;

    private BookModel(){
        this.views = new HashSet<>();
        this.bookList = new HashMap<>();
    }

    /**
     * create/return an unique instance of this class (Singleton-Pattern)
     * @return unique instance
     */
    public static BookModel getInstance(){
        if(uniqueInstance == null)
            uniqueInstance = new BookModel();

        return uniqueInstance;
    }

    @Override
    public void registerView(IObserver view) {
        views.add(view);
    }

    @Override
    public void removeView(IObserver view) {
        views.remove(view);
    }

    /**
     * add not existing book (unique isbn) to the hashmap
     * @param b book which have to be added
     */
    public void addBook(Book b){
        if(!this.bookList.containsKey(b.getIsbn())){
            this.bookList.put(b.getIsbn(),b);
            this.notifyViews();
        }
    }

    /**
     * remove an existing book from the hashmap
     * @param isbn unique number of the book
     */
    public void removeBook(long isbn){
        this.bookList.remove(isbn);
        this.notifyViews();

    }

    /**
     * Edit a existing book
     * @param b new data of the existing book
     */
    public void editBook(Book b){
        if(bookList.containsKey(b.getIsbn())){
            bookList.remove(b.getIsbn());
            bookList.put(b.getIsbn(),b);
            notifyViews();
        }
    }

    /**
     * @return a collection of all books
     */
    public Collection<Book> getBookList(){
        return bookList.values();
    }

    /**
     * notify all registered views, that new data is available
     */
    public void notifyViews(){
        for(IObserver view : views)
            view.update();
    }

    /**
     * Only used for testing
     * @return number of registered views
     */
    public int getNumberOfRegisteredViews(){
        return this.views.size();
    }
}
