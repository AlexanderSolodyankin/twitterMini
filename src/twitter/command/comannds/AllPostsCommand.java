package twitter.command.comannds;

import twitter.command.CommandExistential;
import twitter.exaption.DateUserException;
import twitter.exaption.InputException;
import twitter.exaption.PostException;
import twitter.exaption.UserException;
import twitter.service.PostService;
import twitter.service.impl.PostServiceImpl;

import java.io.IOException;

public class AllPostsCommand implements CommandExistential {
   private PostService postService;

    public AllPostsCommand() {
        postService = new PostServiceImpl();
    }

    @Override
    public boolean commandActive()
            throws UserException, DateUserException, InputException, IOException, PostException {
        System.out.println("<<<<<<<<<< Все публикации  >>>>>>>>>>");
        System.out.println(postService.allPosts() == null? "В системе нет Публикаций!!!":
                 postService.allPosts() );
        System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
        return false;
    }
}
