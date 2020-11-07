package com.codecool.queststore.controller;

import com.codecool.queststore.model.Course;
import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final OrderService orderService;
    private final MentorService mentorService;
    private final CourseService courseService;
    private final StudentQuestService studentQuestService;

    @GetMapping("profile-page")
    public String showStudentProfile(ModelMap model, Principal principal) {
        Student student = studentService.findByUsername(principal.getName());
        studentService.updateRank(student);

        model.addAttribute("studentItems", orderService.findByUserId(student.getId()));
        model.addAttribute("studentOngoingQuests", studentQuestService.findOngoingedByUserId(student.getId()));
        model.addAttribute("studentCompletedQuests", studentQuestService.findCompletedByUserId(student.getId()));
        model.addAttribute("student", student);

        //TODO move to lower level
        model.addAttribute("rank", student.getRank().getName());
        return "student/profile_page";
    }

    @GetMapping("management")
    public String studentManagement(Model model, Principal principal){
        Mentor mentor = mentorService.findByUsername(principal.getName());
        Set<Course> courses = courseService.findByMentorUsername(mentor.getId());
        Set<Student> students = new HashSet<>();
        for (Course course : courses) {
            students.addAll(course.getStudents());
        }
        model.addAttribute("students", students);
        return "/mentor/student_management";
    }

    @GetMapping("{id}/promote")
    public String promoteStudent(@PathVariable(name="id") Long id){
        studentService.promoteStudent(id);
        return "redirect:/student/management";
    }
}
