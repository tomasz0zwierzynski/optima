package pl.tomzwi.optima.service;

import pl.tomzwi.optima.entity.User;
import pl.tomzwi.optima.exception.UserAlreadyExistsException;
import pl.tomzwi.optima.exception.UserEmailAlreadyExistsException;
import pl.tomzwi.optima.exception.UserNotFoundException;

public interface UserService {

    User getByUsername( String username ) throws UserNotFoundException;

    boolean isUsernamePasswordCorrect( String username, String password );

    User registerUser( String username, String password, String email ) throws UserAlreadyExistsException, UserEmailAlreadyExistsException;

}
