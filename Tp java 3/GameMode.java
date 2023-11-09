public abstract class GameMode {

    protected char gameChar;

    public boolean isGameMode(char c){
        return c == gameChar;
    }

    public void setGameMode(char c){
        c = gameChar;
    }
}
