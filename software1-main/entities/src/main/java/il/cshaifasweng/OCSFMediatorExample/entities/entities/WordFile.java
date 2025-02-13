package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "word_files")
public class WordFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wordfile_id;

    @OneToOne
    private ExamExecutionManual examExecutionManual;

    @Lob
    @Column(name = "file_content", nullable = false)
    private byte[] fileContent;

    // Constructors, getters, and setters

    public WordFile() {
    }

    public WordFile(ExamExecutionManual examExecutionManual, byte[] fileContent) {
        this.examExecutionManual = examExecutionManual;
        this.fileContent = fileContent;
    }

    // Getters and setters for other fields


    public int getWordfile_id() {
        return wordfile_id;
    }

    public void setExamExecutionManual(ExamExecutionManual examExecutionManual) {
        this.examExecutionManual = examExecutionManual;
    }

    public ExamExecutionManual getExamExecutionManual() {
        return examExecutionManual;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}

