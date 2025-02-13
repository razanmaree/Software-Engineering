package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Scores")
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int score_id;

    @ManyToOne
    @JoinColumn(name = "exam_code")
    private ExamInDrawer exam;

    @ManyToOne
    @JoinColumn(name = "QuestionInDrawer_id")
    private QuestionInDrawer question;

    private int score;


    public ExamInDrawer getExam() {
        return exam;
    }

    public void setExam(ExamInDrawer exam) {
        this.exam = exam;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public QuestionInDrawer getQuestion() {
        return question;
    }

    public void setQuestion(QuestionInDrawer question) {
        this.question = question;
    }

    public Score(ExamInDrawer exam, QuestionInDrawer question, int score)
    {
        setExam(exam);
        setQuestion(question);
        this.score=score;
    }
    public Score(){}


}
