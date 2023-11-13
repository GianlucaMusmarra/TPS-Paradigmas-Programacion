package linea;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameModeManager {

    Linea line;

    public LinkedList<GameMode> allGameModes = new LinkedList<>();

    public GameModeManager(Linea line){
        this.line = line;

        allGameModes.add(new GameModeA());
        allGameModes.add(new GameModeB());
        allGameModes.add(new GameModeC());
    }

    public void SetupGameMode (char userInputGM){
        allGameModes = allGameModes.stream()
                .filter(gm -> gm.isGameMode(userInputGM))
                .collect(Collectors.toCollection(LinkedList::new));

        allGameModes.addLast(new GameModeNone());
        allGameModes.getFirst().isGameMode(userInputGM);
    }

    public void checkWinsAt(int baseIndex){
        allGameModes.get(0).checkModeWins(this, baseIndex);
    }

    public void checkAModeWin(int columnIndex){

        int verticalHeight = line.grid.get(columnIndex).size() - 1;

        line.checkWinInList(line.grid.get(columnIndex), verticalHeight);

        List<Character> levelLayer = new ArrayList<>();

        IntStream.range(0, line.maxBaseBounds.size()-1).forEach(x -> levelLayer.add(line.getToken(x, verticalHeight)));

        line.checkWinInList(levelLayer, verticalHeight);
    }

    public void checkBModeWin(int basedIndex){

        int actualColumHeight = line.grid.get(basedIndex).size() - 1;

        int mathMin = Math.min(basedIndex, actualColumHeight);

        int minimumBaseCoordDiagonal = basedIndex - mathMin ;
        int maxHeightDiagonal = actualColumHeight - mathMin ;

        int[] actualIndexes = {minimumBaseCoordDiagonal, maxHeightDiagonal};

        List<Character> capaDeNivelC = new ArrayList<>();

        IntStream.range(0, Math.min(line.maxBaseBounds.size() - actualIndexes[0]-1, line.maxHeightBounds.size() - actualIndexes[1]))
                .forEach(i -> capaDeNivelC.add(line.getToken(actualIndexes[0] + i, actualIndexes[1] + i)));

        line.checkWinInList(capaDeNivelC, actualColumHeight);

        int[] actualIndexes2 = {basedIndex, actualColumHeight};

        IntStream.iterate(0, i -> actualIndexes2[1] > 0 && actualIndexes2[0] < line.maxHeightBounds.size() - 2, i -> i + 1)
                .forEach(i -> {
                    actualIndexes2[0]++;
                    actualIndexes2[1]--;
                });

        List<Character> levelLayerDiagonal = new ArrayList<>();

        IntStream.iterate(actualIndexes2[0], i -> i >= 0 && actualIndexes2[1] < line.maxHeightBounds.size(), i -> i - 1)
                .forEach(i -> {
                    levelLayerDiagonal.add(line.getToken(i, actualIndexes2[1]));
                    actualIndexes2[1]++;
                });

        line.checkWinInList(levelLayerDiagonal, actualColumHeight);
    }
}
