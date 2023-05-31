public class Card {
    private String value;
    private String suit;

    private String cardName;
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
        this.cardName= getCardName();
    }

    public String getValue(){

        return value;
    }

    public String getSuit(){

        return suit;
    }
    public String getCardName(){
        return value + " of " + suit;
    }
}
