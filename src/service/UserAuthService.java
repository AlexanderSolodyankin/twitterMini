package mini.twter.service;

import mini.twter.util.CommandKey;
import mini.twter.holder.UserHolder;
import mini.twter.entity.user.User;

public class UserAuthService {
    private static User aunthUser;



    public  void authenticationUser(){

        System.out.println("Введите логин:");
        CommandKey.nextLine();
        User user = UserHolder.getLogin(CommandKey.getLine());
        if(user == null) {
            System.err.println("Такого пользователя не существует!");
        }else {
            System.out.println("Введите пароль:");
            CommandKey.nextLine();
            if (isPassword(user, CommandKey.getLine())) {
                System.out.println("Вход в систему прошол успешно!");
                aunthUser = user;
            }else System.err.println("Неверный Пароль");
        }

    }

    public  boolean isPassword(User user,String password){
        if (user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public static User getAunthUser() {
        return aunthUser;
    }

    public static void setAunthUser(User aunthUser) {
        UserAuthService.aunthUser = aunthUser;
    }
}
