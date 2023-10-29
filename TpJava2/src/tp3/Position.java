package tp3;

public class Position {

    private int x;
    private int y;
    private int z;

    public Position(int startX, int startY){
        this.setCoordinates(startX, startY, 0);
    }

    public int getX (){return this.x;}
    public int getY (){return this.y;}
    public int getZ (){return this.z;}

    public void setCoordinates(int x2, int y2, int z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
    }
}
