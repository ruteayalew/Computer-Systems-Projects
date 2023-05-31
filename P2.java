import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;

//this class is dedicated to the construction of Player 2's hand of cards
public class P2 extends Deck { //extends Deck class to access its methods and items

    private ArrayList<Card> deck2 = new ArrayList<Card>(26);

    public P2(ArrayList< Card > shuffledDeck) {

        for (int d = 0; d < 26; d++) {   //fills deck1 with first 26 shuffled cards temporarily so function below can run
            Card currentCard = shuffledDeck.get(d);
            deck2.add(d, currentCard);
        }

        int index = 0;
        int counter=0;//counter to measure index of deck1 because 'index' value for shuffled deck > bounds for deck1
        for (int i = 0; i < shuffledDeck.size(); i++) {   //runs through shuffled deck to distribute the 26 EVEN cards to P2
            Card currentCard = shuffledDeck.get(i);
            if ((i % 2 == 0)||(i==0)) { //make sure to include card at zero index for deck 2
                if(counter<26){ //replaces the 26 elements currently in deck1
                    deck2.set(counter, currentCard);
                    counter++;
                }
            }
            else{
            }
        }
        //System.out.println("P2 DECK NOW---------------------------------------------------------------------------------");
        //printDeck(deck2);
    }

    public ArrayList<Card> sendDeck2(){
        return deck2;
    }


}
