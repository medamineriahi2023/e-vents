package tn.esprit.events.services;

import org.springframework.http.ResponseEntity;
import tn.esprit.events.dtos.Role;
import tn.esprit.events.dtos.UserDto;

import java.util.List;

public interface IUserService {

    ResponseEntity<?> resetUserPassword(String userId, String newPassword);


    ResponseEntity<List<Role>> getPermissions();

    ResponseEntity<?> deleteRole(String roleName);

    ResponseEntity<?> updateRole(Role role);


    ResponseEntity<?> createRole(String roleName);

    ResponseEntity<?> assignCompositeRolesForRole(String roleId, List<String> rolesIds);

    ResponseEntity<List<Role>> getAllRoles();


    ResponseEntity<?> assignRolesToUser(String userId, List<String> roleIds);


    ResponseEntity<?> editUser(UserDto userRequest);


    ResponseEntity<?> createUser(UserDto user);

}
