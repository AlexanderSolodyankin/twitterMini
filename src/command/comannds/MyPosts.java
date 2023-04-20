package command.comannds;

import command.CommandExistential;
import exaption.DateUserException;
import exaption.InputException;
import exaption.PostException;
import exaption.UserException;
import holder.UserHolder;
import service.PostService;
import service.impl.PostServiceImpl;

import java.io.IOException;

public class MyPosts implements CommandExistential {
    private PostService postService;
    private UserHolder userHolder;

    public MyPosts() {
        postService = new PostServiceImpl();
        userHolder = UserHolder.getInstance();
    }

    @Override
    public boolean commandActive(String command)
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
