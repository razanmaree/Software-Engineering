package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LecturerExamexecutionsLinks")
public class LecturerExamexecutionsLink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lecturerExamexecutionsLink_id;

    @OneToMany
    List<ExamExecution> examExecutions = new ArrayList<>();

    @ManyToOne
    Lecturer lecturer;

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public void setExamExecutions(List<ExamExecution> examExecutions) {
        this.examExecutions = examExecutions;
    }

    public List<ExamExecution> getExamExecutions() {
        return examExecutions;
    }

    public int getLecturerExamexecutionsLink_id() {
        return lecturerExamexecutionsLink_id;
    }

    public LecturerExamexecutionsLink(){}

   public LecturerExamexecutionsLink(Lecturer lecturer,List<ExamExecution> examExecutions)
   {
       this.lecturer=lecturer;
       this.examExecutions =examExecutions;
   }
}