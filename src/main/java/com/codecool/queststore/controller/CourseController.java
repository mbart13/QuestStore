package com.codecool.queststore.controller;

import com.codecool.queststore.dto.CourseDto;
import com.codecool.queststore.model.Course;
import com.codecool.queststore.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("courseDto", new CourseDto());
        return "admin/classes";
    }

    @PostMapping
    public String addNew(@ModelAttribute CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        courseService.save(course);
        return "redirect:/courses";
    }
}
