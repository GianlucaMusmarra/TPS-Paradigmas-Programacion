
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
public class Queue {
    LinkedList<Element> elements = new LinkedList<>(Collections.singletonList(new BlankElement().add("Queue is empty")));

    public boolean isEmpty() {
        return elements.size() == 1;
    }

    public Queue add( Object  cargo ) {
        elements.add(size(),new Element().add(cargo)); return this;
    }

    public Object take() {
        return elements.remove(0).getObject();
    }

    public Object head() {
        return elements.get(0).getObject();
    }

    public int size() {
        return elements.size() -1;
    }
}

class Element {
    Object object;
    public Element add(Object o){
        object = o; return this;
    }
    public Object getObject(){
        return object;
    }
}

class BlankElement extends Element{
    @Override
    public Object getObject(){
        throw new Error(object.toString());
    }
}