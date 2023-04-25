package twitter;

import twitter.command.CommandExistential;
import twitter.command.CommandFactory;
import twitter.exaption.*;
import twitter.exaption.InputException;
import twitter.holder.PostHolder;
import twitter.holder.UserHolder;
import twitter.service.FileService;
import twitter.service.PostService;
import twitter.service.UserAuthenticationService;
import twitter.service.UserRegistrationService;
import twitter.service.impl.FileServiceImpl;
import twitter.service.impl.PostServiceImpl;
import twitter.service.impl.UserAuthenticationServiceImpl;
import twitter.service.impl.UserRegistrationServiceImpl;

import java.io.IOException;

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

        String commandIn;
        CommandExistential commandExistential;

        while (true) {
            commandExistential = CommandFactory.getCommand();


            boolean exist = false;
            try {
                exist = commandExistential.commandActive();
            } catch (UserException | DateUserException | InputException | IOException | PostException |
                     UserHoldException e) {
                System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                System.out.println("\033[0m");
            } catch (NullPointerException e) {
                System.out.println("\033[0;31m  Неверная команда!!! Введите команду help что бы увидить список команд \033[0m");
            }
            if (exist) break;
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

    private void threadCreatePostsFileData() {
        try {
            userHolder.setUserList(fileService.getAllUsersFromFile());
            postHolder.setPostAllHolder(fileService.getAllPostsFromFile(userHolder.getUserList()));
        } catch (IOException | FileDataHolderException e) {
            try {
                if (fileService.createFile("PostData.txt"))
                    System.out.println("\033[0;32m Был создан файл для хранения Публикаций! \033[0m");
            } catch (IOException ex) {
                System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                System.out.println("\033[0m");
            }
        }
    }
}
