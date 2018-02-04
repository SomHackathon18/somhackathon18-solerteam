package com.app.app.RestController;


import com.app.app.DaoController.UserDAOController;
import com.app.app.DaoController.UserRoleDAOController;
import com.app.app.Domini.DTO.UserDTO;
import com.app.app.Domini.User;
import com.app.app.Domini.UserRole;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role/")
public class UserRoleRESTController {

    private UserRoleDAOController userDAOController;

    public UserRoleRESTController(UserRoleDAOController userDAOController) {
        this.userDAOController = userDAOController;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRole> listAll() {
        return userDAOController.all();
    }

    @GetMapping(value = "getUserRole/{email:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int listUsers(@PathVariable("email") String email) {
        return userDAOController.getUserRole(email);
    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public int insert(@RequestBody UserRole user) {
        return userDAOController.insert(user);
    }
}
