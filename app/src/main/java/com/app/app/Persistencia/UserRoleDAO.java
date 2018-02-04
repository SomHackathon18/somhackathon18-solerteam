package com.app.app.Persistencia;

import com.app.app.Domini.Historic;
import com.app.app.Domini.UserRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDAO {

    private JdbcTemplate jdbcTemplate;

    public UserRoleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String all = "SELECT * FROM rel_user_role";
    private final String insert = "INSERT INTO rel_user_role (EMAIL,ID_ROLE) VALUES (?,?); ";
    private final String getUserRole = "SELECT * FROM rel_user_role WHERE EMAIL = ?";

    private final RowMapper<UserRole> mapper = (resultSet, i) -> new UserRole(
            resultSet.getString("EMAIL"),
            resultSet.getInt("ID_ROLE"));

    public int insert(UserRole userRole) {
        return jdbcTemplate.update(insert, new Object[]{
                userRole.getEmail(),
                userRole.getId_role()});
    }

    public int getUserRole(String email) {
        List<UserRole> users = jdbcTemplate.query(getUserRole, new Object[]{email}, mapper);
        if (users.size() == 0) return 0;
        UserRole userRole = users.get(0);
        return userRole.getId_role();
    }

    public List<UserRole> all() {
        return jdbcTemplate.query(all, mapper);
    }
}
