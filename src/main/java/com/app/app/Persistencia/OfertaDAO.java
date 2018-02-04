package com.app.app.Persistencia;

import com.app.app.Domini.Oferta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfertaDAO {

    private JdbcTemplate jdbcTemplate;

    private final String all = "SELECT* FROM OFERTA";
    private final String insert = "INSERT INTO OFERTA (ID_BOTIGA,DESCRIPCIO_OFERTA,ENABLE,PUNTS) VALUES (?,?,?,?)";

    private final String findByEmail = "SELECT * FROM OFERTA WHERE EMAIL = ?";

    public OfertaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Oferta> mapper = (resultSet, i) ->
            new Oferta(
                    resultSet.getInt("ID_BOTIGA"),
                    resultSet.getString("DESCRIPCIO_OFERTA"),
                    resultSet.getBoolean("ENABLE"),
                    resultSet.getInt("PUNTS")
            );

    public List<Oferta> all() {
        return jdbcTemplate.query(all, mapper);
    }

    public int insert(Oferta oferta) {
        return jdbcTemplate.update(insert,
                oferta.getId_botiga(),
                oferta.getDescripcio(),
                oferta.isEnable(),
                oferta.getPunts());
    }

}
