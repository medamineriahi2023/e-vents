package tn.esprit.events.userUtils;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.security.KeycloakConfig;
import tn.esprit.events.services.ILocationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserKcService {

    private static ILocationService iLocationService;

    public UserKcService(ILocationService iLocationService) {
        UserKcService.iLocationService = iLocationService;
    }

    public static UserDto findById(String id) {
        if (id != null) {
            return userRepresentationToDto(KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users().get(id).toRepresentation());
        }
        return null;
    }

    public static List<UserDto> splitAndReturn(String ids) {
        String[] list = ids.split(",");
        List<UserDto> userDtos = new ArrayList<>();
            for (String l: list){
                userDtos.add(findById(l));
            }
        return userDtos;
    }


    public static UserDto userRepresentationToDto(UserRepresentation userRepresentation){
        return new UserDto(userRepresentation.getId(), userRepresentation.getFirstName(), userRepresentation.getLastName(), userRepresentation.getUsername(),
                userRepresentation.getEmail(),iLocationService.getById(Long.parseLong(userRepresentation.getAttributes().get("locationId").get(0))));
    }

    public static UserRepresentation dtoToUserRepresentation(UserDto userDto){
        var userRepresentation =  new UserRepresentation();
        userRepresentation.setId(userDto.getId());
        userRepresentation.setEmail(userDto.getEmail());
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
            Map<String, List<String>> map = new HashMap<>();
            map.put("locationId", List.of(userDto.getLocationDto().getId().toString()));
            userRepresentation.setAttributes(map);
        return userRepresentation;
    }

}
