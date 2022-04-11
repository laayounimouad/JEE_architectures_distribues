package com.laayouni.studentmanagement.web;

import com.laayouni.studentmanagement.entities.Student;
import com.laayouni.studentmanagement.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String student(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Student> pageStudents=studentRepository.findByFirstName(keyword,PageRequest.of(page,5));
        model.addAttribute("listStudent",pageStudents);
        model.addAttribute("pages",new int[pageStudents.getTotalPages()]);
        model.addAttribute("keyword",keyword);
        model.addAttribute("currentPage",page);
        //model.addAttribute("currentPage",page);
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
