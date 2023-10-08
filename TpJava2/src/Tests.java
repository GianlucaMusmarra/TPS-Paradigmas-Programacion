import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Tests {

    @Test // robot creates undestroyed
    public void test00(){
        Nemo robot = new Nemo(0,0);

        assertEquals(false, robot.isDestroyed);
    }

    @Test // robot creates at vector 0
    public void test01(){
        Nemo robot = new Nemo(0,0);

        assertEquals(0, robot.coordinates.get(0).intValue());
        assertEquals(0, robot.coordinates.get(1).intValue());

        assertEquals("North", robot.bow);
    }

    @Test // robot respects surface levels
    public void test02(){
        Nemo robot = new Nemo(0,0);

        assertEquals(false, robot.isUnderWater);
    }

    @Test // robot bow heading north when created
    public void test03(){
        Nemo robot = new Nemo(0,0);

        assertEquals("North", robot.bow);
    }

    @Test // stays in the same place when move is empty
    public void test04(){
        Nemo robot = new Nemo(0,0);
        robot.move("");

        assertEquals(0, robot.coordinates.get(0).intValue());
        assertEquals(0, robot.coordinates.get(1).intValue());
    }

    @Test // moves down correctly
    public void test05(){
        Nemo robot = new Nemo(0,0);
        robot.move("d");

        assertEquals(0, robot.coordinates.get(0).intValue());
        assertEquals((-1), robot.coordinates.get(1).intValue());
    }


    @Test // moves up correctly
    public void test06(){
        Nemo robot = new Nemo(0,0);
        robot.move("u");

        assertEquals(0, robot.coordinates.get(0).intValue());
        assertEquals((1), robot.coordinates.get(1).intValue());
    }

    @Test // combination of u and d works correctly
    public void test07(){
        Nemo robot = new Nemo(0,0);
        robot.move("uuud");

        assertEquals(0, robot.coordinates.get(0).intValue());
        assertEquals((2), robot.coordinates.get(1).intValue());
    }

    @Test // rotates correctly -90 grades
    public void test08(){
        Nemo robot = new Nemo(0,0);
        robot.move("l");

        assertEquals("East", robot.bow);
    }

    @Test // rotates correctly +90 grades
    public void test09(){
        Nemo robot = new Nemo(0,0);
        robot.move("r");

        assertEquals("West", robot.bow);
    }

    @Test // Combination of l and r works correctly
    public void test10() {
        Nemo robot = new Nemo(0, 0);
        robot.move("lrl");

        assertEquals("East", robot.bow);
    }

    @Test // combination of u , d, l and r works correctly
    public void test11() {
        Nemo robot = new Nemo(0, 0);
        robot.move("urrudulu");

        assertEquals(0, robot.coordinates.get(0).intValue());
        assertEquals((3), robot.coordinates.get(1).intValue());
        assertEquals("West", robot.bow);
    }

    @Test // forward changes y value
    public void test12() {
        Nemo robot = new Nemo(0, 0);
        robot.move("f");

        assertEquals(1, robot.coordinates.get(1).intValue());
    }

    @Test // forward does not change x value
    public void test13() {
        Nemo robot = new Nemo(0, 0);
        robot.move("f");

        assertEquals(0, robot.coordinates.get(0).intValue());
    }

    @Test // combination of f, l and r works correctly
    public void test14() {
        Nemo robot = new Nemo(0, 0);
        robot.move("rflf");

        assertEquals(1, robot.coordinates.get(0).intValue());
        assertEquals(1, robot.coordinates.get(1).intValue());
    }

    @Test // m in surface does not destroys robot
    public void test15() {
        Nemo robot = new Nemo(0, 0);
        robot.move("m");

        assertEquals(false, robot.isDestroyed);
    }

    @Test // m almost in surface does not destroys robot
    public void test16() {
        Nemo robot = new Nemo(0, 0);
        robot.move("dm");

        assertEquals(false, robot.isDestroyed);
    }

    @Test // m underwater destroys robot
    public void test17() {
        Nemo robot = new Nemo(0, 0);
        robot.move("ddm");

        assertEquals(true, robot.isDestroyed);
    }


}
