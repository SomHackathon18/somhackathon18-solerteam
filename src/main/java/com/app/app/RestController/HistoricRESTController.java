package com.app.app.RestController;

import com.app.app.DaoController.HisotricDAOController;
import com.app.app.Domini.Historic;
import com.app.app.Domini.Puntuacio;
import com.app.app.Persistencia.HistoricDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("historic/")
public class HistoricRESTController {

    private HisotricDAOController hisotricDAOController;

    public HistoricRESTController(HisotricDAOController hisotricDAOController) {
        this.hisotricDAOController = hisotricDAOController;
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Historic> listUsers() {
        return hisotricDAOController.all();
    }

    public int insert(String nomCarrer) {
        if (hisotricDAOController.exists(nomCarrer)) {
            return hisotricDAOController.update(nomCarrer);
        } else {
            return hisotricDAOController.insert(nomCarrer);
        }

    }


}
