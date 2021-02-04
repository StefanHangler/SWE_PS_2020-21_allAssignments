package a11se2020ws;

import a11se2020ws.model.Book;
import a11se2020ws.model.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BookModelTest{

    BookModel bookModel;

    @BeforeEach
    void setUp(){ bookModel = BookModel.getInstance(); }

    @Test
    void bookListChangeTest(){
        Book b1 = new Book("Harry Potter", "J.K.Rowling", 1997, 978190754);
        Book b2 = new Book("Catching Fire", "Suzanne Collins", 2010, 9780439);
        Book b3 = new Book("Matilda", "Roald Dahl", 1978, 97801413);
        Book b4 = new Book("Tintenherz", "Cornelia Funke", 2003,97801413 );

        bookModel.addBook(b1);
        bookModel.addBook(b2);
        bookModel.addBook(b3);
        bookModel.addBook(b4);

        Collection<Book> books = bookModel.getBookList();

        assertTrue(books.contains(b1));
        assertTrue(books.contains(b2));
        assertTrue(books.contains(b3));
        assertFalse(books.contains(b4)); //book b4 has the same isbn as b3 -> has to be not in the list

        for(Book b : books)
            System.out.println(b.toString());

        //delete existing book
        bookModel.removeBook(b1.getIsbn());

        Collection<Book> books2 = bookModel.getBookList();
        assertFalse(books2.contains(b1));

        //edit existing book
        Book newB3 = new Book("MATILDA", b3.getAuthor(), 1988, b3.getIsbn());
        bookModel.editBook(newB3);

        Collection<Book> books3 = bookModel.getBookList();

        System.out.println(System.lineSeparator());
        for(Book b : books3)
            System.out.println(b.toString());

        assertFalse(books3.contains(b3));
        assertTrue(books3.contains(newB3));
    }
}