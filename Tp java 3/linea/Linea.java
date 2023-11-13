package linea;

import java.util.*;
import java.util.stream.IntStream;

public class Linea {

    List<List<Character>> grid = new ArrayList<>();

    public LinkedList<ListBounds> maxHeightBounds = new LinkedList<>(List.of(new ListBoundOut()));
    public LinkedList<ListBounds> maxBaseBounds = new LinkedList<>(List.of(new ListBoundOut()));

    public LinkedList<Turn> actualTurn = new LinkedList<>(Arrays.asList(new TurnRed(),new TurnBlue()));

    public char red = 'x';
    public char blue = 'o';

    public GameModeManager gameModeManager = new GameModeManager(this);

    public LinkedList<FinalResult> finalResult = new LinkedList<>();
    public String getFinalResult(){return finalResult.get(0).toString();}

    public Linea(int base, int height, char userInputGM) {

        IntStream.range(0,height).forEach(i -> maxHeightBounds.addFirst(new ListBoundIn()));
        IntStream.range(0,height).forEach(i -> maxBaseBounds.addFirst(new ListBoundIn()));

        maxHeightBounds.get(0).checkBoundTooSmall();
        maxBaseBounds.get(0).checkBoundTooSmall();

        gameModeManager.SetupGameMode(userInputGM);

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

        gameModeManager.checkWinsAt(columnIndex);
    }

    public char getToken(int base,int indexHeight){
        return IntStream.range(0, 1)
                .filter(i -> base < grid.size())
                .filter(i -> indexHeight < grid.get(base).size())
                .mapToObj(i -> grid.get(base).get(indexHeight))
                .findFirst()
                .orElse(' ');
    }

    public void checkWinInList(List<Character> levelLayer, int columnIndex){

        IntStream.range(0, 1).filter(n->
                IntStream.range(-3,4).filter(
                        row -> columnIndex + row < levelLayer.size() && columnIndex + row >= 0 && levelLayer.get(row + columnIndex)
                                .equals(blue)).count() >= 4).forEach(n ->finalResult.addFirst(new FinalResultBlue()));

        IntStream.range(0, 1).filter(n->
                IntStream.range(-3,4).filter(
                        row -> columnIndex + row < levelLayer.size() && columnIndex + row >= 0 && levelLayer.get(row + columnIndex)
                                .equals(red)).count() >= 4).forEach(n ->finalResult.addFirst(new FinalResultRed()));

    }
}