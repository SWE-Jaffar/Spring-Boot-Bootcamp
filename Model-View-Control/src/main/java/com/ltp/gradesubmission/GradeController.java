package com.ltp.gradesubmission;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {
    
    //creating a list of grades objects
    List<Grade> studentGrades = new ArrayList<>();

    @GetMapping("/grades")
    public String getGrades(Model model){
        //passing to the view
        model.addAttribute("grades", studentGrades);
        return "grades";
    }
     @PostMapping("/handelSubmit")
    public String submitGrade(Grade grade){
        if(getGradeIndex(grade.getId()) != null){
            studentGrades.set(getGradeIndex(grade.getId()), grade);
        }else{
            studentGrades.add(grade);
        }
        // System.out.println(grade);
        //redirecting to the grade page
        return "redirect:/grades";

    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false ) String id){
        Grade grade;
        if(getGradeIndex(id) != null){
            grade = studentGrades.get(getGradeIndex(id));
        }else{
            grade = new Grade();
        }
        // Grade grade = new Grade();
        //passing to the view
        model.addAttribute("grade", grade);
        return "form";
    }
   
    public Integer getGradeIndex(String id){
        for(int i = 0; i < studentGrades.size(); i++){
            //If the name mathces the name in the list, return the index
            if(studentGrades.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    
}
