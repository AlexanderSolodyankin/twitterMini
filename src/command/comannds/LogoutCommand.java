package command.comannds;

import command.CommandExistential;
import entity.user.Organization;
import entity.user.Person;
import holder.PostHolder;
import holder.UserHolder;

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
