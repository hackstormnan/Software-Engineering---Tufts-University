package jrails;

import books.Book;
import books.TestClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ModelTest {

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model(){};
    }

//    @Test
//    public void id() {
//        assertThat(model.id(), notNullValue());
//    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public <T> void BookTest() {
        Model.reset();
//        Book book1 = new Book();
//        book1.title = "WHITE";
//        book1.author = "cat";
//        book1.num_copies = 777;
//        book1.save();
//
//        Book book2 = new Book();
//        book2.title = "BLACK";
//        book2.author = "dog";
//        book2.num_copies = 666;
//        book2.save();

//        TestClass testClass1 = new TestClass();
//        testClass1.title = "WHITE";
//        testClass1.author = "cat";
//        testClass1.num_copies = 11;
//        testClass1.has_author = true;
//        testClass1.has_title = false;
//        testClass1.save();
//
//
////        Book books = Model.find(Book.class, book1.id());
////        System.out.println(books.id());
////        System.out.println(books.title);
////        System.out.println(books.author);
////        System.out.println(books.num_copies);
//
//        TestClass testClass = Model.find(TestClass.class, testClass1.id());
//        System.out.println("Bookid is: " + testClass.id());
//        System.out.println("title is: " + testClass.title);
//        System.out.println("author is: " + testClass.author);
//        System.out.println("num_copies is: " + testClass.num_copies);
//        System.out.println(testClass.has_author);
//        System.out.println(testClass.has_title);
////
//        List<TestClass> all = Model.all(TestClass.class);
//        for (TestClass testClass : all) {
//            System.out.println("Bookid is: " + testClass.id());
//            System.out.println("title is: " + testClass.title);
//            System.out.println("author is: " + testClass.author);
//            System.out.println("num_copies is: " + testClass.num_copies);
//            System.out.println(testClass.has_author);
//            System.out.println(testClass.has_title);
//        }

        Model.reset();
        Book b = new Book();
        b.title = "Programming Languages: Build, Prove, and Compare";
        b.author = "Norman Ramsey";
        b.num_copies = 2222;
// The book b exists in memory but isn't saved to the db
        b.save(); // now the book is in the db

//


        System.out.println("find");
        Book book1 = Model.find(Book.class, b.id());
        System.out.println(book1.title);
        System.out.println(book1.author);
        System.out.println(book1.num_copies);

        System.out.println("all");
        List<Book> bs = Model.all(Book.class); // returns all books in the db
        for (Book book : bs) {
            System.out.println(book.title);
            System.out.println(book.author);
            System.out.println(book.num_copies);
        }

//        List<Book> all1 = Model.all(Book.class);
//        for (Book book : all1) {
//            System.out.println("Bookid is: " + book.id());
//            System.out.println("title is: " + book.title);
//            System.out.println("author is: " + book.author);
//            System.out.println("num_copies is: " + book.num_copies);
//        }


    }
}
