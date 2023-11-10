public class GameModeA extends GameMode{

    public GameModeA() {
        gameChar = 'A';
    }

    @Override
    public void checkModeWins(Linea linea, int columnIndex) {
        linea.checkAModeWin(columnIndex);
    }
}
