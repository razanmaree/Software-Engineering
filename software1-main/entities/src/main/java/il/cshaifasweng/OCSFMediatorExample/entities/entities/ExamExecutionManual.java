package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "ExamExecutionsManual")
public class ExamExecutionManual extends ExamExecution implements Serializable {


    @OneToOne
    private WordFile wordFile;


    public WordFile getWordFile() {
        return wordFile;
    }

    public void setWordFile(WordFile wordFile) {
        this.wordFile = wordFile;
    }

    public ExamExecutionManual() {
        // Default constructor
    }

    public ExamExecutionManual(ExamInDrawer exam, Lecturer lecturer,Student student, String code) {

        setStudent(student);
        setExam(exam);
        setStudent(student);

        setLecturer(lecturer);
        setExecution_code(code);
        setExam_duration(exam.getExam_time());

    }
}
