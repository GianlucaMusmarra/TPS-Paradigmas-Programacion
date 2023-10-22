package tp3;

public class CardinalSouth extends CardinalPoints {
    @Override
    public void forwardSubmarine(Nemo nemo) {
        nemo.movementManager.addToCoordinates(0,-1,0);
    }
    @Override
    public String toString() {
        return "South";
    }

    @Override
    public CardinalPoints rotateRight(){
        return new CardinalWest();
    }

    @Override
    public CardinalPoints rotateLeft(){
        return new CardinalEast();
    }
}