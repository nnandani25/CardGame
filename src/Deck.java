import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;
    private int cardsLeft;


    // constructor for the deck which takes in the rank, suit, and point
    public Deck(String[] rank, String[] suit, int[] point)
    {
        // initializes the array list card
        cards = new ArrayList<Card>();

        // nested for loop, because it goes through and makes one set of cards with each suit
        // At the end, the deck will have cards of 4 suits, each with point amount of cards.
        // The first loop goes through the amount of suits
        for(int i = 0; i < suit.length; i++)
        {
            // This loop goes through the amount of ranks
            for(int j = 0; j < rank.length; j++)
            {
                // creates a card using the points, suits, and ranks
                Card card = new Card(point[j], suit[i], rank[j]);
                // adds the new card into the array cards
                cards.add(card);
            }
        }
        // sets cardsLeft to the amount of cards in the deck, because no cards have been removed when the deck
        // is initialized.
        cardsLeft = cards.size();

    }

    // checks if the deck is empty and if so, returns true
    // if the deck is not empty, it returns false
    public boolean isEmpty()
    {
        if(cardsLeft == 0)
        {
            return true;
        }

        return false;
    }

    // returns the amount of cards left
    public int getCardsLeft()
    {
        return cardsLeft;
    }

    // checks if the deck is empty and if it isn't, it deals the top card
    // returns the card
    public Card deal()
    {
        // if the deck is not empty, deals a card
        if(!isEmpty())
        {
            // sets the index to the size of the cards - the cards left, because that will make it deal from the top of
            // the deck
            int index = cards.size() - cardsLeft;
            //decreases the amount of cards left, because the computer just dealed a card
            cardsLeft --;
            //gets the card at that index and returns it
            return cards.get(index);

        }
        // if the deck is empty, returns null
        return null;
    }


    // shuffles the deck and returns nothing
    public void Shuffle()
    {
        // resets cardsLeft ot the amount of cards in the deck
        cardsLeft = cards.size();

        // goes through the amount of cards in the deck
        for(int i = 0; i < cards.size(); i++)
        {
            // makes a variable which gets a random number between 1 and the deck size
            int r = (int)((Math.random() * cards.size()));
            // sets the current card
            Card cardI = cards.get(i);
            // sets the card to be the card at the index of the randomly generated number
            Card cardR = cards.get(r);
            // makes the current card to the one with the randomly generated number
            cards.set(i, cardR);
            // puts the current card at the index of the randomly generated card
            cards.set(r, cardI);
        }
    }
}
