import java.util.LinkedList;
import java.util.List;

public class CommandManager {

    public static LinkedList<Command> commandList = new LinkedList<>(List.of( new CommandUp(),
        new CommandDown(),
        new CommandForward(),
        new CommandLeft(),
        new CommandRight(),
        new CommandShootChocolate())
    );

    public static void executeCommand(String command, Nemo nemo){
        commandList.stream().filter(c -> c.acceptsCommand(command)).forEach(c -> c.executeCommand(nemo));
    }
}
