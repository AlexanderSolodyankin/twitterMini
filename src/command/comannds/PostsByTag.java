package command.comannds;

import command.CommandExistential;
import exaption.*;
import service.PostService;
import service.impl.PostServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class PostsByTag implements CommandExistential {
    private Scanner scan;
    private PostService postService;

    public PostsByTag() {
        scan = new Scanner(System.in);
        postService = new PostServiceImpl();
    }

    @Override
    public boolean commandActive(String command) {
        System.out.println("<<<<<<<<<< Все публикации по тэгу >>>>>>>>>>");
        System.out.print("Введите тэег для поиска: ");
        String tag = scan.nextLine();
        try {
            System.out.println(postService.postByTag(tag));
        } catch (DatePostException e) {
            System.out.println("\033[0;31m" + e.getMessage());
            System.out.println("\033[0m");
        }
        System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
        return false;
    }
}
