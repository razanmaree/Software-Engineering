package il.cshaifasweng.OCSFMediatorExample.entities.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @OneToMany
    private List<CourseLecturerLink> coursesLinks = new ArrayList<>();


    public List<CourseLecturerLink> getCoursesLinks() {
        return coursesLinks;
    }

    public void setCoursesLinks(List<CourseLecturerLink> coursesLinks) {
        this.coursesLinks = coursesLinks;
    }



    public void addCoursesLinks(CourseLecturerLink link)
    {
        this.coursesLinks.add(link);
    }








    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "course_id")
    private String name;



    @OneToMany(mappedBy = "course")
    private List<Grade> grades = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private int course_average;
    private int course_median;

    @OneToMany(mappedBy = "question")
    private List<CourseQuestionInDrawerLink> courseQuestionInDrawerLinks = new ArrayList<>();



    public Course() {
    }

    public Course(String name) {
        this.name = name;
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


    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public int getCourse_average() {
        return course_average;
    }

    public void setCourse_average(int course_average) {
        this.course_average = course_average;
    }

    public int getId() {
        return id;
    }

    public int getCourse_median() {
        return course_median;
    }

    public void setCourse_median(int course_median) {
        this.course_median = course_median;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return name; // Display the course name in the ComboBox
    }




}
