import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

public class LineaTests {

    @Test
    public void gameNotFinish(){
        MatchLine match = new MatchLine(5,5,'A');

        assertFalse(match.finished());
    }
    @Test
    public void invalidArguments(){
        assertThrowsLike(() ->new MatchLine(0,0,'A'), "Invalid setup! Too small!" );
    }
    @Test
    public void invalidGameMode(){

        assertThrowsLike(() ->new MatchLine(4,4,'X'), "Invalid setup." );
    }

    @Test
    public void redAlwaysStart(){
        MatchLine linea = new MatchLine(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertEquals("red", linea.getCurrentTurn());
    }
    @Test
    public void moveChangesTurn(){
        MatchLine match = new MatchLine(4,4,'A');
        match.playRedAt(1);
        assertEquals("blue", match.getCurrentTurn());

        match.playBlueAt(1);
        assertEquals("red", match.getCurrentTurn());

    }

    @Test
    public void gridCreatesWithCorrectBase(){
        MatchLine match = new MatchLine(4,4,'A');

        assertEquals(4, match.grid.size());
    }

    @Test
    public void blueCantMoveIfNotHisTurn(){
        MatchLine match = new MatchLine(4,4,'A');

        assertThrowsLike( () ->match.playBlueAt(1), "Wrong turn!");
    }


    @Test
    public void redCantMoveIfNotHisTurn(){
        MatchLine match = new MatchLine(4,4,'A');
        match.playRedAt(1);

        assertThrowsLike( () ->match.playRedAt(1), "Wrong turn!");
    }


    @Test
    public void movementIncreasesHeight(){
        MatchLine match = new MatchLine(4,4,'A');

        match.playRedAt(1);

        assertEquals(1 , match.grid.get(0).size());
        assertEquals(0 , match.grid.get(1).size());
        assertEquals(0 , match.grid.get(2).size());
        assertEquals(0 , match.grid.get(3).size());
    }

    @Test
    public void twoMovementsIncreasesTwoUnities(){
        MatchLine match = new MatchLine(4,4,'A');

        match.playRedAt(1);
        match.playBlueAt(1);


        assertEquals(2 , match.grid.get(0).size());
        assertEquals(0 , match.grid.get(1).size());
        assertEquals(0 , match.grid.get(2).size());
        assertEquals(0 , match.grid.get(3).size());
    }

    @Test
    public void exceedsVerticalLimit(){
        MatchLine match = new MatchLine(4,4,'A');

        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(1);
        match.playBlueAt(1);

        assertThrowsLike(()->match.playRedAt(1), "Out of bounds!");

    }
    @Test
    public void exceedsHorizontalLimit(){
        MatchLine match = new MatchLine(4,4,'A');

        assertThrowsLike(()->match.playRedAt(5), "Out of bounds!");
    }



    @Test
    public void exceedsHorizontalLimitNegative(){
        MatchLine linea = new MatchLine(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertThrowsLike(()->linea.playRedAt(-5), "Out of bounds!");

    }

    @Test
    public void redWinsVerticallyInModeA(){
        MatchLine match = new MatchLine(4,4,'A');

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
        MatchLine match = new MatchLine(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);

        assertEquals("blue", match.getFinalResult());
        assertTrue(match.finished());
    }

    @Test
    public void redWinsHorizontallyInModeA(){
        MatchLine match = new MatchLine(4,4,'A');

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
        MatchLine match = new MatchLine(4,4,'A');


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
        MatchLine match = new MatchLine(4,4,'C');

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
        MatchLine match = new MatchLine(4,4,'C');

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
        MatchLine match = new MatchLine(4, 4, 'B');

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
        MatchLine match = new MatchLine(4, 4, 'B');

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
        MatchLine match = new MatchLine(4, 4, 'C');

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
        MatchLine match = new MatchLine(4, 4, 'C');

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

        MatchLine match = new MatchLine(5, 5, 'B');

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
        MatchLine match = new MatchLine(5, 5, 'B');

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

        MatchLine match = new MatchLine(5, 5, 'C');

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
        MatchLine match = new MatchLine(5, 5, 'C');

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

        MatchLine match = new MatchLine(3, 3, 'C');
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
        MatchLine match = new MatchLine(3, 3, 'C');
        assertEquals("\n" + "Turno: "+ "red" + "\n"
                        + "| | | | | | " + "\n"
                        + "| | | | | | " + "\n"
                        + "| | | | | | " + "\n"
                        + "|^| |^| |^| " + "\n"

                , match.show());
    }

    @Test
    public void movementPrintsCorrectly(){
        MatchLine match = new MatchLine(3, 3, 'C');
        match.playRedAt(1);


        assertEquals("""

                     Turno: blue
                     | | | | | |\s
                     | | | | | |\s
                     |x| | | | |\s
                     |^| |^| |^|\s
                     """

                , match.show());
    }

    @Test
    public void twoMovementsInSameColum(){
        MatchLine match = new MatchLine(3, 3, 'C');
        match.playRedAt(1);
        match.playBlueAt(1);


        assertEquals("\n" + "Turno: "+ "red" + "\n"
                        + "| | | | | | " + "\n"
                        + "|o| | | | | " + "\n"
                        + "|x| | | | | " + "\n"
                        + "|^| |^| |^| " + "\n"

                , match.show());
    }
    @Test
    public void matchResultPrintsCorrectly(){
        MatchLine match = new MatchLine(4, 4, 'C');
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);

        assertEquals("\n" + "Turno: "+ "blue" + "\n"
                        + "|x| | | | | | | " + "\n"
                        + "|x| |o| | | | | " + "\n"
                        + "|x| |o| | | | | " + "\n"
                        + "|x| |o| | | | | " + "\n"
                        + "|^| |^| |^| |^| " + "\n"
                        + "\n" + "Resultado final: " + "red"

                , match.show());

    }






    public void assertThrowsLike (Executable executable, String message ) {
        assertEquals (message,
                assertThrows( Exception.class, executable).getMessage() ); }
    }


