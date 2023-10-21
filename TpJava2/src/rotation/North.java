package rotation;

import nemo.Nemo;

public class North extends CardinalPoints {
    @Override
    public void forwardSubmarine(Nemo nemo) {
        nemo.movementManager.addToCoordinates(0,1,0);
    }

    @Override
    public String toString() {
        return "North";
    }
}
