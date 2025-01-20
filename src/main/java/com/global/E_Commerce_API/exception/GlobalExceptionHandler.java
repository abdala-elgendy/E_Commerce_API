package com.global.E_Commerce_API.exception;

public class GlobalExceptionHandler {

    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";


  public String checkSignupException(String username) {

      return "User with username " + username + " already exists";
  }

}
