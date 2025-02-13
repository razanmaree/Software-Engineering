package il.cshaifasweng.OCSFMediatorExample.entities.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "timeExtensionRequests")
public class TimeExtensionRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int request_id;

    @ManyToOne
    private ExamExecution examExecution;


    private String request_body;

    private int extension_time;



    public TimeExtensionRequest(int extension_time,String request_body,ExamExecution examExecution)
    {
        this.extension_time=extension_time;
        this.request_body=request_body;
        this.examExecution=examExecution;
      //  examExecution.AddTimeExtensionRequest(this);
    }

    public TimeExtensionRequest(){}

    public String getExecution_code() {
        return this.examExecution.getExecution_code();
    }



    public int getStudent_id(){return this.examExecution.getStudent().getStudent_id();}

    public String getFirstName ()
    {
        return this.examExecution.getStudent().getFirstName();
    }

    public int getExtension_time() {
        return extension_time;
    }


    public int getExecution_duration() {
        return this.examExecution.getExam_duration();
    }

    public void setExamExecution(ExamExecution examExecution) {
        this.examExecution = examExecution;
    }

    public void setExtension_time(int extension_time) {
        this.extension_time = extension_time;
    }

    public int getRequest_id() {
        return request_id;
    }

    public String getRequest_body() {
        return request_body;
    }

    public void setRequest_body(String request_body) {
        this.request_body = request_body;
    }

    public ExamExecution getExamExecution() {
        return examExecution;
    }



}
