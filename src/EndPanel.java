

import javax.swing.*;
import java.awt.*;


import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;

public class EndPanel extends JPanel {


    /**
     * Creates the drawing canvas
     * @param scale the scale of the panel in pixels
     * @param gameScape the game data object
     **/
    private int scale;
    private Landscape gameScape;
    private boolean didWin = false;


    private Font endFont = new Font("arial", Font.BOLD, 25);
    private String wonStr = "Congrats You Won and Killed The Wumpus";
    private String loseStr = "You Lost Idiot";
    private int moves = 0;



    public EndPanel(Landscape gameScape, int scale, ActionListener actions) {
        super();
        this.setPreferredSize( new Dimension(gameScape.getWidth(), gameScape.getHeight() ) );
        this.setBackground(Color.BLACK);
        this.gameScape = gameScape;
        this.scale = scale;
    }

    public void setMoves(int moves)
    {
        this.moves = moves;
    }

    public void setDidWin(boolean didWin)
    {
        this.didWin = didWin;
    }
    /**
     * Method overridden from JComponent that is responsible for
     * drawing components on the screen.  The supplied Graphics
     * object is used to draw.
     *
     * @param g		the Graphics object used for drawing
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.YELLOW);
        if(this.didWin)
        {
            g.drawString(this.wonStr, this.gameScape.getWidth()/4, (this.gameScape.getHeight()/4));
        }
        else
        {
            g.drawString(this.loseStr, this.gameScape.getWidth()/4, (this.gameScape.getHeight()/4));
        }
        g.drawString("Press 'r' to Restart or 'q' to quit", this.gameScape.getWidth()/4, (this.gameScape.getHeight()/4)*3);

        g.drawString(("You did it in  " + this.moves + " moves"), this.gameScape.getWidth()/4, (this.gameScape.getHeight()/2));
    }
}




