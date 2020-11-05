package com.codecool.queststore.controller;

import com.codecool.queststore.dto.CourseDto;
import com.codecool.queststore.model.Course;
import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.service.CourseService;
import com.codecool.queststore.service.MentorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final MentorService mentorService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("courseDto", new CourseDto());
        model.addAttribute("mentors", mentorService.getAll());
        return "admin/classes";
    }

    @PostMapping
    public String addNew(@ModelAttribute CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        courseService.save(course);
        return "redirect:/courses";
    }

    @PostMapping("/edit/{id}")
    public String assignMentors(@PathVariable("id") Long id, @RequestParam(value = "mentor_id", defaultValue = "") String[] mentorsIds) {
        Course course = courseService.findById(id);
        List<Mentor> mentors = Arrays.stream(mentorsIds)
                                    .map(Long::valueOf)
                                    .map(mentorService::findById)
                                    .collect(Collectors.toList());

        course.getMentors().addAll(mentors);
        mentors.forEach(mentor -> mentor.getCourses().add(course));
        courseService.save(course);

        return "redirect:/courses";
    }
}
