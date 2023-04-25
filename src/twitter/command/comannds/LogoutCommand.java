package twitter.command.comannds;

import twitter.command.CommandExistential;
import twitter.entity.user.Organization;
import twitter.entity.user.Person;
import twitter.holder.PostHolder;
import twitter.holder.UserHolder;

public class LogoutCommand implements CommandExistential {
    private UserHolder userHolder;
    private PostHolder postHolder;

    public LogoutCommand() {
        this.userHolder = UserHolder.getInstance();
        this.postHolder = PostHolder.getPostHolderInstance();
    }

    @Override
    public boolean commandActive() {
        System.out.println("<<<<<<<<<< Выход из сисетмы >>>>>>>>>>");
        System.out.printf("До свидания: [%s] %s  \n",
                userHolder.getUserAuthentication().getUserType(), userHolder.getUserAuthentication() instanceof Person ?
                        ((Person) userHolder.getUserAuthentication()).getName() + " "
                                + ((Person) userHolder.getUserAuthentication()).getSerName() :
                        ((Organization) userHolder.getUserAuthentication()).getName());
        userHolder.setUserAuthentication(null);
        postHolder.setPostAuthUserHolder(null);
        return false;
    }
}
