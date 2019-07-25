package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    //@RequestMapping(value="/register", method= RequestMethod.GET)
    @GetMapping(value="/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    //@RequestMapping(value="/register", method = RequestMethod.POST)
    @PostMapping(value="/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result,
                                          Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors())
            return "registration";
        else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created.");
        }


        //pass currently login user information to index.html
        model.addAttribute("crntuser", user);

        return "index";

    }


    @RequestMapping("/")
    public String index(Principal principal, Model model) {

        //pass currently login user information to index.html
        String username=principal.getName();
        model.addAttribute("crntuser", userRepository.findByUsername(username));
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(Principal principal, Model model) {

        //pass currently login user information to admin.html
        String username = principal.getName();
        model.addAttribute("crntuser", userRepository.findByUsername(username));

        return "admin";
    }

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {

        //pass currently login user information to secure.html
        String username = principal.getName();
        model.addAttribute("crntuser", userRepository.findByUsername(username));

        return "secure";
    }
}
