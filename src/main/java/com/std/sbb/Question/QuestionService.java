package com.std.sbb.Question;

import com.std.sbb.DataNotException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id){
        Optional<Question> oq = this.questionRepository.findById(id);

        if(oq.isPresent() == false) throw new DataNotException("question not found");

        return  oq.get();
    }

    public Question create(String subject, String content){
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());

        this.questionRepository.save(q);
        return q;
    }
}
