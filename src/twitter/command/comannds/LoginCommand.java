package twitter.command.comannds;

import twitter.command.CommandExistential;
import twitter.entity.user.Organization;
import twitter.entity.user.Person;
import twitter.exaption.InputException;
import twitter.exaption.UserHoldException;
import twitter.holder.PostHolder;
import twitter.holder.UserHolder;
import twitter.service.UserAuthenticationService;
import twitter.service.impl.UserAuthenticationServiceImpl;

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
    public boolean commandActive() throws UserHoldException, InputException {

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
