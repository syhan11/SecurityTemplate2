package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class CourseController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/allcourses")
    public String showCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());

        return "showallcourses";
    }

}
