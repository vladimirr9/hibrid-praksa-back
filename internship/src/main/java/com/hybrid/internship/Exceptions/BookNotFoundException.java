package com.hybrid.internship.Exceptions;

import javassist.NotFoundException;

public class BookNotFoundException  extends NotFoundException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
