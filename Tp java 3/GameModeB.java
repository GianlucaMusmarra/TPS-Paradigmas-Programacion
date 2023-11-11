public class GameModeB extends GameMode{

    public GameModeB() {
        gameChar = 'B';
    }

    @Override
    public void checkModeWins(Linea line, int columnIndex) {
        line.checkBModeWin(columnIndex);
    }
}
