package pl.tomzwi.optima.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomzwi.optima.security.configuration.ErrorEntityPreparator;
import pl.tomzwi.optima.security.exception.UserAlreadyExistsException;
import pl.tomzwi.optima.security.exception.UserEmailAlreadyExistsException;
import pl.tomzwi.optima.security.model.ErrorResponse;
import pl.tomzwi.optima.security.model.UserBody;
import pl.tomzwi.optima.security.service.UserService;

@RestController
@RequestMapping( "/${security.endpoint.users.prefix}" )
public class UserController {

    @Autowired
    private ErrorEntityPreparator errorEntity;

    @Autowired
    private UserService userService;

    @PostMapping( "/register" )
    public ResponseEntity<Object> register(@RequestBody UserBody user) {
        userService.registerUser( user.getUsername(), user.getPassword(), user.getEmail() );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @ExceptionHandler( { UserAlreadyExistsException.class, UserEmailAlreadyExistsException.class } )
    public ResponseEntity<ErrorResponse> handleRegisterExceptions( Exception ex ) {
        return errorEntity.prepare( HttpStatus.FORBIDDEN, ex );
    }

}
