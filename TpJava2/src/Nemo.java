import java.util.Arrays;

public class Nemo {

    public MovementManager movementManager = new MovementManager(this);

    public CommandManager commandManager = new CommandManager(this);

    public int getXPos(){return movementManager.getXPos();}
    public int getYPos(){return movementManager.getYPos();}
    public int getZPos(){return movementManager.getZPos();}

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



