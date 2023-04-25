package twitter.command.comannds;

import twitter.command.CommandExistential;
import twitter.exaption.*;
import twitter.service.PostService;
import twitter.service.impl.PostServiceImpl;

import java.util.Scanner;

public class PostsByTagCommand implements CommandExistential {
    private Scanner scan;
    private PostService postService;

    public PostsByTagCommand() {
        scan = new Scanner(System.in);
        postService = new PostServiceImpl();
    }

    @Override
    public boolean commandActive() {
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
