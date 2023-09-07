public class example {
    public static void main(String[] args) {
        Object o = new Empty();
        ((Empty) o).add();
        System.out.println(o instanceof Full);

        Object o2 = new Empty().add();
        System.out.println(o2 instanceof Full);
    }
}

class Empty {
    public Object add(){
        return new Full();
    }
}

class Full{

}
