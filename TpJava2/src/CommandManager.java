import java.util.LinkedList;

public class CommandManager {

    public Nemo submarine;

    public LinkedList<Command> commandList = new LinkedList<>();

    public CommandManager(Nemo nemo){
        this.submarine = nemo;
        commandList.add(new CommandUp());
        commandList.add(new CommandDown());
        commandList.add(new CommandForward());
        commandList.add(new CommandLeft());
        commandList.add(new CommandRight());
        commandList.add(new CommandShootChocolate());
    }

    public void executeCommand(String command, Nemo nemo){
        commandList.stream().filter(c -> c.acceptsCommand(command)).forEach(c -> c.executeCommand(nemo));
    }
}
