package il.cshaifasweng.OCSFMediatorExample.entities.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "students")
public class Student extends User implements Serializable {


    @OneToMany(mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<ExamExecutionManual> examExecutionsManual = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<ExamExecutionDigital> examExecutionsDigital = new ArrayList<>();





    public  List<ExamExecutionManual> getExamExecutionsManual()
    {
        return  this.examExecutionsManual;
    }

    public List<ExamExecutionDigital> getExamExecutionsDigital() {
        return examExecutionsDigital;
    }

    public void setExamExecutionsManual(List<ExamExecutionManual> examExecutionsManual) {
        this.examExecutionsManual = examExecutionsManual;
    }

    public void setExamExecutionsDigital(List<ExamExecutionDigital> examExecutionsDigital) {
        this.examExecutionsDigital = examExecutionsDigital;
    }


    public void addExamExecutionsDigital(ExamExecutionDigital examExecutionDigital)
    {
        this.examExecutionsDigital.add(examExecutionDigital);
        examExecutionDigital.setStudent(this);
    }

    public void addExamExecutionsManual(ExamExecutionManual examExecutionManual)
    {
        this.examExecutionsManual.add(examExecutionManual);
        examExecutionManual.setStudent(this);
    }


    public int getStudent_id() {
        return getUser_id();
    }




    public List<Grade> getGrades() {
        return grades;
    }



    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }



    public Student() {

    }

    public Student(int id,String firstName, String lastName) {
        this.setUser_id(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.grades = new ArrayList<>();
    }

    public Student(int id,String password,String firstName, String lastName) {
        this.setUser_id(id);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.grades = new ArrayList<>();
    }





}