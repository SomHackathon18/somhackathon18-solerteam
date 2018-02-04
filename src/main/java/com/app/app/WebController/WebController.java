package com.app.app.WebController;

import com.app.app.DaoController.OfertaDAOController;
import com.app.app.DaoController.UserDAOController;
import com.app.app.Domini.DTO.UserDTO;
import com.app.app.Domini.Oferta;
import com.app.app.Domini.User;
import com.app.app.Persistencia.OfertaDAO;
import com.app.app.Persistencia.PuntsDAO;
import com.app.app.Persistencia.UserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class WebController {

    @Autowired
    UserDAOController userDAOController;

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    OfertaDAO ofertaDAO;

    @Autowired
    PuntsDAO puntsDao;

    public static UserDTO userDTO;

    @GetMapping("/")
    public String index(Model model, Principal principal) {

        if(userDTO!=null){
            return redirect();
        }
        return "welcome";
    }

    @GetMapping("prova")
    public String prova(Model model) {
        return "index";
    }

    @GetMapping("registerUser")
    public String createUser(Model model) {
        return "createUser";
    }
    @PostMapping("registerUser")
    //See Errors templates.error parameters: it takes the errors from the validators. It MUST be right after the parameters
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("password2") String password2,
                             @RequestParam("gender") String gender,
                             @RequestParam("birthday") String birthday,
                             @RequestParam("address") String address,
                             @RequestParam("telf") String telf,
                             Model model, RedirectAttributes redirectAttributes) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime birthdayFormat = LocalDateTime.parse("1992/07/15" + " 00:00:00", formatter);

        User user = new User.userBuilder()
                .name(name)
                .surname(surname)
                .address(address)
                .telefon(telf)
                .email(email)
                .password(password)
                .gender(gender)
                .birthday(birthdayFormat)
                .build();

        userDAOController.insert(user);

        return "index";
    }
    @GetMapping("gestioUsers")
    public String veureUsuaris(Model model) {

        List<UserDTO> listUser = userDAOController.getAllUsers();

        model.addAttribute("listaUsers",listUser);

        return "gestioUsers";
    }
    @GetMapping("formOferta")
    public String createOferta(Model model) {
        return "crearOferta";
    }
    @PostMapping("createOferta")
    //See Errors templates.error parameters: it takes the errors from the validators. It MUST be right after the parameters
    public String createOferta(@RequestParam("descripcio") String descripcio,
                               @RequestParam("enable") String enable,
                               @RequestParam("punts") int punts,
                               Model model, RedirectAttributes redirectAttributes) {
        boolean estat = false;
        if(enable.equals("true")){
            estat = true;
        }else {
            estat = false;
        }

        Oferta oferta = new Oferta(5,descripcio,estat,punts);

        ofertaDAO.insert(oferta);

        return "veureOferta";
    }


    @GetMapping("veureOferta")
    public String veureOferta(Model model) {

        List<Oferta> listaOferta = ofertaDAO.all();

        model.addAttribute("listaOfertas",listaOferta);

        return "veureOferta";
    }

    @GetMapping("veureOfertaAdmin")
    public String veureOfertaAdmin(Model model) {

        List<Oferta> listaOferta = ofertaDAO.all();

        model.addAttribute("listaOfertas",listaOferta);

        return "veureOfertaAdmin";
    }

    @GetMapping("/consultarPunts")
    public String consultarPunts(Model model) {



        int punts = puntsDao.getPoints(userDTO.getEmail());
        model.addAttribute("punts",punts);

        return "veurePunts";
    }


    @GetMapping("/welcome")
    public ModelAndView initialPage(Model model) {
        return new ModelAndView("welcome");
    }

    @GetMapping("/logUser")
    public String logUser(Model model) {
        return "logUser";
    }

    @RequestMapping(value = "/logUser", method = {RequestMethod.POST})
    public String existsUser(@RequestParam("user") String user, @RequestParam("password") String password) {
        userDTO = userDAOController.validateUser(user, password);

        return redirect();

    }

    private String redirect() {
        if(userDTO!=null){
            int role = userRoleDAO.getUserRole(userDTO.getEmail());
            String error = "";
            switch (role) {
                case 1:
                    return "admin";
                case 2:
                    return "botiguer";
                case 3:
                    return "vianant";
                default:
                    error = "no te role";
                    break;
            }
        }
        return "errors";

    }
}
