package com.std.sbb.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model){
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id")Integer id, AnsweForm answeForm){
        Question q = this.questionService.getQuetion(id);
        model.addAttribute("question",q);
        return "question_detail";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("questionForm", new QuestionForm());
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "questio_form";
        }
        Question q = this.questionService.create( questionForm.getSubject(), questionForm.getContent());

        return "redirect:/question/list";
    }
}
