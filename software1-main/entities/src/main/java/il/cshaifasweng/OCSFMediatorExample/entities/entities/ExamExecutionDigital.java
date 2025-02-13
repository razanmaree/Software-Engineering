package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ExamExecutionsDigital")
public class ExamExecutionDigital extends ExamExecution implements Serializable {


    @OneToOne
    private SolvedExam solved_exam;


    public void setSolved_exam(SolvedExam solved_version) {
        this.solved_exam = solved_version;
    }

    public SolvedExam getSolved_exam() {
        return solved_exam;
    }



    public ExamExecutionDigital() {
        // Default constructor
    }

    public ExamExecutionDigital(ExamInDrawer exam, Lecturer lecturer,Student student, String code) {

        setStudent(student);
        setExam(exam);
        setStudent(student);

        setLecturer(lecturer);
        setExecution_code(code);
        setExam_duration(exam.getExam_time());

    }

    public boolean isApproved() {
        return solved_exam.isApproved();
    }


//    public ExamExecutionDigital(int id, Student student, Lecturer lecturer, int code, int duration,
//                                ExamInDrawer exam, SolvedExam solved_exam){
//        this. setExecution_id(id);
//        this.setStudent(student);
//        this.setLecturer(lecturer);
//        this.setExecution_code(code);
//        this.setExam_duration(duration);
//        this.setExam(exam);
//        this.solved_exam = solved_exam;
//
//    }
}
