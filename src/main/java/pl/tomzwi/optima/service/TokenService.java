package pl.tomzwi.optima.service;

import pl.tomzwi.optima.entity.Token;
import pl.tomzwi.optima.exception.InvalidUsernamePasswordException;
import pl.tomzwi.optima.exception.TokenNotFoundException;

public interface TokenService {

    Token getToken(String username, String password ) throws InvalidUsernamePasswordException;

    Token getToken(String token) throws TokenNotFoundException;

    void purge();

}
