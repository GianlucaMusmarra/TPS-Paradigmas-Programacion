package tp3;

public class CommandLeft extends Command{
    public boolean acceptsCommand(String command){
        return command.equals("l");
    }
    public void executeCommand(Nemo submarine){
        submarine.movementManager.turnBowLeft();
    }
}
