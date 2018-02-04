package com.app.app.DaoController;

import com.app.app.Domini.Historic;
import com.app.app.Persistencia.HistoricDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hisotricController")
public class HisotricDAOController {

    private HistoricDAO historicDAO;

    public HisotricDAOController(HistoricDAO historicDAO) {
        this.historicDAO = historicDAO;
    }

    public List<Historic> all() {
        return historicDAO.getAll();
    }

    public int insert(String nomCarrer) {
        return historicDAO.insert(nomCarrer);
    }

    public boolean exists(String nomCarrer) {
        return historicDAO.exists(nomCarrer);
    }

    public Historic getCarrer(String nomCarrer) {
        return historicDAO.getCarrer(nomCarrer);
    }

    public int update(String nomCarrer) {
        return historicDAO.update(nomCarrer);
    }
}
