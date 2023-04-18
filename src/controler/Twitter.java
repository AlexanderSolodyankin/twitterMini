package controler;

import command.Command;
import entity.post.Post;
import entity.user.Organization;
import entity.user.Person;
import entity.user.User;
import entity.user.UserType;
import exaption.*;
import holder.PostHolder;
import holder.UserHolder;
import service.FileService;
import service.PostService;
import service.UserAuthenticationService;
import service.UserRegistrationService;
import service.impl.FileServiceImpl;
import service.impl.PostServiceImpl;
import service.impl.UserAuthenticationServiceImpl;
import service.impl.UserRegistrationServiceImpl;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Twitter {
    private UserRegistrationService userRegistrationService;
    private UserAuthenticationService authenticationService;
    private PostService postService;
    private UserHolder userHolder;
    private User userAuthentication;
    private PostHolder postHolder;
    private FileService fileService;

    public Twitter() {
        fileService = new FileServiceImpl();
        userHolder = new UserHolder();

        new Thread() {
            @Override
            public void run() {
                threadCreateUserFileData();
            }
        }.start();

        postHolder = new PostHolder();

        new Thread() {
            @Override
            public void run() {
                threadCreatePostsFileData();
            }
        }.start();

        userRegistrationService = new UserRegistrationServiceImpl(userHolder);
        authenticationService = new UserAuthenticationServiceImpl(userHolder);
        postService = new PostServiceImpl(postHolder);
        userAuthentication = null;
        start();
    }

    public void start() {
        Scanner scan = new Scanner(System.in);
        String line;
        do {
            System.out.print("Введите команду: ");
            line = scan.nextLine();
            if (line == null || line.isEmpty()) line = scan.nextLine(); // Кастыль от пропуска сканера

            try {
                Command.valueOf(line.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("\033[0;31m" + " Неверная команда!!! Введите команду help что бы увидить список команд \033[0m");
            }

            if (line.equals(Command.REGISTER.name().toLowerCase())) {
                try {
                    isPresence();
                    User user;
                    user = userRegistrationService.createUser();
                    System.out.println("<<<<<<<<<< Регистрация завершена >>>>>>>>>>");
                    System.out.println("\033[0;33m Желаете войти в систему данным пользователем?! \033[0m");
                    System.out.println("\033[0;34m Введите yes для логирования \033[0m");
                    String userAuthQuestions = scan.nextLine();
                    if (userAuthQuestions.equalsIgnoreCase("yes")) {
                        userAuthentication = user;
                        postHolder.setPostUserAuth(user);
                        System.out.println("\033[0;33m Вы успешно вошли в систему под логином:  \033[0m" +
                                userAuthentication.getLogin());
                    } else System.out.println("\033[0;31m Не распознана команда Вы не вошли в систему! \033[0m");
                } catch (IOException | UserHoldException | InputPasswordException |
                         InputUserTypeException | InputLoginException e) {
                    System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("\033[0m");
                } catch (UserException e) {
                    System.out.println("\033[0;31m Для регистрации нужно выйти из Авторизации!!! Операция прервана \033[0m");
                } catch (Exception e) {
                    System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("\033[0m");
                }
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.LOGIN.name().toLowerCase())) {
                try {
                    isPresence();
                    userAuthentication = authenticationService.authenticationUser();
                    postHolder.setPostUserAuth(userAuthentication);
                    System.out.printf("<<<<<<<<<< Добро пожалывать, [%s] %s! >>>>>>>>>> \n",
                            userAuthentication.getUserType(), userAuthentication instanceof Person ?
                                    ((Person) userAuthentication).getName() + " " + ((Person) userAuthentication).getSerName() :
                                    ((Organization) userAuthentication).getName());
                } catch (UserException | InputPasswordException | UserHoldException e) {
                    System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("\033[0m");
                }
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.LOGOUT.name().toLowerCase())) {
                try {
                    isAuthUser();
                    System.out.println("<<<<<<<<<< Выход из сисетмы >>>>>>>>>>");
                    System.out.printf("До свидания: [%s] %s  \n",
                            userAuthentication.getUserType(), userAuthentication instanceof Person ?
                                    ((Person) userAuthentication).getName() + " " + ((Person) userAuthentication).getSerName() :
                                    ((Organization) userAuthentication).getName());
                    userAuthentication = null;
                    postHolder.setPostAuthUserHolder(null);
                } catch (UserException e) {
                    throw new RuntimeException(e);
                }
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.INFO.name().toLowerCase())) {
                System.out.println("<<<<<<<<<< Информация о пользователе >>>>>>>>>>");
                try {
                    isAuthUser();
                    System.out.println(userAuthentication);
                    System.out.println("<<<<<<<<<< Конец Информации >>>>>>>>>>");
                } catch (UserException e) {
                    System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("\033[0m");
                }
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.INFO_BY_LOGIN.name().toLowerCase())) {
                System.out.println("<<<<<<<<<< Информация о пользователе по логину >>>>>>>>>>");
                System.out.println("Введите логин пользователя о котором хотите узнать ");
                String login = scan.nextLine();
                User user = userHolder.getUserByLogin(login);
                if (user != null) {
                    System.out.println("Найден пользователь");
                    System.out.println(user);
                } else System.out.println("Пользователь под логином " + login + " не найден!");
                System.out.println("<<<<<<<<<< Конец Информации >>>>>>>>>>");
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.INFO_ALL.name().toLowerCase())) {
                System.out.println("<<<<<<<<<< Информация о всех пользователях >>>>>>>>>>");
                List<User> usersListPrint = userHolder.getAllUsers();
                if (usersListPrint == null) {
                    System.err.println("Хранилище Пользователей пусто (Отрефактить)");
                } else usersListPrint.forEach(System.out::println);
                System.out.println("<<<<<<<<<< Конец Информации >>>>>>>>>>");
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.ADD_POST.name().toLowerCase())) {
                try {
                    isAuthUser();
                    try {
                        System.out.println("Созданна публикация " + postService.createPost(userAuthentication));
                    } catch (IOException e) {
                        System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                        System.out.println("\033[0m");
                    } catch (PostException e) {
                        System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                        System.out.println("\033[0m");
                    }
                    System.out.println("<<<<<<<<<< Конец создания публикации >>>>>>>>>>");
                } catch (UserException e) {
                    System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("\033[0m");
                }
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.MY_POSTS.name().toLowerCase())) {
                try {
                    isAuthUser();
                    System.out.println("<<<<<<<<<< Все публикации Пользователя >>>>>>>>>>");
                    System.out.println(postService.myPosts(userAuthentication));
                    System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
                } catch (UserException e) {
                    System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("\033[0m");
                }
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.ALL_POSTS.name().toLowerCase())) {
                System.out.println("<<<<<<<<<< Все публикации  >>>>>>>>>>");
                System.out.println(postService.allPosts());
                System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.POSTS_BY_TAG.name().toLowerCase())) {
                System.out.println("<<<<<<<<<< Все публикации по тэгу >>>>>>>>>>");
                System.out.print("Введите тэег для поиска: ");
                String tag = scan.nextLine();
                System.out.println(postService.postByTag(tag));
                System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.POST_BY_LOGIN.name().toLowerCase())) {
                System.out.println("<<<<<<<<<< Все публикации по пользователю >>>>>>>>>>");
                System.out.print("Введите логин для поиска всех публикаций: ");
                String login = scan.nextLine();
                System.out.println(postService.postByLogin(login));
                System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.POSTS_BY_USER_TYPE.name().toLowerCase())) {
                System.out.println("<<<<<<<<<< Все публикации по типу пользователя >>>>>>>>>>");
                System.out.println("Введите тип пользователя для поиска публикаций. 0 - человек, 1 - организация");
                try {
                    postService.postByUserType(UserType.values()[scan.nextInt()]).forEach(System.out::println);
                    System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("\033[0;31m InputUserTypeException: Введенно неверное значение. Операция прервана!  \033[0m");
                }
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (line.equals(Command.HELP.name().toLowerCase())) {
                System.out.println("1.\texit — Выход из программы.\n" +
                        "2.\tregister — Регистрация нового пользователя в системе\n" +
                        "3.\tlogin — Вход в систему под определенным пользователем.\n" +
                        "4.\tlogout — Выход из авторизации определенного пользователя.\n" +
                        "5.\tinfo — Вывод информации об авторизированном пользователе.\n" +
                        "6.\tinfo_by_login — Вывод информации о пользователе по его логину.\n" +
                        "7.\t info_all — Вывод информации о всех пользователях системы.\n" +
                        "8.\tadd_post — Добавить новую публикацию от текущего пользователя.\n" +
                        "9.\tmy_posts -  Вывод всех публикаций текущего пользователя.\n" +
                        "10.\tall_posts — Вывод всех публикаций в системе.\n" +
                        "11.\tposts_by_tag — Вывод публикаций по тэгу.\n" +
                        "12.\tposts_by_login — Вывод публикаций по логину пользователя.\n" +
                        "13.\tposts_by_user_type — Вывод публикаций по типу пользователя.\n"
                );
            }
        } while (!Command.EXIT.name().equalsIgnoreCase(line));
    }

    private void threadCreateUserFileData() { // Попытка пихнуть поток вынес в меод что бы не громаздить код
        try {
            userHolder.setUserList(fileService.getAllUsersFromFile());
        } catch (IOException e) {
            try {
                if (fileService.createFile("UserData.txt"))
                    System.out.println("\033[0;32m Был создан файл для хранения Польpователей! \033[0m");
            } catch (IOException ex) {
                System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                System.out.println("\033[0m");
            }
        }
    }

    private void threadCreatePostsFileData() { // Попытка пихнуть поток вынес в меод что бы не громаздить код
        try {
            userHolder.setUserList(fileService.getAllUsersFromFile());
            postHolder.setPostAllHolder(fileService.getAllPostsFromFile(userHolder.getUserList()));
        } catch (IOException e) {
            try {
                if (fileService.createFile("PostData.txt"))
                    System.out.println("\033[0;32m Был создан файл для хранения Публикаций! \033[0m");
            } catch (IOException ex) {
                System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                System.out.println("\033[0m");
            }
        } catch (FileDataHolderException e) {
            System.out.println(userHolder.getAllUsers());
            throw new RuntimeException(e);
        }
    }

    private void isAuthUser() throws UserException {
        if (userAuthentication == null)
            throw new UserException("Для данного действия нужно войти в систему!!!");
    }
    private void isPresence() throws UserException {
        if (userAuthentication != null)
            throw new UserException("Для данного действия нужно выйти из акаунта!!! Операция прервана!");
    }

}

