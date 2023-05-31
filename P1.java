import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;

//this class is dedicated to the construction of Player 1's hand of cards
public class P1 extends Deck { //extends Deck class to access its methods and items

    private ArrayList<Card> deck1 = new ArrayList<Card>(26);

    public P1(ArrayList< Card > shuffledDeck) {

        for (int d = 0; d < 26; d++) {   //fills deck1 with first 26 shuffled cards temporarily so function below can run
            Card currentCard = shuffledDeck.get(d);
            deck1.add(d, currentCard);
        }

        int index = 0;
        int counter=0;//counter to measure index of deck1 because 'index' value for shuffled deck > bounds for deck1
        for (int i = 0; i < shuffledDeck.size(); i++) {   //runs through shuffled deck to distribute the 26 odd cards to P1
            Card currentCard = shuffledDeck.get(i);
            if ((i % 2 != 0)&&(i!=0)) { //make sure not to include card at zero index for deck 1, reserving it for deck 2
                if(counter<26){ //replaces the 26 elements currently in deck1
                deck1.set(counter, currentCard);
                counter++;
                }
            }
            else{
            }
        }
        //System.out.println("P1 DECK NOW---------------------------------------------------------------------------------");
        //printDeck(deck1);
    }

    public ArrayList<Card> sendDeck1(){
        return deck1;
    }



}
