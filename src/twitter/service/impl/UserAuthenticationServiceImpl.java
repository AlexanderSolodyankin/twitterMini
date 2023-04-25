package twitter.service.impl;

import twitter.entity.user.User;
import twitter.exaption.InputException;
import twitter.exaption.UserHoldException;
import twitter.holder.UserHolder;
import twitter.service.UserAuthenticationService;

import java.util.Scanner;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private UserHolder userHolder;

    public UserAuthenticationServiceImpl() {
        this.userHolder = UserHolder.getInstance();
    }

    @Override
    public User authenticationUser() throws UserHoldException, InputException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<<<<<<<<<<  Вход в систему >>>>>>>>>>");
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        User user = userHolder.getUserByLogin(login);
        if(user == null){
                throw  new UserHoldException(" Такого пользователя нет в системе! Операция прервана!");
        }
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        if (!user.getPassword().equals(password)){
                throw new InputException("Неверный логин или пароль");
        }
        System.out.println("Вход в систему прощол успешно");
        return user;
    }
}
