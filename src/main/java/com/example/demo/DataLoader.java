package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "password", "Jim",
                     "Jimmerson", true, "jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin",
                "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        Course course = new Course();
        course.setTitle("AWS I");
        course.setInstructor("John Smith");
        course.setDescription("Gain hands on experience with Amazon Web Services(AWS) products and services - Level 1 of 3");
        course.setCredit(3);

        courseRepository.save(course);

        course = new Course();
        course.setTitle("AWS 2");
        course.setInstructor("John Smith");
        course.setDescription("Gain hands on experience with Amazon Web Services(AWS) products and services - Level 2 of 3");
        course.setCredit(3);
        courseRepository.save(course);

        course = new Course();
        course.setTitle("AWS 3");
        course.setInstructor("John Smith");
        course.setDescription("Gain hands on experience with Amazon Web Services(AWS) products and services - Level 3 of 3");
        course.setCredit(3);
        courseRepository.save(course);

        course = new Course();
        course.setTitle("Photoshop");
        course.setInstructor("Silver Doe");
        course.setDescription("Learn the basic tools of Adobe Photoshop");
        course.setCredit(3);
        courseRepository.save(course);

        course = new Course();
        course.setTitle("Painting");
        course.setInstructor("Jane Spring");
        course.setDescription("Learn the basic tools and techniques of watercolor painting");
        course.setCredit(3);
        courseRepository.save(course);

        course = new Course();
        course.setTitle("Classic Guitar");
        course.setInstructor("Takoma Park");
        course.setDescription("Learn fundamental playing techniques of the guitar");
        course.setCredit(3);
        courseRepository.save(course);

        course = new Course();
        course.setTitle("Fiction Writing");
        course.setInstructor("Rock Ville");
        course.setDescription("Learn all the elements of great fiction");
        course.setCredit(3);
        courseRepository.save(course);

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
