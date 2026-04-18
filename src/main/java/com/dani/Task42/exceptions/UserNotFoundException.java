package com.dani.Task42.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User with specific id not found!")
public class UserNotFoundException extends RuntimeException{

}
