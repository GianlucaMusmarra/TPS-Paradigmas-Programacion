package tp3;

import java.util.LinkedList;

public abstract class DepthLevel {
    public abstract void shootChocolate();
    public abstract void goDown(LinkedList<DepthLevel> bowOrientation);
    public abstract int goUp(LinkedList<DepthLevel> bowOrientation);
}
