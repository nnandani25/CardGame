import java.util.Scanner;
import java.awt.*;
public class Game {

    private Player player1;
    private Player player2;
    private Deck deck;
    private static String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static String[] suit = {"♣", "♠", "♦", "♥"};
    private static int[] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private GameViewer window;

    // plays the overall game
    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }

    public Player getPlayer1()
    {
        return player1;
    }
    public Player getPlayer2()
    {
        return player2;
    }



    // constructor for the game class, takes in nothing
    public Game() {
        // asks the user to enter their name and sets the player1 name to their input
        Scanner input = new Scanner(System.in);
        System.out.println("Player 1 Name: ");
        String player1Name = input.nextLine();
        player1 = new Player(player1Name);

        // asks the user to enter their name and sets the player2 name to their input
        System.out.println("Player 2 Name: ");
        String player2Name = input.nextLine();
        player2 = new Player(player2Name);

        // initializes the deck
        deck = new Deck(rank, suit, value);

        // shuffles the deck
        deck.Shuffle();

        // creates a hand for player1
        for (int i = 0; i < 5; i++) {
            player1.addCard(deck.deal());
        }

        // creates a hand for player2
        for (int i = 0; i < 5; i++) {
            player2.addCard(deck.deal());
        }


        // if the players deck has a pair, it gives redeals them cards until there are no pairs
        while (player1.hasPair() || player2.hasPair()) {
            // clears the players hand and repeats the process of dealing cards to the player
            player1.clearHand();
            player2.clearHand();
            deck.Shuffle();
            for (int i = 0; i < 5; i++) {
                player1.addCard(deck.deal());
            }

            for (int i = 0; i < 5; i++) {
                player2.addCard(deck.deal());
            }
        }

        window = new GameViewer(this);

    }

    // prints the instructions
    public void printInstructions() {
        System.out.println("\nINSTRUCTIONS: \n");
        System.out.println("1. Each player gets 5 cards, when it is a players turn, they ask their opponent for a " +
                "card that the current player has in their deck, in order to make a pair.\n2. If the other player has" +
                " that type of card, they have to give you all of those types of cards, and the player that recieved" +
                " the card get 1 point.\n3. If the other player does not have that type of card, then the current" +
                " player 'goes fishing' / gets another card from the deck.\n4. The point is the get the most number" +
                " of pairs until you have no cards left.\n");
    }

    // if either player has an empty hand, checks who won
    // returns nothing
    public void checkWin() {
        // if both players have empty hands, then prints that its a tie
        if (player1.handEmpty() && player2.handEmpty()) {
            System.out.println("--------------------");
            System.out.println("TIE!");
            window.repaint();
        }

        // if only player 1 has an empty hand, prints that player 1 won
        else if (player1.handEmpty()) {
            System.out.println("--------------------");
            System.out.println(player1.getName().toUpperCase() + " WINS!");
            window.setStatus(5);
            window.repaint();

        }

        // if only player 1 has an empty hand, prints that player 1 won
        else if (player2.handEmpty()) {
            System.out.println("--------------------");
            System.out.println(player2.getName().toUpperCase() + " WINS!");
            window.setStatus(6);
            window.repaint();
        }

    }

    // plays the game
    public void play() {
        // prints the instructions
        printInstructions();
        // sets a current player
        Player currentPlayer = player1;
        // sets the opponent
        Player opponent = player2;

        // calls end turn because it asks the user to press enter, so they cannot see each others hand
        opponent.endTurn(currentPlayer, opponent);
        String rankDesired;

        // if either hand is empty, it means that one of the players won, so they do not need to go through another round
        while (!(player1.handEmpty() || player2.handEmpty())) {
            if(currentPlayer == player1)
            {
                // Sets the status to 1, letting the computer know to display player1's hand
                window.setStatus(1);
            }

            else
            {
                // Sets the status to 2, letting the computer know to display player2's hand
                window.setStatus(2);
            }
            window.repaint();
            // prints out whos turn it is
            System.out.println(currentPlayer.getName().toUpperCase() + "'s TURN:");
            // prints out the current players points and amount of cards
            System.out.println(currentPlayer.getName() + " - Points: " + currentPlayer.getPoints() + ", Cards: "
                    + currentPlayer.getNumCards());

            // prints out the opponents points and amount of cards
            System.out.println(opponent.getName() + " - Points: " + opponent.getPoints() + ", Cards: "
                    + opponent.getNumCards() + "\n");

            // sets the card desired to the rank that take turn returns
            rankDesired = currentPlayer.takeTurn(currentPlayer);

            // if the opponent has the desired card, then it removes the card from both the current player and the
            // opponent and increases the current players point
            if (opponent.hasCard(rankDesired)) {
                // removes the card from the opponent
                opponent.removeCard(rankDesired);
                // removes the card from the current player
                currentPlayer.removeCard(rankDesired);
                // increases the currents players points, since they had a pair
                currentPlayer.addPoints(1);

                // checks if either player's hand is empty, becuase that means that someone won.
                if (player1.handEmpty() || player2.handEmpty()) {
                    break;
                }
                // if their hand is not empty it prints out their new hand
                System.out.println("You asked and you got it!\n\nNew Deck:");
                // prints out the current players new hand
                currentPlayer.printHand();
                // Sets status to 4 which lets the computer know that there is a pair and to print it
                window.setStatus(4);
                window.repaint();
            }

            // if the opponent doesn't have a card, makes the player 'go fish' and draw a new card
            else {
                // if there are no more cards in the deck to draw from the deck, prints error and then will exit loop
                // and end turn
                if (deck.isEmpty()) {
                    System.out.println("Go fish!");
                    System.out.println("Oh no! The deck is empty, keep playing without drawing from deck.\n\nNew Deck:");
                    currentPlayer.printHand();
                    // Paints go fish
                    window.setStatus(3);
                    window.repaint();
                }

                // if the deck isn't empty, lets the player pick a card
                else {
                    // creates the card the player drew
                    Card c = deck.deal();

                    // checks if the player has that card already in their deck
                    if (currentPlayer.hasCard(c.getRank())) {
                        // if they have the card, removes it
                        currentPlayer.removeCard(c.getRank());
                        // increases the players pints
                        currentPlayer.addPoints(1);
                        // tells them they received a pair
                        System.out.println("Go fish!");
                        // Paints go fish
                        window.setStatus(3);
                        window.repaint();
                        System.out.println("Looks like you drew a card and got a pair!");
                        // Paints that there is a pair
                        window.setStatus(4);
                        window.repaint();

                        // checks if either hand is empty, meaning that they won
                        if (player1.handEmpty() || player2.handEmpty()) {
                            // exits loop if they won
                            break;
                        }
                        // if they didn't win, prints the current players new hand
                        System.out.println("\n\nNew Deck:");
                        currentPlayer.printHand();
                        window.repaint();
                    }

                    // if the player doesn't have the card that they drew in their hand, adds card and prints the new deck
                    else {
                        System.out.println("Go fish!\n\nNew Deck:");
                        currentPlayer.addCard(c);
                        currentPlayer.printHand();
                        // Prints go fish
                        window.setStatus(3);
                        window.repaint();
                    }
                }
            }
            // makes temporary player, so that the current player and opponent can switch
            // set to current player
            Player temp = currentPlayer;
            // current player becomes opponent
            currentPlayer = opponent;
            // opponent becomes the old current player, which is the temp
            opponent = temp;




            // ends turn, which tells the user to press enter and look away, so they can't see the other persons hand
            temp.endTurn(currentPlayer, opponent);

        }
        // if a player has an empty hand, it checks to see who won
        checkWin();
    }
}



