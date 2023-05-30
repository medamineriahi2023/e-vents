package tn.esprit.events.userUtils;

import tn.esprit.events.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public class UserKcService {
    public static UserDto findById(String id) {
        return new UserDto(UUID.randomUUID().toString(), "amine", "riahi" , "medaminer", "amine@esprit.tn");
    }

    public static List<UserDto> splitAndReturn(String ids) {
        String[] list = ids.split(",");
        return List.of(new UserDto(UUID.randomUUID().toString(), "amine", "riahi" , "medaminer", "amine@esprit.tn"),
                new UserDto(UUID.randomUUID().toString(), "amine", "riahi" , "medaminer", "amine@esprit.tn"));
    }
}
