package com.app.app.DaoController;

import com.app.app.Domini.UserRole;
import com.app.app.Persistencia.UserRoleDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserRoleController")
public class UserRoleDAOController {

    private UserRoleDAO userRoleDAO;

    public UserRoleDAOController(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public int getUserRole(String email) {
        return userRoleDAO.getUserRole(email);
    }

    public int insert(UserRole userRole) {
        return userRoleDAO.insert(userRole);
    }

    public List<UserRole> all() {
        return userRoleDAO.all();
    }
}
