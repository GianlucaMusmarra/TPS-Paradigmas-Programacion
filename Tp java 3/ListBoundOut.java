public class ListBoundOut extends ListBounds{
    @Override
    public void checkBound() {
        throw new RuntimeException("Out of bounds!");
    }

    @Override
    public void checkBoundTooSmall() {
        throw new RuntimeException("Invalid setup! Too small!");
    }
}