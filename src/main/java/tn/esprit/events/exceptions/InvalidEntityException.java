package tn.esprit.events.exceptions;


import tn.esprit.events.exceptions.abstracts.AbstractEntityException;
import tn.esprit.events.handlers.ErrorCodes;

import java.util.List;

public class InvalidEntityException extends AbstractEntityException {

    public InvalidEntityException(String message , ErrorCodes errorCodes , List<String> errors){
        super(message, errorCodes, errors);
    }

    public InvalidEntityException(String message , ErrorCodes errorCodes){
        super(message,errorCodes);
    }
}
