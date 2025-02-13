package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "SolvedExam")
public class SolvedExam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private ExamExecutionDigital examExecutionDigital;

    @OneToMany
    List<SolvedQuestion> solvedQuestions = new ArrayList<>();

    private String studentText;

    private int duration;

    private boolean selfSubmit;

    Date date;

    private char checkSymbol;

    private int finalScore = 0;

    private String lecturerText;

    boolean approved = false;

    ////////////////////////////////////////////
    //added by Becky
  //  private String exam_actual_time;

    public String getExam_execution_code(){
        return examExecutionDigital.getExecution_code();
    }

    public String getExam_actual_time() {

            if (examExecutionDigital.getStart_time() != null && examExecutionDigital.getEnd_time() != null) {
                Duration duration = Duration.between(examExecutionDigital.getStart_time(), examExecutionDigital.getEnd_time());
                long hours = duration.toHours();
                long minutes = duration.toMinutesPart();
                long seconds = duration.toSecondsPart();

                return String.format("%02d:%02d:%02d", hours, minutes, seconds);
            }
            return "00:00:00"; // Return this format if start time or end time is not set

    }
//    //public void setExam_actual_time(int time) {
//        exam_actual_time = time;
//    }
    ////////////////////////////////////////////////


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setExamExecutionDigital(ExamExecutionDigital examExecutionDigital) {
        this.examExecutionDigital = examExecutionDigital;
    }

    public ExamExecutionDigital getExamExecutionDigital() {
        return examExecutionDigital;
    }


    public List<SolvedQuestion> getSolvedQuestions() {
        return solvedQuestions;
    }

    public void setSolvedQuestions(List<SolvedQuestion> solvedQuestions) {
        this.solvedQuestions = solvedQuestions;
    }

    public SolvedExam() {
    }

    public SolvedExam(ExamExecutionDigital examExecutionDigital, String studentText,
                      Date date, int duration, boolean selfSubmit) {
        this.examExecutionDigital = examExecutionDigital;
        this.studentText = studentText;
        this.date = date;
        this.duration = duration;
        this.selfSubmit = selfSubmit;

    }

    public boolean isSelfSubmit() {
        return selfSubmit;
    }

    public void setSelfSubmit(boolean selfSubmit) {
        this.selfSubmit = selfSubmit;
    }

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }


    public void setStudentText(String studentText) {
        this.studentText = studentText;
    }


    public String getLecturerText() {
        return lecturerText;
    }

    public void setLecturerText(String lecturerText) {
        this.lecturerText = lecturerText;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public void setFinalScore(int finalScore) {

        this.finalScore = finalScore;
    }

    public char getCheckSymbol() {
        return checkSymbol;
    }

    ;

    public void setCheckSymbol(char checkSymbol) {
        this.checkSymbol = checkSymbol;
    }

    ;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getStudent_id() {
        return this.examExecutionDigital.getStudent_id();
    }

    public String getFirstName() {
        return this.examExecutionDigital.getFirstName();
    }

    public String getLastName() {
        return this.examExecutionDigital.getLastName();
    }

    public int getFinalScore() {
        return finalScore;
    }

    public String getStudentText() {
        return studentText;
    }
}

