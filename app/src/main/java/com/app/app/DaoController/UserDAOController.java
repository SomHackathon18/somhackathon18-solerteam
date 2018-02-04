package com.app.app.DaoController;

import com.app.app.Domini.DTO.UserDTO;
import com.app.app.Domini.User;
import com.app.app.Persistencia.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDAOController")
public class UserDAOController {

    private UserDAO userDAO;
    private PuntDAOController puntDAOController;

    public UserDAOController(UserDAO userDAO, PuntDAOController puntDAOController) {
        this.userDAO = userDAO;
        this.puntDAOController = puntDAOController;
    }

    public int insert(User user) {
        int result = userDAO.insert(user);
        puntDAOController.create(user.getEmail());
        return result;
    }

    public List<UserDTO> getAllUsers() {
        // return usuariDAO.findAll().stream().map(p -> new UsuariDTO(p.getId(), p.getNom(), p.getCognom1(), p.getCognom2(), p.getEmail(), p.getTelefon(), p.getSexe(), p.isEnabled())).collect(Collectors.toList());

        return userDAO.findAll().stream().map(p ->
                new UserDTO(
                        p.getName(),
                        p.getSurname(),
                        p.getEmail(),
                        p.getTelf(),
                        p.getGender(),
                        p.getAddress())).collect(Collectors.toList());
    }

    public boolean existsUser(String email) {
        return userDAO.existeixUser(email);
    }

    public User existeixToken(String token) {
        return userDAO.existeixToken(token);
    }

    public UserDTO validateUser(String email, String password) {
        return userDAO.validteUser(email, password);
    }

    public int delete(String email) {
        return userDAO.delete(email);
    }
}
