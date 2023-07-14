package tn.esprit.events.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.esprit.events.dtos.Role;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.services.ILocationService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class KcUserRepository {

    private String url = "jdbc:postgresql://localhost:5555/keycloak";

    private String username = "keycloak";

    private String password = "password";


    @Autowired
    ILocationService locationService;

    public List<UserDto> getAllUsers() throws SQLException {
        List<UserDto> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String userSql = "SELECT ue.id, ue.email, ue.enabled, ue.first_name, ue.last_name, ue.username, "
                    + "urm.role_id, kr.name AS role_name, "
                    + "ua.value AS locationId "
                    + "FROM user_entity ue "
                    + "LEFT JOIN user_role_mapping urm ON ue.id = urm.user_id "
                    + "LEFT JOIN keycloak_role kr ON urm.role_id = kr.id "
                    + "LEFT JOIN user_attribute ua ON ue.id = ua.user_id AND ua.name = 'locationId' "
                    + "JOIN realm r ON ue.realm_id = r.id "
                    + "WHERE r.name = 'esprit' "
                    + "AND ue.id NOT IN (SELECT user_id FROM user_role_mapping WHERE role_id IN (SELECT id FROM keycloak_role WHERE name = 'superAdmin')) "
                    + "ORDER BY ue.id";
            try (PreparedStatement stmt = conn.prepareStatement(userSql)) {
                ResultSet rs = stmt.executeQuery();
                UserDto currentUser = null;
                while (rs.next()) {
                    String userId = rs.getString("id");
                    if (currentUser == null || !currentUser.getId().equals(userId)) {
                        // This is a new user, so create a new KcUser object
                        currentUser = new UserDto();
                        currentUser.setId(userId);
                        currentUser.setFirstName(rs.getString("first_name"));
                        currentUser.setLastName(rs.getString("last_name"));
                        currentUser.setEmail(rs.getString("email"));
                        currentUser.setUsername(rs.getString("username"));
                        currentUser.setPassword(""); // Password is not included in the
                        // query result
                        currentUser.setActive(rs.getBoolean("enabled"));
                        currentUser.setRoles(new ArrayList<>());
                        currentUser.setLocationDto(locationService.getById(Long.parseLong(rs.getString("locationId"))));
                        users.add(currentUser);
                    }
                    // Add the current role to the user's list of roles if not duplicate
                    String roleId = rs.getString("role_id");
                    String roleName = rs.getString("role_name");
                    if (roleId != null && roleName != null) {
                        boolean roleExists = false;
                        for (Role role : currentUser.getRoles()) {
                            if (role.getId().equals(roleId) && role.getName().equals(roleName)) {
                                roleExists = true;
                                break;
                            }
                        }
                        if (!roleExists) {
                            Role role = new Role();
                            role.setId(roleId);
                            role.setName(roleName);
                            currentUser.getRoles().add(role);
                        }
                    }
                }
            }
        }
        return users;
    }
}