import javax.swing.*;
import java.awt.*;


import javax.swing.*;
        import java.awt.Graphics;
        import java.awt.Dimension;
        import java.awt.Color;
import java.awt.event.ActionListener;

public class StartMenuPanel extends JPanel {


    /**
     * Creates the drawing canvas
     * @param scale the scale of the panel in pixels
     * @param gameScape the game data object
     **/
    private int scale;
    private Landscape gameScape;
    private JButton easy;
    private  JButton normal;
    private JButton hard;
    private Font titleFont = new Font("arial", Font.BOLD, 25);
    private String titleStr = "Welcome To Hunt the Wampus";


    private String instructions1 = "The Objective of this game is for you the hunter to find and kill the wumpus.";
    private String instructions2 =  "To do so you must gather clues by exploring the dungon. Blood will appear";
    private String instructions3 = "on the floor when you are 2 rooms away form the Wumpus. To win you must ";
    private String instructions4 = " shoot the wumpus with an arrow from the adjacent room. If you go";
    private String instructions5 = "into the room with the wumpus you will dies and lose.";
    private String instructions6 ="Beware as there are traps in the cave that will kill you instantly and";
    private String instructions7 ="also bats where if you stay in the room  with them to long you will also die and lose.";
    private String instructions8 ="the difficulty changes the number of bats and traps there are in the cave.";
    private String instructions9 ="the easy difficulty also gives the hunter an extra arrow.";
    private String instructions10 =" Wove the character with WSADF and shoot ";
    private String instructions11 ="in the direction of your choice with IKJL. Press Q to quit at anytime.";

    public StartMenuPanel(Landscape gameScape, int scale, ActionListener actions) {
        super(null);
        this.setPreferredSize( new Dimension(gameScape.getWidth(), gameScape.getHeight() ) );
        this.setBackground(Color.BLACK);
        this.gameScape = gameScape;
        this.scale = scale;
        this.easy = new JButton("Easy");
        this.normal = new JButton("Normal");
        this.hard = new JButton("Hard");

        this.easy.addActionListener(actions);
        this.normal.addActionListener(actions);
        this.hard.addActionListener(actions);

        this.easy.setBounds(100,500, 100, 50);


        this.normal.setBounds(350,500, 100, 50);

        this.hard.setBounds(600, 500, 100, 50);



        this.add(this.hard);
        this.add(this.normal);
        this.add(this.easy);
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

        //Graphics2D g2d = (Graphics2D)g;

        g.setFont(this.titleFont);
        g.setColor(Color.YELLOW);

        g.drawString(this.titleStr, this.gameScape.getWidth()/4, (this.gameScape.getHeight()/16));
        g.drawString("Chose A Difficulty to Start", this.gameScape.getWidth()/4, (this.gameScape.getHeight()/8)*6);

        Font instructionFont = new Font("arial", Font.BOLD, 15);
        g.setFont(instructionFont);
        g.setColor(Color.white);

        g.drawString(this.instructions1, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*6);
        g.drawString(this.instructions2, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*7);
        g.drawString(this.instructions3, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*8);
        g.drawString(this.instructions4, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*9);
        g.drawString(this.instructions5, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*10);
        g.drawString(this.instructions6, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*11);
        g.drawString(this.instructions7, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*12);
        g.drawString(this.instructions8, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*13);
        g.drawString(this.instructions9, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*14);
        g.drawString(this.instructions10, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*15);
        g.drawString(this.instructions11, this.gameScape.getWidth()/8, (this.gameScape.getHeight()/32)*16);
    }
}
