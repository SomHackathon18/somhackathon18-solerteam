package com.app.app.RestController;

import com.app.app.DaoController.UserDAOController;
import com.app.app.Domini.DTO.LoginDTO;
import com.app.app.Domini.DTO.UserDTO;
import com.app.app.Domini.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/")
public class UserRESTController {

    private UserDAOController userDAOController;

    public UserRESTController(UserDAOController userDAOController) {
        this.userDAOController = userDAOController;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> listUsers() {
        return userDAOController.getAllUsers();
    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO insert(@RequestBody User user) {
        userDAOController.insert(user);

        return user.converetToDTO();
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO login(@RequestBody LoginDTO loginDTO) {
        return userDAOController.validateUser(loginDTO.getEmail(), loginDTO.getPassword());

    }


}
