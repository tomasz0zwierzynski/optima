package pl.tomzwi.optima.security.service;

import pl.tomzwi.optima.security.entity.Token;
import pl.tomzwi.optima.security.exception.InvalidUsernamePasswordException;
import pl.tomzwi.optima.security.exception.TokenNotFoundException;

public interface TokenService {

    Token getToken(String username, String password ) throws InvalidUsernamePasswordException;

    Token getToken(String token) throws TokenNotFoundException;

    void purge();

}
