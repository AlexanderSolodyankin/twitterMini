package command.comannds;

import command.CommandExistential;
import exaption.DateUserException;
import exaption.PostException;
import exaption.UserException;
import exaption.InputException;
import holder.UserHolder;
import service.PostService;
import service.impl.PostServiceImpl;

import java.io.IOException;

public class AddPost implements CommandExistential {
    private PostService postService;
    private UserHolder userHolder;

    public AddPost() {
        postService = new PostServiceImpl();
        userHolder = UserHolder.getInstance();
    }

    @Override
    public boolean commandActive(String command)
            throws UserException, DateUserException, InputException, IOException, PostException {
        System.out.println("<<<<<<<<<<< Создание новой публикации >>>>>>>>>>");
        System.out.println("Созданна публикация " + postService.createPost(userHolder.getUserAuthentication()));
        System.out.println("<<<<<<<<<< Конец создания публикации >>>>>>>>>>");

        return false;
    }
}
