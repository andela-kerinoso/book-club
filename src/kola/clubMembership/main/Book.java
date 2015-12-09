/**
 * This is the base class for all types of book in the club.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 24/11/2015
 */

package kola.clubMembership.main;

public class Book
{
    private String bookName, author, isbnNumber;
    private int numberOfCopies;
    
    public Book()
    {}
    
    @Override
    public String toString()
    {
        return String.format("Book: {name: %s, author: %s, ISBN: %s, copies: %d}", bookName, author, isbnNumber, numberOfCopies);
    }
    
    /**
     * Handle initialization of some properties at instantiation
     * 
     * @param bookName
     * @param author
     * @param isbnNumber
     * @param numberOfCopies 
     */
    public Book(String bookName, String author, String isbnNumber, Integer numberOfCopies)
    {
        this.bookName = bookName;
        this.author = author;
        this.isbnNumber = isbnNumber;
        this.numberOfCopies = numberOfCopies;
    }
    
    /**
     * @param bookName 
     */
    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }
    
    /**
     * @return bookName
     */
    public String getBookName()
    {
        return bookName;
    }
    
    /**
     * @param author 
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    /**
     * @return author
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * @param isbnNumber 
     */
    public void setIsbnNumber(String isbnNumber)
    {
        this.isbnNumber = isbnNumber;
    }
    
    /**
     * @return isbnNumber
     */
    public String getIsbnNumber()
    {
        return isbnNumber;
    }
    
    /**
     * @param numberOfCopies
     */
    public void setNumberOfCopies(int numberOfCopies)
    {
        this.numberOfCopies = numberOfCopies;
    }
    
    /**
     * @return numberOfCopies
     */
    public int getNumberOfCopies()
    {
        return numberOfCopies;
    }
}
