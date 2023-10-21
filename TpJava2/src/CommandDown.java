public class CommandDown extends Command{
    public boolean acceptsCommand(String command){
        return command.equals("d");
    }
    public void executeCommand(Nemo submarine){
        submarine.movementManager.moveDown();
    }
}
