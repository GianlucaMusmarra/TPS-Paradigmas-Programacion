import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

public class LineaTests {

    @Test
    public void gameNotFinish(){
        Linea match = creates5x5Linea('A');
        assertFalse(match.finished());
    }
    @Test
    public void invalidArguments(){
        assertThrowsLike(() ->new Linea(0,0,'A'), "Invalid setup! Too small!" );
    }
    @Test
    public void invalidGameMode(){assertThrowsLike(() ->creates4x4Linea('X'), "Invalid setup." );}

    @Test
    public void redAlwaysStart(){
        Linea match = creates4x4Linea('A');
        verificatesActualTurn("red", match);
    }

    @Test
    public void moveChangesTurn(){
        Linea match = creates4x4Linea('A');

        match.playRedAt(1);
        verificatesActualTurn("blue", match);

        match.playBlueAt(1);
        verificatesActualTurn("red", match);

    }

    @Test
    public void gridCreatesWithCorrectBase(){
        Linea match = creates4x4Linea('A');

        assertEquals(4, match.grid.size());
    }

    @Test
    public void blueCantMoveIfNotHisTurn(){
        Linea match = creates4x4Linea('A');

        assertThrowsLike( () ->match.playBlueAt(1), "Wrong turn!");
    }


    @Test
    public void redCantMoveIfNotHisTurn(){
        Linea match = creates4x4Linea('A');
        match.playRedAt(1);

        assertThrowsLike( () ->match.playRedAt(1), "Wrong turn!");
    }


    @Test
    public void movementIncreasesHeight(){
        Linea match = creates4x4Linea('A');

        match.playRedAt(1);

        assertEquals(1 , match.grid.get(0).size());
        assertEquals(0 , match.grid.get(1).size());
        assertEquals(0 , match.grid.get(2).size());
        assertEquals(0 , match.grid.get(3).size());
    }

    @Test
    public void twoMovementsIncreasesTwoUnities(){
        Linea match = creates4x4Linea('A');

        match.playRedAt(1);
        match.playBlueAt(1);


        assertEquals(2 , match.grid.get(0).size());
        assertEquals(0 , match.grid.get(1).size());
        assertEquals(0 , match.grid.get(2).size());
        assertEquals(0 , match.grid.get(3).size());
    }

    @Test
    public void exceedsVerticalLimit(){
        Linea match = creates4x4Linea('A');

        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(1);
        match.playBlueAt(1);

        assertThrowsLike(()->match.playRedAt(1), "Out of bounds!");

    }
    @Test
    public void exceedsHorizontalLimit(){
        Linea match = creates4x4Linea('A');

        assertThrowsLike(()->match.playRedAt(5), "Out of bounds!");
    }

    @Test
    public void exceedsHorizontalLimitNegative(){
        Linea MatchLine = creates4x4Linea('A');;

        assertThrowsLike(()->MatchLine.playRedAt(-5), "Out of bounds!");

    }

    @Test
    public void redWinsVerticallyInModeA(){
        Linea match = creates4x4Linea('A');

        redCompletes4Vertical(match);

        verificatesFinalResults("red", match);
    }
    @Test
    public void blueWinsVerticallyInModeA(){
        Linea match = new Linea(4,4,'A');

        blueCompletes4Vertical(match);

        verificatesFinalResults("blue", match);
    }
    @Test
    public void redWinsVerticallyInModeC(){
        Linea match = creates4x4Linea('C');

        redCompletes4Vertical(match);

        verificatesFinalResults("red", match);
    }
    @Test
    public void blueWinsVerticallyInModeC(){
        Linea match = creates4x4Linea('C');

        blueCompletes4Vertical(match);

        verificatesFinalResults("blue", match);
    }

    @Test
    public void redWinsHorizontallyInModeA(){
        Linea match = creates4x4Linea('A');

        redCompletes4Horizontal(match);

        verificatesFinalResults("red", match);
    }

