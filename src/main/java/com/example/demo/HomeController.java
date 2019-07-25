package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/*
 * This controller will deal with all but security (login & register)
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    RoleRepository roleRepository;

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
