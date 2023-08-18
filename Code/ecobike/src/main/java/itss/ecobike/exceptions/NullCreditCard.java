package itss.ecobike.exceptions;

public class NullCreditCard extends Exception{
    public NullCreditCard() {
        super("Credit card is null!");
    }
}
