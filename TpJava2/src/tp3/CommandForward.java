package tp3;

public class CommandForward extends Command{
    public boolean acceptsCommand(String command){
        return command.equals("f");
    }
    public void executeCommand(Nemo submarine){
        submarine.movementManager.moveForward();
    }
}
