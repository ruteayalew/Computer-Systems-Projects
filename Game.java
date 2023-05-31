
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends Deck {

    int trickCounter=0; //tracks how many tricks are played
    int warCounter = 0; //track how many wars are entered

    Scanner scnr = new Scanner (System.in);

    public Game(ArrayList<Card> a, ArrayList<Card> b, int selection) {

        if (selection==1){ //if user selected continuous play in main method, corresponding method is called
            contPlayGame(a, b); //sends decks to continuous play method
        }
        else if (selection==2){ //if user selected non-continous play in main method, corresponding method is called
            NOTcontPlayGame(a,b);
        }

    }


    public void contPlayGame(ArrayList<Card> deck1, ArrayList<Card> deck2) {

        for (int i = 0; i < 26; i++) {
            if (!deck1.isEmpty() && !deck2.isEmpty()) {
                Card player1 = deck1.get(i);            //assigns 2 card objects with top card of each deck
                Card player2 = deck2.get(i);

                  System.out.println("A trick has been played!");
                  trickCounter++;
                  System.out.println("Player 1's Hand: " + player1.getCardName());
                  System.out.println("Player 2's Hand: " + player2.getCardName());


                    int value1 = convert(player1);          //calls method to return numeric value of played cards
                    int value2 = convert(player2);

                            if (value1 == value2) {
                              System.out.println("*********************************");
                              System.out.println("*****  WAR * IS * DECLARED! *****");
                              System.out.println("*********************************");

                                System.out.println("Player 1's deck size: " + deck1.size());
                                System.out.println("Player 2's deck size: " + deck2.size());


                                war(deck1, deck2, i);
                            }
                            else if (value1 < value2) {
                                deck2.remove(i);
                                deck1.remove(i);
                                deck2.add(player1);             //places opponents card on bottom of deck2
                                deck2.add(player2);            //places played card on bottom of deck1
                                System.out.println("Player 2 Wins this round!");
                                System.out.println("Player 1's deck size: " + deck1.size());
                                System.out.println("Player 2's deck size: " + deck2.size());
                                System.out.println();
                                contPlayGame(deck1, deck2);         //recursive call
                            }
                            else {
                                deck2.remove(i);
                                deck1.remove(i);
                                deck1.add(player2);           //places opponents card on bottom of deck1
                                deck1.add(player1);           //places played card on bottom of deck1
                                System.out.println("Player 1 Wins this round!");
                                System.out.println("Player 1's deck size: " + deck1.size());
                                System.out.println("Player 2's deck size: " + deck2.size());
                                System.out.println();
                                contPlayGame(deck1, deck2);       //recursive call
                            }
                }
            else if (deck1.isEmpty()||i>=deck1.size()) {      //if player 1 ran out of cards
                System.out.println("Uh oh.....");
                System.out.println("Player 1's deck size: " + deck1.size());
                System.out.println("Player 2's deck size: " + deck2.size());
                System.out.println("PLAYER 2 WINS THE GAME!");
                endGame();
            }
            else if (deck2.isEmpty()||i>=deck2.size()){               //if player 2 ran out of cards
                System.out.println("Uh oh.....");
                System.out.println("Player 1's deck size: " + deck1.size());
                System.out.println("Player 2's deck size: " + deck2.size());
                System.out.println("PLAYER 1 WINS THE GAME!");
                endGame();
            }

        }
    }


    private void war(ArrayList<Card> deck1, ArrayList<Card> deck2, int i) {

        //the first two requirements only allow war when both decks have 3 or more cards after the last game play
        //and the last two requirements prevent cards from entering war if war is called multiple times in sequence
        //and they do not have enough cards to move to the next war
        if(deck1.size()>3 && deck2.size()>3 && i+4<=deck1.size() && i+4<=deck2.size()){
            Card player1TOSS1 = deck1.get(i+1);     //1st facedown card from player 1
            Card player1TOSS2 = deck1.get(i+2);     //2nd facedown card from player 1
            Card player2TOSS1 = deck2.get(i+1);     //1st facedown card from player 2
            Card player2TOSS2 = deck2.get(i+2);     //2nd facedown card from player 2
            Card warPlay1 = deck1.get(i+3);         //warcard object assigned to 3rd pick for player 1
            Card warPlay2 = deck2.get(i+3);         //warcard object assigned to 3rd pick for player 2

            warCounter++;   //keeps track of how many times war is entered
            System.out.println("Player 1 and 2 have put 2 cards facedown for sacrifice...");
            System.out.println("********Player 1's WAR CARD: "+ warPlay1.getCardName());
            System.out.println("********Player 2's WAR CARD: "+ warPlay2.getCardName());


            int value1 = convert(warPlay1);          //calls method to return numeric value of played cards
            int value2 = convert(warPlay2);

            if(value1==value2){
                System.out.println("*********************************");
                System.out.println("*****  WAR * IS * DECLARED! *****");
                System.out.println("*********************************");

                    war(deck1, deck2, i + 4);       //recursive call in case war results in another matching pair
                    //int i is scaled by 4 so the warplay card is passed over in new drawings

            }
            else if (value1<value2){  //player 2 wins
                deck2.remove(i);               //remove played card from 0 index
                deck1.remove(i);               //remove opponents card from their 0 index
                deck1.remove(player1TOSS1);     //removes losers' toss cards
                deck1.remove(player1TOSS2);

                deck2.add(player1TOSS1);            //places both tossup cards of opponent into deck 2
                deck2.add(player1TOSS2);
                deck2.add(warPlay1);             //places opponents card on bottom of deck2
                deck2.add(warPlay2);            //places played card on bottom of deck1
                System.out.println("Player 2 Wins this round by war!");
                System.out.println("WAR AFTERMATH: Player 2 takes 3 cards from Player 1");
                System.out.println("Player 1's deck size: " + deck1.size());
                System.out.println("Player 2's deck size: " + deck2.size());
                System.out.println();
                contPlayGame(deck1, deck2);         //return to regular game call
            }
            else{   //player 1 wins
                deck1.remove(i);             //remove played card from 0 index
                deck2.remove(i);            //remove opponents card from their 0 index
                deck2.remove(player2TOSS1);     //removes losers' toss cards
                deck2.remove(player2TOSS2);

                deck1.add(player2TOSS1);            //places both tossup cards of opponent into deck 1
                deck1.add(player2TOSS2);
                deck1.add(warPlay1);           //places opponents card on bottom of deck1
                deck1.add(warPlay2);           //places played card on bottom of deck1
                System.out.println("Player 1 Wins this round by war!");
                System.out.println("WAR AFTERMATH: Player 1 takes 3 cards from Player 2");
                System.out.println("Player 1's deck size: " + deck1.size());
                System.out.println("Player 2's deck size: " + deck2.size());
                System.out.println();
                contPlayGame(deck1, deck2);        //return to regular game call
            }
        }
        //if player 1 runs out of cards during war, or does not have enough cards to enter war player 2 wins
        else if (i>=deck1.size()+1|| i+3>=deck1.size()+1 ||deck1.size()<=3){
            System.out.println("\nUh oh..... Player 1 has run out of cards and cannot continue");
            System.out.println("SO THE WINNER IS:");
            System.out.println("PLAYER 2!");
            endGame();
        }
        //if player 2 runs out of cards during war, or does not have enough cards to enter war player 1 wins
        else if (i>=deck2.size()+1|| i+3>=deck2.size()+1 ||deck2.size()<=3) {
            System.out.println("\nUh oh.....Player 2 has run out of cards and cannot continue");
            System.out.println("SO THE WINNER IS:");
            System.out.println("PLAYER 1!");
            endGame();
        }
    }

//--------------------------------------------------------------------------------------------------------------
    // below are the 2 game and war methods for non-continuous play

    public void NOTcontPlayGame(ArrayList<Card> deck1, ArrayList<Card> deck2) {
        for (int i = 0; i < 26; i++) {
            if (!deck1.isEmpty() && !deck2.isEmpty()) {
                Card player1 = deck1.get(i);            //assigns 2 card objects with top card of each deck
                Card player2 = deck2.get(i);

                System.out.println("\nPress enter to play trick!");
                String response = scnr.nextLine();
                System.out.println("A trick has been played!");
                System.out.println("Player 1's Hand: " + player1.getCardName());
                System.out.println("Player 2's Hand: " + player2.getCardName());
                trickCounter++;


                int value1 = convert(player1);          //calls method to return numeric value of played cards
                int value2 = convert(player2);

                if (value1 == value2) {
                    System.out.println("*********************************");
                    System.out.println("*****  WAR * IS * DECLARED! *****");
                    System.out.println("*********************************");

                    System.out.println("Player 1's deck size: " + deck1.size());
                    System.out.println("Player 2's deck size: " + deck2.size());

                    System.out.println("Press enter to enter WAR!");
                    response = scnr.nextLine();
                    NOTcontWar(deck1, deck2, i);
                }
                else if (value1 < value2) {
                    deck2.remove(i);
                    deck1.remove(i);
                    deck2.add(player1);             //places opponents card on bottom of deck2
                    deck2.add(player2);            //places played card on bottom of deck1
                    System.out.println("Player 2 Wins this round!");
                    System.out.println("Player 1's deck size: " + deck1.size());
                    System.out.println("Player 2's deck size: " + deck2.size());
                    System.out.println();
                    NOTcontPlayGame(deck1, deck2);         //recursive call
                }
                else {
                    deck2.remove(i);
                    deck1.remove(i);
                    deck1.add(player2);           //places opponents card on bottom of deck1
                    deck1.add(player1);           //places played card on bottom of deck1
                    System.out.println("Player 1 Wins this round!");
                    System.out.println("Player 1's deck size: " + deck1.size());
                    System.out.println("Player 2's deck size: " + deck2.size());
                    System.out.println();
                    NOTcontPlayGame(deck1, deck2);       //recursive call
                }
            }
            else if (deck1.isEmpty()||i>=deck1.size()) {      //if player 1 ran out of cards
                System.out.println("Uh oh.....");
                System.out.println("Player 1's deck size: " + deck1.size());
                System.out.println("Player 2's deck size: " + deck2.size());
                System.out.println("PLAYER 2 WINS THE GAME!");
                endGame();
            }
            else if (deck2.isEmpty()||i>=deck2.size()){               //if player 2 ran out of cards
                System.out.println("Uh oh.....");
                System.out.println("Player 1's deck size: " + deck1.size());
                System.out.println("Player 2's deck size: " + deck2.size());
                System.out.println("PLAYER 1 WINS THE GAME!");
                endGame();
            }

        }
    }


    private void NOTcontWar(ArrayList<Card> deck1, ArrayList<Card> deck2, int i) {

        //the first two requirements only allow war when both decks have 3 or more cards after the last game play
        //and the last two requirements prevent cards from entering war if war is called multiple times in sequence
        //and they do not have enough cards to move to the next war
        if(deck1.size()>3 && deck2.size()>3 && i+4<=deck1.size() && i+4<=deck2.size()){
            System.out.println("\nPress enter to enter sacrifice 2 cards!");
            String response = scnr.nextLine();
            Card player1TOSS1 = deck1.get(i+1);     //1st facedown card from player 1
            Card player1TOSS2 = deck1.get(i+2);     //2nd facedown card from player 1
            Card player2TOSS1 = deck2.get(i+1);     //1st facedown card from player 2
            Card player2TOSS2 = deck2.get(i+2);     //2nd facedown card from player 2
            Card warPlay1 = deck1.get(i+3);         //warcard object assigned to 3rd pick for player 1
            Card warPlay2 = deck2.get(i+3);         //warcard object assigned to 3rd pick for player 2

            warCounter++;   //keeps track of how many times war is entered
            System.out.println("Player 1 and 2 have put 2 cards facedown for sacrifice...");
            System.out.println("\nPress enter to FIGHT IN WAR!");
            response = scnr.nextLine();
            System.out.println("********Player 1's WAR CARD: "+ warPlay1.getCardName());
            System.out.println("********Player 2's WAR CARD: "+ warPlay2.getCardName());


            int value1 = convert(warPlay1);          //calls method to return numeric value of played cards
            int value2 = convert(warPlay2);

            if(value1==value2){
                System.out.println("*********************************");
                System.out.println("*****  WAR * IS * DECLARED! *****");
                System.out.println("*********************************");

                NOTcontWar(deck1, deck2, i + 4);       //recursive call in case war results in another matching pair
                //int i is scaled by 4 so the warplay card is passed over in new drawings

            }
            else if (value1<value2){  //player 2 wins
                deck2.remove(i);               //remove played card from 0 index
                deck1.remove(i);               //remove opponents card from their 0 index
                deck1.remove(player1TOSS1);     //removes losers' toss cards
                deck1.remove(player1TOSS2);

                deck2.add(player1TOSS1);            //places both tossup cards of opponent into deck 2
                deck2.add(player1TOSS2);
                deck2.add(warPlay1);             //places opponents card on bottom of deck2
                deck2.add(warPlay2);            //places played card on bottom of deck1
                System.out.println("Player 2 Wins this round by war!");
                System.out.println("WAR AFTERMATH: Player 2 takes 3 cards from Player 1");
                System.out.println("Player 1's deck size: " + deck1.size());
                System.out.println("Player 2's deck size: " + deck2.size());
                System.out.println();
                NOTcontPlayGame(deck1, deck2);         //return to regular game call
            }
            else{   //player 1 wins
                deck1.remove(i);             //remove played card from 0 index
                deck2.remove(i);            //remove opponents card from their 0 index
                deck2.remove(player2TOSS1);     //removes losers' toss cards
                deck2.remove(player2TOSS2);

                deck1.add(player2TOSS1);            //places both tossup cards of opponent into deck 1
                deck1.add(player2TOSS2);
                deck1.add(warPlay1);           //places opponents card on bottom of deck1
                deck1.add(warPlay2);           //places played card on bottom of deck1
                System.out.println("Player 1 Wins this round by war!");
                System.out.println("WAR AFTERMATH: Player 1 takes 3 cards from Player 2");
                System.out.println("Player 1's deck size: " + deck1.size());
                System.out.println("Player 2's deck size: " + deck2.size());
                System.out.println();
                NOTcontPlayGame(deck1, deck2);        //return to regular game call
            }
        }
        //if player 1 runs out of cards during war, or does not have enough cards to enter war player 2 wins
        else if (i>=deck1.size()+1|| i+3>=deck1.size()+1 ||deck1.size()<=3){
            System.out.println("\nUh oh..... Player 1 has run out of cards and cannot continue");
            System.out.println("SO THE WINNER IS:");
            System.out.println("PLAYER 2!");
            endGame();
        }
        //if player 2 runs out of cards during war, or does not have enough cards to enter war player 1 wins
        else if (i>=deck2.size()+1|| i+3>=deck2.size()+1 ||deck2.size()<=3) {
            System.out.println("\nUh oh.....Player 2 has run out of cards and cannot continue");
            System.out.println("SO THE WINNER IS:");
            System.out.println("PLAYER 1!");
            endGame();
        }
    }




   //------------------------------------------------------------------------------------------------------
   //below is the convert method used by both play methods to compare the values of cards


    private int convert(Card theCard) {
        int num = 0;

        switch(theCard.getValue()){
            case "ACE":
                num = 14;
                break;
            case "KING":
                num = 13;
                break;
            case "QUEEN":
                num = 12;
                break;
            case "JACK":
                num = 11;
                break;
            case "10":
                num = 10;
                break;
            case "9":
                num = 9;
                break;
            case "8":
                num = 8;
                break;
            case "7":
                num = 7;
                break;
            case "6":
                num = 6;
                break;
            case "5":
                num = 5;
                break;
            case "4":
                num = 4;
                break;
            case "3":
                num = 3;
                break;
            case "2":
                num = 2;
                break;

        }
        return num;

    }

    //this method is called to terminate the program
    public void endGame (){
        System.out.println("Game has finished!");
        System.out.println("Total number of tricks played: " + trickCounter);
        System.out.println("Total number of wars: " + warCounter);
        System.exit(0);

    }


    }

