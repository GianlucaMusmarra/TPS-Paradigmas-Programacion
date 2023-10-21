import java.util.LinkedList;

public class DepthAlmostSurface extends DepthLevel {
    @Override
    public void shootChocolate() {
    }

    @Override
    public void goDown(LinkedList<DepthLevel> bowOrientation) {
        bowOrientation.addFirst(new DepthUnderWater());
    }

    @Override
    public int goUp(LinkedList<DepthLevel> bowOrientation) {
        bowOrientation.removeFirst();
        return 1;
    }
}
