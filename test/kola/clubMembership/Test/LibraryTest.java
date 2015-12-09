/**
 * This tests all public methods of class Library.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 08/12/2015
 */

package kola.clubMembership.Test;

import java.util.List;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import java.util.PriorityQueue;
import static org.junit.Assert.*;
import kola.clubMembership.main.*;
import kola.clubMembership.members.*;
import static org.hamcrest.CoreMatchers.is;
import kola.clubMembership.administration.*;
import kola.clubMembership.exception.BookOutOfStockException;
import kola.clubMembership.exception.BookNotAvailableException;

/**
 *
 * @author kola
 */
public class LibraryTest {
    
    private static Member member1, member2, member3, member4;
    private static PriorityQueue<Member> borrowers;
    private static Member[] members;
    private Library clubLibrary;
    private Book book1, book2;
    private static Book book3;
    
    @BeforeClass
    public static void setUpClass() throws InterruptedException
    {
        member4 = new Student("Kola", "kola@gmail.com", "09123456789", "04-05-1990", 'M', 12, 20);
        Thread.sleep(1);
        member3 = new Staff("Kamiye", "abbasakande@gmail.com", "09123456789", "06-01-1967", 'M', 40, 12, 100.45);
        Thread.sleep(1);
        member2 = new Student("Yemisi", "danvery@gmail.com", "09123456789", "04-05-1991", 'M', 5, 4);
        Thread.sleep(1);
        member1 = new Staff("Verem", "abbasakande@gmail.com", "09123456789", "06-01-1967", 'M', 40, 12, 100.45);
        
        members = new Member[]{member1, member2, member3, member4};
        
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        member1 = member2 = member3 = member4 = null;
        members = null;
        book3 = null;
    }
    
    @Before
    public void setUp()
    {
        clubLibrary = new Library();
        book1 = new Book("Things Fall Apart", "Chinua Achebe", "9780435913502", 2);
        book2 = new Book("A Man of the People", "Chinua Achebe", "9780385086165", 3);
    }
    
    @After
    public void tearDown()
    {
        book1 = book2 = null;
        clubLibrary = null;
    }

    /**
     * Test of addBook method, of class Library.
     */
    @Test
    public void testAddBook_Book()
    {
        assertTrue(clubLibrary.getBookCollection().isEmpty());
        
        clubLibrary.addBook(book2);
        assertTrue(clubLibrary.getBookCollection().containsValue(book2));
        assertFalse(clubLibrary.getBookCollection().containsValue(book1));
        
        clubLibrary.addBook(book1);
        assertTrue(clubLibrary.getBookCollection().containsValue(book1));
    }

    /**
     * Test of addBook method, of class Library.
     */
    @Test
    public void testAddBook_List() {
        assertTrue(clubLibrary.getBookCollection().isEmpty());
        clubLibrary.addBook(groupBooksInAList());
        
        assertTrue(clubLibrary.getBookCollection().containsValue(book1));
        assertTrue(clubLibrary.getBookCollection().containsValue(book2));
    }

    /**
     * Test of removeBook method, of class Library.
     */
    @Test
    public void testRemoveBook() {
        clubLibrary.addBook(groupBooksInAList());
        assertEquals(2, clubLibrary.getBookCollection().get(book1.getBookName()).getNumberOfCopies());
        assertEquals(3, clubLibrary.getBookCollection().get(book2.getBookName()).getNumberOfCopies());
        
        // Test removal of all copies of a type of a book
        clubLibrary.removeBook(book2);
        assertEquals(0, clubLibrary.getBookCollection().get(book2.getBookName()).getNumberOfCopies());
        
        // Test removal of a specified number of copies of a type of a book
        clubLibrary.removeBook(new Book(book1.getBookName(), book1.getAuthor(), book1.getIsbnNumber(), 1));
        assertEquals(1, clubLibrary.getBookCollection().get(book1.getBookName()).getNumberOfCopies());
    }

    /**
     * Test of lendBook method, of class Library.
     * 
     * @throws BookOutOfStockException
     * @throws BookNotAvailableException 
     */
    @Test
    public void testLendBook() throws BookOutOfStockException, BookNotAvailableException
    {
        assertTrue(clubLibrary.getBorrowersList().isEmpty());
        
        clubLibrary.addBook(groupBooksInAList());
        borrowers = Borrow.queueForBook(members);
        clubLibrary.lendBook(borrowers, book1);
        
        assertEquals(0, clubLibrary.getBookCollection().get(book1.getBookName()).getNumberOfCopies());
        assertEquals(book1.getBookName(), clubLibrary.getBorrowersList().get(member1).get(0).getBookName());
        assertEquals(book1.getBookName(), clubLibrary.getBorrowersList().get(member3).get(0).getBookName());
        assertFalse(clubLibrary.getBorrowersList().containsKey(member2));
        assertFalse(clubLibrary.getBorrowersList().containsKey(member4));
        
        borrowers = Borrow.queueForBook(members);
        clubLibrary.lendBook(borrowers, book2);
        
        assertEquals(book2.getBookName(), clubLibrary.getBorrowersList().get(member1).get(1).getBookName());
        assertEquals(book2.getBookName(), clubLibrary.getBorrowersList().get(member3).get(1).getBookName());
        assertEquals(book2.getBookName(), clubLibrary.getBorrowersList().get(member4).get(0).getBookName());
        assertFalse(clubLibrary.getBorrowersList().containsKey(member2));
        
        borrowers = Borrow.queueForBook(members);
        
        try {
            clubLibrary.lendBook(borrowers, book1);
            fail("Expected an BookNotAvailableException to be thrown");
        } catch (BookNotAvailableException ex) {
            assertThat(ex.getMessage(), is("All have been lent out. Check back later!"));
        }
        
        
        borrowers = Borrow.queueForBook(members);
        book3 = new Book("Man in the Desert", "Lorenzo Abati", "9780385086169", 5);
        
        try {
            clubLibrary.lendBook(borrowers, book3);
            fail("Expected an BookOutOfStockException to be thrown");
        } catch (BookOutOfStockException ex) {
            assertThat(ex.getMessage(), is("Book is out of stock. Check back next semester!"));
        }
    }
    
    /**
     * Handle organizing of books of different types in a list
     * 
     * @return List
     */
    private List<Book> groupBooksInAList()
    {
        return new ArrayList<>(Arrays.asList(new Book[]{book1, book2}));
    }
}
