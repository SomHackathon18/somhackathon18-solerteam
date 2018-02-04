package com.app.app.Persistencia;

import com.app.app.Domini.Zona;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ZonaDAO {

    private JdbcTemplate jdbcTemplate;

    public ZonaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Zona> mapper = (resultSet, i) -> new Zona(
            resultSet.getDouble("LAT_Z"),
            resultSet.getDouble("LNG_Z")
    );


}
