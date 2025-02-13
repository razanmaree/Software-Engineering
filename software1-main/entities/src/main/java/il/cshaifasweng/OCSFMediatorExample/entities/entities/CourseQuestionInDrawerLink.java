package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CourseQuestionInDrawerLinks")
public class CourseQuestionInDrawerLink implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int score_id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "QuestionInDrawer_id")
    private QuestionInDrawer question;



    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public QuestionInDrawer getQuestion() {
        return question;
    }

    public void setQuestion(QuestionInDrawer question) {
        this.question = question;
    }

    public CourseQuestionInDrawerLink(Course course, QuestionInDrawer question)
    {
        setCourse(course);
        setQuestion(question);

    }
    public CourseQuestionInDrawerLink(){}


}
