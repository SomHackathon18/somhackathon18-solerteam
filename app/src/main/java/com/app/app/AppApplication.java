package com.app.app;

import com.app.app.Domini.User;
import com.app.app.Persistencia.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Autowired
    UserDAO userDAO;

    @Override
    public void run(String... strings) throws Exception {
        // userDAO.findAll().forEach(System.out::println);

      /*  userDAO.insert(new User.userBuilder()
                .name("Axel")
                .surname("Ros")
                .email("aros@edu.tecnocampus.cat")
                .password("aros")
                .gender("home")
                .birthday(LocalDateTime.now())
                .address("carrera adeu")
                .telefon("657157789")
                .confirmationToken("")
                .reset_token("wwe")
                .enabled(false)
                .build()
        );*/

     /*   userDAO.findAll().forEach(System.out::println);

        System.out.println("USER FOUND\n");
        System.out.println(userDAO.validteUser("aros@edu.tecnocampus.cat", "aros"));*/
    }
}
