package service.impl;

import entity.user.User;
import exaption.InputPasswordException;
import exaption.UserHoldException;
import holder.UserHolder;
import service.FileService;
import service.UserAuthenticationService;

import java.util.Scanner;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private UserHolder userHolder;

    public UserAuthenticationServiceImpl(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Override
    public User authenticationUser() throws UserHoldException, InputPasswordException {
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
                throw new InputPasswordException("Неверный логин или пароль");
        }
        System.out.println("Вход в систему прощол успешно");
        return user;
    }
}
