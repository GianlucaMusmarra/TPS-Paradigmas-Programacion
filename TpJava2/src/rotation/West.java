package rotation;

import nemo.Nemo;

public class West extends CardinalPoints {
    @Override
    public void forwardSubmarine(Nemo nemo) {
        nemo.movementManager.addToCoordinates(-1,0,0);
    }

    @Override
    public String toString() {
        return "cardinal_points.West";
    }
}