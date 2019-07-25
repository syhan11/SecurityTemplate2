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

    @Autowired
    RoleRepository roleRepository;

    //@RequestMapping(value="/register", method= RequestMethod.GET)
    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    //@RequestMapping(value="/register", method = RequestMethod.POST)
    @PostMapping("/register")
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

        return "login";

    }

    /*
     * after login has been validated, it will come here
     */
    @RequestMapping("/")
    public String index(Model model) {
        boolean adminflag = false;

        //pass currently logged-in user information to index.html
        User crntuser = userService.getUser();
        if (crntuser != null) {
            model.addAttribute("crntuser", crntuser);

            // check for role of the user to call appropriate page
            long primid = roleRepository.findByRole("ADMIN").getId();
            for (Role onerole : crntuser.getRoles()) {
                if (onerole.getId() == primid) {
                    adminflag = true;
                    break;
                }
            }
        }
        else
            crntuser = new User();

        model.addAttribute("crntuser", crntuser);
        if (adminflag)
            return "admin";
        else
            return "index";


    }

    @RequestMapping("/login")
    public String login() {
        return "login";
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
    public String secure(Model model) {

        //pass currently logged-in user information to index.html
        User crntuser = userService.getUser();
        if (crntuser != null)
            model.addAttribute("crntuser", crntuser);
        return "secure";
    }
}
