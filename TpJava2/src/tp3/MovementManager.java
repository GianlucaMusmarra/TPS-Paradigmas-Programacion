package tp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MovementManager {

    private ArrayList<Integer> coordinates= new ArrayList<>();
    public int getXPos(){return coordinates.get(0);}
    public int getYPos(){return coordinates.get(1);}
    public int getZPos(){return coordinates.get(2);}

    public CardinalPoints bowOrientation;

    public String bow(){return bowOrientation.toString();}

    public LinkedList<DepthLevel> depthMeter = new LinkedList<>(Arrays.asList(new DepthSurface()));

    public boolean isUnderWater(){
        return this.coordinates.get(0) < -1;
    }

    public Nemo nemo;

    public MovementManager(Nemo submarine, CardinalPoints cardinalPoint){
        this.nemo = submarine;
        this.bowOrientation = cardinalPoint;
    }

    public void addToCoordinates(int x, int y, int z) {
        int x0 = this.coordinates.get(0);
        int y0 = this.coordinates.get(1);
        int z0 = this.coordinates.get(2);

        this.coordinates.clear();
        this.coordinates.add(x0 +x);
        this.coordinates.add(y0 +y);
        this.coordinates.add(z0 +z);
    }

    public void setCoordinates(int x, int y, int z) {
        this.coordinates.clear();
        this.coordinates.add(x);
        this.coordinates.add(y);
        this.coordinates.add(z);
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
