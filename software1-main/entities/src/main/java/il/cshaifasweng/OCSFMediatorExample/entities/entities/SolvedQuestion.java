package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "SolvedQuestion")
public class SolvedQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SolvedQuestion_id;

    @ManyToOne
    private QuestionInDrawer question;


    @ManyToOne
    private SolvedExam solvedExam;

    private int answer;

    private int grade;

    private int score;

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public SolvedExam getSolvedExam() {
        return solvedExam;
    }

    public void setSolvedExam(SolvedExam solvedExam) {
        this.solvedExam = solvedExam;
    }

    public SolvedQuestion(){}

    public SolvedQuestion(QuestionInDrawer question,int answer) {
        this.question=question;
        this.answer=answer;
    }

    public SolvedQuestion(QuestionInDrawer question,int answer,int grade) {
        this.question=question;
        this.answer=answer;
        this.grade=grade;
    }

    public QuestionInDrawer getQuestion() {
        return question;
    }

    public void setQuestion(QuestionInDrawer question) {
        this.question = question;
    }

    public int getSolvedQuestion_id() {
        return SolvedQuestion_id;
    }
    public void setSolvedQuestion_id(int solvedQuestion_id) {
        SolvedQuestion_id = solvedQuestion_id;
    }

    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
