package util;

import command.Command;
import entity.post.Post;
import service.CommandService;
import service.PostService;
import service.UserAuthService;

import java.util.List;

public class CommandUtil {
  private static CommandService service = new CommandService();
  private static PostService postService = new PostService();

    public static boolean commandAction() {
        System.out.print("Введите команду: ");
        CommandKey.nextLine();
        if (Command.EXIT.name().equals(CommandKey.getLine().toUpperCase())) return false;

        else if (Command.REGISTER.name().equals(CommandKey.getLine().toUpperCase())){
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Регистрация Нового пользователя >>>>>>>>>>>>>>>>");
            service.register();
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Регистрация Завершена >>>>>>>>>>>>>>>>");

        }else if (Command.LOGIN.name().equals(CommandKey.getLine().toUpperCase())) {
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Вход в систему >>>>>>>>>>>>>>>>");
            service.login();
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Вход в систему завершон >>>>>>>>>>>>>>>>");

        }else if (Command.LOGOUT.name().equals( CommandKey.getLine().toUpperCase())){
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Выход из системы >>>>>>>>>>>>>>>>");
            service.logout();
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Выход завершен >>>>>>>>>>>>>>>>");

        }else if (Command.INFO.name().equals( CommandKey.getLine().toUpperCase())){
            System.out.println("<<<<<<<<<<<<<<<<<<<<    Информация о пользователе >>>>>>>>>>>>>>>>");
            service.info();
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Конец Информации >>>>>>>>>>>>>>>>");

        }else if (Command.INFO_BY_LOGIN.name().equals( CommandKey.getLine().toUpperCase())){
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Информация по логину >>>>>>>>>>>>>>>>");
            service.infoByLogin();
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Конец Информации >>>>>>>>>>>>>>>>");

        }else if (Command.INFO_ALL.name().equals( CommandKey.getLine().toUpperCase())){
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Информация о всех пользователях >>>>>>>>>>>>>>>>");
            service.infoAll();
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Конец Информации >>>>>>>>>>>>>>>>");

        }else if (Command.ADD_POST.name().equals( CommandKey.getLine().toUpperCase())) {
            if(UserAuthService.getAunthUser() == null){
                System.err.println("\nВы не авторезированны для создания публекации!!!!");
            }else {
                System.out.println("<<<<<<<<<<<<<<<<<<<<  Создание новой публиации >>>>>>>>>>>>>>>>");
                service.newPost();
                System.out.println("<<<<<<<<<<<<<<<<<<<<  Публикация создана!!!  >>>>>>>>>>>>>>>>");
            }

        }else if (Command.MY_POSTS.name().equals( CommandKey.getLine().toUpperCase())){
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Мои публикации  >>>>>>>>>>>>>>>>");
            System.out.println(postService.getMyPosts());
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Конец моих публикаций  >>>>>>>>>>>>>>>>");

        }else if (Command.ALL_POSTS.name().equals( CommandKey.getLine().toUpperCase())){
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Все публикации  >>>>>>>>>>>>>>>>");
            System.out.println(postService.getAllPosts());
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Конец  публикаций  >>>>>>>>>>>>>>>>");

        }else if (Command.POSTS_BY_TAG.name().equals( CommandKey.getLine().toUpperCase())) {
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Все публикации по Тэгу  >>>>>>>>>>>>>>>>");
            List<Post> list = postService.getAllPostsByTag();
            System.out.println( list == null || list.isEmpty()?
                    "По  тэгу { " +   CommandKey.getLine() +" }публикаций не найдено" : list);
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Конец  публикаций  >>>>>>>>>>>>>>>>");

        }else if (Command.POSTS_BY_LOGIN.name().equals(CommandKey.getLine().toUpperCase())) {
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Все публикации по логину  >>>>>>>>>>>>>>>>");
            List<Post> list = postService.getAllPostsByLogin();
            System.out.println( list == null || list.isEmpty()?
                    "По  логину { " +  CommandKey.getLine() +" }публикаций не найдено" : list);
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Конец  публикаций  >>>>>>>>>>>>>>>>");
        }

        else if (Command.POSTS_BY_USER_TYPE.name().equals(CommandKey.getLine().toUpperCase())) {
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Все публикации по типу пользователя  >>>>>>>>>>>>>>>>");
            List<Post> list = postService.getAllPostsByUserType();
            System.out.println(list == null || list.isEmpty() ?
                    "По  логину { " + CommandKey.getLine() + " }публикаций не найдено" : list);
            System.out.println("<<<<<<<<<<<<<<<<<<<<  Конец  публикаций  >>>>>>>>>>>>>>>>");

        }else if (Command.HELP.name().equals(CommandKey.getLine().toUpperCase()))
            System.out.println(Command.HELP.getDescriptCommand());

        else System.out.println("Если вам нужна помощь введите help для вывода списка команд.");
        return true;
    }


}
