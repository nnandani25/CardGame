import javax.swing.*;
import java.awt.*;
public class GameViewer extends JFrame {
    /**
     * Instance variables:
        * Game object
        * Image array
        * Magic numbers
     *
     * Constructor:
        * take in a Game object
        * instantiate images / put them in array
        * this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        * this.setTitle("Go Fish");
        * this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        * this.setVisible(true);
     **/
    private Game g;
    private Image[] cards;
    private Image[] spades;
    private Image[] hearts;
    private Image[] diamonds;
    private Image[] clubs;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private final int DECK_LENGTH = 52;
    public GameViewer(Game g)
    {
        this.g = g;
        cards = new Image[DECK_LENGTH];
        spades = new Image[DECK_LENGTH/4];
        hearts = new Image[DECK_LENGTH/4];
        diamonds = new Image[DECK_LENGTH/4];
        clubs = new Image[DECK_LENGTH/4];
        for(int i = 0; i < DECK_LENGTH; i++)
        {
            Image image = new ImageIcon("Resources/Cards/" + i + ".png").getImage();
            cards[i] = image;
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Go Fish");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

    }

    public Image[] getImages()
    {
        return cards;
    }

    public Image[] getSpades()
    {
        for(int i = 0; i < cards.length; i += 4)
        {
            spades[i] = cards[i];
        }
        return spades;
    }

    public Image[] getHearts()
    {
        for(int i = 1; i < cards.length; i += 4)
        {
            hearts[i] = cards[i];
        }
        return hearts;
    }

    public Image[] getDiamonds()
    {
        for(int i = 3; i < cards.length; i += 4)
        {
            diamonds[i] = cards[i];
        }
        return diamonds;
    }

    public Image[] getClubs()
    {
        for(int i = 3; i < cards.length; i += 4)
        {
            diamonds[i] = cards[i];
        }
        return diamonds;
    }

//    @Override
//    public void paint()
//    {
//
//    }

    /**
     *
     * Paint:
        * call draw method in card
        * print any strings needed with draw string
        * checks if there is a tie
     *
     * print hand
     * get card
     * Get Images:
        * returns the array of images
     **/
}
