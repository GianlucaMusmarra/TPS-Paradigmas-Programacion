package tp3;

import java.util.Arrays;

public class Nemo {

    public MovementManager movementManager;

    public int getXPos(){return movementManager.getXPos();}
    public int getYPos(){return movementManager.getYPos();}
    public int getZPos(){return movementManager.getZPos();}

    public Nemo(Position initialPos, CardinalPoints facingDirection){
        movementManager = new MovementManager(this, facingDirection, initialPos);
    }

    public void move(String movement) {
        Arrays.stream(movement.split("")).forEach(c -> CommandManager.executeCommand(c, this));
    }

    public void shootChocolate(){
        movementManager.depthMeter.get(0).shootChocolate();
    }
}



