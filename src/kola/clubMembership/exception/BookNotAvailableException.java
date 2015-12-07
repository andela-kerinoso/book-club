/**
 *
 * @author kola
 */
package kola.clubMembership.exception;

public class BookNotAvailableException extends Exception
{
    @Override
    public String getMessage()
    {
        return "All have been lent out. Check back later!";
    }
}
