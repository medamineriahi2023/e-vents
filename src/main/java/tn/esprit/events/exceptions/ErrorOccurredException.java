package tn.esprit.events.exceptions;


import tn.esprit.events.exceptions.abstracts.AbstractEntityException;
import tn.esprit.events.handlers.ErrorCodes;

import java.util.List;

public class ErrorOccurredException extends AbstractEntityException {

    public ErrorOccurredException(String message , ErrorCodes errorCodes , List<String> errors){
        super(message,errorCodes,errors);
    }

    public ErrorOccurredException(String message , ErrorCodes errorCodes){
        super(message,errorCodes);
    }
}
