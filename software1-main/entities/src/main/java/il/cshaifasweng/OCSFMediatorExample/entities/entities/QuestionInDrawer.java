package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QuestionsInDrawer")
public class QuestionInDrawer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int QuestionInDrawer_id;

    private String text;

    private String first_answer;
    private String second_answer;
    private String third_answer;
    private String fourth_answer;

    private int rightAnswer;


    private int temporary_score = 0;

    @OneToMany(mappedBy = "question")
    private List<Score> scores = new ArrayList<>();

    @ManyToOne
    private Subject subject;


    @OneToMany
    private List<SolvedQuestion> solvedQuestions=new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<CourseQuestionInDrawerLink> courseQuestionInDrawerLinks = new ArrayList<>();





    public List<SolvedQuestion> getSolvedQuestions() {
        return solvedQuestions;
    }

    public void setSolvedQuestions(List<SolvedQuestion> solvedQuestions) {
        this.solvedQuestions = solvedQuestions;
    }



    public void setCourseQuestionInDrawerLinks(List<CourseQuestionInDrawerLink> courseQuestionInDrawerLinks) {
        this.courseQuestionInDrawerLinks = courseQuestionInDrawerLinks;
    }

    public List<CourseQuestionInDrawerLink> getCourseQuestionInDrawerLinks() {
        return courseQuestionInDrawerLinks;
    }


    public void addCourseQuestionInDrawerLink(CourseQuestionInDrawerLink courseQuestionInDrawerLink) {
        courseQuestionInDrawerLinks.add(courseQuestionInDrawerLink);
    }


    public QuestionInDrawer(Subject subject, String text, String option1, String option2, String option3, String option4, int rightAnswer) {

        this.subject = subject;
        this.text = text;
        this.rightAnswer = rightAnswer;

        this.first_answer = option1;
        this.second_answer = option2;
        this.third_answer = option3;
        this.fourth_answer = option4;

    }


    public QuestionInDrawer() {
    }


    public int getTemporary_score() {
        return temporary_score;
    }

    public void setTemporary_score(int temporary_score) {
        this.temporary_score = temporary_score;
    }


    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void addScore(Score score) {
        scores.add(score);
    }


    public int getQuestionInDrawer_id() {
        return QuestionInDrawer_id;
    }

    public void setQuestionInDrawer_id(int questionInDrawer_id) {
        QuestionInDrawer_id = questionInDrawer_id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


    public String getFirst_answer() {
        return first_answer;
    }

    public void setFirst_answer(String first_answer) {
        this.first_answer = first_answer;
    }

    public String getSecond_answer() {
        return second_answer;
    }

    public void setSecond_answer(String second_answer) {
        this.second_answer = second_answer;
    }

    public String getThird_answer() {
        return third_answer;
    }

    public void setThird_answer(String third_answer) {
        this.third_answer = third_answer;
    }

    public String getFourth_answer() {
        return fourth_answer;
    }

    public void setFourth_answer(String fourth_answer) {
        this.fourth_answer = fourth_answer;
    }

}
