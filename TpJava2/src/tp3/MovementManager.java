package tp3;

import java.util.Arrays;
import java.util.LinkedList;

public class MovementManager {

    private Position coordinates;
    public int getXPos(){return coordinates.getX();}
    public int getYPos(){return coordinates.getY();}
    public int getZPos(){return coordinates.getZ();}

    public CardinalPoints bowOrientation;

    public String bow(){return bowOrientation.toString();}

    public LinkedList<DepthLevel> depthMeter = new LinkedList<>(Arrays.asList(new DepthSurface()));

    public boolean isUnderWater(){
        return this.coordinates.getZ() < -1;
    }

    public Nemo nemo;

    public MovementManager(Nemo submarine, CardinalPoints cardinalPoint, Position pos){
        this.nemo = submarine;
        this.bowOrientation = cardinalPoint;
        this.coordinates = pos;
    }

    public void addToCoordinates(int x, int y, int z) {
        int x0 = this.getXPos();
        int y0 = this.getYPos();
        int z0 = this.getZPos();

        this.coordinates.setCoordinates(x0 + x, y0 +y, z +z0);
    }

    public void turnBowRight(){
        bowOrientation = bowOrientation.rotateRight();
    }

    public void turnBowLeft(){bowOrientation = bowOrientation.rotateLeft();}

    public void moveForward(){
        bowOrientation.forwardSubmarine(nemo);
    }

    public void moveUp(){
        addToCoordinates(0,0,depthMeter.get(0).goUp(depthMeter));
    }

    public void moveDown(){
        depthMeter.get(0).goDown(depthMeter);
        addToCoordinates(0,0,-1);
    }

}
