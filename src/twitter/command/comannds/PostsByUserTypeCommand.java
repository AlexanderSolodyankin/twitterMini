package twitter.command.comannds;

import twitter.command.CommandExistential;
import twitter.entity.user.UserType;
import twitter.exaption.DateUserException;
import twitter.exaption.InputException;
import twitter.exaption.PostException;
import twitter.exaption.UserException;
import twitter.service.PostService;
import twitter.service.impl.PostServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class PostsByUserTypeCommand implements CommandExistential {
    private Scanner scan;
    private PostService postService;

    public PostsByUserTypeCommand() {
        scan = new Scanner(System.in);
        postService = new PostServiceImpl();
    }
    @Override
    public boolean commandActive()
            throws UserException, DateUserException, InputException, IOException, PostException {
        System.out.println("<<<<<<<<<< Все публикации по типу пользователя >>>>>>>>>>");
        System.out.println("Введите тип пользователя для поиска публикаций. 0 - человек, 1 - организация");
        try {
            postService
                    .postByUserType(
                            UserType.values()[scan.nextInt()]
                    ).forEach(System.out::println);
            System.out.println("<<<<<<<<<< Конец вывода публикации >>>>>>>>>>");
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new InputException("Введенно неверное значение. Операция прервана!");
        }
        return false;
    }
}
