package command.comannds;

import command.CommandExistential;
import entity.user.Organization;
import entity.user.Person;
import exaption.InputException;
import exaption.UserHoldException;
import holder.PostHolder;
import holder.UserHolder;
import service.UserAuthenticationService;
import service.impl.UserAuthenticationServiceImpl;

public class LoginCommand implements CommandExistential {
    private PostHolder postHolder;
    private UserAuthenticationService authenticationService;
    private UserHolder userHolder;

    public LoginCommand() {
        postHolder = PostHolder.getPostHolderInstance();
        userHolder = UserHolder.getInstance();
        authenticationService = new UserAuthenticationServiceImpl();
    }

    @Override
    public boolean commandActive(String command) throws UserHoldException, InputException {

            userHolder.setUserAuthentication(authenticationService.authenticationUser());
            postHolder.setPostUserAuth(userHolder.getUserAuthentication());
            System.out.printf("<<<<<<<<<< Добро пожалывать, [%s] %s! >>>>>>>>>> \n",
                    userHolder.getUserAuthentication().getUserType(), userHolder.getUserAuthentication() instanceof Person ?
                            ((Person) userHolder.getUserAuthentication()).getName() + " "
                                    + ((Person) userHolder.getUserAuthentication()).getSerName() :
                            ((Organization) userHolder.getUserAuthentication()).getName());

        return false;
    }
}
