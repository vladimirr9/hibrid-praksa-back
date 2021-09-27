package com.hybrid.internship.exception;

public class CurrentlyRentedOutException extends RuntimeException {
    public CurrentlyRentedOutException() {
        super("This book copy is currently rented out");
    }
}
