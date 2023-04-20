package command.comannds;

import command.CommandExistential;
import entity.user.User;
import exaption.*;
import holder.UserHolder;

import java.util.List;

public class InfoAllCommand implements CommandExistential {
    UserHolder userHolder;

    public InfoAllCommand() {
        this.userHolder = UserHolder.getInstance();
    }

    @Override
    public boolean commandActive() throws DateUserException {
        System.out.println("<<<<<<<<<< Информация о всех пользователях >>>>>>>>>>");
        List<User> usersListPrint = userHolder.getAllUsers();
        if (usersListPrint == null) throw new DateUserException("Хранилище Пользователей пусто!!!");
        System.out.println("\033[0;34m");
        usersListPrint.forEach(System.out::println);
        System.out.println("\033[0m");
        System.out.println("<<<<<<<<<< Конец Информации >>>>>>>>>>");
        return false;
    }
}
