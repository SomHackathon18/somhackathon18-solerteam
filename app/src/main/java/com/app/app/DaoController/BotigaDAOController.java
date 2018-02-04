package com.app.app.DaoController;

import com.app.app.Domini.Botiga;
import com.app.app.Persistencia.BotigaDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("botigaController")
public class BotigaDAOController {

    private BotigaDAO botigaDAO;

    public BotigaDAOController(BotigaDAO botigaDAO) {
        this.botigaDAO = botigaDAO;
    }

    public Botiga insert(Botiga botiga) {
        return botigaDAO.insert(botiga);
    }

    public List<Botiga> all() {
        return botigaDAO.getAll();
    }

    public int getIdByEmail(String email) {
        return botigaDAO.getIdByEmail(email);
    }
}
