package com.app.app.Persistencia;

import com.app.app.Domini.DTO.UserDTO;
import com.app.app.Domini.User;
import com.app.app.Serveis.Password;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;
    private Password passwordEncoder;

    public UserDAO(JdbcTemplate jdbcTemplate, Password password) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = password;
    }

    private final String INSERT_USER = "INSERT INTO USER (name, surname, email, password, gender, birthday, address, telf) VALUES (?,?,?,?,?,?,?,?)";

    private final String FIND = "SELECT * FROM USER";
    private final String VALIDATE_USER = FIND + " WHERE EMAIL = ?";
    private final String FIND_EMAIL = FIND + " WHERE EMAIL = ?";
    private final String FIND_TOKEN = FIND + " WHERE confirmation_token = ?";

    private final String UPDATE = "UPDATE USUARI SET enabled = ? WHERE EMAIL = ?";

    private final String DELETE_USER = "DELETE FROM USUARI WHERE EMAIL = ? ";

    private final RowMapper<User> mapper = (resultSet, i) -> new User.userBuilder()
            .name(resultSet.getString("NAME"))
            .surname(resultSet.getString("SURNAME"))
            .email(resultSet.getString("EMAIL"))
            .password(resultSet.getString("PASSWORD"))
            .gender(resultSet.getString("GENDER"))
            .birthday(resultSet.getTimestamp("BIRTHDAY").toLocalDateTime())
            .address(resultSet.getString("ADDRESS"))
            .telefon(resultSet.getString("TELF"))
            .build();


    public int insert(User user) {

        return jdbcTemplate.update(INSERT_USER,
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                user.getGender(),
                user.getBirthday(),
                user.getAddress(),
                user.getTelf());
    }

    public int delete(String email) {
        return jdbcTemplate.update(DELETE_USER, email);
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND, mapper);
    }

    public boolean existeixUser(String email) {
        return jdbcTemplate.query(FIND_EMAIL, new Object[]{email}, mapper).size() == 1;
    }

    public User existeixToken(String token) {
        List<User> trobat = jdbcTemplate.query(FIND_TOKEN, new Object[]{token}, mapper);
        if (trobat.size() == 0) return new User();
        return trobat.get(0);
    }


    public UserDTO validteUser(String email, String password) {
        List<User> trobat = jdbcTemplate.query(VALIDATE_USER, new Object[]{email}, mapper);
        if (trobat.size() == 0) return new UserDTO();
        if (passwordEncoder.matches(password, trobat.get(0).getPassword())) {
            User logat = trobat.get(0);
            return new UserDTO(logat.getName(), logat.getSurname(),
                    logat.getEmail(), logat.getTelf(), logat.getGender(), logat.getAddress());

        }
        return new UserDTO();
    }

}
