
public abstract class Command {
    public abstract boolean acceptsCommand(String command);
    public abstract void executeCommand(Nemo submarine);
}
