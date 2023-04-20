package service;

import entity.user.User;
import exaption.UserHoldException;

public interface UserAuthenticationService {
    User authenticationUser() throws UserHoldException, InputPasswordException;
}
