package twitter.command;

import twitter.exaption.*;
import twitter.exaption.InputException;

import java.io.IOException;

public interface CommandExistential {
    boolean commandActive()
            throws UserException, DateUserException, InputException, IOException, PostException, UserHoldException;
}
