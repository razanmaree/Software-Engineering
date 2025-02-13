package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SubjectLecturerLinks")
public class SubjectLecturerLink implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectLecturerLink_id;


    @ManyToOne
    @JoinColumn(name = "subject_code")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Lecturer lecturer;


    public SubjectLecturerLink(){}

    public SubjectLecturerLink(Subject subject, Lecturer lecturer) {
        this.subject = subject;
        this.lecturer = lecturer;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

}
