package com.app.app.Persistencia;

import com.app.app.Domini.Historic;
import com.app.app.Domini.Puntuacio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoricDAO {

    private JdbcTemplate jdbcTemplate;

    public HistoricDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String insert = "INSERT INTO HISTORIC (NOM_CARRER,QUANTITAT) VALUES (?,1)";
    private final String all = "SELECT * FROM HISTORIC";

    private final String getCarrer = " SELECT * FROM HISTORIC WHERE NOM_CARRER = ?";
    private final String exists = "SELECT * FROM HISTORIC WHERE NOM_CARRER = ?";

    private final String update = "UPDATE HISTORIC SET QUANTITAT = ? WHERE NOM_CARRER = ?";

    private final RowMapper<Historic> mapper = (resultSet, i) -> new Historic(
            resultSet.getString("NOM_CARRER"),
            resultSet.getInt("QUANTITAT"));

    public int insert(String nomCarrer) {
        return jdbcTemplate.update(insert, nomCarrer);
    }

    public boolean exists(String nomCarrer) {
        List<Historic> found = jdbcTemplate.query(exists, new Object[]{nomCarrer}, mapper);
        if (found.size() == 0) return false;
        return true;
    }

    public Historic getCarrer(String nomCarrer) {
        List<Historic> found = jdbcTemplate.query(getCarrer, new Object[]{nomCarrer}, mapper);
        if (found.size() == 0) return new Historic();
        return found.get(0);
    }

    public List<Historic> getAll() {
        return jdbcTemplate.query(all, mapper);
    }

    public int update(String carrerNom) {
        int qauntitat = getCarrer(carrerNom).getQuantitat();
        qauntitat++;
        return jdbcTemplate.update(update, new Object[]{qauntitat, carrerNom});
    }
}
