import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Nemo {

    public LinkedList<CardinalPoints> bowOrientation = new LinkedList<>(Arrays.asList(new North(), new East(), new South(), new West()));
    public String bow(){return bowOrientation.get(0).toString();}

    public LinkedList<DepthLevel> depthMeter = new LinkedList<>(Arrays.asList(new DepthSurface(), new DepthSurface()));

    public ArrayList<Integer> coordinates= new ArrayList<>();

    public Nemo(int x, int y, int z){
        setCoordinates(x,y,z);
    }

    public boolean isUnderWater(){
        return this.coordinates.get(0) < -1;
    }

    public void setCoordinates(int x, int y, int z) {
        this.coordinates.clear();
        this.coordinates.add(x);
        this.coordinates.add(y);
        this.coordinates.add(z);
    }

    public void move(String movement) {
        int x = coordinates.get(0);
        int y = coordinates.get(1);
        int z = coordinates.get(2);

        for (int i = 0; i < movement.length(); i ++ )
        {
            char input = movement.charAt(i);

            if (input == 'u'){
                z++;
                depthMeter.addLast(new DepthSurface());
                depthMeter.addLast(new DepthSurface());
                depthMeter.removeFirst();
                depthMeter.removeFirst();
                setCoordinates(x,y,z);
            }

            if (input == 'd'){
                z--;
                depthMeter.addFirst(new DepthUnderWater());
                depthMeter.addFirst(new DepthUnderWater());
                depthMeter.removeLast();
                setCoordinates(x,y,z);
            }

            if(input == 'm'){
                depthMeter.get(0).shootChocolate();
            }

            if(input == 'l'){
                bowOrientation.addFirst(bowOrientation.removeLast());
            }

            if(input == 'r'){
                bowOrientation.addLast(bowOrientation.removeFirst());
            }

            if(input == 'f'){
                bowOrientation.get(0).forwardSubmarine(this);
            }
        }


    }


    

}



