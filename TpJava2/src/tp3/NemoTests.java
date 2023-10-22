package tp3;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NemoTests {

    private Nemo robot;

    @Before
    public void init(){this.robot = new Nemo(0,0, new CardinalNorth());}

    @Test
    public void NemoCreatesInTheCorrectVector(){verificatesCoordinates(0,0,0); }
    @Test
    public void NemoCreatesHeadingOrientation(){verificatesBow("North");}
    @Test
    public void NemoRespectsSurface(){notUnderWater();}

    @Test
    public void NemoStaysInTheSamePlaceWhenMoveIsEmpty(){
        robot.move("");
        verificatesCoordinates(0,0,0);
    }
    @Test
    public void NemoMovesDownCorrectly(){
        robot.move("d");
        verificatesCoordinates(0,0,(-1));

    }
    @Test
    public void NemoMovesUpCorrectly(){
        robot.move("du");
        verificatesCoordinates(0,0,0);
    }

    @Test
    public void NemoRespectsTheMaximumHeight(){
        robot.move("uuu");
        verificatesCoordinates(0,0,0);
    }

    @Test
    public void NemoRespectsFirstLevelOfDepth(){
        robot.move("d");
        notUnderWater();;
    }

    @Test
    public void NemoDoesNotRotateWhenCommandIsEmpty(){
        robot.move("");
        verificatesBow("North");
    }

    @Test
    public void NemoRotates90GradesLeft(){
        robot.move("l");
        verificatesBow("West");
    }

    @Test
    public void NemoRotates90GradesRight(){
        robot.move("r");
        verificatesBow("East");
    }

    @Test
    public void NemoRotates180GradesLeft(){
        robot.move("ll");
        verificatesBow("South");
    }

    @Test
    public void NemoRotates180GradesRight(){
        robot.move("rr");
        verificatesBow("South");
    }

    @Test
    public void NemoRotates360GradesCorrectly() {
        robot.move("rrrr");
        verificatesBow("North");

        robot.move("llll");
        verificatesBow("North");
    }

    @Test
    public void ForwardHeadingNorthIncreaseYValue() {
        robot.move("f");
        verificatesCoordinates(0,1,0);
    }

    @Test
    public void ForwardHeadingEastDecreaseXValue() {
        robot.move("lf");
        verificatesCoordinates((-1),0,0);
    }

    @Test
    public void ForwardHeadingSouthDecreaseYValue() {
        robot.move("llf");
        verificatesCoordinates(0,(-1),0);
    }

    @Test
    public void ForwardHeadingWestIncreaseXValue() {
        robot.move("rf");
        verificatesCoordinates(1,0,0);
    }

    @Test
    public void CombinationOfCommandsWorkCorrectlty(){
        robot.move("ddfrffllfu");
        verificatesCoordinates(1,1,(-1));
    }

    @Test
    public void NemoDoesNotDestroysInSurface() {assertDoesNotThrow(() -> robot.move("m"));}

    @Test
    public void NemoDoesNotDestroysWhenDivesAndResurfaces() {
        robot.move("ddu");

        assertDoesNotThrow(() -> robot.move("m"));
    }
    @Test
    public void NemoDestroysCorrectly() {
        robot.move("dd");

        assertThrowsLike(() -> robot.move("m"), "tp3.Nemo has been destroyed!");
    }

    @Test
    public void NemoDivesUpDivesDestroysCorrectly() {
        robot.move("ddud");

        assertThrowsLike(() -> robot.move("m"), "tp3.Nemo has been destroyed!");
    }

    private void assertThrowsLike(Executable executable, String message ) {
        Assertions.assertEquals( message,  assertThrows(Error.class, executable).getMessage() );
    }

    private void verificatesCoordinates(int x, int y , int z){
        assertEquals(x, robot.getXPos());
        assertEquals(y, robot.getYPos());
        assertEquals(z, robot.getZPos());
    }

    private void verificatesBow(String bow){ assertEquals(bow, robot.movementManager.bow()); }
    private void notUnderWater() {assertFalse(robot.movementManager.isUnderWater());}

}
