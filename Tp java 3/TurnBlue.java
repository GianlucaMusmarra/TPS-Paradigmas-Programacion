public class TurnBlue extends Turn {
    @Override
    public String toString() {
        return "blue";
    }
    @Override
    public void PlayRed() {
        throw new RuntimeException("Wrong turn!");
    }

    @Override
    public void PlayBlue() {

    }
}
