package twitter.service;

import twitter.entity.user.User;
import twitter.exaption.*;
import twitter.exaption.InputException;

import java.io.IOException;

public interface UserRegistrationService {
    User createUser() throws InputException, DateUserException, UserException, IOException;
    User setPerson(User user) throws DateUserException, UserException;
    User setOrganization(User user) throws DateUserException, UserException;
}
