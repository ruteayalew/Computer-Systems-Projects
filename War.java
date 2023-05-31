/*
@author: Rute Ayalew
@purpose: this program uses class structures, inheritance, and recursive methods to implement the card game War.
          users can select two routes of play: 1) consistent with no user prompts and 2) with user prompts
*/
import java.util.Scanner;

public class War {

    public static void main (String [] args ) {
        Scanner scnr = new Scanner (System.in);
        //making an instance of the deck class to create the main deck of 52 cards which also calls a shuffle method
        Deck theDeck = new Deck ();

        System.out.println("Welcome to War!");
        System.out.println("If you want to run the whole program without stopping, press 1.");
        System.out.println( "If you want to pause after each trick, press 2.");
        String reply = scnr.next();
        switch (reply){
            case "1":
                System.out.println("You chose 1!");
                theDeck.go(1); //method splits the deck and begins game
                break;
            case "2":
                System.out.println("You chose 2!");
                theDeck.go(2); //method splits the deck and begins game
                break;
        }

    }

}
