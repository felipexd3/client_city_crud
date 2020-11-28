package com.blank.ilia.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Class entity){
        super("Cannot find ".concat(entity.getSimpleName().toUpperCase()));
    }
}
