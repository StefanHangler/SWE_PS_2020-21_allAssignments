package a11se2020ws;

import a11se2020ws.model.BookModel;
import org.junit.jupiter.api.BeforeEach;
import a11se2020ws.presentation.MainFX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookModelRegisterViewTest {

    BookModel bookModel;

    @BeforeEach
    void setUp(){ bookModel = BookModel.getInstance(); }

    @Test
    void registerViewTest(){

        Thread t = new Thread(new Runnable() {
            public void run() {
                MainFX view1 = new MainFX();
                MainFX view2 = new MainFX();

                //test registration of a view
                bookModel.registerView(view1);
                bookModel.registerView(view2);
                assertEquals(bookModel.getNumberOfRegisteredViews(), 2);

                //remove registered view
                bookModel.removeView(view1);
                assertEquals(bookModel.getNumberOfRegisteredViews(), 1);
            }
        });

        t.start();
    }
}
