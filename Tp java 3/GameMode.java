
public abstract class GameMode {

    protected char gameChar;

    public boolean isGameMode(char c){
        return c == gameChar || c == 'C';
    }

    public abstract void checkModeWins (MatchLine linea, int columnIndex);
}
