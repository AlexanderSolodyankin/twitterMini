package twitter.command.comannds;

import twitter.command.CommandExistential;
import twitter.exaption.DateUserException;
import twitter.exaption.PostException;
import twitter.exaption.UserException;
import twitter.exaption.InputException;
import twitter.holder.UserHolder;
import twitter.service.PostService;
import twitter.service.impl.PostServiceImpl;

import java.io.IOException;

public class AddPostCommand implements CommandExistential {
    private PostService postService;
    private UserHolder userHolder;

    public AddPostCommand() {
        postService = new PostServiceImpl();
        userHolder = UserHolder.getInstance();
    }

    @Override
    public boolean commandActive()
            throws UserException, DateUserException, InputException, IOException, PostException {
        System.out.println("<<<<<<<<<<< Создание новой публикации >>>>>>>>>>");
        System.out.println("Созданна публикация " + postService.createPost(userHolder.getUserAuthentication()));
        System.out.println("<<<<<<<<<< Конец создания публикации >>>>>>>>>>");

        return false;
    }
}
