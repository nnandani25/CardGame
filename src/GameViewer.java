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
    private Game game;
    private Image[] cards;

    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
   // private final int DECK_LENGTH = 52;
    private int status;
    private Image background;
    private static int counter = 0;

    public GameViewer(Game game)
    {
        status = 0;
        background = new ImageIcon("Resources/Sea/bigOcean.jpg").getImage();
        this.game = game;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Go Fish");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

    }

//    public Image[] getImages()
//    {
//        return cards;
//    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        //Display home screen
        if(status == 0)
        {
            g.setFont(new Font("Blobtastics", Font.PLAIN, 100));
            Image starter = new ImageIcon("Resources/Sea/bigOcean.jpg").getImage();
            g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawString("GO FISH!", 220, 380);
        }

        //Display player 1 hand
        else if(status == 1)
        {
            if(counter < 1)
            {
                printBeginning(g);
                counter++;
            }

            g.setFont(new Font("Blobtastics", Font.PLAIN, 60));
            Image starter = new ImageIcon("Resources/Sea/underwater.jpg").getImage();
            g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawString(game.getPlayer1().getName(), 450, 220);
            game.getPlayer1().drawHand(g, this);
        }

        //Display player 2 hand
        else if(status == 2)
        {
            Image starter = new ImageIcon("Resources/Sea/underwater.jpg").getImage();
            g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.setFont(new Font("Blobtastics", Font.PLAIN, 60));
            g.drawString(game.getPlayer2().getName(), 450, 220);
            game.getPlayer2().drawHand(g, this);

        }

        //Display starter
        else if(status == 3)
        {

        }

        //Display go fish
        else if(status == 4)
        {
            g.setFont(new Font("Blobtastics", Font.PLAIN, 100));
            Image starter = new ImageIcon("Resources/Sea/underwater.jpg").getImage();
            g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawString("GO FISH!", 250, 350);
            g.setFont(new Font("Blobtastics", Font.ITALIC, 60));
            g.drawString("Switch players...", 170, 450);
        }

        // Display pair
        else if(status == 5)
        {
            g.setFont(new Font("Blobtastics", Font.PLAIN, 100));
            Image starter = new ImageIcon("Resources/Sea/underwater.jpg").getImage();
            g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawString("PAIR!", 350, 350);
            g.setFont(new Font("Blobtastics", Font.ITALIC, 60));
            g.drawString("Switch players...", 170, 450);
        }
        //Display player 1 wins
        else if(status == 6)
        {
            g.setFont(new Font("Blobtastics", Font.PLAIN, 100));
            Image starter = new ImageIcon("Resources/Sea/bigOcean.jpg").getImage();
            g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawString(game.getPlayer1().getName(), 250, 350);
            g.drawString("WINS!", 300, 500);
        }

        //Display player 2 wins
        else if(status == 7)
        {
            g.setFont(new Font("Blobtastics", Font.PLAIN, 100));
            Image starter = new ImageIcon("Resources/Sea/bigOcean.jpg").getImage();
            g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawString(game.getPlayer2().getName(), 250, 350);
            g.drawString("WINS!", 300, 500);
        }

        //Display a tie
        else
        {
            g.setFont(new Font("Blobtastics", Font.PLAIN, 100));
            Image starter = new ImageIcon("Resources/Sea/bigOcean.jpg").getImage();
            g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawString("TIE!", 260, 350);
        }
    }

    public void setStatus(int s)
    {
        status = s;
    }

    public void printBeginning(Graphics g)
    {
        Image starter = new ImageIcon("Resources/Sea/bigOcean.jpg").getImage();
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setFont(new Font("Blobtastics", Font.PLAIN, 60));
        g.drawString(game.getPlayer1().getName() + "'s turn will", 40, 350);
        g.drawString("start in ", 250, 450);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("3", 500, 400);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("2", 500, 400);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("1", 500, 400);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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
