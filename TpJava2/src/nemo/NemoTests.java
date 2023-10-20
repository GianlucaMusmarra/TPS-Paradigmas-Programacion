package nemo;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NemoTests {

    @Test // robot creates undestroyed
    public void test00(){
        Nemo robot = new Nemo(0,0,0);
    }

    @Test // robot creates at vector 0
    public void test01(){
        Nemo robot = new Nemo(0,0,0);

        assertEquals(0, robot.getXPos());
        assertEquals(0, robot.getYPos());

        assertEquals("cardinal_points.North", robot.movementManager.bow());
    }

    @Test // robot respects surface levels
    public void test02(){
        Nemo robot = new Nemo(0,0,0);

        assertEquals(false, robot.movementManager.isUnderWater());
    }

    @Test // robot bow() heading north when created
    public void test03(){
        Nemo robot = new Nemo(0,0,0);

        assertEquals("cardinal_points.North", robot.movementManager.bow());
    }

    @Test // stays in the same place when move is empty
    public void test04(){
        Nemo robot = new Nemo(0,0,0);
        robot.move("");

        assertEquals(0, robot.getXPos());
        assertEquals(0, robot.getYPos());
    }

    @Test // moves down correctly
    public void test05(){
        Nemo robot = new Nemo(0,0,0);
        robot.move("d");

        assertEquals(0, robot.getXPos());
        assertEquals((-1), robot.getZPos());
    }


    @Test // moves up correctly
    public void test06(){
        Nemo robot = new Nemo(0,0,0);
        robot.move("u");

        assertEquals(0, robot.getXPos());
        assertEquals((1), robot.getZPos());
    }

    @Test // combination of u and d works correctly
    public void test07(){
        Nemo robot = new Nemo(0,0,0);
        robot.move("uuud");

        assertEquals(0, robot.getXPos());
        assertEquals((2), robot.getZPos());
    }

    @Test // rotates correctly -90 grades
    public void test08(){
        Nemo robot = new Nemo(0,0,0);
        robot.move("l");

        assertEquals("cardinal_points.West", robot.movementManager.bow());
    }

    @Test // rotates correctly +90 grades
    public void test09(){
        Nemo robot = new Nemo(0,0,0);
        robot.move("r");

        assertEquals("cardinal_points.East", robot.movementManager.bow());
    }

    @Test // Combination of l and r works correctly
    public void test10() {
        Nemo robot = new Nemo(0,0,0);
        robot.move("lrl");

        assertEquals("cardinal_points.West", robot.movementManager.bow());
    }

    @Test // combination of u , d, l and r works correctly
    public void test11() {
        Nemo robot = new Nemo(0,0,0);
        robot.move("urrudulu");

        assertEquals(0, robot.getXPos());
        assertEquals((3), robot.getZPos());
        assertEquals("cardinal_points.East", robot.movementManager.bow());
    }

    @Test // forward changes y value
    public void test12() {
        Nemo robot = new Nemo(0,0,0);
        robot.move("f");

        assertEquals(1, robot.getYPos());
    }

    @Test // forward does not change x value
    public void test13() {
        Nemo robot = new Nemo(0,0,0);
        robot.move("f");

        assertEquals(0, robot.getXPos());
    }

    @Test // combination of f, l and r works correctly
    public void test14() {
        Nemo robot = new Nemo(0,0,0);
        robot.move("rflf");

        assertEquals(1, robot.getXPos());
        assertEquals(1, robot.getYPos());
    }

    @Test // m in surface does not destroys robot
    public void test15() {
        Nemo robot = new Nemo(0,0,0);
    }

    @Test // m almost in surface does not destroys robot
    public void test16() {
        Nemo robot = new Nemo(0,0,0);
        robot.move("d");
    }

    @Test // m underwater destroys robot
    public void test17() {
        Nemo robot = new Nemo(0,0,0);
        robot.move("dd");

        assertThrowsLike(() -> robot.move("m"), "Nemo has been destroyed!");
    }

    @Test // m underwater u surface and m, underwater again destroys robot
    public void test18() {
        Nemo robot = new Nemo(0,0,0);
        robot.move("ddud");

        assertThrowsLike(() -> robot.move("m"), "Nemo has been destroyed!");
    }

    private void assertThrowsLike(Executable executable, String message ) {
        Assertions.assertEquals( message,  assertThrows(Error.class, executable).getMessage() );
    }

}
