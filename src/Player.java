import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
public class Player {

    private ArrayList<Card> hand = new ArrayList<Card>();
    private int points;
    private String name;
    // constructor which only takes in the player's name
    public Player(String name)
    {
        this.name = name;
        this.points = 0;
    }

    // constructor which takes in the name and their hand
    public Player(String name, ArrayList<Card> hand)
    {
        this.name = name;
        this.hand = hand;
        this.points = 0;
    }

    // getter method for name
    public String getName()
    {
        return name;
    }

    // getter method for points
    public int getPoints()
    {
        return points;
    }

    // adds the points to the players overall score
    public void addPoints(int playerPoints)
    {
        points += playerPoints;
    }

    // adds a card to the players hand
    public void addCard(Card card)
    {
        hand.add(card);
    }

    // prints out each card in the players hand
    public void printHand()
    {
        for(Card card: hand)
        {
            System.out.print(card + "   ");
        }
    }

    // checks if the hand is empty, meaning that the player has not cards left
    public boolean handEmpty()
    {
        if(hand.size() == 0)
        {
            return true;
        }
        return false;
    }

    // returns the number of cards in the hand
    public int getNumCards()
    {
        return hand.size();
    }

    // checks if a player has a certain card in their hand
    public boolean hasCard(String rank)
    {
        // uses a for-each loop, because it is not changing the value of anything
        for(Card card : hand)
        {
            // checks if the card is equal to the card that the other player has
            if(card.getRank().equals(rank))
            {
                return true;
            }
        }
        return false;
    }

    // checks if a hand has a pair
    public boolean hasPair()
    {
        // loops through the hand
        for(int i = 0; i < hand.size(); i++)
        {
            // loops through the hand, but starts at i + 1, so it can compare it to the elements in the array
            for(int j = i+1; j < hand.size(); j++)
            {
                // checks if the card is equal to the other cards in the hand
                if(hand.get(i).getRank().equals(hand.get(j).getRank()))
                {
                    // returns true if there is a pair
                    return true;
                }
            }
        }
        // returns false if there is no pair found
        return false;
    }

    // goes through the hand and removes a card that is given
    public void removeCard(String rank)
    {
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).getRank().equals(rank))
            {
                hand.remove(i);
            }
        }
    }

    // clears/resets the players hand
    public void clearHand()
    {
        hand.clear();
    }

    // lets the player take turn and returns the number/string of the card they want to remove
    public String takeTurn(Player player)
    {
        // prints the players hand
        player.printHand();

        // asks the player what card they want to ask their opponent for
        Scanner input = new Scanner(System.in);
        System.out.println("\n\nWhich card would you like to ask for?");
        String output = input.nextLine().toUpperCase();

        // if they do not answer a card that they have in their hand, they get asked again
        while(!hasCard(output))
        {
            System.out.println("\nWhich card would you like to ask for? (Make sure it is a card in your hand)");
            output = input.nextLine().toUpperCase();
        }
        return output;
    }

    // ends the players turn by telling them to press enter to continue and clearing the screen
    // takes in the currentPlayer and the opponent, so it can print whose turn it will be
    public void endTurn(Player currentPlayer, Player opponent)
    {

        Scanner input = new Scanner(System.in);
        System.out.println("\n\nPress enter to continue");
        System.out.println("It will be " + currentPlayer.getName().toUpperCase() +  "'s turn " +
                opponent.getName().toUpperCase() + " please look away!" );
        String output = input.nextLine();

        // if they do not press enter, it asks them again to press enter in order to continue
        while(!(output.equals("")))
        {
            System.out.println("Press enter to continue");
            output = input.nextLine();
        }

        // if they press enter, it prints out 80 lines (I looked it up and you can't clear a screen in intelliJ, so this
        // is what I came up with)
        if(output.equals(""))
        {
            for(int i = 0; i < 80; i++)
            {
                System.out.println();
            }
        }

    }

    public void drawHand(Graphics g, GameViewer b)
    {
        int counter = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            if(counter >= 5)
            {
                hand.get(i).draw(g,b, 250 + (i-counter)*100, 400);
            }

            else
            {
                hand.get(i).draw(g,b, 250 + i*100, 280);
            }
            counter++;
        }
    }

    public String toString()
    {
        return name + " has " + points + " points \n" + name +"'s cards: " + hand;
    }
}
