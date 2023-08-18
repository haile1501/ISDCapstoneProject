package itss.ecobike.models;


import java.time.YearMonth;

public class CreditCard {

    /**
     * The name of the holder of the card
     */
    private String cardHolderName;

    /**
     * The valid number of the card
     */
    private String cardNumber;

    /**
     * The security code of the card
     */
    private String cardSecurity;

    /**
     * The money left in the card.
     */
    private double balance;

    /**
     * The expired date of the card in defined time format
     */
    private YearMonth expirationDate;


    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardSecurity(String cardSecurity) {
        this.cardSecurity = cardSecurity;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setExpirationDate(int year, int month) {
        this.expirationDate = YearMonth.of(year, month);
    }
    public CreditCard(String cardHolderName, String cardNumber, String cardSecurity, int expMonth, int expYear) {
        this.setCardHolderName(cardHolderName);
        this.setCardNumber(cardNumber);
        this.setCardSecurity(cardSecurity);
        this.setExpirationDate(expYear, expMonth + 1);
        this.setBalance(1_000_000);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public String getCardSecurity() {
        return cardSecurity;
    }

    public void reset() {
        this.setBalance(1_000_000);
    }
}
