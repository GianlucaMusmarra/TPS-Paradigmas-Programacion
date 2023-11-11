import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

public class LineaTests {

    @Test
    public void gameNotFinish(){
        Linea match = new Linea(5,5,'A');

        assertFalse(match.finished());
    }
    @Test
    public void invalidArguments(){
        assertThrowsLike(() ->new Linea(0,0,'A'), "Invalid setup! Too small!" );
    }
    @Test
    public void invalidGameMode(){

        assertThrowsLike(() ->new Linea(4,4,'X'), "Invalid setup." );
    }

    @Test
    public void redAlwaysStart(){
        Linea MatchLine = new Linea(4,4,'A');

        assertEquals("red", MatchLine.getCurrentTurn());
    }

    @Test
    public void moveChangesTurn(){
        Linea match = new Linea(4,4,'A');
        match.playRedAt(1);
        assertEquals("blue", match.getCurrentTurn());

        match.playBlueAt(1);
        assertEquals("red", match.getCurrentTurn());

    }

    @Test
    public void gridCreatesWithCorrectBase(){
        Linea match = new Linea(4,4,'A');

        assertEquals(4, match.grid.size());
    }

    @Test
    public void blueCantMoveIfNotHisTurn(){
        Linea match = new Linea(4,4,'A');

        assertThrowsLike( () ->match.playBlueAt(1), "Wrong turn!");
    }


    @Test
    public void redCantMoveIfNotHisTurn(){
        Linea match = new Linea(4,4,'A');
        match.playRedAt(1);

        assertThrowsLike( () ->match.playRedAt(1), "Wrong turn!");
    }


    @Test
    public void movementIncreasesHeight(){
        Linea match = new Linea(4,4,'A');

        match.playRedAt(1);

        assertEquals(1 , match.grid.get(0).size());
        assertEquals(0 , match.grid.get(1).size());
        assertEquals(0 , match.grid.get(2).size());
        assertEquals(0 , match.grid.get(3).size());
    }

    @Test
    public void twoMovementsIncreasesTwoUnities(){
        Linea match = new Linea(4,4,'A');

        match.playRedAt(1);
        match.playBlueAt(1);


        assertEquals(2 , match.grid.get(0).size());
        assertEquals(0 , match.grid.get(1).size());
        assertEquals(0 , match.grid.get(2).size());
        assertEquals(0 , match.grid.get(3).size());
    }

    @Test
    public void exceedsVerticalLimit(){
        Linea match = new Linea(4,4,'A');

        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(1);
        match.playBlueAt(1);

        assertThrowsLike(()->match.playRedAt(1), "Out of bounds!");

    }
    @Test
    public void exceedsHorizontalLimit(){
        Linea match = new Linea(4,4,'A');

        assertThrowsLike(()->match.playRedAt(5), "Out of bounds!");
    }



    @Test
    public void exceedsHorizontalLimitNegative(){
        Linea MatchLine = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertThrowsLike(()->MatchLine.playRedAt(-5), "Out of bounds!");

    }

    @Test
    public void redWinsVerticallyInModeA(){
        Linea match = new Linea(4,4,'A');

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);

