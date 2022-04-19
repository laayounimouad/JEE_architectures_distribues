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
        return "redirect:/user/index";
    }
    @GetMapping(path="/user/index")
    public String student(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Student> pageStudents=studentRepository.findByFirstNameContains(keyword,PageRequest.of(page,5));
        //Page<Student> pageStudents=studentRepository.findAll(PageRequest.of(page,5));
        model.addAttribute("listStudent",pageStudents.getContent());
        model.addAttribute("pages",new int[pageStudents.getTotalPages()]);
        model.addAttribute("keyword",keyword);
        model.addAttribute("currentPage",page);
        return "students";
    }
    @GetMapping(path = "/admin/formStudents")
    public String formPatients(Model model){
        model.addAttribute("student",new Student());
        return "formStudents";
    }
    @PostMapping(path = "/admin/save")
    public String save(Model model, @Valid Student student, BindingResult bindingResult,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasFieldErrors()) return "formStudents";
        studentRepository.save(student);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping(path="/admin/update")
    public String updatePatients(Model model, Long id,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "") String keyword){
        Student student= studentRepository.findById(id).orElse(null);//.get();
        if(student==null)throw new RuntimeException("Student does not exist");
        model.addAttribute("student",student);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "updateStudents";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(defaultValue = "") String keyword){
        studentRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

}