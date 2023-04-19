package mini.twter.service;

import mini.twter.util.CommandKey;
import mini.twter.holder.UserHolder;
import mini.twter.entity.user.Organization;
import mini.twter.entity.user.Person;
import mini.twter.entity.user.User;
import mini.twter.entity.user.UserType;

import java.time.LocalDate;

public class UserRegistration {

    public static User newUser() {

        System.out.println("Выберите тип пользователя ( 0 - человек, 1 - организация ) ");

        User user;

        while (true) {
            CommandKey.nextNumber();
            int userType = CommandKey.getNumber();
            if (userType == 0) {
                user = new Person();
                break;
            } else if (userType == 1) {
                user = new Organization();
                break;
            } else System.err.println("Неверный ввод данных введите 0 или 1");
        }

        return  UserHolder.save(setUser(user));
    }

    public static User setUser(User user) {
        while (true) {
            System.out.print("Введите логин: ");
            CommandKey.nextLine();
            if (UserHolder.getLogin(CommandKey.getLine()) == null) {
                user.setLogin(CommandKey.getLine());
                break;
            } else System.err.println("Такой пользователь уже существует!!! Введите другой логин");
        }

        while (true) {
            System.out.print("Введите пароль: ");
            CommandKey.nextLine();
            String pass1 = CommandKey.getLine();
            System.out.print("Подтвердите пароль: ");
            CommandKey.nextLine();
            String pass2 = CommandKey.getLine();
            if(pass1.equals(pass2)) {
                user.setPassword(CommandKey.getLine());
                break;
            }else System.err.println("Пароли не совпадают!!!   Повторите попытку.");
        }
        user.setId(UserHolder.getAll().size());
        user.setRegisterDate(LocalDate.now());
        if(UserType.PERSON == user.getUserType()){
              return  setPersona(user);
        }else return setOrganization(user);
    }

    public static User setPersona(User user) {
        Person person = (Person) user;
        System.out.print("Введите Имя: ");
        CommandKey.nextLine();
        person.setName(CommandKey.getLine());
        System.out.print("Введите Фамилию: ");
        CommandKey.nextLine();
        person.setSerName(CommandKey.getLine());
        System.out.println("Введите свою дату рождения");
        person.setDateBerth(CommandKey.nextDate());
        return user;
    }

    public static User setOrganization(User user) {
        Organization organization = (Organization) user;
        System.out.println("Введите название организации:");
        CommandKey.nextLine();
        organization.setName(CommandKey.getLine());
        System.out.println("Введите род деятельности организации:");
        CommandKey.nextLine();
        organization.setOccupation(CommandKey.getLine());
        System.out.println("Введите дату основания компании");
        organization.setDateFound(CommandKey.nextDate());
        return user;
    }
}
