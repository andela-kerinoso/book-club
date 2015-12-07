/**
 *
 * @author kola
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
