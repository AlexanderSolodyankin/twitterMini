package command.comannds;

import command.CommandExistential;
import exaption.UserException;
import holder.UserHolder;

public class InfoCommand implements CommandExistential {
    private UserHolder userHolder;

    public InfoCommand() {
        this.userHolder = UserHolder.getInstance();
    }

    @Override
    public boolean commandActive() throws UserException {
        if (userHolder.getUserAuthentication() == null)
            throw new UserException("Для выполнения данной команды вы должны войти в систему!!!");
        System.out.println("<<<<<<<<<< Информация о пользователе >>>>>>>>>>");
        System.out.println(userHolder.getUserAuthentication());
        System.out.println("<<<<<<<<<< Конец Информации >>>>>>>>>>");

        return false;
    }
}
