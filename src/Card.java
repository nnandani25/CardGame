import java.util.ArrayList;

public class Card {
    private int point;
    private String rank;
    private String suit;

    // constructor which sets the point, suit, and rank
    public Card(int point, String suit, String rank)
    {
        this.point = point;
        this.suit = suit;
        this.rank = rank;
    }

    // returns the point
    public int getPoint() {
        return point;
    }

    // sets the point
    public void setPoint(int point) {
        this.point = point;
    }

    // returns the rank
    public String getRank() {
        return rank;
    }

    // sets the rank
    public void setRank(String rank) {
        this.rank = rank;
    }

    // returns the suit
    public String getSuit() {
        return suit;
    }

    // sets the suit
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // toString method
    public String toString()
    {
        return rank + " " +  suit;
    }
}
