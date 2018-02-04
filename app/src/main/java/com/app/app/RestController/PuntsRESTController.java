package com.app.app.RestController;


import com.app.app.DaoController.PuntDAOController;
import com.app.app.Domini.DTO.UserDTO;
import com.app.app.Domini.Puntuacio;
import com.app.app.Domini.User;
import com.app.app.Persistencia.PuntsDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("punts/")
public class PuntsRESTController {

    private PuntDAOController puntsDAO;

    public PuntsRESTController(PuntDAOController puntsDAO) {
        this.puntsDAO = puntsDAO;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Puntuacio> listPunts() {
        return puntsDAO.getAll();
    }

    @GetMapping(value = "getPoint/{email:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getPoints(@PathVariable String email) {
        return puntsDAO.getPoints(email);
    }

    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public int insert(@RequestBody Puntuacio puntuacio) {
        puntsDAO.updatePoints(puntuacio.getUserEmail(), puntuacio.getQuantitat());
        return getPoints(puntuacio.getUserEmail());
    }

}
