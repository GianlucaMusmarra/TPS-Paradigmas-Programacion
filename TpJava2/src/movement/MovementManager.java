package movement;

import rotation.*;
import nemo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MovementManager {
    public ArrayList<Integer> coordinates= new ArrayList<>();

    public LinkedList<CardinalPoints> bowOrientation = new LinkedList<>();

    public String bow(){return bowOrientation.get(0).toString();}

    public LinkedList<DepthLevel> depthMeter = new LinkedList<>(Arrays.asList(new DepthSurface(), new DepthSurface()));

    public boolean isUnderWater(){
        return this.coordinates.get(0) < -1;
    }

    public Nemo nemo;

    public MovementManager(Nemo submarine){
        this.nemo = submarine;
        this.bowOrientation.add(new North());
        this.bowOrientation.add(new East());
        this.bowOrientation.add(new South());
        this.bowOrientation.add(new West());
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
        bowOrientation.addLast(bowOrientation.removeFirst());
    }

    public void turnBowLeft(){
        bowOrientation.addFirst(bowOrientation.removeLast());
    }

    public void moveForward(){
        bowOrientation.get(0).forwardSubmarine(nemo);
    }

    public void moveUp(){
        int x = this.coordinates.get(0);
        int y = this.coordinates.get(1);
        int z = this.coordinates.get(2);
        depthMeter.addLast(new DepthSurface());
        depthMeter.addLast(new DepthSurface());
        depthMeter.removeFirst();
        depthMeter.removeFirst();
        addToCoordinates(0,0,1);
    }

    public void moveDown(){
        int x = this.coordinates.get(0);
        int y = this.coordinates.get(1);
        int z = this.coordinates.get(2);
        depthMeter.addFirst(new DepthUnderWater());
        depthMeter.addFirst(new DepthUnderWater());
        depthMeter.removeLast();
        addToCoordinates(0,0,-1);
    }

}
