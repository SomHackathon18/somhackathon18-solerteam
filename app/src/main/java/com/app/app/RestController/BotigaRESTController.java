package com.app.app.RestController;


import com.app.app.DaoController.BotigaDAOController;
import com.app.app.Domini.Botiga;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("botiga/")
public class BotigaRESTController {

    private BotigaDAOController botigaDAOController;

    public BotigaRESTController(BotigaDAOController botigaDAOController) {
        this.botigaDAOController = botigaDAOController;
    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public Botiga insert(@RequestBody Botiga botiga) {
        return  botigaDAOController.insert(botiga);
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Botiga> listBotigues() {
        return botigaDAOController.all();
    }

    @GetMapping(value = "findId/{email:.+ }", produces = MediaType.APPLICATION_JSON_VALUE)
    public int findId(@PathVariable("email") String email) {
        return botigaDAOController.getIdByEmail(email);
    }

}
