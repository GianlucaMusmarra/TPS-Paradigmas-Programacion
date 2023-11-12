
public abstract class GameMode {

    protected char gameChar;

    public boolean isGameMode(char c){
        return c == gameChar;
    }

    public abstract void checkModeWins (GameModeManager manager, int columnIndex);
}
