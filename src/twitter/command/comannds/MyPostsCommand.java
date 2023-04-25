package twitter.command.comannds;

import twitter.command.CommandExistential;
import twitter.exaption.DateUserException;
import twitter.exaption.InputException;
import twitter.exaption.PostException;
import twitter.exaption.UserException;
import twitter.holder.UserHolder;
import twitter.service.PostService;
import twitter.service.impl.PostServiceImpl;

import java.io.IOException;

public class MyPostsCommand implements CommandExistential {
    private PostService postService;
    private UserHolder userHolder;

    public MyPostsCommand() {
        postService = new PostServiceImpl();
        userHolder = UserHolder.getInstance();
    }

    @Override
    public boolean commandActive()
            throws UserException, DateUserException, InputException, IOException, PostException {
        if (userHolder.getUserAuthentication() == null)
            throw new UserException("Вы должны войти в систему для данного действия!!!");

        System.out.println("<<<<<<<<<< Все публикации Пользователя >>>>>>>>>>");
        System.out.println(postService.myPosts(userHolder.getUserAuthentication()) == null ? "У вас нет буликаций!!!" :
                postService.myPosts(userHolder.getUserAuthentication()));
        System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
        return false;
    }
}
