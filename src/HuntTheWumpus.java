/*
***Matt Bass***
HuntTheWampus.java

This is the main game interactive display

Fall 2020
CS 231 Project 9
*/



//TODO
// 1. finish makeing panels
// 2. implement graphics by importing image.
// 3. make a randomly generated map
//





import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

/**
 * Creates a window with two text fields, one buttons, and a large
 * display area. The app then tracks the mouse over the display area.
 **/
public class HuntTheWumpus {

    // These four fields are as in the LandscapeDisplay class (though
    // they are now all private)
    private JFrame frame;
    private LandscapeGamePanel gamePanel;
    private Landscape gameScape;
    private int scale;
    private  StartMenuPanel startMenuPanel;
    private Control control;
    private  EndPanel endPanel;
    /** fields related to demo of mouse interaction **/
    // Unless you have a good reason to report the mouse position in
    // HuntTheWumpus, you should remove these fields and all the
    // code that affects them.
    // There here to demonstrate how you would add a message to the bottom
    // of the window. For HuntTheWumpus, you may want to use it to indicate
    // that the Hunter is armed or close to the Wumpus, or dead.
    JLabel fieldX; // Label field 1, displays the X location of the mouse
    JLabel fieldY; // Label field 2, displays the Y location of the mouse

    // controls whether the game is playing or exiting
    private enum PlayState { MENU, PLAY, END, STOP }
    private PlayState state;

    private final int X_MAX = 12;
    private final int Y_MAX = 9;

    private int numRooms;


    //dificulty are easy medium and hards going from 0 to 2
    private int difficulty = 0;

    private int percentTraps = 5;

    private int numBats = 4;


    //this is a field to hold a graph
    private Graph gameGraph = new Graph(Y_MAX,X_MAX);



    //fields to hold hunter and wumpus
    private Hunter hunter;
    private Wumpus wumpus;


    //field to hold traps
    private ArrayList<Trap> trapList = new ArrayList<Trap>();

    private ArrayList<Bat> batList = new ArrayList<Bat>();


    //field to hold number of move it took to beat or lose game
    private int numMoves = 0;

    //field to hold if the player won originaly set to fales
    private boolean playerWon = false;


    private int numArrows = 1;

    private int percentConnect = 56;

//    /**
//     * Creates the main window
//     * @param gameScape the Landscape that will hold the objects we display
//     * @param scale the size of each grid element
//     **/
    public HuntTheWumpus() {
        // The game should begin in the play state.
        this.state = PlayState.MENU;

        this.control = new Control();

        // Create the elements of the Landscape and the game.
        this.scale = 64; // determines the size of the grid




        // Make the main window
        this.frame = new JFrame("Hunt the Wumpus");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapeGamePanel we have been using). and put it in the window\
        this.numRooms = 0;
        createGameBoard();
        this.gamePanel = new LandscapeGamePanel(this.gameScape, this.scale);
        this.frame.add( this.gamePanel, BorderLayout.CENTER );
        this.frame.pack();



        ///make the Main Menu Screen and add it to the window frame
        this.startMenuPanel = new StartMenuPanel(this.gameScape, this.scale, this.control );
        this.frame.add(this.startMenuPanel);
        this.frame.pack();




        ///make the Ending screen
        this.endPanel = new EndPanel(this.gameScape, this.scale, this.control );
        this.frame.add(this.endPanel);
        this.frame.pack();

        



        // make the labels and a button and put them into the frame
        // below the canvas.
        this.fieldX = new JLabel("X");
        this.fieldY = new JLabel("Y");
        JButton quit = new JButton("Quit");
        JPanel menuPanel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
        menuPanel.add( this.fieldX );
        menuPanel.add( this.fieldY );
        menuPanel.add( quit );
        this.frame.add( menuPanel, BorderLayout.SOUTH);
        this.frame.pack();


        // Add key and button controls.
        // We are binding the key control object to the entire window.
        // That means that if a key is pressed while the window is
        // in focus, then control.keyTyped will be executed.
        // Because we are binding quit (the button) to control, control.actionPerformed will
        // be called if the quit button is pressed. If you make more than one button,
        // then the same function will be called. Use an if-statement in the function
        // to determine which button was pressed (check out Control.actionPerformed and
        // this advice should make sense)



