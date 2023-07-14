package tn.esprit.events.exceptions;


import tn.esprit.events.exceptions.abstracts.AbstractEntityException;
import tn.esprit.events.handlers.ErrorCodes;

import java.util.List;

public class EntityNotFoundException extends AbstractEntityException {

    public EntityNotFoundException(String message , ErrorCodes errorCodes , List<String> errors){
        super(message,errorCodes,errors);
    }

    public EntityNotFoundException(String message , ErrorCodes errorCodes){
        super(message,errorCodes);
    }
}
