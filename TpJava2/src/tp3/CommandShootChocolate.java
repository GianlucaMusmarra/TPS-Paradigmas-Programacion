package tp3;

public class CommandShootChocolate extends Command{
    public boolean acceptsCommand(String command){
        return command.equals("m");
    }
    public void executeCommand(Nemo submarine){
        submarine.shootChocolate();
    }
}