        this.frame.addKeyListener(control);
        this.frame.setFocusable(true);
        this.frame.requestFocus();
        quit.addActionListener( control );

        // for mouse control
        // Make a MouseControl object and then bind it to the canvas
        // (the part that displays the Landscape). When the mouse
        // enters, exits, moves, or clicks in the canvas, the appropriate
        // method will be called.
//        MouseControl mc = new MouseControl();
//        this.gamePanel.addMouseListener( mc );
//        this.gamePanel.addMouseMotionListener( mc );

        MouseControl mc = new MouseControl();
        this.frame.addMouseListener( mc );
        this.frame.addMouseMotionListener( mc );

        // The last thing to do is make it all visible.
        this.frame.setVisible( true );

    }




    //getter to get Jframe
    public JFrame getFrame(){
        return this.frame;
    }


    // This is the class where you define functions that are
    // executed when certain mouse events take place.
    private class MouseControl extends MouseInputAdapter {
        public void mouseMoved(MouseEvent e) {
            fieldX.setText( "" + e.getPoint().x );
            fieldY.setText( "" + e.getPoint().y );
        }

        public void mouseDragged(MouseEvent e) {
            fieldX.setText( "" + e.getPoint().x );
            fieldY.setText( "" + e.getPoint().y );
        }

        public void mousePressed(MouseEvent e) {
            System.out.println( "Pressed: " + e.getClickCount() );
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println( "Released: " + e.getClickCount());
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println( "Entered: " + e.getPoint() );
        }

        public void mouseExited(MouseEvent e) {
            System.out.println( "Exited: " + e.getPoint() );
        }

        public void mouseClicked(MouseEvent e) {
            System.out.println( "Clicked: " + e.getClickCount() );
        }
    } // end class MouseControl

    private class Control extends KeyAdapter implements ActionListener {




        //this is for keyActions
        public void keyTyped(KeyEvent e) {
            System.out.println( "Key Pressed: " + e.getKeyChar() );



            //keys that have the same function no matter what panel (screen is being displayed)

            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") )
            {
                state = PlayState.STOP;
            }



            //keys that have different commands based on the panel

                //wasd

                //W
                if( ("" + e.getKeyChar()).equalsIgnoreCase("w") )
                {
                    //PLAYSTATE
                    if(state == PlayState.PLAY)
                    {
                        hunter.move(0);
                        hunterWithBat();
                        numMoves++;
                    }
                }

                //S
                if( ("" + e.getKeyChar()).equalsIgnoreCase("s") )
                {
                    //PLAYSTATE
                    if(state == PlayState.PLAY)
                    {
                        hunter.move(1);
                        hunterWithBat();
                        numMoves++;
                    }
                }


                //D
                if( ("" + e.getKeyChar()).equalsIgnoreCase("d") )
                {
                    //PLAYSTATE
                    if(state == PlayState.PLAY)
                    {
                        hunter.move(2);
                        hunterWithBat();
                        numMoves++;
                    }
                }

                //A
                if( ("" + e.getKeyChar()).equalsIgnoreCase("a") )
                {
                    //PLAYSTATE
                    if(state == PlayState.PLAY)
                    {
                        hunter.move(3);
                        hunterWithBat();
                        numMoves++;
                    }
                }







                //shooting arrow keys
            ///IKJL for NSEW
            //I
            if( ("" + e.getKeyChar()).equalsIgnoreCase("i") )
            {
                //PLAYSTATE
                if(state == PlayState.PLAY)
                {
                    Arrow arrowShot = hunter.shoot(0);
                    if(arrowShot.getRoom() == wumpus.getRoom())
                    {
                        wumpus.shot(arrowShot);
                    }
                }
            }

            //K
            if( ("" + e.getKeyChar()).equalsIgnoreCase("k") )
            {
                //PLAYSTATE
                if(state == PlayState.PLAY)
                {
                    Arrow arrowShot = hunter.shoot(1);
                    if(arrowShot.getRoom() == wumpus.getRoom())
                    {
                        wumpus.shot(arrowShot);
                    }
                }
            }


            //J
            if( ("" + e.getKeyChar()).equalsIgnoreCase("j") )
            {
                //PLAYSTATE
                if(state == PlayState.PLAY)
                {
                    Arrow arrowShot = hunter.shoot(2);
                    if(arrowShot.getRoom() == wumpus.getRoom())
                    {
                        wumpus.shot(arrowShot);
                    }
                }
            }

            //L
            if( ("" + e.getKeyChar()).equalsIgnoreCase("l") )
            {
                //PLAYSTATE
                if(state == PlayState.PLAY)
                {
                    Arrow arrowShot = hunter.shoot(3);
                    if(arrowShot.getRoom() == wumpus.getRoom())
                    {
                        wumpus.shot(arrowShot);
                    }
                }
            }







            //SPACEBAR ACTION ONLY WORKS ON START SCREEN
            if( ("" + e.getKeyChar()).equalsIgnoreCase(" ") )
            {
                //PLAYSTATE
                if(state == PlayState.MENU)
                {
                   state = PlayState.PLAY;
                }
            }





            //reset game with r when on end screen
            if( ("" + e.getKeyChar()).equalsIgnoreCase("r") )
            {
                //PLAYSTATE
                if(state == PlayState.END)
                {
                    reset();
                    state = PlayState.MENU;
                }
            }
        }


        //this is for button clicked actions
        public void actionPerformed(ActionEvent event) {

            // If the Quit button was pressed
            if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
                System.out.println("Quit button clicked");
                state = PlayState.STOP;
            }

            // If the Easy button was pressed
            else if( event.getActionCommand().equalsIgnoreCase("Easy") ) {
                System.out.println("Easy button clicked");
                numArrows = 2;
                reset();
                state = PlayState.PLAY;
            }
            // If the Normal button was pressed
            else if( event.getActionCommand().equalsIgnoreCase("Normal") ) {
                System.out.println("Normal button clicked");
                numBats = 8;
                percentTraps = 10;
                reset();
                state = PlayState.PLAY;
            }
            // If the Hard button was pressed
            else if( event.getActionCommand().equalsIgnoreCase("Hard") ) {
                System.out.println("Hard button clicked");
                numBats = 16;
                percentTraps = 15;
                reset();
                state = PlayState.PLAY;
            }

        }
    } // end class Control









    //also where I add game controls
    public void repaint()
    {
        //the player lost
        if(this.wumpus.getRoom().equals(this.hunter.getRoom()))
        {
            this.state = PlayState.END;
            this.endPanel.setMoves(this.numMoves);
        }

        //if the wumpus is dead
        if(!this.wumpus.isLiving())
        {
            this.playerWon = true;
            this.endPanel.setDidWin(this.playerWon);
            this.endPanel.setMoves(this.numMoves);
            this.state = PlayState.END;
        }



        //if player goes in a trap
        for(Trap trap : this.trapList)
        {
            if(this.hunter.getRoom().equals(trap.getRoom()))
            {
                this.endPanel.setMoves(this.numMoves);
                this.state = PlayState.END;
            }
        }



        //if player is in a room with a bat
        //if player goes in a trap
        for(Bat bat : this.batList)
        {
            if(this.hunter.getRoom().equals(bat.getRoom()))
            {
                this.hunter.inflictDamage(bat.getDamage());
            }
        }


        //if the player health is zero
        if(!this.hunter.isAlive())
        {
            this.endPanel.setMoves(this.numMoves);
            this.state = PlayState.END;
        }


        this.frame.repaint();
    }

    public void dispose() {
        this.frame.dispose();
    }








    public Room makeNeighbor(int xPos, int yPos, int parentDirection, Room parent)
    {
        this.numRooms++;

        ArrayList<Integer> possibleNeighborDirections = new ArrayList<>();
        for (int i=0; i<4; i++)
        {
            if (i != parentDirection)
            {
                possibleNeighborDirections.add(i);
            }
        }

        Random ran = new Random();
        int amtNeighboirs = ran.nextInt(4)+1;


        Room newRoom = new Room(xPos,yPos, false);
//        newRoom.setVisible(true);

        if (roomExists(newRoom.getXPos(), newRoom.getYPos()) != null){

            return null;
        }


        this.gameGraph.addBiEdge(newRoom, parent);
        if(xPos * yPos <= ((this.X_MAX-1) * (this.Y_MAX - 1)))
        {
            gameScape.addBackgroundAgent(newRoom);
        }
        else
        {
            return null;
        }

        for (int i=0; i< amtNeighboirs; i++)
        {
            if (possibleNeighborDirections.size() == 0){
                break;
            }
            int bound = possibleNeighborDirections.size()-1;
            int newNeighnorDirection;
            if (bound == 0){
                newNeighnorDirection = possibleNeighborDirections.get(0);
            } else{
                int index = ran.nextInt(bound);
                newNeighnorDirection = possibleNeighborDirections.get(index);
            }

            possibleNeighborDirections.remove(possibleNeighborDirections.indexOf(newNeighnorDirection));
            Room newNeighbor = null;
            if (newNeighnorDirection == 0){ //north
                if (yPos == 0 || roomExists(xPos, yPos-1) != null){
                    i--;
                    if((roomExists(xPos, yPos-1)!= null) && this.percentConnect < ran.nextInt(100))
                    {

                        if(this.percentTraps >= ran.nextInt(100))
                        {
                            Trap trap = new Trap(newRoom);
                            this.trapList.add(trap);
                            this.gameScape.addTrap(trap);
                            this.gameGraph.addUniEdge(roomExists(xPos, yPos-1), newRoom );
                        }
                        else
                        {
                            this.gameGraph.addBiEdge(newRoom, roomExists(xPos, yPos-1));
                        }
                    }

                    continue;
                }
                newNeighbor = makeNeighbor(xPos, yPos-1, 1, newRoom);
            } else if (newNeighnorDirection == 1){ //south
                if (yPos + 1 == Y_MAX || roomExists(xPos, yPos+1) != null){
                    i--;
                    //TODO maybe add it so it randomly connects definitly make a mroe random map

                    if((roomExists(xPos, yPos+1)!= null) && this.percentConnect < ran.nextInt(100))
                    {
                        if(this.percentTraps >= ran.nextInt(100))
                        {
                            Trap trap = new Trap(newRoom);
                            this.trapList.add(trap);
                            this.gameScape.addTrap(trap);
                            this.gameGraph.addUniEdge(roomExists(xPos, yPos+1), newRoom );
                        }
                        else
                        {
                            this.gameGraph.addBiEdge(newRoom, roomExists(xPos, yPos+1));
                        }
                    }
                    continue;
                }
                newNeighbor = makeNeighbor(xPos, yPos+1, 0, newRoom);
            }else if (newNeighnorDirection == 2){//east
                if (xPos == 0 || roomExists(xPos-1, yPos) != null) {
                    i--;
                    if((roomExists(xPos-1, yPos)!= null) && this.percentConnect < ran.nextInt(100))
                    {
                        if(this.percentTraps >= ran.nextInt(100))
                        {
                            Trap trap = new Trap(newRoom);
                            this.trapList.add(trap);
                            this.gameScape.addTrap(trap);
                            this.gameGraph.addUniEdge(roomExists(xPos-1, yPos), newRoom );
                        }
                        else
                        {
                            this.gameGraph.addBiEdge(newRoom, roomExists(xPos-1, yPos));
                        }
                    }
                    continue;
                }
                newNeighbor = makeNeighbor(xPos-1, yPos, 3, newRoom);
            }else if (newNeighnorDirection == 3){//west
                if (xPos + 1 == X_MAX || roomExists(xPos+1, yPos)!= null)
                {
                    i--;
                    if((roomExists(xPos+1, yPos)!= null ) && this.percentConnect < ran.nextInt(100))
                    {
                        if(this.percentTraps >= ran.nextInt(100))
                        {
                            Trap trap = new Trap(newRoom);
                            this.trapList.add(trap);
                            this.gameScape.addTrap(trap);
                            this.gameGraph.addUniEdge(roomExists(xPos+1, yPos), newRoom );
                        }
                        else
                        {
                            this.gameGraph.addBiEdge(newRoom, roomExists(xPos+1, yPos));
                        }
                    }
                    continue;
                }
                newNeighbor = makeNeighbor(xPos+1, yPos, 2, newRoom);
            }
            if (newNeighbor == null){
                break;
            }

            this.gameGraph.addBiEdge(newRoom, newNeighbor);

        }

        return newRoom;
    }




    Vertex roomExists(int xPos, int yPos){
        for (Vertex r: gameScape.getRoomList()){
            if (xPos == r.getXPos() && yPos == r.getYPos()){
                return r;
            }
        }
        return null;
    }




    //this is a method to build the game board
    //the player will always start at 0,0 room
    //
    public void createGameBoard()
    {
        //make it 12 by 9 aspect ratio
        gameScape = new Landscape(this.scale*X_MAX,this.scale*Y_MAX);

        //Todo make room make neighbors

        Room startingRoom = new Room(0,0, true);

        //startingRoom.setVisible(true);


        startingRoom.connect(makeNeighbor(0, 0, 0, startingRoom));

        System.out.println(gameScape.toString());


        Random ran = new Random();
        this.hunter = new Hunter(gameScape.getRoomList().get(0));
        this.gameScape.addHunter(this.hunter);

        //make arrows
        for(int i = 0; i<this.numArrows; i++)
        {
            Arrow arrow = new Arrow();
            this.hunter.addArrow(arrow);
            this.gameScape.addArrow(arrow);
        }

        boolean goodPosition = false;

        int wumpPos = 0;
        while(!goodPosition)
        {
                wumpPos = ran.nextInt(this.numRooms);
                if(wumpPos > 0)
                {
                    goodPosition = true;
                }
        }

        Vertex wupusStart = gameScape.getRoomList().get(wumpPos);
        this.wumpus = new Wumpus(wupusStart);
        this.gameGraph.shortestPath(wupusStart);
        this.gameScape.addWumpus(this.wumpus);



        //add bats

        for(int i = 0; i < this.numBats; i++)
        {
            goodPosition = false;

            int batPosition = 0;

            while(!goodPosition)
            {
                batPosition = ran.nextInt(this.numRooms);
                if(batPosition > 0)
                {
                    goodPosition = true;
                    Vertex batStart = gameScape.getRoomList().get(batPosition);
                    Bat newBat = new Bat(batStart);
                    this.batList.add(newBat);
                    this.gameScape.addBat(newBat);
                }
            }


        }
    }


