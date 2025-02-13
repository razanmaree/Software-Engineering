package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;

import java.util.ArrayList;
import java.util.List;

public class PageData {


    ////for logout/////

    private String currentControllerName;

    public String getCurrentControllerName() {
        return currentControllerName;
    }

    public void setCurrentControllerName(String currentControllerName) {
        this.currentControllerName = currentControllerName;
    }


    //for ViewDataStudentController AND ViewCoursesDataLecturerController//////
    private boolean TeacherSelectExamToImplement;
    public void setTeacherSelectExamToImplement(boolean teacherSelectExamToImplement) {
        TeacherSelectExamToImplement = teacherSelectExamToImplement;
    }
    public boolean isTeacherSelectExamToImplement() {
        return TeacherSelectExamToImplement;
    }

    private boolean first_time_enter_viewCoursesDataLecturer = true;

    public boolean isFirst_time_enter_viewCoursesDataLecturer() {
        return first_time_enter_viewCoursesDataLecturer;
    }

    public void setFirst_time_enter_viewCoursesDataLecturer(boolean first_time_enter_viewCoursesDataLecturer) {
        this.first_time_enter_viewCoursesDataLecturer = first_time_enter_viewCoursesDataLecturer;
    }

    private boolean flag;
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public boolean isFlag() {
        return flag;
    }
///////////////////////////////

    /////////// for ExamResultsForMangerController/////

    private boolean is_first_time_enter_from_manager=true;

    public boolean isIs_first_time_enter_from_manager() {
        return is_first_time_enter_from_manager;
    }

    public void setIs_first_time_enter_from_manager(boolean is_first_time_enter_from_manager) {
        this.is_first_time_enter_from_manager = is_first_time_enter_from_manager;
    }

    ///////// for updating an exam ////////////////////////////////////////////////////

    private boolean first_time_enter_choose_questions =true;

    public boolean isFirst_time_enter_choose_questions() {
        return first_time_enter_choose_questions;
    }

    public void setFirst_time_enter_choose_questions(boolean first_time_enter_choose_questions) {
        this.first_time_enter_choose_questions = first_time_enter_choose_questions;
    }

    private boolean exam_update = false;

    public boolean isExam_update() {
        return exam_update;
    }

    public void setExam_update(boolean exam_update) {
        this.exam_update = exam_update;
    }


    /*****************for ExamsRepositoryForManegerController*****************************/

    private int flag_IAmInExamRepositoryForManager=0;

    public int getFlag_IAmInExamRepositoryForManager() {
        return flag_IAmInExamRepositoryForManager;
    }

    public void setFlag_IAmInExamRepositoryForManager(int flag_IAmInExamRepositoryForManager) {
        this.flag_IAmInExamRepositoryForManager = flag_IAmInExamRepositoryForManager;
    }

    /////// for updating a question //////

    private int selected_question_id;

    public int getSelected_question_id() {
        return selected_question_id;
    }

    public void setSelected_question_id(int selected_question_id) {
        this.selected_question_id = selected_question_id;
    }

    private boolean question_update = false;

    public boolean isQuestion_update() {
        return question_update;
    }

    public void setQuestion_update(boolean question_update) {
        this.question_update = question_update;
    }

    ////// for logout ///////////////

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    //////////////////// for tested exam controller ////////////////////////


    private int solved_exam_id;

    public int getSolved_exam_id() {
        return solved_exam_id;
    }

    public void setSolved_exam_id(int solved_exam_id) {
        this.solved_exam_id = solved_exam_id;
    }


    //////view exam in progress controller /////


    int lecturerExamExecutionLink_id = -1;

    public int getLecturerExamExecutionLink_id() {
        return lecturerExamExecutionLink_id;
    }

    public void setLecturerExamExecutionLink_id(int lecturerExamExecutionLink_id) {
        this.lecturerExamExecutionLink_id = lecturerExamExecutionLink_id;
    }


    ///////student controller //////////

    private int examExecutionId = 0;

    public int getExamExecutionId() {
        return examExecutionId;
    }

    public void setExamExecutionId(int examExecutionId) {
        this.examExecutionId = examExecutionId;
    }

    //////////////////////////////////////

    private int teacher_id;

    private int student_id;

    private int manager_id;

    private String user_type; //"teacher" ,"student", "Manager"


    /////////// for create an exam //////////////

    private List<Score> scores = new ArrayList<>();

    private List<QuestionInDrawer> choosenQuestions = new ArrayList<>();


    private Subject selected_subject = null;

    private Course selected_course = null;

    // private ExamInDrawer selected_exam=null;

    private int selected_exam_id = -1;


    ///////////////////////////////////////////////


    public int getSelected_exam_id() {
        return selected_exam_id;
    }

    public void setSelected_exam_id(int selected_exam_id) {
        this.selected_exam_id = selected_exam_id;
    }


    public Course getSelected_course() {
        return selected_course;
    }


    public Subject getSelected_subject() {
        return selected_subject;
    }

    public void setSelected_course(Course selected_course) {
        this.selected_course = selected_course;
    }

    public void setSelected_subject(Subject selected_subject) {
        this.selected_subject = selected_subject;
    }

    public void setChoosenQuestions(List<QuestionInDrawer> choosenQuestions) {
        this.choosenQuestions = choosenQuestions;
    }

    public List<QuestionInDrawer> getChoosenQuestions() {
        return choosenQuestions;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_type() {
        return user_type;
    }

    public PageData() {
    }

    ;
}



