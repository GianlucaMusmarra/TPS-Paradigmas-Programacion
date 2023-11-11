import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {

    List<List<Character>> grid = new ArrayList<>();

    public LinkedList<ListBounds> maxHeightBounds = new LinkedList<>(List.of(new ListBoundOut()));
    public LinkedList<ListBounds> maxBaseBounds = new LinkedList<>(List.of(new ListBoundOut()));

    public LinkedList<Turn> actualTurn = new LinkedList<>(Arrays.asList(new TurnRed(),new TurnBlue()));

    public char red = 'x';
    public char blue = 'o';

    public char gameMode;
    public LinkedList<GameMode> allGameModes = new LinkedList<>();

    public LinkedList<FinalResult> finalResult = new LinkedList<>();
    public String getFinalResult(){return finalResult.get(0).toString();}

    public Linea(int base, int height, char userInputGM) {

        IntStream.range(0,height).forEach(i -> maxHeightBounds.addFirst(new ListBoundIn()));
        IntStream.range(0,height).forEach(i -> maxBaseBounds.addFirst(new ListBoundIn()));

        maxHeightBounds.get(0).checkBoundTooSmall();
        maxBaseBounds.get(0).checkBoundTooSmall();

        allGameModes.add(new GameModeA());
        allGameModes.add(new GameModeB());
        allGameModes.add(new GameModeC());

        allGameModes = allGameModes.stream()
                .filter(gm -> gm.isGameMode(userInputGM))
                .collect(Collectors.toCollection(LinkedList::new));

        allGameModes.addLast(new GameModeNone());
        allGameModes.getFirst().isGameMode(userInputGM);

        gameMode = userInputGM;

        finalResult.add(new FinalResultNone());

        IntStream.range(0, base).forEach(x -> grid.add(new ArrayList<>()));
    }


    public String show() {

        StringBuilder impression =new StringBuilder();
        impression.append("\n" + "Turn: ").append(actualTurn.get(0).toString()).append("\n");

        IntStream.range(0,maxHeightBounds.size()-1)
                .mapToObj(i -> maxHeightBounds.size()-2 - i)
                .forEach(i-> {
            IntStream.range(0,maxHeightBounds.size()-1).forEach(x ->{
                impression.append("|").append(getToken(x, i)).append("| ");
            });

            impression.append("\n");
        });


        IntStream.range(0,maxHeightBounds.size()-1).forEach(e ->
                impression.append("|^| ")
        );
        impression.append("\n");

        impression.append(finalResult.get(0).printFinalResult());

        return impression.toString();
    }

    public boolean finished() {
        return !(finalResult.get(0) instanceof FinalResultNone);
    }

    public void playRedAt(int column) {
        actualTurn.get(0).PlayRed();
        introduceMovement(column, red);
        actualTurn.addFirst(actualTurn.removeLast());
    }

    public void playBlueAt(int column) {
        actualTurn.get(0).PlayBlue();
        introduceMovement(column, blue);
        actualTurn.addFirst(actualTurn.removeLast());
    }

    public String getCurrentTurn(){
        return actualTurn.get(0).toString();
    }

    public void introduceMovement(int column, char token) {

        int realIndex = column - 1;

        int index = (realIndex % maxBaseBounds.size() + maxBaseBounds.size()) % maxBaseBounds.size();
        maxBaseBounds.get(index).checkBound();

        int index2 = (grid.get(realIndex).size() % maxHeightBounds.size() + maxHeightBounds.size()) % maxHeightBounds.size();
        maxHeightBounds.get(index2).checkBound();

        grid.get(realIndex).add(token);

        checkWin(realIndex);
    }

    public void checkWin(int columnIndex) {

        finalResult.addFirst(new FinalResultTie());

        IntStream.range(0, maxBaseBounds.size()-1).filter(i -> grid.get(i).size() != maxHeightBounds.size()-1)
                .forEach(e -> finalResult.addFirst(new FinalResultNone()));

        allGameModes.get(0).checkModeWins(this, columnIndex);
    }

    public void checkAModeWin(int columnIndex){
        checkWinInList(grid.get(columnIndex));

        int verticalHeight = grid.get(columnIndex).size() - 1;
        List<Character> levelLayer = new ArrayList<>();

        IntStream.range(0, maxBaseBounds.size()-1).forEach(x -> levelLayer.add(getToken(x, verticalHeight)));

        checkWinInList(levelLayer);
    }

    public void checkBModeWin(int columnIndex){

        int actualColumHeight = grid.get(columnIndex).size() - 1;
        int actualWorkBase = columnIndex;

        int mathMin = Math.min(actualWorkBase, actualColumHeight);

        int minimumBaseCoordDiagonal = actualWorkBase - mathMin ;
        int maxHeightDiagonal = actualColumHeight - mathMin ;


        int[] actualIndexes = {minimumBaseCoordDiagonal, maxHeightDiagonal};

        List<Character> capaDeNivelC = new ArrayList<>();

        IntStream.range(0, Math.min(maxBaseBounds.size() - actualIndexes[0]-1, maxHeightBounds.size() - actualIndexes[1]))
                .forEach(i -> capaDeNivelC.add(getToken(actualIndexes[0] + i, actualIndexes[1] + i)));

        checkWinInList(capaDeNivelC);

        int actualHeightDiagonal = grid.get(columnIndex).size() - 1;


        int[] actualIndexes2 = {columnIndex, actualHeightDiagonal};

        IntStream.iterate(0, i -> actualIndexes2[1] > 0 && actualIndexes2[0] < maxHeightBounds.size() - 2, i -> i + 1)
                .forEach(i -> {
                    actualIndexes2[0]++;
                    actualIndexes2[1]--;
                });

        List<Character> levelLayerDiagonal = new ArrayList<>();

        IntStream.iterate(actualIndexes2[0], i -> i >= 0 && actualIndexes2[1] < maxHeightBounds.size(), i -> i - 1)
                .forEach(i -> {
                    levelLayerDiagonal.add(getToken(i, actualIndexes2[1]));

                    actualIndexes2[1]++;
                });

        checkWinInList(levelLayerDiagonal);
    }

    public char getToken(int column,int indexHeight){
        return IntStream.range(0, 1)
                .filter(i -> indexHeight < grid.get(column).size())
                .mapToObj(i -> grid.get(column).get(indexHeight))
                .findFirst()
                .orElse(' ');
    }

    public void checkWinInList(List<Character> levelLayer){
        char[] actualC = {'p'};
        int[] redCounter = {0};
        int[] blueConter = {0};

        levelLayer.forEach(character->{
            actualC[0] = character;
            if (actualC[0] == red) {
                redCounter[0]++;
                blueConter[0] = 0;
            } else if (actualC[0] == blue) {
                blueConter[0]++;
                redCounter[0] = 0;
            } else {
                blueConter[0] = 0;
                redCounter[0] = 0;
            }
            if (redCounter[0] == 4) {
                finalResult.addFirst(new FinalResultRed());
            }
            if (blueConter[0] == 4) {
                finalResult.addFirst(new FinalResultBlue());
            }
        });
    }
}