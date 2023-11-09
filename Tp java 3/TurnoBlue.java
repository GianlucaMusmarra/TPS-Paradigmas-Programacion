public class TurnoBlue extends Turno{
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
