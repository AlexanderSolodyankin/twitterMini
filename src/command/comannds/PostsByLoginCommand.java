package command.comannds;

import command.CommandExistential;
import exaption.*;
import service.PostService;
import service.impl.PostServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class PostsByLoginCommand implements CommandExistential {
    private Scanner scan;
    private PostService postService;

    public PostsByLoginCommand() {
        scan = new Scanner(System.in);
        postService = new PostServiceImpl();
    }
    @Override
    public boolean commandActive()
            throws UserException, DateUserException, InputException, IOException, PostException {
        System.out.println("<<<<<<<<<< Все публикации по пользователю >>>>>>>>>>");
        System.out.print("Введите логин для поиска всех публикаций: ");
        String login = scan.nextLine();
        try {
            System.out.println(postService.postByLogin(login));
        } catch (DatePostException e) {
            System.out.println("\033[0;31m" + e.getMessage());
            System.out.println("\033[0m");
        }
        System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
        return false;
    }
}
