import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class Card {
    private int point;
    private String rank;
    private String suit;
    private Image image;


    // Constructor which sets the point, suit, and rank
    public Card(int point, String suit, String rank)
    {
        this.point = point;
        this.suit = suit;
        this.rank = rank;

        // Suit Point is a variable which seperates the cards by the suit in order to get the correct card
        int suitPoint;

        // Sets the suitPoint based on the suit
        if(suit.equals("♠"))
        {
            suitPoint = 1;
        }

        else if(suit.equals("♥"))
        {
            suitPoint = 2;
        }

        else if(suit.equals("♦"))
        {
            suitPoint = 3;
        }

        else
        {
            suitPoint = 4;
        }

        // Gets the image by taking the numerical value and subtracting 1, then multiplying by 4 because there are 4 suits,
        // Then adding the suit point which gets the number in the correct suit
        int imageNum = (point - 1) * 4 + suitPoint;
        image = new ImageIcon("Resources/Cards/" + imageNum + ".png").getImage();
    }

    // Returns the point
    public int getPoint() {
        return point;
    }

    // Sets the point
    public void setPoint(int point) {
        this.point = point;
    }

    // Returns the rank
    public String getRank() {
        return rank;
    }

    // Sets the rank
    public void setRank(String rank) {
        this.rank = rank;
    }

    // Returns the suit
    public String getSuit() {
        return suit;
    }

    // Sets the suit
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // toString method
    public String toString()
    {
        return rank + " " +  suit;
    }

    // Draws the card image
    public void draw(Graphics g,  GameViewer b, int x, int y)
    {
        g.drawImage(image, x, y, 75,115 , b);
    }
}
