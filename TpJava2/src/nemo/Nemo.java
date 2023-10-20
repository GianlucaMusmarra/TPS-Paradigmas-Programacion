package nemo;

import commands.CommandManager;
import movement.MovementManager;

import java.util.Arrays;

public class Nemo {

    public MovementManager movementManager = new MovementManager(this);

    public CommandManager commandManager = new CommandManager(this);

    public int getXPos(){return movementManager.coordinates.get(0);}
    public int getYPos(){return movementManager.coordinates.get(1);}
    public int getZPos(){return movementManager.coordinates.get(2);}

    public Nemo(int x, int y, int z){
        movementManager.setCoordinates(x,y,z);
    }

    public void move(String movement) {
        Arrays.stream(movement.split("")).forEach(c -> commandManager.executeCommand(c, this));
    }

    public void shootChocolate(){
        movementManager.depthMeter.get(0).shootChocolate();
    }
}



