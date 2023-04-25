package twitter.service.impl;

import twitter.entity.user.Organization;
import twitter.entity.user.Person;
import twitter.entity.user.User;
import twitter.entity.user.UserType;
import twitter.exaption.*;
import twitter.exaption.InputException;
import twitter.holder.UserHolder;
import twitter.service.FileService;
import twitter.service.UserRegistrationService;

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

    public UserRegistrationServiceImpl() {
        this.userHolder = UserHolder.getInstance();
        scan = new Scanner(System.in);
        fileService = new FileServiceImpl();
    }

    @Override
    public User createUser() throws InputException, DateUserException, UserException, IOException {
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
            throw new UserException("Неверное зночение для типа пользователя!");
        }
        System.out.print("Введите логин: ");
        String login = scan.nextLine(); /* овторил ввод строки так как это костыль от пропуска как поступить по другому не знаю */
        while (true) {
            login = scan.nextLine();
//  ==================== Из за сканера непонятки на всяк написал ========================================
     /* если сночало ввести числовое зночение то следующий ввод строки сканет пропускает по непонятной пречине
         Пришлось на всякий случай написать такой костль так как не хотел дублировать строку ввода в итоге в консоль всегда
             и увлекся игрой с исключениями решил это оставить так чисто ддля демонстрации ученикам (если одобришь)
     */
 /**/            try {                                                                                    /**/
 /**/                if (login == null || login.isEmpty()) {                                              /**/
 /**/                    throw new InputSystemType("Системная ошибка ввода Повторите ввод");              /**/
 /**/                }                                                                                    /**/
 /**/            } catch (InputSystemType e) {                                                            /**/
 /**/                System.err.println(e.getClass().getName() + ": " + e.getMessage());                  /**/
 /**/                login = scan.nextLine();                                                             /**/
 /**/            }                                                                                        /**/
//  ===========================================================================================================

            if (!login.isEmpty()) {
                break;
            }
        }
        if (!login.matches("[a-zA-Z]+") || (login.length() > 11)) {
            throw new InputException("Неверный формат логина. Регистрация прервана");
        }

        if (userHolder.getUserByLogin(login) != null) {
            throw new DateUserException("Такой пользователя уже есть в системе! Операция Прервана!!!");
        }

        String password1;
        int limit = 5;// Лимитирование попыток создания пароля

        while (true) {  /* Нужен для лимита ввода пороля что бы не начинать регистрацию с ночала */
            System.out.print("Создайте пароль:");
            password1 = scan.nextLine();
            System.out.print("Повторите созданый пароль:");
            String password2 = scan.nextLine();
            /* Регулярное выражение на проверку пароля на валидацию */
            if(password1.matches(
                    "^" + /* Ночало проверки строки */
                    "(?=.*[A-Z])" + /*  хотябы одна из перечисленых заглавных букв  */
                    "(?=.*[0-9])" +/* хотябы одна из перечисленных цифр*/
                    "(?=.*[!@#$%^&*_№?])" +/* проверяем на перечисленные символы */
                    "[^\\s]" +/* отрицание пробела в условии */
                    "+$"/* конец строки проверки */
            )) {
                break;/* Останавливаем цикл что бы пойти дальше */
            } else {
                /*Сообщаем пользователю что веден пароль некректно и просим повторить попытку и выводим лимит попыток*/
                System.out.println("\033[0;31m Некоректный пароль повторите попытку \033[0m");
                System.out.println("\033[0;31m Пароль должен содержать хотя бы:" +
                        " Одну заглавную букву, один символ, одну цифру, состоять из латинских букв" +
                        " и не быть меньше 5-ти симвалов \033[0m");
                System.out.println("\033[0;31m Осталось папыток: \033[0m" + limit);
            }

            if (limit == 0) {
                /* Если лимит превышен выкидываем исключение и завершаем операцию */
                throw new InputException("Превышен лимит для ввода пароля. Операция прервана!");
            }
            limit--;
        }

        user   /* Если проверки все проши успешно заночим данные в пользователя */
            .setLogin(login)
            .setPassword(password1)
            .setDataRegister(LocalDateTime.now());

        if (user.getUserType() == UserType.PERSON) {
            user = setPerson(user);
        } else user = setOrganization(user);
        user = userHolder.saveUser(user);
        fileService.writeUserFileData(userHolder.getAllUsers());
        return user;
    }

    @Override
    public User setPerson(User user) throws DateUserException, UserException {
        System.out.print("Введите имя: ");
        String name = scan.nextLine();
        if (name == null || name.isEmpty() || name.matches("^(?=.*[A-Z])[^\\s]+$"))
            throw new UserException("Имя должно начинаться с большой буквы и не должно быть пустым! Операция прервана");
        System.out.print("Введите Фамилию: ");
        String serName = scan.nextLine();
        if (serName == null || serName.isEmpty() || serName.matches("^(?=.*[A-Z])[^\\s]+$") )
            throw new UserException("Фамилия должна начинаться с большой буквы и не должно быть пустым! Операция прервана");

        ((Person)user)
                .setName(name)
                .setSerName(serName)
                .setDataBerth(setData(user.getUserType(), 5));
        return user;
    }

    @Override
    public User setOrganization(User user) throws DateUserException, UserException {
        System.out.print("Введите название организации: ");
        String name = scan.nextLine();
        if (name == null || name.isEmpty() || name.matches("^(?=.*[A-Z])[^\\s]+$"))
            throw new UserException("Имя должно начинаться с большой буквы и не должно быть пустым! Операция прервана");

        System.out.print("Введите род деятельности: ");
        String occupation = scan.nextLine();
        if (occupation == null || occupation.isEmpty())
            throw new UserException("Род занятости не должно быть пустым! Операция прервана");

        ((Organization)user)
                .setName(name)
                .setOccupation(occupation)
                .setDataFounding(setData(user.getUserType(), 5));
        return user;
    }

    private LocalDate setData(UserType userType, int limit) throws DateUserException {

        if (userType == UserType.ORGANIZATION) {
            System.out.print("Введите дату основания в формате гггг-мм-дд: ");
        }else System.out.print("Введите день рождения в формате гггг-мм-дд: ");

        String founding = scan.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(founding);

            if (UserType.PERSON.equals(userType)) {
                if ((LocalDate.now().toEpochDay() - date.toEpochDay()) / 365 < 10)
                    throw new DateUserException("Возраст регистрируемого не должен быть менее 10 лет!!! Операция прервана ");

            } else if (LocalDate.now().toEpochDay() - date.toEpochDay() < 31)
                throw new DateUserException("Возраст организации не должен быть менее 31 дня!!! Операция прервана ");

        } catch (DateTimeParseException e) {
            if(limit <= 0) throw new DateUserException("Слишком много попыток ввода даты операция регистрации прервана!");
            System.out.println("\033[0;31m" + e.getClass().getName() + ": " +
                    "Неверный формат даты введите дату в формате год-месяц-день пример: " + LocalDate.now());
            System.out.println("\033[0m");
            date = setData(userType, limit--);
        }
        return date;
    }
}
