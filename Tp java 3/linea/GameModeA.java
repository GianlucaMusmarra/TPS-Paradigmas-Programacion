package linea;

public class GameModeA extends GameMode{

    public GameModeA() {
        gameChar = 'A';
    }

    @Override
    public void checkModeWins(GameModeManager manager, int columnIndex) {
        manager.checkAModeWin(columnIndex);
    }
}
