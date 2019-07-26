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


    @PostConstruct()
    public void initCourseTable() {
        Course item = new Course();
        item.setTitle("AWS I");
        item.setInstructor("John Smith");
        item.setDescription("Gain hands on experience with Amazon Web Services(AWS) products and services - Level 1 of 3");
        item.setCredit(3);
        courseRepository.save(item);

        item = new Course();
        item.setTitle("AWS 2");
        item.setInstructor("John Smith");
        item.setDescription("Gain hands on experience with Amazon Web Services(AWS) products and services - Level 2 of 3");
        item.setCredit(3);
        courseRepository.save(item);

        item = new Course();
        item.setTitle("AWS 3");
        item.setInstructor("John Smith");
        item.setDescription("Gain hands on experience with Amazon Web Services(AWS) products and services - Level 3 of 3");
        item.setCredit(3);
        courseRepository.save(item);

        item = new Course();
        item.setTitle("Photoshop");
        item.setInstructor("Silver Doe");
        item.setDescription("Learn the basic tools of Adobe Photoshop");
        item.setCredit(3);
        courseRepository.save(item);

        item = new Course();
        item.setTitle("Painting");
        item.setInstructor("Jane Spring");
        item.setDescription("Learn the basic tools and techniques of watercolor painting");
        item.setCredit(3);
        courseRepository.save(item);

        item = new Course();
        item.setTitle("Classic Guitar");
        item.setInstructor("Takoma Park");
        item.setDescription("Learn fundamental playing techniques of the guitar");
        item.setCredit(3);
        courseRepository.save(item);

        item = new Course();
        item.setTitle("Fiction Writing");
        item.setInstructor("Rock Ville");
        item.setDescription("Learn all the elements of great fiction");
        item.setCredit(3);
        courseRepository.save(item);
    }

    @PostConstruct()
    public void initSubjectTable() {

        Subject item = new Subject();
        item.setName("Computer Science");
        subjectRepository.save(item);

        item = new Subject();
        item.setName("Math");
        subjectRepository.save(item);

        item = new Subject();
        item.setName("English");
        subjectRepository.save(item);

        item = new Subject();
        item.setName("Art");
        subjectRepository.save(item);

        item = new Subject();
        item.setName("Business");
        subjectRepository.save(item);

    }

}
