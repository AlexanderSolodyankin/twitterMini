package service;

import entity.user.User;
import exaption.*;
import exaption.input.exception.InputUserTypeException;

import java.io.IOException;

public interface UserRegistrationService {
    User createUser() throws UserHoldException, InputPasswordException, IOException, InputLoginException, InputUserTypeException, DateRegisterException, UserException;
    User setPerson(User user) throws DateRegisterException, UserException;
    User setOrganization(User user) throws DateRegisterException, UserException;
}