        assertEquals("red", match.getFinalResult());
        assertTrue(match.finished());
    }
    @Test
    public void blueWinsVerticallyInModeA(){
        Linea match = new Linea(4,4,'A');

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(2);

        assertEquals("blue", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void redWinsHorizontallyInModeA(){
        Linea match = new Linea(4,4,'A');

        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(2);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(3);
        match.playRedAt(4);

        assertEquals("red", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void blueWinsHorizontallyInModeA(){
        Linea match = new Linea(4,4,'A');


        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(2);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(3);
        match.playRedAt(1);
        match.playBlueAt(4);
        match.playRedAt(1);
        match.playBlueAt(4);

        assertEquals("blue", match.getFinalResult());
        assertTrue(match.finished());
    }
    @Test
    public void redWinsHorizontallyInModeC(){
        Linea match = new Linea(4,4,'C');

        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(2);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(3);
        match.playRedAt(4);

        assertEquals("red", match.getFinalResult());
        assertTrue(match.finished());
    }
    @Test
    public void blueWinsHorizontallyInModeC(){
        Linea match = new Linea(4,4,'C');

        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(2);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(3);
        match.playRedAt(1);
        match.playBlueAt(4);
        match.playRedAt(1);
        match.playBlueAt(4);

        assertEquals("blue", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void redCompletesPositiveDiagonalInModeB(){
        Linea match = new Linea(4, 4, 'B');

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(2);
        match.playBlueAt(3);
        match.playRedAt(3);
        match.playBlueAt(1);
        match.playRedAt(3);
        match.playBlueAt(4);
        match.playRedAt(4);
        match.playBlueAt(4);
        match.playRedAt(4);

        assertEquals("red", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void redCompletesNegativeDiagonalInModeB(){
        Linea match = new Linea(4, 4, 'B');

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(2);
        match.playBlueAt(3);
        match.playRedAt(3);
        match.playBlueAt(2);
        match.playRedAt(4);

        assertEquals("red", match.getFinalResult());
        assertTrue(match.finished());

    }
    @Test
    public void redCompletesPositiveDiagonalInModeC(){
        Linea match = new Linea(4, 4, 'C');

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(2);
        match.playBlueAt(3);
        match.playRedAt(3);
        match.playBlueAt(1);
        match.playRedAt(3);
        match.playBlueAt(4);
        match.playRedAt(4);
        match.playBlueAt(4);
        match.playRedAt(4);

        assertEquals("red", match.getFinalResult());
        assertTrue(match.finished());
    }
    @Test
    public void redCompletesNegativeDiagonalInModeC(){
        Linea match = new Linea(4, 4, 'C');

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(2);
        match.playBlueAt(3);
        match.playRedAt(3);
        match.playBlueAt(2);
        match.playRedAt(4);

        assertEquals("red", match.getFinalResult());
        assertTrue(match.finished());

    }

    @Test
    public void BlueCompletesPositiveDiagonalInModeB(){

        Linea match = new Linea(5, 5, 'B');

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(3);
        match.playRedAt(4);
        match.playBlueAt(4);
        match.playRedAt(1);
        match.playBlueAt(4);
        match.playRedAt(5);
        match.playBlueAt(5);
        match.playRedAt(5);
        match.playBlueAt(5);

        assertEquals("blue", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void blueCompletesNegativeDiagonalInModeB(){
        Linea match = new Linea(5, 5, 'B');

        match.playRedAt(2);
        match.playBlueAt(1);
        match.playRedAt(4);
        match.playBlueAt(5);
        match.playRedAt(3);
        match.playBlueAt(4);
        match.playRedAt(1);
        match.playBlueAt(3);
        match.playRedAt(1);
        match.playBlueAt(3);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(2);
        match.playBlueAt(2);

        assertEquals("blue", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void BlueCompletesPositiveDiagonalInModeC(){

        Linea match = new Linea(5, 5, 'C');

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(3);
        match.playRedAt(4);
        match.playBlueAt(4);
        match.playRedAt(1);
        match.playBlueAt(4);
        match.playRedAt(5);
        match.playBlueAt(5);
        match.playRedAt(5);
        match.playBlueAt(5);

        assertEquals("blue", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void blueCompletesNegativeDiagonalInModeC(){
        Linea match = new Linea(5, 5, 'C');

        match.playRedAt(2);
        match.playBlueAt(1);
        match.playRedAt(4);
        match.playBlueAt(5);
        match.playRedAt(3);
        match.playBlueAt(4);
        match.playRedAt(1);
        match.playBlueAt(3);
        match.playRedAt(1);
        match.playBlueAt(3);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(2);
        match.playBlueAt(2);

        assertEquals("blue", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void matchIsADraw(){

        Linea match = new Linea(3, 3, 'C');
        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(2);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(3);
        match.playRedAt(3);

        assertEquals("tie", match.getFinalResult());
        assertTrue(match.finished());
    }



    @Test
    public void printInitializesCorrectly()
    {
        Linea match = new Linea(3, 3, 'C');
        assertEquals("\n" + "Turn: "+ "red" + "\n"
                        + "| | | | | | " + "\n"
                        + "| | | | | | " + "\n"
                        + "| | | | | | " + "\n"
                        + "|^| |^| |^| " + "\n"

                , match.show());
    }

    @Test
    public void movementPrintsCorrectly(){
        Linea match = new Linea(3, 3, 'C');
        match.playRedAt(1);


        assertEquals("""

                     Turn: blue
                     | | | | | |\s
                     | | | | | |\s
                     |x| | | | |\s
                     |^| |^| |^|\s
                     """

                , match.show());
    }

    @Test
    public void twoMovementsInSameColum(){
        Linea match = new Linea(3, 3, 'C');
        match.playRedAt(1);
        match.playBlueAt(1);


        assertEquals("\n" + "Turn: "+ "red" + "\n"
                        + "| | | | | | " + "\n"
                        + "|o| | | | | " + "\n"
                        + "|x| | | | | " + "\n"
                        + "|^| |^| |^| " + "\n"

                , match.show());
    }
    @Test
    public void matchResultPrintsCorrectlyForRed(){
        Linea match = new Linea(4, 4, 'C');
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);

        assertEquals("\n" + "Turn: "+ "blue" + "\n"
                        + "|x| | | | | | | " + "\n"
                        + "|x| |o| | | | | " + "\n"
                        + "|x| |o| | | | | " + "\n"
                        + "|x| |o| | | | | " + "\n"
                        + "|^| |^| |^| |^| " + "\n"
                        + "\n" + "Final result: " + "red wins"
                , match.show());

    }
    @Test
    public void matchResultPrintsCorrectlyForBlue(){
        Linea match = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(2);

        assertEquals("\n" + "Turn: "+ "red" + "\n"
                        + "| | |o| | | | | " + "\n"
                        + "|x| |o| | | | | " + "\n"
                        + "|x| |o| | | | | " + "\n"
                        + "|x| |o| |x| | | " + "\n"
                        + "|^| |^| |^| |^| " + "\n"
                        + "\n" + "Final result: " + "blue wins"
                , match.show());
    }






    public void assertThrowsLike (Executable executable, String message ) {
        assertEquals (message,
                assertThrows( Exception.class, executable).getMessage() ); }
    }


