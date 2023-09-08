import java.util.ArrayList;

public class Queue {
    ArrayList<Object> objectsList = new ArrayList<Object>();
    static Queue queueSwitch = new EmptyQueue();

    public boolean isEmpty() {
        return objectsList.isEmpty();
    }

    public Queue add( Object  cargo ) {
        queueSwitch = queueSwitch.add(cargo);
        objectsList.add(cargo);
        return this;
    }

    public Object take() {
        Queue safe = new EmptyQueue();
        for (Object o : objectsList) safe = safe.add(o);
        safe.take();

        Object taken = objectsList.get(0);
        objectsList.remove(0);
        queueSwitch.objectsList.remove(0);
        return taken;
    }

    public Object head() {
        return queueSwitch.head();
    }

    public int size() {
        return objectsList.size();
    }

}

class EmptyQueue extends Queue{
    @Override
    public Queue add( Object  cargo ) {
        return new ThingsQueue().add(cargo);
    }
    @Override
    public Object head() {
        throw new Error("Queue is empty");
    }

    @Override
    public Object take() {
        throw new Error("Queue is empty");
    }
}

class ThingsQueue extends Queue {
    @Override
    public Queue add(Object cargo) {
        this.objectsList.add(cargo);
        return this;
    }

    @Override
    public Object head() {
        return this.objectsList.get(0);
    }

    @Override
    public Object take() {
        return null;
    }
}
