import java.util.ArrayList;

public class Deck {
    protected ArrayList<Card> deck ;  //declare ArrayList private so inaccessible by itself
    //make the ArrayList hold the "card" objects
    protected ArrayList<Card> shuffledDeck;   //declare ArrayList private so inaccessible by itself
    //make the ArrayList hold the "card" objects
    protected String [] value = {"ACE", "2", "3",
            "4", "5", "6",
            "7", "8", "9", "10",
            "JACK", "QUEEN", "KING"};
    protected String [] suit = {"HEARTS", "SPADES", "DIAMONDS", "CLUBS"};


    public Deck(){ //default constructor for theDeck object
        deck = new ArrayList<Card>();  //allocate space for the deck
        shuffledDeck = new ArrayList<Card>();  //allocate space for the shuffled deck
        int index = 0;
        for(int i = 0; i < 4; i++){
            for(int j =0; j< 13; j++) {
                Card newCard = new Card(value[j], suit[i]);
                deck.add(newCard);
            }
        }
        shuffledDeck= shuffle(); //deck constructor automatically calls the shuffle method
    }

    //method for the subclasses P1 and P2 to send their deck1 and deck2 to in case user wants to see full hand

    public ArrayList<Card> shuffle() {
        for(int i = 0; i < deck.size(); i++){
            Card currentCard = deck.get(i);
            Card temp = new Card (currentCard.getValue(), currentCard.getSuit());
            int index = (int) (Math.random()*(51)+0);
            deck.remove(currentCard);
            deck.add(index, temp);
        }
        return deck;
    }


    public void go(int a){ //creates 2 objects of player 1 and player 2's classes, passing the shuffled deck
        P1 P1object = new P1 (shuffledDeck);
        P2 P2object = new P2 (shuffledDeck);

        //creates a new game instance here because it requires use of player objects which is also declared here
        //also calls sendDeck methods of P1 and P2 to return the seperate player decks and send to Game constructor
        if(a == 1) {
            Game newGame = new Game(P1object.sendDeck1(), P2object.sendDeck2(),a);
        }
        else if (a==2){
            Game newGame = new Game(P1object.sendDeck1(), P2object.sendDeck2(),a);
        }
    }

    public void printDeck(ArrayList<Card> toPrint){
        System.out.println("PRINTING------------------------------------------------------------------------------------");
        int index = 0;
        for(int i = 0; i < toPrint.size(); i++){
            Card currentCard = toPrint.get(i);
            System.out.println(currentCard.getCardName());
        }
    }


}

