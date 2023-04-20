package command;

import command.comannds.*;

public enum Command {
    EXIT(new ExitCommand()),
    REGISTER(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    INFO(new InfoCommand()),
    INFO_BY_LOGIN(new InfoByLoginCommand()),
    INFO_ALL(new InfoAll()),
    ADD_POST(new AddPost()),
    MY_POSTS(new MyPosts()),
    ALL_POSTS(new AllPosts()),
    POSTS_BY_TAG(new PostsByTag()),
    POST_BY_LOGIN(new PostsByLogin()),
    POSTS_BY_USER_TYPE(new PostsByUserType()),
    HELP(new HelpCommand());

    CommandExistential command;

    Command(CommandExistential command) {
        this.command = command;
    }

    public CommandExistential getCommand() {
        return command;
    }
}
