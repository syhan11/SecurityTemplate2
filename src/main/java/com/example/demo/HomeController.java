package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/*
 * This controller will deal with all but security (login & register)
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    /*
     * after login has been validated, it will come here
     */
    @RequestMapping("/")
    public String homepg() {
        return "index";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {

        //pass currently logged-in user information to index.html
        User crntuser = userService.getUser();
        if (crntuser != null)
            model.addAttribute("crntuser", crntuser);

        return "admin";
    }

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        String username=principal.getName();
        model.addAttribute("crntuser", userRepository.findByUsername(username));
        return "secure";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
