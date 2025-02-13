package il.cshaifasweng.OCSFMediatorExample.entities.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ExamsInDrawer")
public class ExamInDrawer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exam_code;




    private int exam_time;

    private String author_name;

    private int author_id;

    private String teacherComments;

    private String studentsComments;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Course course ;

    @OneToMany(mappedBy = "exam")
    private List<Score> scores = new ArrayList<>();


    @OneToMany
    @JoinColumn(name = "exam_code")
    private List<ExamExecution> exams =new ArrayList<>();


    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void addScore(Score score)
    {
        scores.add(score);
    }



    public String getStudentsComments() {
        return studentsComments;
    }

    public void setStudentsComments(String studentsComments) {
        this.studentsComments = studentsComments;
    }

    public String getTeacherComments() {
        return teacherComments;
    }

    public void setTeacherComments(String teacherComments) {
        this.teacherComments = teacherComments;
    }

    public ExamInDrawer(Subject subject,Course course,int exam_time,String author_name,String studentsComments,String teacherComments){


        this.author_name=author_name;
        this.subject=subject;
        this.course=course;
        this.exam_time=exam_time;
        this.studentsComments=studentsComments;
        this.teacherComments=teacherComments;
    }



    public String getSubject_name(){return subject.getSubject_name();}

    public String getCourse_name(){return course.getName();}

    public int getExam_code() {
        return exam_code;
    }

    public int getExam_time() {
        return exam_time;
    }


    public String getAuthor_name() {
        return author_name;
    }

    public Course getCourse() {
        return course;
    }



    public Subject getSubject() {
        return subject;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setExam_code(int exam_code) {
        this.exam_code = exam_code;
    }


    public void setExam_time(int exam_time) {
        this.exam_time = exam_time;
    }



    public void setSubject(Subject subject) {
        this.subject = subject;
    }


    public ExamInDrawer(){}

}
