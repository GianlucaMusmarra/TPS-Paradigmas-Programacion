package rotation;

import nemo.Nemo;

public class East extends CardinalPoints {
    @Override
    public void forwardSubmarine(Nemo nemo) {
        nemo.movementManager.addToCoordinates(1,0,0);
    }

    @Override
    public String toString() {
        return "East";
    }
}