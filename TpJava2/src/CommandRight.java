public class CommandRight extends Command{
    public boolean acceptsCommand(String command){
        return command.equals("r");
    }
    public void executeCommand(Nemo submarine){
        submarine.movementManager.turnBowRight();
    }
}
