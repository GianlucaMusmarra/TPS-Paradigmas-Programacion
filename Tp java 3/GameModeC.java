
public class GameModeC extends GameMode{

    public GameModeC() {
        gameChar = 'C';
    }

    @Override
    public void checkModeWins(GameModeManager manager, int columnIndex) {
        manager.checkAModeWin(columnIndex);
        manager.checkBModeWin(columnIndex);
    }
}
