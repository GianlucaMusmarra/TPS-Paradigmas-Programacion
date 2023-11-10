import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class MatchLine {


    List<List<Character>> grid = new ArrayList<>();
    private final int indexMaxHeight;
    private final int indexMaxBase;

    public LinkedList<Turn> turn = new LinkedList<>();
    public char red = 'x';
    public char blue = 'o';

    public char gameMode;
    public LinkedList<GameMode> allGameModes = new LinkedList<>();

    public LinkedList<FinalResult> finalResult = new LinkedList<>();
    public String getFinalResult(){return finalResult.get(0).toString();}

    public MatchLine(int base, int height, char inputMode) {
        turn.add(new TurnRed());
        turn.add(new TurnBlue());

        if (base < 1 || height < 1) {
            throw new RuntimeException("Invalid setup.");
        }

        allGameModes.add(new GameModeC());
        allGameModes.add(new GameModeB());
        allGameModes.add(new GameModeA());
        if (allGameModes.stream().noneMatch(gameMode->gameMode.isGameMode(inputMode))) {
            throw new RuntimeException("Invalid setup.");
        }
        gameMode = inputMode;

        finalResult.add(new FinalResultNone());

        IntStream.range(0, base).forEach(x -> grid.add(new ArrayList<>()));

        this.indexMaxHeight = height - 1;
        this.indexMaxBase = base - 1;
    }


    public String show() {

        StringBuilder impression =new StringBuilder();
        impression.append("\n" + "Turno: ").append(turn.get(0).toString()).append("\n");

        IntStream.range(0, indexMaxHeight +1)
                .mapToObj(i -> indexMaxHeight - i)
                .forEach(i-> {
            IntStream.range(0, indexMaxHeight +1).forEach(x ->{
                impression.append("|").append(fichaEnCasilla(x, i)).append("| ");
            });

            impression.append("\n");
        });


        IntStream.range(0, indexMaxHeight +1).forEach(e ->
                impression.append("|^| ")
        );
        impression.append("\n");

        impression.append(finalResult.get(0).printFinalResult());

        return impression.toString();
    }

    public boolean finished() {
        return !(finalResult.get(0) instanceof FinalResultNone);
    }

    public void playRedAt(int columna) {
        turn.get(0).PlayRed();
        introduceMovement(columna, red);
        turn.addFirst(turn.removeLast());
    }

    public void playBlueAt(int columna) {
        turn.get(0).PlayBlue();
        introduceMovement(columna, blue);
        turn.addFirst(turn.removeLast());
    }

    public String getCurrentTurn(){
        return turn.get(0).toString();
    }

    public void introduceMovement(int column, char token) {


        int realIndex = column - 1;

        if(realIndex > indexMaxBase || realIndex <= (-1) || grid.get(realIndex).size() == indexMaxHeight + 1){
            throw new RuntimeException("Out of bounds!");
        }

        grid.get(realIndex).add(token);

        verificatesResult(realIndex);
    }

    public void verificatesResult(int indexColumn) {

        finalResult.addFirst(new FinalResultTie());
        IntStream.range(0, indexMaxBase + 1).filter(i -> grid.get(i).size() != indexMaxHeight + 1)
                .forEach(e -> finalResult.addFirst(new FinalResultNone()));

        if (gameMode == 'A' || gameMode == 'C') {
            int[] verticalBlueCounter = {0};
            int[] verticalRedCounterV = {0};

            grid.get(indexColumn).forEach(actualV -> {
                if (actualV == red) {
                    verticalRedCounterV[0]++;
                    verticalBlueCounter[0] = 0;
                } else if (actualV == blue) {
                    verticalBlueCounter[0]++;
                    verticalRedCounterV[0] = 0;
                } else {
                    verticalBlueCounter[0] = 0;
                    verticalRedCounterV[0] = 0;
                }
            });

            if (verticalRedCounterV[0] == 4) {
                finalResult.addFirst(new FinalResultRed());
            }
            if (verticalBlueCounter[0] == 4) {
                finalResult.addFirst(new FinalResultBlue());
            }

            int horizontalHeight = grid.get(indexColumn).size() - 1;
            List<Character> levelLayer = new ArrayList<>();

            IntStream.range(0, indexMaxBase + 1).forEach(x -> levelLayer.add(fichaEnCasilla(x, horizontalHeight)));

            checkWin(levelLayer);


        }
        if (gameMode == 'B' || gameMode == 'C') {


            int actualHeightPositive = grid.get(indexColumn).size() - 1;
            int actualBasePositive = indexColumn;




            int minimum = Math.min(actualBasePositive, actualHeightPositive);

            int minimumBase = actualBasePositive - minimum;
            int minimumHeight = actualHeightPositive - minimum;

            actualBasePositive = minimumBase;
            actualHeightPositive = minimumHeight;


            List<Character> posistivelevelLayer = new ArrayList<>();

            while (actualBasePositive <= indexMaxBase && actualHeightPositive <= indexMaxHeight) {
                posistivelevelLayer.add(fichaEnCasilla(actualBasePositive, actualHeightPositive));

                actualBasePositive++;
                actualHeightPositive++;

            }

            checkWin(posistivelevelLayer);


            int actualHeightNegative = grid.get(indexColumn).size() - 1;
            int minimumBaseNegative = indexColumn;

            int minimumHeightNegative = actualHeightNegative;

            while (minimumHeightNegative > 0 && minimumBaseNegative < indexMaxHeight){
                minimumBaseNegative ++;
                minimumHeightNegative--;
            }

            List<Character> capaDeNivelD = new ArrayList<>();

            while (minimumBaseNegative >= 0 && minimumHeightNegative <= indexMaxHeight) {
                capaDeNivelD.add(fichaEnCasilla(minimumBaseNegative, minimumHeightNegative));

                minimumBaseNegative--;
                minimumHeightNegative++;

            }

            checkWin(capaDeNivelD);
        }
    }

        public char fichaEnCasilla(int column, int realHeight){
            if (realHeight > grid.get(column).size() - 1) {
                return ' ';
            } else {
                return grid.get(column).get(realHeight);
            }
        }

        public void checkWin(List<Character> levelLayer){
            char actual;
            int redCounter = 0;
            int blueCounter = 0;


            for (Character character : levelLayer) {
                actual = character;

                if (actual == red) {
                    redCounter += 1;
                    blueCounter = 0;
                } else if (actual == blue) {
                    blueCounter += 1;
                    redCounter = 0;
                } else {
                    blueCounter = 0;
                    redCounter = 0;
                }

                if (redCounter == 4) {
                    finalResult.addFirst(new FinalResultRed());
                }
                if (blueCounter == 4) {
                    finalResult.addFirst(new FinalResultBlue());
                }

            }
        }
    }


