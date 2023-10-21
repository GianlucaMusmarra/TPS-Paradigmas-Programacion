public class CardinalWest extends CardinalPoints {
    @Override
    public void forwardSubmarine(Nemo nemo) {
        nemo.movementManager.addToCoordinates(-1,0,0);
    }

    @Override
    public String toString() {
        return "West";
    }
}