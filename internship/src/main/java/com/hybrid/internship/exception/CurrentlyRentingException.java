package com.hybrid.internship.exception;

public class CurrentlyRentingException extends RuntimeException {
    public CurrentlyRentingException() {
        super("This user is currently renting out at least one book copy");
    }
}
