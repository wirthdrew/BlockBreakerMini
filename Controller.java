package blockbreak.controller;

import blockbreak.model.Model;
import blockbreak.view.WindowView;

/**
 * Controller  Block Breaker Mini game logic
 */
public class Controller {

    private Model model;
    private WindowView view;
    private int numTurns;
    public int turnCountControl;
   // private Symbol nextPlayer;

    public Controller(Model model) {
        this.model = model;
        this.numTurns = 0;
      //  this.nextPlayer = firstPlayer;
        this.view = new WindowView(this);
        this.turnCountControl = this.model.turnCount;
    }

    /**
     * Switches the position of two tiles
     * @return int[][]
     * @param cursorPosColOne cursorPosRowOne cursorPosColTwo cursorPosRowTwo
     */
    public int[][] switchPos(int cursorPosColOne, int cursorPosRowOne, int cursorPosColTwo, int cursorPosRowTwo){

        int[][] blankswitch = this.model.getTilePlacement();
        if(blankswitch[cursorPosColTwo][cursorPosRowTwo]!=4&&blankswitch[cursorPosColOne][cursorPosRowOne]!=4
                && (Math.abs(cursorPosColOne -cursorPosColTwo) ==1 && Math.abs(cursorPosRowOne -cursorPosRowTwo) == 0)
                || (Math.abs(cursorPosColOne -cursorPosColTwo) ==0 && Math.abs(cursorPosRowOne -cursorPosRowTwo) == 1)){
            this.turnCountControl--;
            return this.model.switchedTiles(cursorPosColOne, cursorPosRowOne, cursorPosColTwo, cursorPosRowTwo);}
        return this.model.getTilePlacement();

    }
    /**
     * Check rows from matching colors
     * @return int[][]
     */

    public int[][] checkRows(){
        this.model.checkRow();

        return this.model.getTilePlacement();
    }

    /**
     * Get the tile arrangement from the model
     * @return int[][]
     */

    public int[][] getControlTilePlacement(){
        return this.model.getTilePlacement();

    }
    /**
     * Get the score from the model
     * @return int
     */
    public int getModelScore(){

        return this.model.getScore();
    }

    /**
     * Runs the game
     *
     */

    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);


    }
}
