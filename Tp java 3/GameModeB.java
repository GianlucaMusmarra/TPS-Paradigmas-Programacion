public class GameModeB extends GameMode{

    public GameModeB() {
        gameChar = 'B';
    }

    @Override
    public void checkModeWins(GameModeManager manager, int columnIndex) {
        manager.checkBModeWin(columnIndex);
    }
}
