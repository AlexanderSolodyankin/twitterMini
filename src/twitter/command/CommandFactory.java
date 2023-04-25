package twitter.command;

import twitter.command.comannds.*;

import java.util.Scanner;

public class CommandFactory {
   private static Scanner in = new Scanner(System.in);

    public static CommandExistential getCommand(){
        System.out.print("Введите команду: ");
        String commandIn = in.nextLine();
        if (commandIn.equals("exit")) return new ExitCommand();
        if (commandIn.equals("help")) return new HelpCommand();
        if (commandIn.equals("register")) return new RegisterCommand();
        if (commandIn.equals("login")) return new LoginCommand();
        if (commandIn.equals("logout")) return new LogoutCommand();
        if (commandIn.equals("info")) return new InfoCommand();
        if (commandIn.equals("info_all")) return new InfoAllCommand();
        if (commandIn.equals("info_by_login")) return new InfoByLoginCommand();
        if (commandIn.equals("add_post")) return new AddPostCommand();
        if (commandIn.equals("all_posts")) return new AllPostsCommand();
        if (commandIn.equals("my_posts")) return new MyPostsCommand();
        if (commandIn.equals("posts_by_login")) return new PostsByLoginCommand();
        if (commandIn.equals("posts_by_type")) return new PostsByUserTypeCommand();

        return null;
    }
}
