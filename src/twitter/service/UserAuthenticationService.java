package twitter.service;

import twitter.entity.user.User;
import twitter.exaption.InputException;
import twitter.exaption.UserHoldException;

public interface UserAuthenticationService {
    User authenticationUser() throws UserHoldException, InputException;
}
