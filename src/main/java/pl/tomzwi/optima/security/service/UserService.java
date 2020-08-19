package pl.tomzwi.optima.security.service;

import pl.tomzwi.optima.security.entity.User;
import pl.tomzwi.optima.security.exception.UserAlreadyExistsException;
import pl.tomzwi.optima.security.exception.UserEmailAlreadyExistsException;
import pl.tomzwi.optima.security.exception.UserNotFoundException;

public interface UserService {

    User getByUsername( String username ) throws UserNotFoundException;

    boolean isUsernamePasswordCorrect( String username, String password );

    User registerUser( String username, String password, String email ) throws UserAlreadyExistsException, UserEmailAlreadyExistsException;

}
