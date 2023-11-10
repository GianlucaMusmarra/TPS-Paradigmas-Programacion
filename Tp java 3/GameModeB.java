public class GameModeB extends GameMode{

    public GameModeB() {
        gameChar = 'B';
    }

    @Override
    public void checkModeWins(Linea linea, int columnIndex) {
        linea.checkBModeWin(columnIndex);
    }
}
