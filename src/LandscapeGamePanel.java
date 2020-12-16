import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class LandscapeGamePanel extends JPanel {


    /**
     * Creates the drawing canvas
     * @param scale the scale of the panel in pixels
     * @param gameScape the game data object
     **/
    private int scale;
    private Landscape gameScape;
    public LandscapeGamePanel(Landscape gameScape, int scale) {
        super();
        this.setPreferredSize( new Dimension(gameScape.getWidth(), gameScape.getHeight() ) );
        this.setBackground(Color.black);
        this.gameScape = gameScape;
        this.scale = scale;
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
        gameScape.draw( g, scale );
    }
}
