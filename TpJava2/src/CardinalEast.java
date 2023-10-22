public class CardinalEast extends CardinalPoints {
    @Override
    public void forwardSubmarine(Nemo nemo) {
        nemo.movementManager.addToCoordinates(1,0,0);
    }

    @Override
    public String toString() {
        return "East";
    }

    @Override
    public CardinalPoints rotateRight(){
        return new CardinalSouth();
    }

    @Override
    public CardinalPoints rotateLeft(){
        return new CardinalNorth();
    }
}