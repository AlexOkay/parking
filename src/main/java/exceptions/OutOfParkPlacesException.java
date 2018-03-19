package exceptions;

public class OutOfParkPlacesException extends ArrayIndexOutOfBoundsException
{
    public OutOfParkPlacesException(String message) {
        super(message);
    }
}
