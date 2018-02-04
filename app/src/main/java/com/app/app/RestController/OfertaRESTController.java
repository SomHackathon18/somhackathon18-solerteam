package com.app.app.RestController;

import com.app.app.DaoController.OfertaDAOController;
import com.app.app.Domini.Oferta;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("oferta/")
public class OfertaRESTController {

    private OfertaDAOController ofertaDAOController;

    public OfertaRESTController(OfertaDAOController ofertaDAOController) {
        this.ofertaDAOController = ofertaDAOController;
    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public int insert(@RequestBody Oferta oferta) {
        return ofertaDAOController.insert(oferta);
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Oferta> listOfertes() {
        return ofertaDAOController.getAll();
    }
}
