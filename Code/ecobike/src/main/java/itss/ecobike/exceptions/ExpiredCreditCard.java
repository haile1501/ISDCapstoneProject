package itss.ecobike.exceptions;

public class ExpiredCreditCard extends Exception{
    public ExpiredCreditCard() {
        super("Credit card is expired!");
    }
}
