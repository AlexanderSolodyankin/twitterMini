package service;


import entity.post.Post;
import entity.user.Organization;
import entity.user.Person;
import entity.user.User;
import holder.UserHolder;
import util.CommandKey;

import java.time.LocalDate;

public class CommandService {
    UserAuthService authService = new UserAuthService();
    PostService postService = new PostService();


    public static void register(){

        User user =  UserRegistration.newUser();

        if (UserAuthService.getAunthUser() == null) {
            while (true) {
                System.out.println("Желаете зайти этим пользователем в систему? " +
                        "Нажмите <да> что бы согласится или <нет> чтобы отказатся");
                CommandKey.nextLine();
                if(CommandKey.getLine().equalsIgnoreCase("ДА")){
                    UserAuthService.setAunthUser(user);
                    break;
                }else if (CommandKey.getLine().equalsIgnoreCase("НЕТ")){
                    System.out.println("Вы выбрали остатся на данном акаунте");
                    break;
                }else System.err.println("Неверный ввод");
            }
        }
    }

    public  void login(){
        if(UserAuthService.getAunthUser() == null) {
            authService.authenticationUser();
            System.out.printf("<<<<<< Добро пожалывать [%s] %s %s! >>>> ",
                    UserAuthService.getAunthUser().getUserType(),
                    UserAuthService.getAunthUser() instanceof Person?
                            ((Person) UserAuthService.getAunthUser()).getName()
                            : ((Organization)UserAuthService.getAunthUser()).getName(),
                    UserAuthService.getAunthUser() instanceof Person?
                            ((Person)UserAuthService.getAunthUser()).getSerName()
                            : ((Organization)UserAuthService.getAunthUser()).getOccupation());
        }else System.err.println("Что бы зайти в систему вам нужно выйти из акаунта!");
    }

    public  void logout(){
        System.out.println("Вы точно хотите выйти из системы? Введите <Да> что бы подтвердить свои действия");
        CommandKey.nextLine();
        if(CommandKey.getLine().equalsIgnoreCase("ДА")){
            System.out.printf("До свидания: [%s] %s %s", UserAuthService.getAunthUser().getUserType().name(),
                    UserAuthService.getAunthUser() instanceof Person ?
                            ((Person)UserAuthService.getAunthUser()).getName()
                            : ((Organization) UserAuthService.getAunthUser()).getName(),
                    UserAuthService.getAunthUser() instanceof Person ?
                            ((Person) UserAuthService.getAunthUser()).getSerName()
                            : ((Organization) UserAuthService.getAunthUser()).getOccupation() );
            UserAuthService.setAunthUser(null);

        }else System.out.println("Вы не подтверили выход из системы!!!");
    }

    public  void info(){
        System.out.println(authService.getAunthUser() == null?
                "Вы не авторезированы!!! " : authService.getAunthUser());
    }

    public  void infoByLogin(){
        System.out.println("Введите логин пользователя для получения информации: ");
        CommandKey.nextLine();
        User user = UserHolder.getLogin(CommandKey.getLine());
        if (user != null){
            System.out.println(user);
        }else System.out.println("Пользователь под логином: " + CommandKey.getLine() + " не найден");
    }

    public  void infoAll(){
        for (User element : UserHolder.getAll()){
            System.out.println(element);
        }
    }

    public void newPost(){
        Post newPosts = new Post();
        newPosts.setAuthor(UserAuthService.getAunthUser());
        System.out.println("Введите тему публикации");
        CommandKey.nextLine();
        newPosts.setTheme(CommandKey.getLine());
        System.out.println("Введите текст публикации");
        CommandKey.nextLine();
        newPosts.setPostsText(CommandKey.getLine());
        System.out.println("Введите теги публикации: (Поле межет быть пустым или тэгов может быть много)");
        System.out.println("Для разделения тэгов используйтя ',' ");
        CommandKey.nextLine();
        newPosts.setTag(postService.newTags(CommandKey.getLine()));
        newPosts.setPostsDate(LocalDate.now());

       newPosts =  postService.addPosts(newPosts);

        System.out.println("Создана новая публикация " );
        System.out.printf("Публикация { \n" +
                        " Автор: [%s] %s %s \n" +
                        "Создано: %s \n" +
                        "Тема: %s \n" +
                        "Текст: %s \n" +
                        "Тэги: %s \n" +
                        "}",
                newPosts.getAuthor().getUserType(), newPosts.userNameToString(), newPosts.userSerNameToString(),
                newPosts.getPostsDate(), newPosts.getTheme(), newPosts.getPostsText(), newPosts.tagsToString());
    }

}
