public class GameModeA extends GameMode{

    public GameModeA() {
        gameChar = 'A';
    }

    @Override
    public void checkModeWins(MatchLine linea, int columnIndex) {
        linea.checkAModeWin(columnIndex);
    }
}
