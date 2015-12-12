/**
 * This administers all activities in the library.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 05/12/2015
 */

package kola.clubMembership.administration;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import kola.clubMembership.main.*;
import kola.clubMembership.exception.*;

public class Library
{
    private HashMap<String, Book> bookCollection;
    private HashMap<Member, List<Book>> borrowersList;
    
    public Library()
    {
        bookCollection = new HashMap<>();
        borrowersList = new HashMap<>();
    }
    
    /**
     * Handle stocking of the library with a type of a book
     * 
     * @param book
     */
    public void addBook(Book book) 
    {
        String bookName = book.getBookName();
     
        if (bookCollection.putIfAbsent(bookName, book) != null) {
            bookCollection.get(bookName).incrementNumberOfCopies(book.getNumberOfCopies());
        }
    }
    
    /**
     * Handle stocking of the library with a collection of a type of a book
     * 
     * @param books 
     */
    public void addBook(List<Book> books)
    {
        books.stream().forEach((book) -> {
            addBook(book);
        });
    }
    
    /**
     * Handle removal of a type of a book from the library
     * 
     * @param book 
     */
    public void removeBook(Book book)
    {
        String bookName = book.getBookName();
        Book tmp = bookCollection.get(bookName);
        int availableCopiesOfBook = getAvailableCopiesOfBook(bookName);
        
        if (availableCopiesOfBook > 0) {
            int newNumberOfCopies = availableCopiesOfBook - book.getNumberOfCopies();
            
            if (newNumberOfCopies >= 0) {
                tmp.decrementNumberOfCopies(book.getNumberOfCopies());
            } else {
                while (tmp.getNumberOfCopies() > 0) {
                    tmp.decrementNumberOfCopies(1);
                }
            }
        }
    }
    
    /**
     * Handle lending of books to a group of borrowers
     * 
     * @param borrowers
     * @param book
     * @return String | NULL
     * @throws BookOutOfStockException
     * @throws BookNotAvailableException 
     */
    public String lendBook(PriorityQueue<Member> borrowers, Book book) throws BookOutOfStockException, BookNotAvailableException
    {
        int availableCopiesOfBook;
        
        if (getAvailableCopiesOfBook(book.getBookName()) > 0) {
            availableCopiesOfBook = getAvailableCopiesOfBook(book.getBookName());
        } else if (getAvailableCopiesOfBook(book.getBookName()) == -1) {
            borrowers.clear();
            throw new BookOutOfStockException();
        } else {
            borrowers.clear();
            throw new BookNotAvailableException();
        }
        
        if (borrowers.size() > availableCopiesOfBook)
        {
            issueBook(borrowers, book, availableCopiesOfBook);
        } else {
            issueBook(borrowers, book, borrowers.size());
        }
        
        return null;
    }
    
    /**
     * Get available copies of a type of a book in the library
     * 
     * @param bookName
     * @return Integer
     */
    private int getAvailableCopiesOfBook(String bookName)
    {
        if (bookCollection.containsKey(bookName)) {
            return bookCollection.get(bookName).getNumberOfCopies();
        }
        
        return -1;
    }
    
    /**
     * Helper method to method lendBook
     * 
     * @param borrowers
     * @param book
     * @param copiesToBeIssued 
     */
    private void issueBook(PriorityQueue<Member> borrowers, Book book, int copiesToBeIssued)
    {
        int counter = 0;
            
        while (counter < copiesToBeIssued) {
            Book bookToBeIssued = new Book(book.getBookName(), book.getAuthor(), book.getIsbnNumber(), 1);
            Member nextBorrower = borrowers.peek();
            
            if (borrowersList.putIfAbsent(nextBorrower, new ArrayList<>(Arrays.asList(bookToBeIssued))) != null) {
                borrowersList.get(nextBorrower).add(bookToBeIssued);
            }
                
            removeBook(bookToBeIssued);
            borrowers.poll();
            ++counter;
        }
        
        borrowers.clear();
    }
    
    /**
     * Retrieve the collection of all books in the library
     * 
     * @return bookCollection
     */
    public HashMap<String, Book> getBookCollection()
    {
        return bookCollection;
    }
    
    /**
     * Retrieve the collection of all members that have not returned book(s) 
     * they borrowed along with all the books they borrowed.
     * 
     * @return borrowersList
     */
    public HashMap<Member, List<Book>> getBorrowersList()
    {
        return borrowersList;
    }
}