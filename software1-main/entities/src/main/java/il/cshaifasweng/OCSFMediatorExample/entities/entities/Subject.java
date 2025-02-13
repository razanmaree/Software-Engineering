package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int subject_code;
    private String subject_name;

    @OneToMany
    @JoinColumn(name = "subject_code")
    private List<Course> courses =new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "subject_code")
    private List<ExamInDrawer> exams=new ArrayList<>();


    @OneToMany
    @JoinColumn(name = "subject_code")
    private List<SubjectLecturerLink> lecturerLinks = new ArrayList<>();


    public void addSubjectLecturerLink(SubjectLecturerLink link) {
        lecturerLinks.add(link);
    }


    public List<SubjectLecturerLink> getLecturerLinks() {
        return lecturerLinks;
    }

    public void setExams(List<ExamInDrawer> exams) {
        this.exams = exams;
    }

    public List<ExamInDrawer> getExams() {
        return exams;
    }


    public void  addCourse(Course course)
    {
        this.courses.add(course);
        course.setSubject(this);
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }


    public int getSubject_code() {
        return subject_code;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_name() {
        return subject_name;
    }
    public Subject(){}
    public Subject(String name)
    {
        this.subject_name=name;
        this.courses=new ArrayList<>();
    }

    @Override
    public String toString() {
        return subject_name ; // Display the course name in the ComboBox
    }

}
