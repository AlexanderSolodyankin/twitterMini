package command.comannds;

import command.CommandExistential;

public class ExitCommand implements CommandExistential {
    @Override
    public boolean commandActive() {
        System.out.println("Прогамма завершна!!");
        return true;
    }
}
