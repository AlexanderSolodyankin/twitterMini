import command.CommandExistential;
import command.CommandFactory;
import exaption.*;
import exaption.InputException;
import holder.PostHolder;
import holder.UserHolder;
import service.FileService;
import service.PostService;
import service.UserAuthenticationService;
import service.UserRegistrationService;
import service.impl.FileServiceImpl;
import service.impl.PostServiceImpl;
import service.impl.UserAuthenticationServiceImpl;
import service.impl.UserRegistrationServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class Twitter {

    private UserRegistrationService userRegistrationService;
    private UserAuthenticationService authenticationService;
    private PostService postService;
    private UserHolder userHolder;
    private PostHolder postHolder;
    private FileService fileService;


    public Twitter() {
        fileService = new FileServiceImpl();
        userHolder = UserHolder.getInstance();

        new Thread() {
            @Override
            public void run() {
                threadCreateUserFileData();
            }
        }.start();

        postHolder = PostHolder.getPostHolderInstance();

        new Thread() {
            @Override
            public void run() {
                threadCreatePostsFileData();
            }
        }.start();

        userRegistrationService = new UserRegistrationServiceImpl();
        authenticationService = new UserAuthenticationServiceImpl();
        postService = new PostServiceImpl();
    }


    public void start() {
        Scanner in = new Scanner(System.in);
        String commandIn;

        while (true) {
            System.out.print("Введите команду: ");
            commandIn = in.nextLine();
            CommandExistential commandExistential;
            try {
                commandExistential = CommandFactory.getCommand(commandIn);
                if (commandExistential != null) {
                    if (commandExistential.commandActive(commandIn)) return;
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("\033[0;31m  Неверная команда!!! Введите команду help что бы увидить список команд \033[0m");
            } catch (UserException | DateUserException | InputException | IOException | PostException |
                     UserHoldException e) {
                System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                System.out.println("\033[0m");
            }
        }
    }

    private void threadCreateUserFileData() {
        try {
            userHolder.setUserList(fileService.getAllUsersFromFile());
        } catch (IOException e) {
            try {
                if (fileService.createFile("UserData.txt"))
                    System.out.println("\033[0;32m Был создан файл для хранения Польpователей! \033[0m");
            } catch (IOException ex) {
                System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                System.out.println("\033[0m");
            }
        }
    }

    private void threadCreatePostsFileData() { // Попытка пихнуть поток вынес в меод что бы не громаздить код
        try {
            userHolder.setUserList(fileService.getAllUsersFromFile());
            postHolder.setPostAllHolder(fileService.getAllPostsFromFile(userHolder.getUserList()));
        } catch (IOException e) {
            try {
                if (fileService.createFile("PostData.txt"))
                    System.out.println("\033[0;32m Был создан файл для хранения Публикаций! \033[0m");
            } catch (IOException ex) {
                System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                System.out.println("\033[0m");
            }
        } catch (FileDataHolderException e) {
            System.out.println(userHolder.getAllUsers());
            throw new RuntimeException(e);
        }
    }


}
