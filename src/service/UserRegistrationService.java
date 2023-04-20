package service;

import entity.user.User;
import exaption.*;
import exaption.InputException;

import java.io.IOException;

public interface UserRegistrationService {
    User createUser() throws InputException, DateUserException, UserException, IOException;
    User setPerson(User user) throws DateUserException, UserException;
    User setOrganization(User user) throws DateUserException, UserException;
}
