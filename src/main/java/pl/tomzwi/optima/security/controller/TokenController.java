package pl.tomzwi.optima.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomzwi.optima.security.configuration.CurrentlyLoggedUser;
import pl.tomzwi.optima.security.configuration.ErrorEntityPreparator;
import pl.tomzwi.optima.security.entity.Token;
import pl.tomzwi.optima.security.entity.User;
import pl.tomzwi.optima.security.exception.InvalidUsernamePasswordException;
import pl.tomzwi.optima.security.model.ErrorResponse;
import pl.tomzwi.optima.security.model.TokenResponse;
import pl.tomzwi.optima.security.model.UserResponse;
import pl.tomzwi.optima.security.service.TokenService;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping( "/${security.endpoint.token.prefix}" )
public class TokenController {

    @Autowired
    private ErrorEntityPreparator errorEntity;

    @Autowired
    private TokenService tokenService;

    @PostMapping( "/token" )
    public ResponseEntity<TokenResponse> token(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        Token token = tokenService.getToken(username, password);
        TokenResponse response = new TokenResponse(
                token.getToken(),
                DateTimeFormatter.ISO_DATE_TIME.format(token.getExpires()) );
        return new ResponseEntity<>( response, HttpStatus.OK );
    }

    @GetMapping( "/current" )
    public ResponseEntity<UserResponse> current(@CurrentlyLoggedUser User user) {
        UserResponse userBody = new UserResponse(user.getUsername(), user.getEmail());

        return new ResponseEntity<>( userBody, HttpStatus.OK );
    }

    @ExceptionHandler( { InvalidUsernamePasswordException.class } )
    public ResponseEntity<ErrorResponse> handleInvalidUsernamePasswordException( Exception ex ) {
        return errorEntity.prepare( HttpStatus.BAD_REQUEST, ex );
    }

}
