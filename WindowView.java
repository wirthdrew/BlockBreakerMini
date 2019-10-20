package blockbreak.view;

import blockbreak.controller.Controller;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * View  Block Breaker Mini game logic
 */

public class WindowView extends JPanel implements KeyListener {


    private int[][] map;
    private Controller controller;
    private JFrame window;
    private int cursorRow;
    private int cursorCol;
    private int switchCounter;
    private int cursorPosRowOne;
    private int cursorPosRowTwo;
    private int cursorPosColTwo;
    private int cursorPosColOne;
    private int x;
    private int viewTurns;
    private Graphics s;


    private boolean gameOver;

    private static final int TITLE_BAR = 23;
    private static final int CELL_SIZE = 150;
    private static int windowSize() {
        return CELL_SIZE * 4;
    }

    /**
     * Window View Constructor
     **/
    public WindowView (Controller controller) {
        makeWindow();
        this.switchCounter = 0;

        this.map = new int[4][4];
        this.controller = controller;
        this.map =  this.controller.getControlTilePlacement();
        this.x =0;
        this.viewTurns = this.controller.turnCountControl;


        window.addKeyListener(this);
    }

    /**
     * Make windows
     *
     **/

    private void makeWindow() {
        window = new JFrame("Match Colors");
        window.setContentPane(this);
        window.setSize(windowSize(), TITLE_BAR + windowSize());
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Block Breaker Mini");
    }



    /**
     * Drives most of the window graphic
     *
     **/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrid(g);
        paintLattice(g);
        paintCursor(g);

        this.x= this.controller.getModelScore();
        g.drawString(String.valueOf(x), 70, 25);
        this.viewTurns= this.controller.turnCountControl;
        g.drawString(String.valueOf(viewTurns), 550, 25);

        checkWin(g);
    }
    /**
     *Paints the graph lines
     **
     **/
    private static void paintLattice(Graphics g) {
        int yTop = 0;
        int yBottom = windowSize();
        int xLeft = 0;
        int xRight = windowSize();
        g.setColor(Color.BLACK);
        g.drawLine(xLeft+CELL_SIZE, yTop, xLeft+CELL_SIZE, yBottom);
        g.drawLine(xLeft+2*CELL_SIZE, yTop, xLeft+2*CELL_SIZE, yBottom);
        g.drawLine(xLeft+3*CELL_SIZE, yTop, xLeft+3*CELL_SIZE, yBottom);
        g.drawLine(xLeft, yTop+CELL_SIZE, xRight, yTop+CELL_SIZE);
        g.drawLine(xLeft, yTop+2*CELL_SIZE, xRight, yTop+2*CELL_SIZE);
        g.drawLine(xLeft, yTop+3*CELL_SIZE, xRight, yTop+3*CELL_SIZE);
        g.drawString("Score:", 25, 25);
        g.drawString("Turns Left:", 450, 25);


    }

    /**
     *Fills the grid
     *
     **/
    private void paintGrid(Graphics g) {
        setIgnoreRepaint(true);
        for (int row=0; row<4; row++) {
            for (int col=0; col<4; col++) {
                int y = col * CELL_SIZE;
                int x = row * CELL_SIZE;

                   if (this.map[col][row] == 0){
                        paintRed(g, x, y); }
                else if (this.map[col][row] == 1){
                        paintGreen(g, x, y); }
                    else if (this.map[col][row] == 2){
                        paintBlue(g, x, y); }
                    else if (this.map[col][row] == 3){
                        paintYellow(g, x, y); }
                }
            }
        }

    /**
     *Blue Fill
     *
     **/
    private static void paintBlue(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
       // g.draw3DRect( x,  y,  CELL_SIZE,  CELL_SIZE, true);
    }
    /**
     *Green Fill
     *
     **/

    private static void paintGreen(Graphics g, int x, int y) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
     //   g.draw3DRect( x,  y,  CELL_SIZE,  CELL_SIZE, true);
    }
    /**
     *Yellow Fill
     *
     **/

    private static void paintYellow(Graphics g, int x, int y) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
     //   g.draw3DRect( x,  y,  CELL_SIZE,  CELL_SIZE, true);
    }
    /**
     *Red Fill
     *
     **/
    private static void paintRed(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);

    }

    /**
     *Paint the cursor
     *
     **/
    private void paintCursor(Graphics g) {
        int x = cursorRow * CELL_SIZE;
        int y = cursorCol * CELL_SIZE;
        Color currentColor = g.getColor();
        g.setColor(Color.PINK);

        g.drawLine(x, y, x, y + CELL_SIZE);
        g.drawLine(x, y, x+CELL_SIZE, y);
        g.drawLine(x+CELL_SIZE, y, x+CELL_SIZE, y + CELL_SIZE);
        g.drawLine(x, y+CELL_SIZE, x+CELL_SIZE, y+CELL_SIZE);

        g.setColor(currentColor);
    }


    /**
     *Check if the game is a win
     *@param g
     **/


    public void checkWin(Graphics g){

       if(this.viewTurns == 0){
        if(this.x >2000){
            g.drawString("You Win!! Points are redeemable at your local Wal-mart", 100, 250);
        repaint();
        }
        else{ g.drawString("You lose", 250, 250);
        repaint();
        }
       }
    }
    /**
     *Handles Key events
     *
     **/
    @Override
    public void keyTyped(KeyEvent e) { }
    /**
     *Handles Key events
     *
     **/
    @Override
    public void keyPressed(KeyEvent e) { }
    /**
     *Handles Key events specifically when the user presses enter.
     *
     **/
    @Override
    public void keyReleased(KeyEvent e) {
        if (this.gameOver) return;
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (this.cursorRow > 0) {
                    this.cursorRow--;

                    break;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (this.cursorRow < 3) {
                    this.cursorRow++;
                    break;
                }
                break;
            case KeyEvent.VK_UP:
                if (this.cursorCol > 0) {
                    this.cursorCol--;
                    break;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (this.cursorCol < 3) {
                    this.cursorCol++;
                    break;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (this.switchCounter==0){
                 cursorPosRowOne  = cursorRow;
                 cursorPosColOne = cursorCol;
                this.switchCounter++;
                }
                else if (this.switchCounter == 1 ){
                cursorPosRowTwo = cursorRow ;
                cursorPosColTwo =cursorCol;

                this.map = this.controller.switchPos(cursorPosColOne,cursorPosRowOne, cursorPosColTwo, cursorPosRowTwo);
                this.switchCounter = 0;

                repaint();
                this.map = this.controller.checkRows();
                 }
                break;
            default:
        }
        repaint();

    }
}
