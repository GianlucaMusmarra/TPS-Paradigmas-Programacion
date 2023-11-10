import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {

    List<List<Character>> grilla = new ArrayList<>();

    public LinkedList<ListBounds> maxHeightBounds = new LinkedList<>(List.of(new ListBoundOut()));
    public LinkedList<ListBounds> maxBaseBounds = new LinkedList<>(List.of(new ListBoundOut()));

    public LinkedList<Turn> turno = new LinkedList<>(Arrays.asList(new TurnRed(),new TurnBlue()));

    public char red = 'x';
    public char blue = 'o';

    public char gameMode;
    public LinkedList<GameMode> allGameModes = new LinkedList<>();

    public LinkedList<FinalResult> finalResult = new LinkedList<>();
    public String getFinalResult(){return finalResult.get(0).toString();}

    public Linea(int base, int altura, char c) {

        IntStream.range(0,altura).forEach(i -> maxHeightBounds.addFirst(new ListBoundIn()));
        IntStream.range(0,altura).forEach(i -> maxBaseBounds.addFirst(new ListBoundIn()));

        maxHeightBounds.get(0).checkBoundTooSmall();
        maxBaseBounds.get(0).checkBoundTooSmall();

        allGameModes.add(new GameModeA());
        allGameModes.add(new GameModeB());
        allGameModes.add(new GameModeC());

        allGameModes = allGameModes.stream()
                .filter(gm -> gm.isGameMode(c))
                .collect(Collectors.toCollection(LinkedList::new));

        allGameModes.addLast(new GameModeNone());
        allGameModes.getFirst().isGameMode(c);

        gameMode = c;

        finalResult.add(new FinalResultNone());

        IntStream.range(0, base).forEach(x -> grilla.add(new ArrayList<>()));
    }


    public String show() {

        StringBuilder impression =new StringBuilder();
        impression.append("\n" + "Turno: ").append(turno.get(0).toString()).append("\n");

        IntStream.range(0,maxHeightBounds.size()-1)
                .mapToObj(i -> maxHeightBounds.size()-2 - i)
                .forEach(i-> {
            IntStream.range(0,maxHeightBounds.size()-1).forEach(x ->{
                impression.append("|").append(fichaEnCasilla(x, i)).append("| ");
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
        turno.get(0).PlayRed();
        introduceMovement(column, red);
        turno.addFirst(turno.removeLast());
    }

    public void playBlueAt(int column) {
        turno.get(0).PlayBlue();
        introduceMovement(column, blue);
        turno.addFirst(turno.removeLast());
    }

    public String getCurrentTurn(){
        return turno.get(0).toString();
    }

    public void introduceMovement(int column, char ficha) {

        int realIndex = column - 1;

        int index = (realIndex % maxBaseBounds.size() + maxBaseBounds.size()) % maxBaseBounds.size();
        maxBaseBounds.get(index).checkBound();

        int index2 = (grilla.get(realIndex).size() % maxHeightBounds.size() + maxHeightBounds.size()) % maxHeightBounds.size();
        maxHeightBounds.get(index2).checkBound();

        grilla.get(realIndex).add(ficha);

        checkWin(realIndex);
    }

    public void checkWin(int columnIndex) {

        finalResult.addFirst(new FinalResultTie());
        IntStream.range(0, maxBaseBounds.size()-1).filter(i -> grilla.get(i).size() != maxHeightBounds.size()-1)
                .forEach(e -> finalResult.addFirst(new FinalResultNone()));

        allGameModes.get(0).checkModeWins(this, columnIndex);
    }

    public void checkAModeWin(int columnIndex){
        checkWinInList(grilla.get(columnIndex));

        int alturaHorizontal = grilla.get(columnIndex).size() - 1;
        List<Character> capaDeNivel = new ArrayList<>();

        IntStream.range(0, maxBaseBounds.size()-1).forEach(x -> capaDeNivel.add(fichaEnCasilla(x, alturaHorizontal)));

        checkWinInList(capaDeNivel);
    }

    public void checkBModeWin(int columnIndex){
        int alturaActual = grilla.get(columnIndex).size() - 1;
        int baseActual = columnIndex;

        int minimo = Math.min(baseActual, alturaActual);

        int baseMinima = baseActual - minimo;
        int alturaMinima = alturaActual - minimo;


        int[] actualIndexes = {baseMinima, alturaMinima};

        List<Character> capaDeNivelC = new ArrayList<>();

        IntStream.range(0, Math.min(maxBaseBounds.size() - actualIndexes[0]-1, maxHeightBounds.size() - actualIndexes[1]))
                .forEach(i -> capaDeNivelC.add(fichaEnCasilla(actualIndexes[0] + i, actualIndexes[1] + i)));

        checkWinInList(capaDeNivelC);

        int heightActualD = grilla.get(columnIndex).size() - 1;


        int[] actualIndexes2 = {columnIndex, heightActualD};

        IntStream.iterate(0, i -> actualIndexes2[1] > 0 && actualIndexes2[0] < maxHeightBounds.size() - 2, i -> i + 1)
                .forEach(i -> {
                    actualIndexes2[0]++;
                    actualIndexes2[1]--;
                });

        List<Character> capaDeNivelD = new ArrayList<>();

        IntStream.iterate(actualIndexes2[0], i -> i >= 0 && actualIndexes2[1] < maxHeightBounds.size(), i -> i - 1)
                .forEach(i -> {
                    capaDeNivelD.add(fichaEnCasilla(i, actualIndexes2[1]));

                    actualIndexes2[1]++;
                });

        checkWinInList(capaDeNivelD);
    }

    public char fichaEnCasilla(int column, int alturaReal){
        return IntStream.range(0, 1)
                .filter(i -> alturaReal < grilla.get(column).size())
                .mapToObj(i -> grilla.get(column).get(alturaReal))
                .findFirst()
                .orElse(' ');
    }

    public void checkWinInList(List<Character> capaDeNivel){
        Map<Character, Integer> counters = new HashMap<>();
        counters.put(red, 0);
        counters.put(blue, 0);

        capaDeNivel.stream().forEach(character -> {
            counters.compute(character, (key, count) -> (count != null) ? count + 1 : 0);
            counters.forEach((color, count) -> {
                counters.entrySet().stream()
                        .filter(entry -> entry.getValue() == 4)
                        .forEach(entry -> finalResult.addFirst(entry.getKey() == red ? new FinalResultRed() : new FinalResultBlue()));
            });
        });
    }
}