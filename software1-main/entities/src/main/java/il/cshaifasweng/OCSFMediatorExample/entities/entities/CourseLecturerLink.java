package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "CourseLecturerLinks")
public class CourseLecturerLink implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseLecturerLink_id;

    @ManyToOne
    Course course;

    @ManyToOne
    Lecturer lecturer;


    public Lecturer getLecturer() {
        return lecturer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public int getCourseLecturerLink_id() {
        return courseLecturerLink_id;
    }

    public CourseLecturerLink(){}

    public CourseLecturerLink(Course course, Lecturer lecturer)
    {
        this.course=course;
        this.lecturer=lecturer;
    }


}
