package command;

public class CommandFactory {
    public static CommandExistential getCommand(String commandIn){
        Command command = Command.valueOf(commandIn.toUpperCase());
        return command.getCommand();
    }
}
