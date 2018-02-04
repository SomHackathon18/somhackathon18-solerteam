package com.app.app.DaoController;

import com.app.app.Domini.Puntuacio;
import com.app.app.Persistencia.PuntsDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("puntDAOController")
public class PuntDAOController {

    private PuntsDAO puntsDAO;

    public PuntDAOController(PuntsDAO puntsDAO) {
        this.puntsDAO = puntsDAO;
    }

    public List<Puntuacio> getAll() {
        return puntsDAO.getAll();
    }

    public int  getPoints(String email) {
        return puntsDAO.getPoints(email);
    }

    public int updatePoints(String email, int points) {
        return puntsDAO.addPoints(email, points);
    }

    public void create(String email) {
        puntsDAO.create(email);
    }
}
