package tn.esprit.events.validators;

import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import tn.esprit.events.dtos.UserDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class RegisterRequestValidator {

    public static List<String> validator(UserDto user){
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasLength(user.getUsername())){
            errors.add("username should not be null");
        }

        if (!StringUtils.hasLength(user.getPassword())){
            errors.add("password should not be null");
        }

        if (!StringUtils.hasLength(user.getEmail())){
            errors.add("email should not be null");
        }

        if (!StringUtils.hasLength(user.getLastName())){
            errors.add("lastname should not be null");
        }
        if (!StringUtils.hasLength(user.getFirstName())){
            errors.add("firstname should not be null");
        }

        return errors;
    }
}