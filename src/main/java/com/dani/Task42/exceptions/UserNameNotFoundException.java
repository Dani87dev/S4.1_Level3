package com.dani.Task42.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User with specific name not exist!")
public class UserNameNotFoundException extends RuntimeException{

}
