package a11se2020ws;

import a11se2020ws.controller.BookController;
import a11se2020ws.model.Book;
import a11se2020ws.model.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookControllerTest {
    BookController bookController;
    BookModel bookModel;

    @BeforeEach
    void setUp(){
        bookModel = BookModel.getInstance();
        bookController = new BookController(bookModel);
    }

    @Test
    void BookMethodsTest() {
        //test exception scenarios
        assertThrows(RuntimeException.class, () -> bookController.addBook("Harry Potter", "J.K.Rowling", 1997, 0));
        assertThrows(RuntimeException.class, () -> bookController.addBook("", "J.K.Rowling", 1997, 978190754));
        assertThrows(RuntimeException.class, () -> bookController.addBook(null, "J.K.Rowling", 1997, 978190754));

        //add books
        bookController.addBook("Matilda", "Roald Dahl", 1988, 97801413);
        bookController.addBook("Catching Fire", "Shakespeare", 2010, 9780439);

        ArrayList<Book> books = new ArrayList<>(bookModel.getBookList());
        assertEquals(books.get(0).getIsbn(), 97801413);
        assertEquals(books.get(1).getIsbn(), 9780439);

        //remove a book
        bookController.removeBook(97801413);
        ArrayList<Book> books2 = new ArrayList<>(bookModel.getBookList());
        assertEquals(books2.size(), 1);

        //edit a existing book
        bookController.editBook("The Hunger Games", "Suzanne Collins", 2008, 9780439);
        ArrayList<Book> books3 = new ArrayList<>(bookModel.getBookList());
        assertEquals(books3.get(0).getYear(), 2008);
    }
}
