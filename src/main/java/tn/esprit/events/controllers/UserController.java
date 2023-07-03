package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.dtos.Role;
import tn.esprit.events.dtos.UserDto;
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

    @PostMapping(path = "/roles/{roleName}")
    public ResponseEntity<?> createRole(@PathVariable(value = "roleName") String roleName) {
        return userService.createRole(roleName);
    }


    @GetMapping(path = "/roles/permissions")
    public ResponseEntity<?> getAllPermissions() {
        return userService.getPermissions();
    }


    @PutMapping(path = "roles")
    public ResponseEntity<?> updateRole(@RequestBody Role role) {
        return userService.updateRole(role);
    }

    @DeleteMapping(path = "/roles/{roleName}")
    public ResponseEntity<?> deleteRole(@PathVariable(value = "roleName") String roleName) {
        return userService.deleteRole(roleName);
    }


    @PostMapping("/roles/assignPermissionToRole")
    public ResponseEntity<?> assignCompositeRolesForRole(@RequestBody Map<String, Object> requestBody) {
        String roleId = (String) requestBody.get("roleId");
        List<String> rolesIds = (List<String>) requestBody.get("rolesIds");
        return userService.assignCompositeRolesForRole(roleId, rolesIds);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        return userService.getAllRoles();
    }

    @PostMapping("/roles/assignRolesToUser")
    public ResponseEntity<?> assignRolesToUser(@RequestBody Map<String, Object> requestBody) {
        String userId = (String) requestBody.get("userId");
        List<String> roleIds = (List<String>) requestBody.get("roleIds");
        return userService.assignRolesToUser(userId, roleIds);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        return userService.editUser(user);
    }

}
