package twitter.command.comannds;

import twitter.command.CommandExistential;
import twitter.entity.user.User;
import twitter.exaption.*;
import twitter.holder.UserHolder;

import java.util.Scanner;

public class InfoByLoginCommand implements CommandExistential {
    private Scanner scan;
    private UserHolder userHolder;

    public InfoByLoginCommand() {
        scan = new Scanner(System.in);
        userHolder = UserHolder.getInstance();
    }

    @Override
    public boolean commandActive() throws UserException{
        System.out.println("<<<<<<<<<< Информация о пользователе по логину >>>>>>>>>>");
        System.out.println("Введите логин пользователя о котором хотите узнать ");
        String login = scan.nextLine();
        User user = userHolder.getUserByLogin(login);
        if (user == null) throw new UserException("Пользователь под логином " + login + " не найден!");
        System.out.println("Найден пользователь");
        System.out.println(user);
        System.out.println("<<<<<<<<<< Конец Информации >>>>>>>>>>");
        return false;
    }
}
