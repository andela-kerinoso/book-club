/**
 * This is a custom exception for unavailability of a type of a book.
 * 
 * @author kola <kola.erinoso@gmail.com>
 * @date 08/12/2015
 */

package kola.clubMembership.exception;

public class BookOutOfStockException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Book is out of stock. Check back next semester!";
    }
}
