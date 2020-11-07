package com.codecool.queststore.controller;

import com.codecool.queststore.dto.CourseDto;
import com.codecool.queststore.model.Course;
import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.service.CourseService;
import com.codecool.queststore.service.MentorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final MentorService mentorService;
    private static final String REDIRECT_TO_COURSES = "redirect:/courses";

    @GetMapping
    public String index(Model model) {
        model.addAttribute("courseDto", new CourseDto());
        return "admin/classes";
    }

    @PostMapping
    public String addNew(@ModelAttribute @Valid CourseDto courseDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/classes";
        }
        Course course = new Course();
        course.setName(courseDto.getName());
        courseService.save(course);
        return REDIRECT_TO_COURSES;
    }

    @GetMapping("/edit/{id}")
    public String showClass(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.findById(id));
        model.addAttribute("mentors", mentorService.getAll());

        return "admin/class-item";
    }

    @PostMapping("/edit/{id}")
    public String assignMentorsToClass(@PathVariable("id") Long id,
                                       @RequestParam(value = "mentor_id", defaultValue = "") String[] mentorsIds) {

        Course course = courseService.findById(id);
        course.removeMentorsFromCourse();
        List<Mentor> mentors = Arrays.stream(mentorsIds)
                                    .map(Long::valueOf)
                                    .map(mentorService::findById)
                                    .collect(Collectors.toList());

        course.getMentors().addAll(mentors);
        mentors.forEach(mentor -> mentor.getCourses().add(course));
        courseService.save(course);

        return REDIRECT_TO_COURSES;
    }


    @GetMapping("/{id}")
    public String deleteClass(@PathVariable Long id) {
        Course course = courseService.findById(id);
        course.removeMentorsFromCourse();
        course.removeStudentsFromCourse();
        courseService.delete(course);
        return REDIRECT_TO_COURSES;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("courses", courseService.getAll());
    }
}
