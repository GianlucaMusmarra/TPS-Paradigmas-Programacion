import java.util.LinkedList;

public class DepthSurface extends DepthLevel {
    @Override
    public void shootChocolate() {}

    @Override
    public void goDown(LinkedList<DepthLevel> bowOrientation) {
        bowOrientation.addFirst(new DepthAlmostSurface());
    }

    @Override
    public int goUp(LinkedList<DepthLevel> bowOrientation) {
        return 0;
    }
}
