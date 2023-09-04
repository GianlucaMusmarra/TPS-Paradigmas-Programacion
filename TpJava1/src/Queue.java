import java.util.ArrayList;

public class Queue {

    ArrayList<Object> ObjectsList = new ArrayList<Object>();

    public boolean isEmpty() {
        return ObjectsList.isEmpty();
    }

    public Queue add( Object  cargo ) {
        ObjectsList.add(cargo);
        return this;
    }

    public Object take() {
        if (this.isEmpty()) throw new Error("Queue is empty");
        else {
            Object taken = ObjectsList.get(0);
            ObjectsList.remove(taken);
            return taken;
        }
    }

    public Object head() {
        if (this.isEmpty()) throw new Error("Queue is empty");
        else {
            return ObjectsList.get(0);
        }
    }

    public int size() {
        return ObjectsList.size();
    }

}