//helper method to see if bat is in same room as hunter and update visability
    private  void hunterWithBat()
    {
        if(!this.batList.isEmpty())
        {
            for(Bat bat: this.batList)
            {
                if(this.hunter.getRoom() == bat.getRoom())
                {
                    bat.setIsVisable(true);
                }
            }
        }
    }



//method to reset the game
    public void reset()
    {
        this.playerWon = false;
        this.numRooms = 0;
        this.numMoves = 0;
        this.trapList.clear();
        this.batList.clear();
        createGameBoard();
        this.gamePanel = new LandscapeGamePanel(this.gameScape, this.scale);
        this.frame.add( this.gamePanel, BorderLayout.CENTER );
        this.frame.pack();

    }







    public static void main(String[] argv) throws InterruptedException {
        HuntTheWumpus w = new HuntTheWumpus();
        while (w.state != PlayState.STOP) {
            if(w.state == PlayState.MENU){
                w.gamePanel.setVisible(false);
                w.startMenuPanel.setVisible(true);
                w.endPanel.setVisible(false);
            }
            else if(w.state == PlayState.PLAY){
                w.gamePanel.setVisible(true);
                w.startMenuPanel.setVisible(false);
                w.endPanel.setVisible(false);
            }
            else if(w.state == PlayState.END){
                w.gamePanel.setVisible(false);
                w.startMenuPanel.setVisible(false);
                w.endPanel.setVisible(true);
            }


            w.repaint();
            Thread.sleep(33);
        }
        System.out.println("Disposing window");
        w.dispose();
    }

}


