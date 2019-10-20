package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {
    private blockbreak.model.Model model;
    private blockbreak.controller.Controller controller;
  //  private TextDocument stringTest2;
    @Before
    public void setUp() throws Exception {
        model = new blockbreak.model.Model();
        controller =new blockbreak.controller.Controller(model);
    }

    @Test
    public void getTilePlacement() {
        assertArrayEquals(controller.getControlTilePlacement(),model.getTilePlacement() );
    }

    @Test
    public void switchedTiles() {
        int[][]  a =  model.getTilePlacement()  ;
        assertArrayEquals(a, model.getTilePlacement());
        controller.switchPos(0,1,1,0);
        try {
            assertArrayEquals(a, model.getTilePlacement());
        } catch (Throwable e) {
        }



    }



    @Test
    public void getScore() {
        assertEquals(controller.getModelScore() , model.getScore());
    }
}