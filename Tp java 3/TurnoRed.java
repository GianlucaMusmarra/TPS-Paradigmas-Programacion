import javax.sound.sampled.Line;

public class TurnoRed extends Turno{
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
