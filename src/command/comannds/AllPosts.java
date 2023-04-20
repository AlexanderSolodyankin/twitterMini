package command.comannds;

import command.CommandExistential;
import exaption.DateUserException;
import exaption.InputException;
import exaption.PostException;
import exaption.UserException;
import service.PostService;
import service.impl.PostServiceImpl;

import java.io.IOException;

public class AllPosts implements CommandExistential {
   private PostService postService;

    public AllPosts() {
        postService = new PostServiceImpl();
    }

    @Override
    public boolean commandActive(String command)
            throws UserException, DateUserException, InputException, IOException, PostException {
        System.out.println("<<<<<<<<<< Все публикации  >>>>>>>>>>");
        System.out.println(postService.allPosts() == null? "В системе нет Публикаций!!!":
                 postService.allPosts() );
        System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
        return false;
    }
}
