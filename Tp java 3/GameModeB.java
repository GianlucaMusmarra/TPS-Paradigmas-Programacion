public class GameModeB extends GameMode{

    public GameModeB() {
        gameChar = 'B';
    }

    @Override
    public void checkModeWins(MatchLine line, int columnIndex) {
        line.checkBModeWin(columnIndex);
    }
}
