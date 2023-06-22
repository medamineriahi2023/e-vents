package tn.esprit.events.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private LocationDto locationDto;
    private boolean isActive;
    private String password;

    private List<Role> roles;



    //TODO missing attributes


}
