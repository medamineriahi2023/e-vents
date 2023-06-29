package tn.esprit.events.services.implementations;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.Role;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.security.KeycloakConfig;
import tn.esprit.events.services.IUserService;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public String getToken() {
        return KeycloakConfig.getInstance().tokenManager().getAccessTokenString();
    }

    @Override
    public ResponseEntity<?> resetUserPassword(String userId, String newPassword) {
        Keycloak keycloak = KeycloakConfig.getInstance();
        UserResource userResource = keycloak.realm(KeycloakConfig.realm).users().get(userId);
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(newPassword);
        credentialRepresentation.setTemporary(false);
        userResource.resetPassword(credentialRepresentation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Role>> getPermissions() {
        Keycloak keycloak = KeycloakConfig.getInstance();
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);

        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId(KeycloakConfig.clientID)
                .get(0);
        List<RoleRepresentation> roleRepresentations = realmResource.clients().get(clientRepresentation.getId()).roles()
                .list();
        List<Role> roles = new ArrayList<>();

        for (RoleRepresentation roleRepresentation : roleRepresentations) {
            Role role = new Role();
            role.setId(roleRepresentation.getId());
            role.setName(roleRepresentation.getName());
            roles.add(role);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> deleteRole(String roleName) {
        Keycloak keycloak = KeycloakConfig.getInstance();
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);
        RoleResource roleResource = realmResource.roles().get(roleName);

        if (roleResource == null) {
            return new ResponseEntity<>("Role with this id " + roleName + " is not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<UserRepresentation> users = realmResource.users().list().stream().filter(u -> {
            UserResource userResource = realmResource.users().get(u.getId());
            RoleMappingResource roleMappingResource = userResource.roles();
            return roleMappingResource.realmLevel().listAll().stream().anyMatch(r -> r.getName().equals(roleName));
        }).collect(Collectors.toList());

        if (!users.isEmpty()) {
            return new ResponseEntity<>("Cannot delete role as it is assigned to one or more users",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<GroupRepresentation> groups = realmResource.groups().groups().stream().filter(g -> {
            GroupResource groupResource = realmResource.groups().group(g.getId());
            RoleMappingResource roleMappingResource = groupResource.roles();
            return roleMappingResource.realmLevel().listAll().stream().anyMatch(r -> r.getName().equals(roleName));
        }).collect(Collectors.toList());

        if (!groups.isEmpty()) {
            return new ResponseEntity<>("Cannot delete role as it is assigned to one or more groups",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        roleResource.remove();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> updateRole(Role role) {
        Keycloak keycloak = KeycloakConfig.getInstance();
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);

        List<RoleRepresentation> existingRoles = realmResource.roles().list();
        if (existingRoles.stream().noneMatch(r -> r.getId().equals(role.getId()))) {
            return new ResponseEntity<>("Role with this id does not exist", HttpStatus.NOT_FOUND);
        }

        RoleRepresentation updatedRole = new RoleRepresentation();
        updatedRole.setId(role.getId());
        updatedRole.setName(role.getName());

        RoleRepresentation roleRepresentation = realmResource.rolesById().getRole(role.getId());
        realmResource.roles().get(roleRepresentation.getName()).update(updatedRole);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createRole(String roleName) {
        Keycloak keycloak = KeycloakConfig.getInstance();
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);

        List<RoleRepresentation> existingRoles = realmResource.roles().list();
        if (existingRoles.stream().anyMatch(r -> r.getName().equals(roleName))) {
            return new ResponseEntity<>("Role with this name already exists", HttpStatus.BAD_REQUEST);
        }

        RoleRepresentation role = new RoleRepresentation();
        role.setName(roleName);
        role.setClientRole(false);
        realmResource.roles().create(role);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> assignCompositeRolesForRole(String roleId, List<String> rolesIds) {
        Keycloak keycloak = KeycloakConfig.getInstance();
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);

        RoleRepresentation role = realmResource.rolesById().getRole(roleId);
        RoleResource roleResource = realmResource.roles().get(role.getName());

        if (role == null) {
            return new ResponseEntity<>("Role with ID " + roleId + " not found.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Set<RoleRepresentation> existingComposites = roleResource.getRoleComposites();
        List<RoleRepresentation> compositesToRemove = existingComposites.stream()
                .filter(r -> !rolesIds.contains(r.getId())).collect(Collectors.toList());
        if (!compositesToRemove.isEmpty()) {
            roleResource.deleteComposites(compositesToRemove);
        }

        List<RoleRepresentation> compositesToAdd = rolesIds.stream().map(id -> realmResource.rolesById().getRole(id))
                .filter(Objects::nonNull).collect(Collectors.toList());
        roleResource.addComposites(compositesToAdd);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<Role>> getAllRoles() {
        Keycloak keycloak = KeycloakConfig.getInstance();
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);
        RolesResource rolesResource = realmResource.roles();

        List<RoleRepresentation> roleRepresentations = rolesResource.list();
        List<Role> roles = new ArrayList<>();

        for (RoleRepresentation roleRepresentation : roleRepresentations) {
            Role role = new Role();
            role.setId(roleRepresentation.getId());
            role.setName(roleRepresentation.getName());

            Set<RoleRepresentation> compositeRoles = rolesResource.get(roleRepresentation.getName()).getRoleComposites();
            List<Role> permissions = new ArrayList<>();

            for (RoleRepresentation compositeRole : compositeRoles) {
                Role permission = new Role();
                permission.setId(compositeRole.getId());
                permission.setName(compositeRole.getName());
                permissions.add(permission);
            }

            role.setPermissions(permissions);
            roles.add(role);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> assignRolesToUser(String userId, List<String> roleIds) {
        Keycloak keycloak = KeycloakConfig.getInstance();
        RealmResource realmResource = keycloak.realm(KeycloakConfig.realm);
        UserResource userResource = realmResource.users().get(userId);

        if (userResource == null) {
            return new ResponseEntity<>("User with ID " + userId + " not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        RoleMappingResource roleMappingResource = userResource.roles();
        RoleScopeResource roleScopeResource = roleMappingResource.realmLevel();
        List<RoleRepresentation> existingRoles = roleScopeResource.listEffective();

        for (RoleRepresentation existingRole : existingRoles) {
            if (!roleIds.contains(existingRole.getId())) {
                roleScopeResource.remove(Collections.singletonList(existingRole));
            }
        }
        List<RoleRepresentation> rolesToAdd = new ArrayList<>();
        for (String roleId : roleIds) {
            RoleRepresentation roleRepresentation = realmResource.roles().list().stream()
                    .filter(a -> a.getId().equals(roleId)).findFirst().get();
            rolesToAdd.add(roleRepresentation);
        }
        roleScopeResource.add(rolesToAdd);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> editUser(UserDto userRequest) {
        RealmResource realmResource = KeycloakConfig.getInstance().realm(KeycloakConfig.realm);
        List<UserRepresentation> existingUsers = realmResource.users().search(userRequest.getUsername(), null, null, null,
                0, 1);
        if (!existingUsers.isEmpty()) {
            for (UserRepresentation existingUser : existingUsers) {
                if (!existingUser.getId().equals(userRequest.getId())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
                }
            }
        }

        existingUsers = realmResource.users().search(null, userRequest.getEmail(), null, null, 0, 1);
        if (!existingUsers.isEmpty()) {
            for (UserRepresentation existingUser : existingUsers) {
                if (!existingUser.getId().equals(userRequest.getId())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
                }
            }
        }

        UserRepresentation user = realmResource.users().get(userRequest.getId()).toRepresentation();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEnabled(userRequest.isActive());
        if (user.getAttributes() != null && user.getAttributes().size() > 0) {
            user.getAttributes().put("locationId", List.of(userRequest.getLocationDto().getId().toString()));
        } else {
            Map<String, List<String>> map = new HashMap<>();
            map.put("locationId", List.of(userRequest.getLocationDto().getId().toString()));
            user.setAttributes(map);
        }
        realmResource.users().get(userRequest.getId()).update(user);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<?> createUser(UserDto user) {
        final int resp = 201;
        UsersResource usersResource = KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(user.isActive());
        kcUser.setEmailVerified(false);
        Map<String, List<String>> map = new HashMap<>();
        map.put("locationId", List.of(user.getLocationDto().getId().toString()));
        kcUser.setAttributes(map);

        Response response = usersResource.create(kcUser);

        if (response.getStatus() == resp) {
            String userId = getIdFromLocationHeader(response.getHeaderString("Location"));
            UserRepresentation userRepresentation = usersResource.get(userId).toRepresentation();
            return new ResponseEntity<>(userRepresentation, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("this user is already exist", HttpStatus.INTERNAL_SERVER_ERROR);

    }


    private String getIdFromLocationHeader(String locationHeader) {
        String[] parts = locationHeader.split("/");
        return parts[parts.length - 1];
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
