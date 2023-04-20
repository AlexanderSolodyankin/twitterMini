package command.comannds;

import command.CommandExistential;
import entity.user.UserType;
import exaption.DateUserException;
import exaption.InputException;
import exaption.PostException;
import exaption.UserException;
import service.PostService;
import service.impl.PostServiceImpl;

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
