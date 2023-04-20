package command.comannds;

import command.CommandExistential;
import entity.user.User;
import exaption.*;
import exaption.InputException;
import holder.PostHolder;
import holder.UserHolder;
import service.UserRegistrationService;
import service.impl.UserRegistrationServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class RegisterCommand implements CommandExistential {
    private UserRegistrationService userRegistrationService;
    private PostHolder postHolder;
    private UserHolder userHolder;
    private Scanner scan;

    public RegisterCommand() {
        this.userRegistrationService = new UserRegistrationServiceImpl();
        userHolder = UserHolder.getInstance();
        postHolder = PostHolder.getPostHolderInstance();
        scan = new Scanner(System.in);
    }

    @Override
    public boolean commandActive() throws DateUserException, InputException, UserException, IOException {

        if (userHolder.getUserAuthentication() == null)
            throw new UserException("Для данного действия вы должны выйти из системы!!!");

        User user;
        user = userRegistrationService.createUser();
        System.out.println("<<<<<<<<<< Регистрация завершена >>>>>>>>>>");
        System.out.println("\033[0;33m Желаете войти в систему данным пользователем?! \033[0m");
        System.out.println("\033[0;34m Введите yes для логирования \033[0m");
        String userAuthQuestions = scan.nextLine();
        if (userAuthQuestions.equalsIgnoreCase("yes")) {
            userHolder.setUserAuthentication(user);
            postHolder.setPostUserAuth(user);
            System.out.println("\033[0;33m Вы успешно вошли в систему под логином:  \033[0m" +
                    userHolder.getUserAuthentication().getLogin());
        } else System.out.println("\033[0;31m Не распознана команда Вы не вошли в систему! \033[0m");

        return false;
    }

}
