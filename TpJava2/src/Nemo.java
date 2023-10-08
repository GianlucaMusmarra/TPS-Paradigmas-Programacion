import java.util.ArrayList;
import java.util.Arrays;

public class Nemo {

    public Boolean isUnderWater ;
    public String bow = "North";
    public ArrayList<Integer> coordinates= new ArrayList<>();

    public boolean isDestroyed = false;

    public Nemo(int x, int y){
        setCoordinates(x,y);
    }

    public void setCoordinates(int x, int y) {
        this.coordinates.clear();
        this.coordinates.add(x);
        this.coordinates.add(y);

        this.isUnderWater = y < 0;
    }

    public void move(String movement) {
        int x = coordinates.get(0);
        int y = coordinates.get(1);

        for (int i = 0; i < movement.length(); i ++ )
        {
            char input = movement.charAt(i);

            if (input == 'u'){ y++;}
            if (input == 'd'){ y--;}
            if(input == 'f'){
                if (this.bow == "North"){
                    y++;
                }
                else if (this.bow == "East"){
                    x--;
                }
                else if (this.bow == "South"){
                    y--;
                }
                else if (this.bow == "West"){
                    x++;
                }
            }

            if(input == 'l'){
                if (this.bow == "North"){
                    this.bow = "East";
                }
                else if (this.bow == "East"){
                    this.bow = "South";
                }
                else if (this.bow == "South"){
                    this.bow = "West";
                }
                else if (this.bow == "West"){
                    this.bow = "North";
                }
            }

            if(input == 'r'){
                if (this.bow == "North"){
                    this.bow = "West";
                }
                else if (this.bow == "West"){
                    this.bow = "South";
                }
                else if (this.bow == "South"){
                    this.bow = "East";
                }
                else if (this.bow == "East"){
                    this.bow = "North";
                }
            }
            
            if(input == 'm'){
                if(y<-1){
                    this.isDestroyed = true;
                }
            }
        }

        setCoordinates(x,y);
    }


    

}



