
public class GameModeC extends GameMode{

    public GameModeC() {
        gameChar = 'C';
    }

    @Override
    public void checkModeWins(Linea linea, int columnIndex) {
        linea.checkAModeWin(columnIndex);
        linea.checkBModeWin(columnIndex);
    }
}
