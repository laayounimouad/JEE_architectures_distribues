package com.laayouni.studentmanagement.web;

import com.laayouni.studentmanagement.entities.Student;
import com.laayouni.studentmanagement.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentRepository studentRepository;

    @GetMapping(path="/")
    public  String home(){
        return "redirect:/index";
    }
    @GetMapping(path="/index")
    public String student(Model model){
        List<Student> listStudents=studentRepository.findAll();
        model.addAttribute("listStudent",listStudents);
        return "students";
    }
    @GetMapping(path = "/formStudents")
    public String formPatients(Model model){
        model.addAttribute("student",new Student());
        return "formStudents";
    }
    @PostMapping(path = "/save")
    public String save(Model model, @Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()) return "formStudents";
        studentRepository.save(student);
        return "redirect:/index";
    }
}
