import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class Card {
    private int point;
    private String rank;
    private String suit;
    private Image image;


    // constructor which sets the point, suit, and rank
    public Card(int point, String suit, String rank)
    {
        this.point = point;
        this.suit = suit;
        this.rank = rank;

        int suitPoint;
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

        int imageNum = (point - 1) * 4 + suitPoint;
        image = new ImageIcon("Resources/Cards/" + imageNum + ".png").getImage();
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

    public void draw(Graphics g,  GameViewer b, int x, int y)
    {
        g.drawImage(image, x, y, 75,115 , b);
    }
}
