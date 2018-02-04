package com.app.app.Persistencia;

import com.app.app.Domini.Botiga;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sun.security.krb5.internal.PAForUserEnc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BotigaDAO {

    private JdbcTemplate jdbcTemplate;

    private final String insert = "INSERT INTO BOTIGA (ID_BOTIGA, EMAIL, NOM_TENDA, LAT_BOT, LNG_BOT, ID_TIPUS) VALUES (?,?,?,?,?,?)";
    private final String getMaxId = "SELECT MAX(ID_BOTIGA) FROM BOTIGA";

    private final String getIdByEmail = "SELECT * FROM BOTIGA WHERE EMAIL = ?";
    private final String getAll = "SELECT * FROM BOTIGA";

    public BotigaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Botiga> mapper = (RowMapper<Botiga>) (resultSet, i) -> new Botiga(
            resultSet.getInt("ID_BOTIGA"),
            resultSet.getString("EMAIL"),
            resultSet.getString("NOM_TENDA"),
            resultSet.getDouble("LAT_BOT"),
            resultSet.getDouble("LNG_BOT"),
            resultSet.getInt("id_tipus"));

    private RowMapper<Integer> mapperInt = (RowMapper<Integer>) (resultSet, i) -> resultSet.getInt(1);


    public Botiga insert(Botiga botiga) {

        int id = getId() + 1;

        jdbcTemplate.update(insert,
                id,
                botiga.getEmail(),
                botiga.getNomTenda(),
                botiga.getLat(),
                botiga.getLng(),
                botiga.getTipus());

        botiga.setIdBotiga(id);
        return botiga;

    }

    private int getId() {
        List<Integer> maxId = jdbcTemplate.query(getMaxId, mapperInt);
        if (maxId.size() == 0) return 0;
        return maxId.get(0);
    }


    public int getIdByEmail(String email) {
        List<Botiga> botigues = jdbcTemplate.query(getIdByEmail, new Object[]{email}, mapper);
        if (botigues.size() == 0) return 0;
        return botigues.get(0).getIdBotiga();
    }

    public List<Botiga> getAll() {
        return jdbcTemplate.query(getAll, mapper);
    }
}
