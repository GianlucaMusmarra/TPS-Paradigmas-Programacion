
public class GameModeNone extends GameMode{

    @Override
    public boolean isGameMode(char c) {
        throw new RuntimeException("Invalid setup.");
    }

    @Override
    public void checkModeWins(Linea line, int columnIndex) {}
}
