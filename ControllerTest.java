package controller;

import static org.junit.Assert.*;

public class ControllerTest {
    blockbreak.model.Model model;
    blockbreak.controller.Controller controller;

    @org.junit.Before
    public void setUp() throws Exception {
        model = new blockbreak.model.Model();
       controller = new blockbreak.controller.Controller(model);
    }

    @org.junit.Test
    public void switchPos() throws Throwable{

    int[][]  a =  controller.getControlTilePlacement();  ;
    assertArrayEquals(a, controller.getControlTilePlacement());
    controller.switchPos(0,1,1,0);
        try {
            assertArrayEquals(a, controller.getControlTilePlacement());
        } catch (Throwable e) {
        }
    }

    @org.junit.Test
    public void checkRows() {
        assertArrayEquals(controller.checkRows(),model.getTilePlacement() );
    }

    @org.junit.Test
    public void getControlTilePlacement() {
        assertArrayEquals(controller.getControlTilePlacement(),model.getTilePlacement() );
    }

    @org.junit.Test
    public void getModelScore() {
        assertEquals(controller.getModelScore() , model.getScore());
    }


}