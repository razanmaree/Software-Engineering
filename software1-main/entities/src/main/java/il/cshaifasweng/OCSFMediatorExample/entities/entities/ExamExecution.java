package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "examExecutions")
public class ExamExecution implements Serializable {



    /////////added by wesal 03/08

    @ManyToOne
    LecturerExamexecutionsLink lecturerExamexecutionsLink;

    public LecturerExamexecutionsLink getLecturerExamexecutionsLink() {
        return lecturerExamexecutionsLink;
    }

    public void setLecturerExamexecutionsLink(LecturerExamexecutionsLink lecturerExamexecutionsLink) {
        this.lecturerExamexecutionsLink = lecturerExamexecutionsLink;
    }

    ///////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int execution_id;

    private String execution_code;  //4 digits
    private int exam_duration;

    boolean finished =false;

    boolean approved=false;


    @ManyToOne
    private ExamInDrawer exam;

    @ManyToOne
    private Student student;


    @ManyToOne
    private Lecturer lecturer;

    ZonedDateTime start_time ;

    ZonedDateTime end_time;

    @OneToMany(mappedBy = "examExecution", cascade = CascadeType.ALL)
    private List<TimeExtensionRequest> timeExtensionRequests = new ArrayList<>();


    public ZonedDateTime getEnd_time() {
        return end_time;
    }

    public ZonedDateTime getStart_time() {
        return start_time;
    }

    public void setEnd_time(ZonedDateTime end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(ZonedDateTime start_time) {
        this.start_time = start_time;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }


    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public ExamInDrawer getExam() {
        return exam;
    }

    public void setExam(ExamInDrawer exam) {
        this.exam = exam;
    }


    public int getExecution_id() {
        return execution_id;
    }

    public void AddTimeExtensionRequest(TimeExtensionRequest request)
    {
        this.timeExtensionRequests.add(request);
    }

    public void setExecution_id(int id) {
        this.execution_id = id;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public String getExecution_code() {
        return execution_code;
    }

    public void setExecution_code(String code) {
        this.execution_code = code;
    }

    public int getExam_duration() {
        return exam_duration;
    }

    public void setExam_duration(int duration) {
        this.exam_duration = duration;
    }

    public List<TimeExtensionRequest> getTimeExtensionRequests() {
        return timeExtensionRequests;
    }

    public void setTimeExtensionRequests(List<TimeExtensionRequest> timeExtensionRequests) {
        this.timeExtensionRequests = timeExtensionRequests;
    }


    public ExamExecution() {

    }

    public ExamExecution(ExamInDrawer exam, Lecturer lecturer, Student student, String code) {
        this.student = student;
        this.lecturer = lecturer;
        this.execution_code = code;
        this.exam_duration=exam.getExam_time();
        this.exam=exam;
    }





    //////////////////

    public String getLecturerExecutedTheExamName(){
        return  lecturer.getFirstName()+" "+lecturer.getLastName();
    }

    public Integer getExamTime(){
        return this.exam_duration;
    }

    public String getCourseName(){
        return this.exam.getCourse_name();
    }

    public String getSubjectName(){
        return this.exam.getSubject_name();
    }



    ////////////////////


    public int getStudent_id() {
        return student.getUser_id();
    }

    public String getFirstName() {
        return student.getFirstName();
    }

    public String getLastName() {
        return student.getLastName();
    }


    public String getTimeLift() {
        ZonedDateTime currentTimeInJerusalem = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem"));
        Duration duration = Duration.between(currentTimeInJerusalem, end_time);
        long secondsLeft = duration.getSeconds();

        // Ensure that the time left is positive (end_time is in the future)
        if (secondsLeft < 0) {
            return "00:00:00";
        }

        long hours = secondsLeft / 3600;
        long minutes = (secondsLeft % 3600) / 60;
        long seconds = secondsLeft % 60;

        // Format the time left as a string
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    public String getEndTime() {
        LocalTime endTimeLocalTime = end_time.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return endTimeLocalTime.format(formatter);
    }

    public String getStartTime() {
        LocalTime endTimeLocalTime = start_time.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return endTimeLocalTime.format(formatter);
    }


}
