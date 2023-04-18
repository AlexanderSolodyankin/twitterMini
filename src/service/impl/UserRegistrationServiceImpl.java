package service.impl;

import entity.user.Organization;
import entity.user.Person;
import entity.user.User;
import entity.user.UserType;
import exaption.*;
import holder.UserHolder;
import service.FileService;
import service.UserRegistrationService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserRegistrationServiceImpl implements UserRegistrationService {
    private Scanner scan;
    private UserHolder userHolder;
    private FileService fileService;

    public UserRegistrationServiceImpl(UserHolder userHolder) {
        this.userHolder = userHolder;
        scan = new Scanner(System.in);
        fileService = new FileServiceImpl();
    }

    @Override
    public User createUser() throws UserHoldException, InputPasswordException, IOException,
            InputLoginException, InputUserTypeException, DateRegisterException, UserException {
        System.out.println("<<<<<<<<<< Регистрация нового пользователя >>>>>>>>>>");
        System.out.println("Выберите тип пользователя: 0 - человек, 1 - Организация.");
        int userType;
        User user;
        try {
            userType = scan.nextInt();
        } catch (InputMismatchException ex) {
            throw new InputMismatchException(" Ошибка ввода нужно ввести либо 0 либо 1. Операция прервана!");
        }
        if (userType == 0) {
            user = new Person();
        } else if (userType == 1) {
            user = new Organization();
        } else {
                throw new InputUserTypeException("Неверное зночение для типа пользователя!");
        }
        System.out.print("Введите логин: ");
        String login = scan.nextLine();
        // Задать вадиму почему сканер после ввода интеджер пропускает ввод строки
        // и как это исправить тоже надо узнать.
        while (true) {
            login = scan.nextLine();
            try {
                if (login == null || login.isEmpty()) {
                    throw new InputSystemType("Системная ошибка ввода Повторите ввод");
                }
            } catch (InputSystemType e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                login = scan.nextLine();
            }
            if (!login.isEmpty()) {
                break;
            }
        }
        if (!login.matches("[a-zA-Z]+") || (login.length() > 11)) {
            throw new InputLoginException("Неверный формат логина. Регистрация прервана");
        }

        if (userHolder.getUserByLogin(login) != null) {
            throw new UserHoldException("Такой пользователя уже есть в системе! Операция Прервана!!!");
        }
        user.setLogin(login);
        String password1;
        int limit = 5;// Лимитирование попыток создания пароля

        while (true) {
            System.out.print("Создайте пароль:");
            password1 = scan.nextLine();
            System.out.print("Повторите созданый пароль:");
            String password2 = scan.nextLine();
            boolean upperChar = false;
            boolean symbol = false;
            boolean letters = false;
            boolean passLength = password1.length() > 5;
            boolean num  = false;
            for(char el : password1.toCharArray()){
                if(el == 64 || el == 33 || el == 35 || el == 36 ||
                        el == 37 || el == 38 || el == 42) symbol = true;
                if(el >= 65 && el <= 90) upperChar = true;
                if(el >= 97 && el <= 122) letters = true;
                if (el >= 48 && el <= 57) num = true;
            }
            if (password1.equals(password2) && upperChar && symbol && letters && passLength && num) {
                break;
            } else {
                System.out.println("\033[0;31m Некоректный пароль повторите попытку \033[0m");
                System.out.println("\033[0;31m Пароль должен содержать хотя бы:" +
                        " Одну заглавную букву, один символ, одну цифру, состоять из латинских букв" +
                        " и не быть меньше 5-ти симвалов \033[0m");
                System.out.println("\033[0;31m Осталось папыток: \033[0m" + limit);
            }

            if (limit == 0){
                throw new InputPasswordException("Превышен лимит для ввода пароля. Операция прервана!");
            }
            limit--;
        }
        user.setPassword(password1);
        user.setDataRegister(LocalDateTime.now());
        if (user.getUserType() == UserType.PERSON) {
            user = setPerson(user);
        } else user = setOrganization(user);
        user = userHolder.saveUser(user);
        fileService.writeUserFileData(userHolder.getAllUsers());
        return user;
    }

    @Override
    public User setPerson(User user) throws DateRegisterException, UserException {
        Person person = (Person) user;
        System.out.print("Введите имя: ");
        String name = scan.nextLine();
        if (name == null || name.isEmpty()) throw new UserException("Имя не должно быть пустым! Операция прервана");
        person.setName(name);
        System.out.print("Введите Фамилию: ");
        String serName = scan.nextLine();
        if (serName == null || serName.isEmpty()) throw new UserException("Фамилия не должна быть пустой! Операция прервана");
        person.setSerName(serName);
        person.setDataBerth(setData(user.getUserType()));
        return person;
    }

    @Override
    public User setOrganization(User user) throws DateRegisterException, UserException {
        Organization organization = (Organization) user;
        System.out.print("Введите название организации: ");
        String name = scan.nextLine();
        if (name == null || name.isEmpty()) throw new UserException("Имя не должно быть пустым! Операция прервана");
        organization.setName(name);
        System.out.print("Введите род деятельности: ");
        String occupation = scan.nextLine();
        if (occupation == null || occupation.isEmpty()) throw new UserException("Род занятости не должно быть пустым! Операция прервана");
        organization.setOccupation(occupation);
        organization.setDataFounding(setData(user.getUserType()));
        return organization;
    }

    private LocalDate setData(UserType userType) throws DateRegisterException {
        System.out.print("Введите дату основания в формате гггг-мм-дд: ");
        String founding = scan.nextLine();
        LocalDate date = null;
        try {
            date = LocalDate.parse(founding);
            if(UserType.PERSON.equals(userType)) {
                if ((LocalDate.now().toEpochDay() - date.toEpochDay()) / 365 < 10)
                    throw new DateRegisterException("Возраст регистрируемого не должен быть менее 10 лет!!! Операция прервана ");
            }else if(LocalDate.now().toEpochDay() - date.toEpochDay() < 31)
                throw new DateRegisterException("Возраст организации не должен быть менее 31 дня!!! Операция прервана ");
        } catch (DateTimeParseException e) {
            System.out.println("\033[0;31m" + e.getClass().getName() + ": " +
                    "Неверный формат даты введите дату в формате год-месяц-день пример: " + LocalDate.now());
            System.out.println("\033[0m");
            date = setData(userType);
        }
        return date;
    }
}
