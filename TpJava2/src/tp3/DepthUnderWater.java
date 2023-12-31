package tp3;

import java.util.LinkedList;

public class DepthUnderWater extends DepthLevel {
    @Override
    public void shootChocolate() {
        throw new Error("tp3.Nemo has been destroyed!");
    }

    @Override
    public void goDown(LinkedList<DepthLevel> bowOrientation) {
        bowOrientation.add(new DepthUnderWater());
    }

    @Override
    public int goUp(LinkedList<DepthLevel> bowOrientation) {
        bowOrientation.removeFirst();
        return 1;
    }
}
