package tn.esprit.events.services;

import org.springframework.http.ResponseEntity;
import tn.esprit.events.dtos.Role;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.exceptions.EntityAlreadyExistException;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.exceptions.ErrorOccurredException;
import tn.esprit.events.exceptions.InvalidEntityException;

import java.util.List;

public interface IUserService {

    String getToken();

    ResponseEntity<?> resetUserPassword(String userId, String newPassword);


    ResponseEntity<List<Role>> getPermissions();

    ResponseEntity<?> deleteRole(String roleName) throws InvalidEntityException, ErrorOccurredException;
    ResponseEntity<?> deleteUser(String userId) throws InvalidEntityException, ErrorOccurredException;

    ResponseEntity<?> updateRole(Role role) throws EntityNotFoundException;


    ResponseEntity<?> createRole(String roleName) throws EntityNotFoundException, EntityAlreadyExistException;

    ResponseEntity<?> assignCompositeRolesForRole(String roleId, List<String> rolesIds) throws EntityNotFoundException;

    ResponseEntity<List<Role>> getAllRoles();


    ResponseEntity<?> assignRolesToUser(String userId, List<String> roleIds) throws EntityNotFoundException;


    ResponseEntity<?> editUser(UserDto userRequest) throws EntityAlreadyExistException;


    ResponseEntity<?> createUser(UserDto user) throws EntityAlreadyExistException;

}
