package il.cshaifasweng.OCSFMediatorExample.entities;

import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class Message implements Serializable {


    /////for data refresh in listof students who execute an examm///////

    private int LecturerExamExecutionLink_id =-1;

    public void setLecturerExamExecutionLink_id(int lecturerExamExecutionLink_id) {
        LecturerExamExecutionLink_id = lecturerExamExecutionLink_id;
    }

    public int getLecturerExamExecutionLink_id() {
        return LecturerExamExecutionLink_id;
    }
    //////////////for refreshing data //////////

    boolean forRefresh=false;

    public boolean isForRefresh() {
        return forRefresh;
    }

    public void setForRefresh(boolean forRefresh) {
        this.forRefresh = forRefresh;
    }

    //for ViewDataStudentController AND ViewCoursesDataLecturerController//////
    private Course course;
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    private int examCode;
    public void setExamCode(int examCode) {
        this.examCode = examCode;
    }
    public int getExamCode() {
        return examCode;
    }

    List<Integer> statistics;
    public List<Integer> getStatistics() {
        return statistics;
    }
    public void setStatistics(List<Integer> statistics) {
        this.statistics = statistics;
    }
/////////////////////////////////


    //////////////////for examResultsForManagerController///

    private List<SolvedExam> solved_exams_from_server;
    public void setSolved_exams_from_server(List<SolvedExam> exams) { this.solved_exams_from_server = exams; }

    public List<SolvedExam> getSolved_exams_from_server() { return solved_exams_from_server; }


    /*********************for ExamsRepositoryForManegerController ***************************/
    private int subject_code_for_manager;

    public int getSubject_code_for_manager() {
        return subject_code_for_manager;
    }

    public void setSubject_code_for_manager(int subject_code_for_manager) {
        this.subject_code_for_manager = subject_code_for_manager;
    }

    private List<Subject> subject_list_for_manager;

    public List<Subject> getSubject_list_for_manager() {
        return subject_list_for_manager;
    }
    public void setSubject_list_for_manager(List<Subject> subject_list_for_manager) {
        this.subject_list_for_manager = subject_list_for_manager;
    }
    private  List<Course> courses_list_for_manager;

    public List<Course> getCourses_list_for_manager() {
        return courses_list_for_manager;
    }

    public void setCourses_list_for_manager(List<Course> courses_list_for_manager) {
        this.courses_list_for_manager = courses_list_for_manager;
    }

    private Course selcted_course_for_manager;

    public Course getSelcted_course_for_manager() {
        return selcted_course_for_manager;
    }

    public void setSelcted_course_for_manager(Course selcted_course_for_manager) {
        this.selcted_course_for_manager = selcted_course_for_manager;
    }
    private  Subject subject_selected_for_manager;

    public Subject getSubject_selected_for_manager() {
        return subject_selected_for_manager;
    }

    public void setSubject_selected_for_manager(Subject subject_selected_for_manager) {
        this.subject_selected_for_manager = subject_selected_for_manager;
    }
    private List<ExamInDrawer> exam_in_drawer_list_for_manager;

    public List<ExamInDrawer> getExam_in_drawer_list_for_manager() {
        return exam_in_drawer_list_for_manager;
    }

    public void setExam_in_drawer_list_for_manager(List<ExamInDrawer> exam_in_drawer_list_for_manager) {
        this.exam_in_drawer_list_for_manager = exam_in_drawer_list_for_manager;
    }

    /************************************************/

    /////////////////////////showQuestionForManagerController/////////////////////////////////

    private List<QuestionInDrawer> questions_list_from_server;
    public void setQuestions_list_from_server (List<QuestionInDrawer> questions) {
        this.questions_list_from_server = questions;
    }

    public List<QuestionInDrawer> getQuestions_list_from_server() {
        return questions_list_from_server;
    }



    ////////////////////teacherCheckExamByImplementionCodeController///

    List<SolvedExam> solvedExams = new ArrayList<>();

    public void setSolvedExams(List<SolvedExam> solvedExams) {
        this.solvedExams = solvedExams;
    }

    public List<SolvedExam> getSolvedExams() {
        return solvedExams;
    }

    ///////TeacherGetSolvedWordFileController/////
    List<ExamExecutionManual> exam_exec_manual;

    public List<ExamExecutionManual> getExam_exec_manual() {
        return exam_exec_manual;
    }

    public void setExam_exec_manual(List<ExamExecutionManual> exam_exec_manual) {
        this.exam_exec_manual = exam_exec_manual;
    }

    WordFile wordFile;

    public void setWordFile(WordFile wordFile) {
        this.wordFile = wordFile;
    }

    public WordFile getWordFile() {
        return wordFile;
    }
///////////////////////////////////////////////////////

    //////////////////// for tested exam controller ////////////////////////

    private int solved_exam_grade;

    public int getSolved_exam_grade() {
        return solved_exam_grade;
    }

    public void setSolved_exam_grade(int solved_exam_grade) {
        this.solved_exam_grade = solved_exam_grade;
    }

    private int solved_exam_id;

    public int getSolved_exam_id() {
        return solved_exam_id;
    }

    public void setSolved_exam_id(int solved_exam_id) {
        this.solved_exam_id = solved_exam_id;
    }

    //////////////////// for execution of manual exam controller////////////

    private String fileContent;

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }



    ///////////////for view students who execute an exam controller ///////////////////////////


    //////////////////////For time extension request /////////////////
    private int extensionTime;

    private String requestBody;

    public void setExtensionTime(int extensionTime) {
        this.extensionTime = extensionTime;
    }

    public int getExtensionTime() {
        return extensionTime;
    }

    public void setRequestBody( String body ){
        this.requestBody = body;
    }

    public String getRequestBody() {
        return requestBody;
    }

    private int link_id;

    public int getLink_id() {
        return link_id;
    }

    public void setLink_id(int link_id) {
        this.link_id = link_id;
    }


    ///////////// for view exam in progress controller////////////////////////////////////////////////////////////////
    List<LecturerExamexecutionsLink> LecturerExamexecutionsLinkList;

    public List<LecturerExamexecutionsLink> getLecturerExamexecutionsLinkList() {
        return LecturerExamexecutionsLinkList;
    }

    public void setLecturerExamexecutionsLinkList(List<LecturerExamexecutionsLink> lecturerExamexecutionsLinkList) {
        LecturerExamexecutionsLinkList = lecturerExamexecutionsLinkList;
    }


    ///for managertimextensionrequests////////////

    private int timeExtensionValue = 0;

    private int manager_id;


    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public int getTimeExtensionValue() {
        return timeExtensionValue;
    }

    public void setTimeExtensionValue(int timeExtensionValue) {
        this.timeExtensionValue = timeExtensionValue;
    }

    ///////for exam exec digital controller//////////

    private boolean is_self_submit;

    private int exam_duration;

    private List<SolvedQuestion> solvedQuestions = new ArrayList<>();

    public void setSolvedQuestions(List<SolvedQuestion> solvedQuestions) {
        this.solvedQuestions = solvedQuestions;
    }

    public List<SolvedQuestion> getSolvedQuestions() {
        return solvedQuestions;
    }

    public boolean isIs_self_submit() {
        return is_self_submit;
    }

    public void setIs_self_submit(boolean is_self_submit) {
        this.is_self_submit = is_self_submit;
    }

    public int getExam_duration() {
        return exam_duration;
    }

    public void setExam_duration(int exam_duration) {
        this.exam_duration = exam_duration;
    }

    //////for student controller////////////////////

    private List<ExamExecution> examExecutions_list_from_server = new ArrayList<>();

    public List<ExamExecution> getExamExecutions_list_from_server() {
        return examExecutions_list_from_server;
    }

    public void setExamExecutions_list_from_server(List<ExamExecution> examExecutions_list_from_server) {
        this.examExecutions_list_from_server = examExecutions_list_from_server;
    }

    ///////////for ChooseStudentsToExam///////////////////
    private String exam_type;

    private String exam_execution_code;

    private List<Student> Students_list_by_course = new ArrayList<>();

    public List<Student> getStudents_list_by_course() {
        return Students_list_by_course;
    }

    public void setStudents_list_by_course(List<Student> students_list_by_course) {
        Students_list_by_course = students_list_by_course;
    }

    public String getExam_execution_code() {
        return exam_execution_code;
    }

    public void setExam_execution_code(String exam_execution_code) {
        this.exam_execution_code = exam_execution_code;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String type) {
        this.exam_type = type;
    }


    ///////////////////

    private List<Score> scores = new ArrayList<>();

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    //////////////////////////////

    private int question_score;

    public int getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(int question_score) {
        this.question_score = question_score;
    }

    //////////////////////////nawras////////////////////

    int question_number;
    private int selectedexam_id;

    private List<QuestionInDrawer> question_list;

    public List<QuestionInDrawer> getQuestion_list() {
        return question_list;
    }

    public void setQuestion_list(List<QuestionInDrawer> question_list) {
        this.question_list = question_list;
    }

    public int getSelectedexam_id() {
        return selectedexam_id;
    }

    public void setSelectedexam_id(int selectedexam_id) {
        this.selectedexam_id = selectedexam_id;
    }

    ExamInDrawer exam_selected;

    public void setExam_selected(ExamInDrawer exam_selected) {
        this.exam_selected = exam_selected;
    }

    public ExamInDrawer getExam_selected() {
        return exam_selected;
    }

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }


    /////////////////////////////////////
    int id;
    LocalDateTime timeStamp;
    String median;
    String message;
    String data;
    int execution_id;
    int studentId;
    String course_name;

    int course_id;


    int grade_to_change;

    int username;
    String password;

    int user_type;

    //RAZAN///////////////////
    String execution_code;
    int studentID;
    List<Subject> subjects;
    String body;
    String first;
    String second;
    String third;
    String fourth;
    Integer score;
    QuestionInDrawer question;
    String subject_name;
    int right_answer;

    List<QuestionInDrawer> allQuestions;

    ExamInDrawer exam;

    List<ExamInDrawer> exams_list_from_server;

    ////////////////////////////////


    private List<Student> students_list_from_server;
    private List<Grade> grades_list_from_server;

    private List<Course> courses_list_from_server;
    private List<Subject> subjects_list_from_server;
    private List<Lecturer> lecturers_list_from_server;

    private Subject subject;

    private List<QuestionInDrawer> questions_to_exam;

    private List<Course> courses_to_question;

    private int author_id;
    private int exam_time;


    private List<Course> filter_by_courses;
    private List<Subject> filter_by_Subject;
    private List<Lecturer> filter_by_Lecturers;

    private int filter_by_range;


    //////////////////////////////////wesal/////////////////////


    private String teacher_comments;

    private String students_comment;

    public String getStudents_comment() {
        return students_comment;
    }

    public String getTeacher_comments() {
        return teacher_comments;
    }

    public void setStudents_comment(String students_comment) {
        this.students_comment = students_comment;
    }

    public void setTeacher_comments(String teacher_comments) {
        this.teacher_comments = teacher_comments;
    }

    private Course course_to_exam;

    private String Controller_name = "";

    public String getController_name() {
        return Controller_name;
    }

    public void setController_name(String controller_name) {
        Controller_name = controller_name;
    }

    public Course getCourse_to_exam() {
        return course_to_exam;
    }

    public void setCourse_to_exam(Course course_to_exam) {
        this.course_to_exam = course_to_exam;
    }

    private int subjectCode;

    private int lecturerId;

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    /////////////////////////////////////////////////////////


    public int getFilter_by_range() {
        return filter_by_range;
    }

    public List<Course> getFilter_by_courses() {
        return filter_by_courses;
    }

    public List<Lecturer> getFilter_by_Lecturers() {
        return filter_by_Lecturers;
    }

    public List<Subject> getFilter_by_Subject() {
        return filter_by_Subject;
    }

    public void setFilter_by_courses(List<Course> filter_by_courses) {
        this.filter_by_courses = filter_by_courses;
    }

    public void setFilter_by_Lecturers(List<Lecturer> filter_by_Lecturers) {
        this.filter_by_Lecturers = filter_by_Lecturers;
    }

    public void setFilter_by_Subject(List<Subject> filter_by_Subject) {
        this.filter_by_Subject = filter_by_Subject;
    }

    public void setFilter_by_range(int filter_by_range) {
        this.filter_by_range = filter_by_range;
    }


    public void setExam_time(int exam_time) {
        this.exam_time = exam_time;
    }

    public int getExam_time() {
        return exam_time;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public List<Course> getCourses_to_question() {
        return courses_to_question;
    }

    public void setCourses_to_question(List<Course> courses_to_question) {
        this.courses_to_question = courses_to_question;
    }

    public List<QuestionInDrawer> getQuestions_to_exam() {
        return questions_to_exam;
    }

    public void setQuestions_to_exam(List<QuestionInDrawer> questions_to_exam) {
        this.questions_to_exam = questions_to_exam;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    /////////////////////////////////////////////

    private List<TimeExtensionRequest> timeExtensionRequests_from_server;

    public List<Lecturer> getLecturers_list_from_server() {
        return lecturers_list_from_server;
    }

    public void setLecturers_list_from_server(List<Lecturer> lecturers_list_from_server) {
        this.lecturers_list_from_server = lecturers_list_from_server;
    }

    public List<ExamInDrawer> getExams_list_from_server() {
        return exams_list_from_server;
    }

    public void setExams_list_from_server(List<ExamInDrawer> exams) {
        this.exams_list_from_server = exams;
    }

    public int getExecution_id() {
        return execution_id;
    }

    ;

    public void setExecution_id(int execution_id) {
        this.execution_id = execution_id;
    }

    public List<TimeExtensionRequest> getTimeExtensionRequests_from_server() {
        return timeExtensionRequests_from_server;
    }

    public void setTimeExtensionRequests_from_server(List<TimeExtensionRequest> timeExtensionRequests_from_server) {
        this.timeExtensionRequests_from_server = timeExtensionRequests_from_server;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getMedian() {
        return median;
    }

    public void setMedian(String median) {
        this.median = median;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getGrade_to_change() {
        return grade_to_change;
    }

    public void setGrade_to_change(int grade_to_change) {
        this.grade_to_change = grade_to_change;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int number) {
        this.studentId = number;
    }

    public Message(String message, List<Student> students, List<Grade> grades) {
        this.students_list_from_server = students;
        this.grades_list_from_server = grades;
        this.message = message;
    }

    public List<Grade> getGrades_list_from_server() {
        return grades_list_from_server;
    }

    public List<Student> getStudents_list_from_server() {

        return this.students_list_from_server;

    }

    public void setGrades_list_from_server(List<Grade> grades_list_from_server) {

        this.grades_list_from_server = grades_list_from_server;
    }

    public void setStudents_list_from_server(List<Student> students_list_from_server) {

        this.students_list_from_server = students_list_from_server;

    }

    public Message(int id, LocalDateTime timeStamp, String message) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public Message(int id, String message) {
        this.id = id;
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.data = null;
    }

    public Message(String message) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.data = null;
    }

    public Message(int id, String message, String data) {
        this.id = id;
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Subject> getSubjects_list_from_server() {
        return subjects_list_from_server;
    }/*nawras*/

    public List<Course> getCourses_list_from_server() {
        return courses_list_from_server;
    }/*nawras*/

    public void setCourses_list_from_server(List<Course> courses_list_from_server) {/*nawras*/

        this.courses_list_from_server = courses_list_from_server;
    }

    public void setSubjects_list_from_server(List<Subject> subjects_list_from_server) {
        this.subjects_list_from_server = subjects_list_from_server;
    }

    //ADDED RAZAN ////////


    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public QuestionInDrawer getQuestion() {
        return question;
    }

    public void setQuestion(QuestionInDrawer question) {
        this.question = question;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(int right_answer) {
        this.right_answer = right_answer;
    }

    ///
    public List<QuestionInDrawer> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(List<QuestionInDrawer> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public ExamInDrawer getExam() {
        return exam;
    }

    public void setExam(ExamInDrawer exam) {
        this.exam = exam;
    }
    ////////////////////////////
}
