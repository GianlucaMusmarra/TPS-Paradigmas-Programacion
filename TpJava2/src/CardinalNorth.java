public class CardinalNorth extends CardinalPoints {
    @Override
    public void forwardSubmarine(Nemo nemo) {
        nemo.movementManager.addToCoordinates(0,1,0);
    }

    @Override
    public String toString() {
        return "North";
    }

    @Override
    public CardinalPoints rotateRight(){
        return new CardinalEast();
    }

    @Override
    public CardinalPoints rotateLeft(){
        return new CardinalWest();
    }
}
