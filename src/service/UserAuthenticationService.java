package service;

import entity.user.User;
import exaption.InputException;
import exaption.UserHoldException;

public interface UserAuthenticationService {
    User authenticationUser() throws UserHoldException, InputException;
}
