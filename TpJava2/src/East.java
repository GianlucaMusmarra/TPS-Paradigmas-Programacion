public class East extends CardinalPoints{
    @Override
    public void forwardSubmarine(Nemo nemo) {
        nemo.setCoordinates(nemo.coordinates.get(0)+1,nemo.coordinates.get(1),nemo.coordinates.get(2));
    }

    @Override
    public String toString() {
        return "East";
    }
}