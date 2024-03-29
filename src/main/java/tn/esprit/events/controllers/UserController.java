package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.dtos.Role;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.exceptions.EntityAlreadyExistException;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.exceptions.ErrorOccurredException;
import tn.esprit.events.exceptions.InvalidEntityException;
import tn.esprit.events.repositories.KcUserRepository;
import tn.esprit.events.services.IUserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final KcUserRepository kcUserRepository;
    private final IUserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUserSql() throws SQLException {
        return new ResponseEntity<>(kcUserRepository.getAllUsers(), HttpStatus.OK);
    }


    @PostMapping(path = "/resetPassword/{userId}/{password}")
    public ResponseEntity<?> resetUserPassword(@PathVariable String userId, @PathVariable String password) {
        return userService.resetUserPassword(userId, password);
    }

    @PostMapping(path = "/token")
    public String getToken() {
        return userService.getToken();
    }

    @PostMapping(path = "/roles/{roleName}")
    public ResponseEntity<?> createRole(@PathVariable(value = "roleName") String roleName) throws EntityAlreadyExistException, EntityNotFoundException {
        return userService.createRole(roleName);
    }


    @GetMapping(path = "/roles/permissions")
    public ResponseEntity<?> getAllPermissions() {
        return userService.getPermissions();
    }


    @PutMapping(path = "roles")
    public ResponseEntity<?> updateRole(@RequestBody Role role) throws EntityNotFoundException {
        return userService.updateRole(role);
    }

    @DeleteMapping(path = "/roles/{roleName}")
    public ResponseEntity<?> deleteRole(@PathVariable(value = "roleName") String roleName) throws InvalidEntityException, ErrorOccurredException {
        return userService.deleteRole(roleName);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") String userId) throws InvalidEntityException, ErrorOccurredException {
        return userService.deleteUser(userId);
    }


    @PostMapping("/roles/assignPermissionToRole")
    public ResponseEntity<?> assignCompositeRolesForRole(@RequestBody Map<String, Object> requestBody) throws EntityNotFoundException {
        String roleId = (String) requestBody.get("roleId");
        List<String> rolesIds = (List<String>) requestBody.get("rolesIds");
        return userService.assignCompositeRolesForRole(roleId, rolesIds);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        return userService.getAllRoles();
    }

    @PostMapping("/roles/assignRolesToUser")
    public ResponseEntity<?> assignRolesToUser(@RequestBody Map<String, Object> requestBody) throws EntityNotFoundException {
        String userId = (String) requestBody.get("userId");
        List<String> roleIds = (List<String>) requestBody.get("roleIds");
        return userService.assignRolesToUser(userId, roleIds);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto user) throws EntityAlreadyExistException {
        return userService.createUser(user);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) throws EntityAlreadyExistException {
        return userService.editUser(user);
    }

}
