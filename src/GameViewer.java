import javax.swing.*;
import java.awt.*;
public class GameViewer extends JFrame {
    private Game game;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private int status;
    private Image background;
    private static int counter = 0;

    // Initializes variables and sets up background.
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
    @Override
    // Paints the different screens depending on the status of the game
    public void paint(Graphics g)
    {
        // Draws the background
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        // Display home screen and prints "Go Fish!"
        if(status == 0)
        {
            drawStartScreen(g);
            g.drawString("GO FISH!", 220, 380);
        }

        // Displays player 1's hand
        else if(status == 1)
        {
            /**
             * The counter variable helps keep track of how many times player 1's turn has happened
             * If it is the players first turn, it prints that the first player will start along with a count down
             * in order to show that the game is starting
             **/
            if(counter < 1)
            {
                printBeginning(g);
                counter++;
            }

            // Draws the screen and prints the players hand
            drawPlayScreen(g, 60);
            g.drawString(game.getPlayer1().getName(), 450, 220);
            game.getPlayer1().drawHand(g, this);
        }

        //Display player 2 hand
        else if(status == 2)
        {
            // Draws the screen that is used to play the game
            drawPlayScreen(g, 60);
            // Prints the players name and draws their hand
            g.drawString(game.getPlayer2().getName(), 450, 220);
            game.getPlayer2().drawHand(g, this);

        }

        //Display go fish and tells the players to switch
        else if(status == 3)
        {
            drawPlayScreen(g, 100);
            g.drawString("GO FISH!", 250, 350);
            g.setFont(new Font("Blobtastics", Font.ITALIC, 60));
            g.drawString("Switch players...", 170, 450);
        }

        // Displays that there is a pair and tells the players to switch
        else if(status == 4)
        {
            drawPlayScreen(g,100);
            g.drawString("PAIR!", 350, 350);
            g.setFont(new Font("Blobtastics", Font.ITALIC, 60));
            g.drawString("Switch players...", 170, 450);
        }

        //Displays that player 1 wins
        else if(status == 5)
        {
            drawStartScreen(g);
            g.drawString(game.getPlayer1().getName(), 250, 350);
            g.drawString("WINS!", 300, 500);
        }

        //Displays that player 2 wins
        else if(status == 6)
        {
            drawStartScreen(g);
            g.drawString(game.getPlayer2().getName(), 250, 350);
            g.drawString("WINS!", 300, 500);
        }

        //Displays that a tie has occured
        else
        {
            drawStartScreen(g);
            g.drawString("TIE!", 260, 350);
        }
    }

    // Sets the status of the screen — which happens in the Game class
    public void setStatus(int s)
    {
        status = s;
    }

    // Prints the beginning countdown which lets the user know that player 1 will begin and that the game is starting
    public void printBeginning(Graphics g)
    {
        // Sets the background image
        Image starter = new ImageIcon("Resources/Sea/bigOcean.jpg").getImage();
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        // Sets the font and prints the players turn
        g.setFont(new Font("Blobtastics", Font.PLAIN, 60));
        g.drawString(game.getPlayer1().getName() + "'s turn will", 100, 350);
        g.drawString("start in ", 250, 450);

        // Does the countdown by using the sleep method
        // Uses a try catch method in order to throw an error if something goes wrong during the sleep time
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Prints the three and sleeps for 1  second
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("3", 500, 400);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Prints the 2 and sleeps for 1 second
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("2", 500, 400);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Prints the 1 and sleeps for 1 second
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("1", 500, 400);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Draws the starting screen which is used at the beginning and end of the program
    public void drawStartScreen(Graphics g)
    {
        g.setFont(new Font("Blobtastics", Font.PLAIN, 100));
        Image starter = new ImageIcon("Resources/Sea/bigOcean.jpg").getImage();
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    // Draws the screen which is used when the game is being played
    public void drawPlayScreen(Graphics g, int fontSize)
    {
        g.setFont(new Font("Blobtastics", Font.PLAIN, fontSize));
        Image starter = new ImageIcon("Resources/Sea/underwater.jpg").getImage();
        g.drawImage(starter, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }
}
