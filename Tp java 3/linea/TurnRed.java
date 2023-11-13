package linea;

public class TurnRed extends Turn {
    @Override
    public String toString() {
        return "red";
    }

    @Override
    public void PlayRed() {}

    @Override
    public void PlayBlue() {
        throw new RuntimeException("Wrong turn!");
    }
}
