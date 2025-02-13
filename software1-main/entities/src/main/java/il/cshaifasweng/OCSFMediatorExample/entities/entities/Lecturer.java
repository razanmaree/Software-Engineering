package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "lecturers")
public class Lecturer extends User implements Serializable {



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




    @OneToMany
    List<LecturerExamexecutionsLink> lecturerExamexecutionsLinks=new ArrayList<>();

    public List<LecturerExamexecutionsLink> getLecturerExamexecutionsLinks() {
        return lecturerExamexecutionsLinks;
    }

    public void setLecturerExamexecutionsLinks(List<LecturerExamexecutionsLink> lecturerExamexecutionsLinks) {
        this.lecturerExamexecutionsLinks = lecturerExamexecutionsLinks;
    }




    @OneToMany
    private List<SubjectLecturerLink> subjectLinks = new ArrayList<>();



    public void addSubjectLecturerLink( SubjectLecturerLink link) {
        subjectLinks.add(link);
    }

    public List<SubjectLecturerLink> getSubjectLinks() {
        return subjectLinks;
    }


    public int getId() {
        return getUser_id();
    }

    public void setId(int id) {
        this.setUser_id(id);
    }


    public Lecturer(int id, String password, String firstName, String lastName) {
        this.setPassword(password);
        this.setUser_id(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.coursesLinks = new ArrayList<>();
    }

    public Lecturer() {

    }


}