    @Test
    public void blueWinsHorizontallyInModeA(){
        Linea match = creates4x4Linea('A');

        blueCompletes4Horizontal(match);

        verificatesFinalResults("blue", match);
    }
    @Test
    public void redWinsHorizontallyInModeC(){
        Linea match = creates4x4Linea('C');

        redCompletes4Horizontal(match);

        verificatesFinalResults("red", match);
    }
    @Test
    public void blueWinsHorizontallyInModeC(){
        Linea match = creates4x4Linea('C');

        blueCompletes4Horizontal(match);

        verificatesFinalResults("blue", match);
    }

    @Test
    public void redCompletesPositiveDiagonalInModeB(){
        Linea match = creates4x4Linea('B');

        redCompletes4PositiveDiagonal(match);

        verificatesFinalResults("red", match);
    }

    @Test
    public void redCompletesNegativeDiagonalInModeB(){
        Linea match = creates4x4Linea('B');

        redCompletes4NegativeDiagonal(match);

        verificatesFinalResults("red", match);

    }
    @Test
    public void redCompletesPositiveDiagonalInModeC(){
        Linea match = creates4x4Linea('C');

        redCompletes4PositiveDiagonal(match);

        verificatesFinalResults("red", match);
    }
    @Test
    public void redCompletesNegativeDiagonalInModeC(){
        Linea match = creates4x4Linea('C');

        redCompletes4NegativeDiagonal(match);

        verificatesFinalResults("red", match);

    }

    @Test
    public void blueCompletesPositiveDiagonalInModeB(){
        Linea match = creates5x5Linea('B');

        blueCompletes4PositiveDiagonal(match);

        verificatesFinalResults("blue", match);
    }

    @Test
    public void blueCompletesNegativeDiagonalInModeB(){
        Linea match = creates5x5Linea('B');

        blueCompletes4NegativeDiagonal(match);

        verificatesFinalResults("blue", match);
    }

    @Test
    public void blueCompletesPositiveDiagonalInModeC(){
        Linea match = creates5x5Linea('C');

        blueCompletes4PositiveDiagonal(match);

        verificatesFinalResults("blue", match);
    }

    @Test
    public void blueCompletesNegativeDiagonalInModeC(){
        Linea match = creates5x5Linea('C');

        blueCompletes4NegativeDiagonal(match);

        verificatesFinalResults("blue", match);
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
        Linea match = creates3x3Linea('C');

        assertEquals("\n" + "Turn: "+ "red" + "\n"
                        + "| | | | | | " + "\n"
                        + "| | | | | | " + "\n"
                        + "| | | | | | " + "\n"
                        + "|^| |^| |^| " + "\n"

                , match.show());
    }

    @Test
    public void movementPrintsCorrectly(){
        Linea match = creates3x3Linea('C');

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
        Linea match = creates3x3Linea('C');

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
        Linea match = creates4x4Linea('C');

        redCompletes4Vertical(match);

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
        Linea match = creates4x4Linea('A');

        blueCompletes4Vertical(match);

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

    public Linea creates3x3Linea(char gameMode){
        return new Linea(3,3,gameMode);
    }
    public Linea creates4x4Linea(char gameMode){
       return new Linea(4,4,gameMode);
    }
    public Linea creates5x5Linea(char gameMode){
        return new Linea(5,5,gameMode);
    }

    public void redCompletes4Vertical(Linea match){
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
    }
    public void blueCompletes4Vertical(Linea match){
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(1);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(2);
    }
    public void redCompletes4Horizontal(Linea match){
        match.playRedAt(1);
        match.playBlueAt(1);
        match.playRedAt(2);
        match.playBlueAt(2);
        match.playRedAt(3);
        match.playBlueAt(3);
        match.playRedAt(4);
    }
    public void blueCompletes4Horizontal(Linea match){
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
    }
    public void redCompletes4PositiveDiagonal(Linea match){
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
    }
    public void blueCompletes4PositiveDiagonal(Linea match){
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
    }
    public void redCompletes4NegativeDiagonal(Linea match){
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
    }
    public void blueCompletes4NegativeDiagonal(Linea match){
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
    }
    public void verificatesFinalResults(String expectedResult, Linea match){
        assertEquals(expectedResult, match.getFinalResult());
        assertTrue(match.finished());
    }

    public void verificatesActualTurn(String expectedResult, Linea match){
        assertEquals(expectedResult, match.getCurrentTurn());
    }


    }




