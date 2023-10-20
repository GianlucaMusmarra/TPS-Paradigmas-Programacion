package movement;

public class DepthUnderWater extends DepthLevel {
    @Override
    public void shootChocolate() {
        throw new Error("Nemo has been destroyed!");
    }
}
