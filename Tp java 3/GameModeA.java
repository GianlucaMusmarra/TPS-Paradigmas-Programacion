public class GameModeA extends GameMode{

    public GameModeA() {
        gameChar = 'A';
    }

    @Override
    public void checkModeWins(Linea line, int columnIndex) {
        line.checkAModeWin(columnIndex);
    }
}
