/**
 *
 * @author kola
 */
package kola.clubMembership;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
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
    
    public void addBook(Book book) 
    {
        String bookName = book.getBookName();
     
        if (bookCollection.putIfAbsent(bookName, book) != null) {
            int newNumberOfCopies = getAvailableCopiesOfBook(bookName) + book.getNumberOfCopies();
            bookCollection.get(bookName).setNumberOfCopies(newNumberOfCopies);
        }
    }
    
    public void addBook(List<Book> books)
    {
        books.stream().forEach((book) -> {
            addBook(book);
        });
    }
    
    public String getBookCollection()
    {
        return String.format("%s", bookCollection);
    }
    
    public String getBorrowersList()
    {
        return String.format("%s", borrowersList);
    }
    
    public void removeBook(Book book)
    {
        String bookName = book.getBookName();
        int availableCopiesOfBook = getAvailableCopiesOfBook(bookName);
        
        if (availableCopiesOfBook >= 0) {
            int newNumberOfCopies = availableCopiesOfBook - book.getNumberOfCopies();
            
            if (newNumberOfCopies >= 0) {
                bookCollection.get(bookName).setNumberOfCopies(newNumberOfCopies);
            }
        }
    }
    
    public String lendBook(PriorityQueue<Member> borrowers, Book book)
    {
        int availableCopiesOfBook;
        
        if (getAvailableCopiesOfBook(book.getBookName()) > 0) {
            availableCopiesOfBook = getAvailableCopiesOfBook(book.getBookName());
        } else if (getAvailableCopiesOfBook(book.getBookName()) == -1) {
            try
            {
                throw new BookOutOfStockException();
            } catch (BookOutOfStockException e) {
                return e.getMessage();
            }
        } else {
            try
            {
                throw new BookNotAvailableException();
            } catch (BookNotAvailableException e) {
                return e.getMessage();
            }
        }
        
        if (borrowers.size() > availableCopiesOfBook)
        {
            issueBook(borrowers, book, availableCopiesOfBook);
        } else {
            issueBook(borrowers, book, borrowers.size());
        }
        
        return null;
    }
    
    private int getAvailableCopiesOfBook(String bookName)
    {
        if (bookCollection.containsKey(bookName)) {
            return bookCollection.get(bookName).getNumberOfCopies();
        }
        
        return -1;
    }
    
    private void issueBook(PriorityQueue<Member> borrowers, Book book, int copiesToBeIssued)
    {
        int counter = 0;
            
        while (counter < copiesToBeIssued) {
            Book bookToBeIssued = new Book(book.getBookName(), book.getAuthor(), book.getIsbnNumber(), 1);
            
            if (borrowersList.putIfAbsent(borrowers.peek(), new ArrayList<>(Arrays.asList(bookToBeIssued))) != null) {
                borrowersList.get(borrowers.peek()).add(bookToBeIssued);
            }
                
            removeBook(bookToBeIssued);
            borrowers.poll();
            ++counter;
        }
        
        borrowers.clear();
    }
}