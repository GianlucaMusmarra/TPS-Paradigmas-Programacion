package commands;

import nemo.Nemo;

public class CommandUp extends Command{
    public boolean acceptsCommand(String command){
        return command.equals("u");
    }
    public void executeCommand(Nemo submarine){
        submarine.movementManager.moveUp();
    }
}
