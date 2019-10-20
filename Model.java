package blockbreak.model;

import java.util.Random;
import java.util.Arrays;

/**
 * Model  Block Breaker Mini game logic
 */

public class Model {

    private int[][] tilePlacement;
    private int[] counter = {0, 0, 0, 0, 0};
    private int Score = 0;
    public int turnCount = 7;

    public Model() {

        this.tilePlacement = setGraph();
    }


    /**
     * Set the initial graph
     * @return int[][]
     */

    private int[][] setGraph() {
        int[][] colorIndex;
        colorIndex = new int[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Random rand = new Random();
                colorIndex[row][col] = rand.nextInt(4);
            }
        }
        return colorIndex;
    }

    /**
     * Get the current graph layout
     * @return int[][]
     */

    public int[][] getTilePlacement() {

        return this.tilePlacement;
    }
    /**
     * Switch the tiles in the graph
     * @return int[][]
     */

    public int[][] switchedTiles(int cursorPosColOne, int cursorPosRowOne, int cursorPosColTwo, int cursorPosRowTwo) {

        int temp = this.tilePlacement[cursorPosColOne][cursorPosRowOne];

        this.tilePlacement[cursorPosColOne][cursorPosRowOne] = this.tilePlacement[cursorPosColTwo][cursorPosRowTwo];
        this.tilePlacement[cursorPosColTwo][cursorPosRowTwo] = temp;
        return this.getTilePlacement();
    }


    /**
     * Driver for checking if there are matching colors removing rows and dropping higher blocks
     *
     */

    public void refreshTiles(int l, int j, int recursivetracker) {

        if (l == 4 && j == 0 && recursivetracker == 0) {
            Random rand = new Random();
            int scored = rand.nextInt(1000);
            this.Score = Score +scored;
            this.tilePlacement[0][0] = 4;
            this.tilePlacement[0][1] = 4;
            this.tilePlacement[0][2] = 4;
            this.tilePlacement[0][3] = 4;
        } else if (l == 4 && j != 0 && recursivetracker == 0) {
            this.tilePlacement[j][0] = this.tilePlacement[j - 1][0];
            this.tilePlacement[j][1] = this.tilePlacement[j - 1][1];
            this.tilePlacement[j][2] = this.tilePlacement[j - 1][2];
            this.tilePlacement[j][3] = this.tilePlacement[j - 1][3];
            refreshTiles(l, j - 1, 0);
        }
        if (l == 3 && j == 0) {
            if (recursivetracker == 1 || this.tilePlacement[0][0] == this.tilePlacement[0][1] && this.tilePlacement[0][1] == this.tilePlacement[0][2]) {
                this.tilePlacement[0][0] = 4;
                this.tilePlacement[0][1] = 4;
                this.tilePlacement[0][2] = 4;
                Random rand = new Random();
                int scored = rand.nextInt(500);
                this.Score = Score +scored;
            } else if (recursivetracker == 2 || this.tilePlacement[0][1] == this.tilePlacement[0][2] && this.tilePlacement[0][2] == this.tilePlacement[0][3]) {
                this.tilePlacement[0][1] = 4;
                this.tilePlacement[0][2] = 4;
                this.tilePlacement[0][3] = 4;
                Random rand = new Random();
                int scored = rand.nextInt(500);
                this.Score = Score +scored;
            }

        } else if (l == 3 && j > 0) {
            if (j > 0 && (recursivetracker == 1 || this.tilePlacement[j][0] == this.tilePlacement[j][1] && this.tilePlacement[j][1] == this.tilePlacement[j][2])) {
                this.tilePlacement[j][0] = this.tilePlacement[j - 1][0];
                this.tilePlacement[j][1] = this.tilePlacement[j - 1][1];
                this.tilePlacement[j][2] = this.tilePlacement[j - 1][2];
                refreshTiles(l, j - 1, 1);
            } else if (j > 0 && (recursivetracker == 2 || this.tilePlacement[j][1] == this.tilePlacement[j][2] && this.tilePlacement[j][2] == this.tilePlacement[j][3])) {
                this.tilePlacement[j][1] = this.tilePlacement[j - 1][1];
                this.tilePlacement[j][2] = this.tilePlacement[j - 1][2];
                this.tilePlacement[j][3] = this.tilePlacement[j - 1][3];
                refreshTiles(l, j - 1, 2);
            }

        }
    }

    /**
     * Checks rows for mathcing colors
     *
     */
    public void checkRow() {

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.counter[this.tilePlacement[j][i]]++;
            }


            for (int l = 0; l < this.counter.length; l++) {

                refreshTiles(this.counter[l], j, 0);
            }
            Arrays.fill(this.counter, 0);
        }



    }

    /**
     * Checks the current score
     * @return int
     */

    public int getScore(){
        return this.Score;
    }
    }

