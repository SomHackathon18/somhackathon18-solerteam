package com.app.app.Persistencia;

import com.app.app.Domini.Puntuacio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PuntsDAO {

    private JdbcTemplate jdbcTemplate;

    public PuntsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String CREATE = "INSERT INTO PUNTUACIO (EMAIL, QUANTITAT) VALUES (?,0)";

    private final String ALL = "SELECT * FROM PUNTUACIO";
    private final String FIND_SPECIFIC = ALL + " WHERE EMAIL = ?";

    private final String ADDPOINTS = "UPDATE PUNTUACIO SET QUANTITAT = ? WHERE EMAIL = ?;";

    private final RowMapper<Puntuacio> mapper = (resultSet, i) -> new Puntuacio(
            resultSet.getString("EMAIL"),
            resultSet.getInt("QUANTITAT"));

    private final RowMapper<Integer> mapperInt = (resultSet, i) -> resultSet.getInt("ID_PUNTUACIO");


    public int create(String email) {
        return jdbcTemplate.update(CREATE, email);
    }

    public List<Puntuacio> getAll() {
        return jdbcTemplate.query(ALL, mapper);
    }

    public int getPoints(String email) {
        Puntuacio puntuacio = getUser(email);
        System.out.println(puntuacio);
        return puntuacio.getQuantitat();
    }

    private Puntuacio getUser(String email) {
        return jdbcTemplate.query(FIND_SPECIFIC, new Object[]{email}, mapper).get(0);
    }


    public int addPoints(String email, int morePoints) {
        int currentPoints = getPoints(email);
        int futurePoints = currentPoints + morePoints;

        return jdbcTemplate.update(ADDPOINTS, new Object[]{futurePoints, email});
    }

}
