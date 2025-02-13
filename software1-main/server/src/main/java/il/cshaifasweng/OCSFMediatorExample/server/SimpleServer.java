package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.SubscribedClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.server.App.*;
import static il.cshaifasweng.OCSFMediatorExample.server.App.getGradeBySolvedExamId;

public class SimpleServer extends AbstractServer {
    private static ArrayList<SubscribedClient> SubscribersList = new ArrayList<>();

    //private static Session session;

    public SimpleServer(int port) {
        super(port);

    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Message message = (Message) msg;
            String request = message.getMessage();

            System.out.println(request);

            try {

                if (request.isBlank()) {
                    message.setMessage("Error! we got an empty message"); //see simpleclient handle messagefromserver
                    client.sendToClient(message);

                } else if (request.equals("add client")) {
                    SubscribedClient connection = new SubscribedClient(client);
                    SubscribersList.add(connection);
                    message.setMessage("client added successfully");
                    client.sendToClient(message);

                } else if (request.equals("give me the students")) {
                    message.setMessage("i will give you the students");
                    message.setStudents_list_from_server(getAllStudents());
                    client.sendToClient(message);

                } else if (request.equals("give me the student grades")) {
                    message.setMessage("i will give you the student grades");
                    message.setGrades_list_from_server(getGradesByStudentId(message.getStudentId()));
                    client.sendToClient(message);
                } else if (request.equals("change the student grade")) {
                    changeGrade(message.getStudentId(), message.getCourse_id(), message.getGrade_to_change());
                    message.setMessage("i changed the grade");
                    client.sendToClient(message);


                } else if (request.equals("give me all exams")) {
                    message.setExams_list_from_server(getAllExams());
                    message.setMessage("i gave all exams");
                    client.sendToClient(message);

                } else if (request.equals("give me the courses")) {
                    message.setCourses_list_from_server(getAllCourses());
                    message.setMessage("i will give you the courses");
                    client.sendToClient(message);

                } else if (request.equals("login")) {
                    message.setMessage(logIn(message.getUsername(), message.getPassword()));
                    client.sendToClient(message);

                } else if (request.equals("give me time extension requests")) {
                    message.setTimeExtensionRequests_from_server(Get_All_Time_Extension_requests());
                    message.setMessage("i gave you all time extension requests");
                    System.out.println("finished getting the requests");
                    client.sendToClient(message);

                } else if (request.equals("give me the subjects")) {
                    message.setSubjects(getAllSubjects());
                    message.setSubjects_list_from_server(getAllSubjects());
                    message.setMessage("DONE");
                    client.sendToClient(message);

                } else if (request.equals("give me the lecturers")) {
                    message.setLecturers_list_from_server(getlecturers());
                    //  System.out.println("iiiiiiiiiiii");
                    message.setMessage("i will give you the lecturers");
                    client.sendToClient(message);

                } else if (request.equals("CreateAquestion")) {
                    message.setQuestion(addQuestion(message.getSubject(), message.getCourses_to_question(), message.getBody(), message.getFirst(), message.getSecond(), message.getThird(), message.getFourth(), message.getRight_answer()));
                    message.setMessage("DoneCreateTheQuestion");
                    client.sendToClient(message);

                    //added razan 9-8 ShowQuestionForMenager
                    message.setMessage("question added check if it is for you");
                    message.setSubjectCode(message.getQuestion().getSubject().getSubject_code());
                    message.setController_name("ShowQuestionForMangerController");
                    message.setForRefresh(true);
                    sendToAllClients(message);
                    ///////////////////////////////////////////


                    //becky
                    ////can't test this we can only have one lecturer for the same course
                    message.setMessage("a new question is added check if you can use it");
                    message.setSubjectCode(message.getQuestion().getSubject().getSubject_code());
                    message.setCourses_to_question(message.getCourses_to_question());
                    message.setController_name("ChooseQuestionsController");
                    message.setForRefresh(true);
                    sendToAllClients(message);


                    //becky
                    ////can't test this we can only have one lecturer for the same course
                    message.setMessage("a new question is added check if you can use it");
                    message.setSubjectCode(message.getQuestion().getSubject().getSubject_code());
                    message.setCourses_to_question(message.getCourses_to_question());
                    message.setController_name("chooseQuestionToUpdateController");
                    message.setForRefresh(true);
                    sendToAllClients(message);


                } else if (request.equals("give me the questions")) {
                    message.setAllQuestions(getAllQuestions());
                    message.setMessage("finished");
                    client.sendToClient(message);
                } else if (request.equals("add an exam")) {

                    int subjectCode = message.getSubject().getSubject_code();
                    int courseID = message.getCourse_to_exam().getId();

                    AddAnExamToTheDrawer(message.getSubject(), message.getCourse_to_exam(), message.getQuestions_to_exam(), message.getExam_time(), message.getAuthor_id(), message.getTeacher_comments(), message.getStudents_comment());
                    message.setMessage("i added the exam");
                    client.sendToClient(message);

                    //added razan 9-8 ExamRepository

                    message.setMessage("exam added check if it is yours");
                    message.setSubjectCode(subjectCode);
                    message.setCourse_id(courseID);
                    message.setController_name("ExamsRepositoryForManegerController");
                    message.setForRefresh(true);
                    sendToAllClients(message);
                    ////////////////////////////////////

                    //////////

                    message.setMessage("exam added check if it is related to you");
                    message.setSubjectCode(subjectCode);
                    message.setCourse_id(courseID);

                    message.setController_name("TeacherSelectExamToImplementController");
                    message.setForRefresh(true);
                    sendToAllClients(message);
                    ////////////////////////////////////


                } else if (request.equals("give me the subjects for teacher")) {

                    message.setSubjects_list_from_server(getAllSubjectsByTeacherId(message.getLecturerId()));
                    message.setMessage("i gave you the teacher's subjects");
                    client.sendToClient(message);

                } else if (request.equals("give me the courses for teacher")) {

                    message.setCourses_list_from_server(getAllCoursesByTeacherId(message.getLecturerId()));
                    message.setMessage("i gave you the teacher's courses");
                    client.sendToClient(message);

                } else if (request.equals("give Me The Subject Courses")) {

                    // System.out.println("the subject is: "+message.getSubjectCode()+" and the teacher is:"+message.getLecturerId());
                    message.setCourses_list_from_server(getAllCoursesBySubjectCodeAndTeacherId(message.getSubjectCode(), message.getLecturerId()));
                    message.setMessage("i gave you the courses by subject");
                    client.sendToClient(message);

                } else if (request.equals("give me the questions by subject and course")) {
                    message.setAllQuestions(getAllQuestionsBySubjectAndCourse(message.getSubjectCode(), message.getCourse_to_exam().getId()));

                    message.setMessage("i gave you the questions by subject and course");
                    client.sendToClient(message);

                } else if (request.equals("give me the question's list of the selected exam")) {         /*nawras*/
//                    message.setMessage(manualExecution(message.getExecution_id(),message.getStudentID()));
                    message.setScores(getTheSelectedExamQuestionList(message.getSelectedexam_id()));
                    message.setStudents_comment(getTheSelectedExamStudentsComment(message.getSelectedexam_id()));
                    message.setTeacher_comments(getTheSelectedExamTeacherComment(message.getSelectedexam_id()));
                    message.setMessage("get the scores list of the selected exam");
                    client.sendToClient(message);

                } else if (request.equals("give me the exam data by exam_id")) {
                    message.setScores(getTheSelectedExamQuestionList(message.getSelectedexam_id()));
                    message.setStudents_comment(getTheSelectedExamStudentsComment(message.getSelectedexam_id()));
                    message.setTeacher_comments(getTheSelectedExamTeacherComment(message.getSelectedexam_id()));
                    message.setExam_time(getTheSelectedExamTime(message.getSelectedexam_id()));
                    message.setMessage("gave you the exam data by exam_id");
                    client.sendToClient(message);

                } else if (request.equals("give me the students by course")) {


                    message.setStudents_list_by_course(getTheSelectedCourseOfTheExamStudents(message.getSelectedexam_id()));
                    message.setMessage("i gave you all the students that learn this course");
                    client.sendToClient(message);


                } else if (request.equals("Add examExecution to students list")) {

                    addAnExamExecution(message.getSelectedexam_id(), message.getLecturerId(), message.getStudents_list_by_course(), message.getExam_type(), message.getExam_execution_code());
                    message.setMessage("examExecution added to students list");
                    client.sendToClient(message);

                    for (Student student : message.getStudents_list_by_course()) {
                        message.setMessage("exam execution added check if it is for you");
                        message.setStudentId(student.getStudent_id());
                        message.setController_name("StudentController");
                        message.setForRefresh(true);
                        sendToAllClients(message);
                    }


                } else if (request.equals("give me the courses by student and give me the exams execute by student")) {

                    message.setCourses_list_from_server(getCoursesListByStudentId(message.getStudentId()));
                    message.setExamExecutions_list_from_server(getExamExecutionsListByStudentId(message.getStudentId()));

                    message.setMessage("gave you the courses by student and the examExecutions by student");
                    client.sendToClient(message);
                } else if (request.equals("give me the questions and the time by execution id")) {

                    message.setScores(getTheSelectedExamQuestionList(get_exam_by_execId(message.getExecution_id()).getExam_code()));
                    message.setTeacher_comments(getStudentCommentsByExecCode(message.getExecution_id()));

                    /////also update the start_time and the end_time of the exam execution
                    message.setExam_duration(getTheTimeByExecCode(message.getExecution_id()));
                    message.setMessage("got_questions_by_exec_id");
                    client.sendToClient(message);


                    /////need to send that he started an exam
                    message.setMessage("exam execution have been started");
                    message.setController_name("ListOfStudentsWhoExecuteAnExamController");
                    message.setForRefresh(true);
                    message.setLecturerExamExecutionLink_id(getLecturerExamexecutionsLinkByExecution_id(message.getExecution_id()));
                    sendToAllClients(message);

                    message.setMessage("exam execution have been started ExamsInProgress check if you execute it");
                    message.setController_name("ViewExamsInProgressController");
                    message.setForRefresh(true);
                    message.setLecturerId(getLecturerWhoExecutedTheExamByExecutionId(message.getExecution_id()));
                    sendToAllClients(message);

                } else if (request.equals("add solved exam")) {
                    Date date = new Date();
                    addSolvedExam(message.getExecution_id(), message.getSolvedQuestions(), message.getStudents_comment(), date, message.getExam_duration(), message.isIs_self_submit());
                    message.setMessage("Solved exam added");
                    client.sendToClient(message);

                    message.setMessage("exam execution have been ended");
                    message.setController_name("ListOfStudentsWhoExecuteAnExamController");
                    message.setForRefresh(true);
                    message.setLecturerExamExecutionLink_id(getLecturerExamexecutionsLinkByExecution_id(message.getExecution_id()));
                    sendToAllClients(message);


                    message.setMessage("exam execution have been ended ExamsInProgress check if you execute it");
                    message.setController_name("ViewExamsInProgressController");
                    message.setForRefresh(true);
                    message.setLecturerId(getLecturerWhoExecutedTheExamByExecutionId(message.getExecution_id()));
                    sendToAllClients(message);

                    message.setMessage("exam execution have been ended ExamsToApprove check if you execute it");
                    message.setController_name("ViewExamsToApproveController");
                    message.setForRefresh(true);
                    message.setLecturerId(getLecturerWhoExecutedTheExamByExecutionId(message.getExecution_id()));
                    sendToAllClients(message);


                    //Becky
                    message.setMessage("digital exam execution have been ended check if you execute it");
                    message.setController_name("teacherCheckExamByImplementionCodeController");
                    message.setForRefresh(true);
                    message.setLecturerId(getLecturerWhoExecutedTheExamByExecutionId(message.getExecution_id()));
                    sendToAllClients(message);


                } else if (request.equals("is the execution_code correct")) {
                    if (getExamExecutionCodeByExecId(message.getExecution_id()).equals(message.getExam_execution_code())) {
                        message.setMessage("correct execution code");
                    } else {
                        message.setMessage("wrong execution code");
                    }
                    client.sendToClient(message);

                } else if (request.equals("confirm the selected extensions requests")) {

                    for (TimeExtensionRequest extensionRequest : message.getTimeExtensionRequests_from_server()) {

                        message.setTimeExtensionValue(extensionRequest.getExtension_time());  /// setExtensionValue

                        if (extensionRequest.getExamExecution() instanceof ExamExecutionDigital) {
                            message.setController_name("ExecutionDigitalExamController");    ///set controllername (digital|| manual)

                        } else {
                            message.setController_name("ExecutionOfAManualExamController");
                        }

                        message.setExecution_id(extensionRequest.getExamExecution().getExecution_id());   ///set examexecid
                        message.setMessage("you got time extension");

                        updateEndTimeByExecId(extensionRequest.getExamExecution().getExecution_id(), extensionRequest.getExtension_time());

                        deleteExtensionRequest(extensionRequest.getRequest_id());
                        sendToAllClients(message);
                    }

                    for (TimeExtensionRequest extensionRequest : message.getTimeExtensionRequests_from_server()) {
                        message.setMessage("time extension have been confirmed");
                        message.setController_name("ListOfStudentsWhoExecuteAnExamController");
                        message.setForRefresh(true);
                        message.setLecturerExamExecutionLink_id(getLecturerExamexecutionsLinkByExecution_id(
                                extensionRequest.getExamExecution().getExecution_id()));
                        sendToAllClients(message);
                    }

                    Message message1 = new Message("the Extension requests Confirmed successfully");

                    message.setManager_id(message.getManager_id());
                    message1.setController_name("ManagerTimeExtensionController");
                    client.sendToClient(message1);


                } else if (request.equals("give me the exam In drawer and the time by execution id")) {

                    message.setExam_duration(getTheTimeByExecCode(message.getExecution_id()));
                    message.setExam(getExamInDrawerByExecCode(message.getExecution_id()));
                    message.setScores(getTheSelectedExamQuestionList(message.getExam().getExam_code()));
                    message.setMessage("got_exam_and_duration_by_exec_id");
                    client.sendToClient(message);

                    /////need to send that he started an exam
                    message.setMessage("exam execution have been started");
                    message.setController_name("ListOfStudentsWhoExecuteAnExamController");
                    message.setForRefresh(true);
                    message.setLecturerExamExecutionLink_id(getLecturerExamexecutionsLinkByExecution_id(message.getExecution_id()));
                    sendToAllClients(message);

                    message.setMessage("exam execution have been started ExamsInProgress check if you execute it");
                    message.setController_name("ViewExamsInProgressController");
                    message.setForRefresh(true);
                    message.setLecturerId(getLecturerWhoExecutedTheExamByExecutionId(message.getExecution_id()));
                    sendToAllClients(message);


                } else if (request.equals("get exams_execution link by Teacher id")) {  ////

                    List<LecturerExamexecutionsLink> lecturerExamexecutionsLinks = new ArrayList<>();
                    message.setExamExecutions_list_from_server(getExamExecutionsListByLecturerId(message.getLecturerId(), lecturerExamexecutionsLinks));
                    message.setLecturerExamexecutionsLinkList(lecturerExamexecutionsLinks);

                    message.setMessage("exams_execution link given");
                    client.sendToClient(message);
                } else if (message.getMessage().equals("upload the file to database")) {

                    // Get the file content from the message
                    String base64Content = message.getFileContent();
                    // Convert the Base64-encoded string back to a byte array
                    byte[] fileContent = Base64.getDecoder().decode(base64Content);
                    AddWordFilebyExecutionId(message.getExecution_id(), fileContent);
                    message.setMessage("file uploaded");
                    client.sendToClient(message);


                    message.setMessage("exam execution have been ended");
                    message.setController_name("ListOfStudentsWhoExecuteAnExamController");
                    message.setForRefresh(true);
                    message.setLecturerExamExecutionLink_id(getLecturerExamexecutionsLinkByExecution_id(message.getExecution_id()));
                    sendToAllClients(message);


                    message.setMessage("exam execution have been ended ExamsInProgress check if you execute it");
                    message.setController_name("ViewExamsInProgressController");
                    message.setForRefresh(true);
                    message.setLecturerId(getLecturerWhoExecutedTheExamByExecutionId(message.getExecution_id()));
                    sendToAllClients(message);

                    message.setMessage("exam execution have been ended ExamsToApprove check if you execute it");
                    message.setController_name("ViewExamsToApproveController");
                    message.setForRefresh(true);
                    message.setLecturerId(getLecturerWhoExecutedTheExamByExecutionId(message.getExecution_id()));
                    sendToAllClients(message);

                    //Becky
                    message.setMessage("manual exam execution have been ended check if you execute it");
                    message.setController_name("TeacherGetSolvedWordFileController");
                    message.setForRefresh(true);
                    message.setLecturerId(getLecturerWhoExecutedTheExamByExecutionId(message.getExecution_id()));
                    sendToAllClients(message);


                } else if (request.equals("get exams_execution link by Teacher id waiting to be approved")) {
                    List<LecturerExamexecutionsLink> lecturerExamexecutionsLinks = new ArrayList<>();
                    message.setExamExecutions_list_from_server(getExamExecutionsListByLecturerId_approving(message.getLecturerId(), lecturerExamexecutionsLinks));
                    message.setLecturerExamexecutionsLinkList(lecturerExamexecutionsLinks);
                    message.setMessage("exams_execution link to be approved given");
                    client.sendToClient(message);
                } else if (message.getMessage().equals("give me solved questions and questions by soved_exam id")) {

                    List<QuestionInDrawer> questions = new ArrayList<>();

                    message.setSolvedQuestions(getSolvedQuestionsBySolvedExamId(message.getSolved_exam_id(), questions));
                    message.setQuestion_list(questions);
                    message.setSolved_exam_grade(getGradeBySolvedExamId(message.getSolved_exam_id()));
                    message.setStudents_comment(getStudentCommentsBySolvedExamId(message.getSolved_exam_id()));
                    message.setTeacher_comments(getTeacherCommentsBySolvedExamId(message.getSolved_exam_id()));

                    message.setMessage("got_solved_questions_and questions by_solvedExam_id");
                    client.sendToClient(message);
                } else if (request.equals("change solved exam grade")) {

                    message.setGrade_to_change(ChangeGradeBySolvedExamId(message.getSolved_exam_id(), message.getGrade_to_change()));
                    message.setMessage("solved exam grade Changed");
                    client.sendToClient(message);
                } else if (request.equals("add comments to solve exam")) {

                    AddCommentsBySolvedExamId(message.getSolved_exam_id(), message.getTeacher_comments());
                    message.setMessage("comments were added");
                    client.sendToClient(message);
                } else if (request.equals("get Exam Executions list by link_id")) {
                    message.setExamExecutions_list_from_server(getExamExecutionsList_ByLinkId(message.getLink_id()));
                    message.setMessage("Exam executions list by link_id given");
                    client.sendToClient(message);
                } else if (request.equals("GIVE ME THE EXAMS EXECUTION MANUAL BY LINK ID")) {
                    message.setExam_exec_manual(getExamExecutionsList_ByLinkId_Manual(message.getLink_id()));
                    message.setMessage("EXAMS EXECUTION MANUAL by link_id given");
                    client.sendToClient(message);
                } else if (request.equals("send time extension request")) {
                    AddAnTimeExtensionRequest(message.getExamExecutions_list_from_server(), message.getExtensionTime(), message.getRequestBody());
                    message.setMessage("time extension request sent to manger");
                    client.sendToClient(message);


                    message.setMessage("time extension request were added");
                    message.setController_name("ManagerTimeExtensionController");
                    message.setForRefresh(true);
                    sendToAllClients(message);


                } else if (request.equals("get solved Exams list by link_id")) {
                    message.setSolvedExams(getsolvedExamsList_ByLinkId(message.getLink_id()));
                    message.setMessage("solved exams by link_id given");
                    client.sendToClient(message);

                } else if (request.equals("approve_solved_exam")) {
                    approveSolvedExamById(message.getSolved_exam_id());
                    message.setMessage("solved exam approved");
                    client.sendToClient(message);

                    //added razan 9-8
                    //for(Student student:message.getStudents_list_by_course())
                    System.out.println("HERE IN SIMPLE SERVER 1");
                    System.out.println("HERE IN SIMPLE SERVER 1 check " + message.getSolvedExams().size());
                    for (SolvedExam solvedExam : message.getSolvedExams()) {
                        System.out.println("HERE IN SIMPLE SERVER 2 CHECK " + solvedExam.getExamExecutionDigital().getExam().getExam_code());
                        message.setMessage("exam aproved check if it is yours");
                        // message.setStudentId(solvedExam.getStudent_id());
                        message.setStudentId(solvedExam.getExamExecutionDigital().getStudent().getUserId());
                        message.setController_name("ViewDataStudentController");
                        message.setForRefresh(true);
                        sendToAllClients(message);
                    }
                    /////////////////

                    //added razan 9-8 ViewCoursesDataLecturerController
                    System.out.println("HERE IN SIMPLE SERVER LECTURER");
                    for (SolvedExam solvedExam : message.getSolvedExams()) {
                        System.out.println("HERE IN SIMPLE SERVER LECTURER CHECK " + solvedExam.getExamExecutionDigital().getExam().getExam_code());
                        message.setMessage("exam aproved check if it is yours Lecturer");
                        message.setAuthor_id(solvedExam.getExamExecutionDigital().getExam().getAuthor_id());
                        message.setController_name("ViewCoursesDataLecturerController");
                        message.setForRefresh(true);
                        sendToAllClients(message);
                    }
                    //////////////////////////////////////////

                    //added razan 9-8 Exam Results
                    for (SolvedExam solvedExam : message.getSolvedExams()) {
                        message.setMessage("exam aproved check if it is yours Maneger");
                        message.setExamCode(solvedExam.getExamExecutionDigital().getExam().getExam_code());
                        message.setController_name("ExamResultsForMangerController");
                        message.setForRefresh(true);
                        sendToAllClients(message);
                    }

                    //////////////////////////////////


                } else if (request.equals("GIVE ME THE SOLVED EXAM BY STUDENT_ID AND COURSE_ID")) {
                    message.setSolvedExams(getApprovedSolvedExamsbyCourseIdAndStudentId(message.getCourse_id(), message.getStudentID()));
                    message.setMessage("SOLVED EXAMS BY STUDENT_ID AND COURSE_ID GIVEN");
                    client.sendToClient(message);
                } else if (request.equals("LogOut")) {

                    logOut(message.getUsername());
                    message.setMessage("logOutConfirmed");
                    client.sendToClient(message);

                } else if (request.equals("give me the courses by subjects for teacher")) {
                    message.setCourses_list_from_server(getCoursesListByLecturerIdAndSubjectsFilter(message.getLecturerId(), message.getSubjects()));
                    message.setMessage("gave you the courses by subjects for teacher");
                    client.sendToClient(message);

                } else if (request.equals("give me the exams by teacher all subjects and courses")) {

                    message.setExams_list_from_server(getAllExamsByAllSubjectsAndCourses(message.getLecturerId()));
                    message.setMessage("i gave you the filtered exams");
                    client.sendToClient(message);

                } else if (request.equals("filterBy")) {

                    message.setExams_list_from_server(getAllExamsByFilter(
                            message.getLecturerId(),
                            message.getFilter_by_Subject(),
                            message.getFilter_by_courses(),
                            message.getFilter_by_Lecturers(),
                            message.getFilter_by_range()
                    ));
                    message.setMessage("i gave you the filtered exams");
                    client.sendToClient(message);

                } else if (request.equals("give me the question by it's id")) {

                    message.setQuestion(getQuestionById(message.getQuestion_number()));
                    // message.setCourses_list_from_server(getQuestionCoursesById(message.getQuestion_number()));
                    message.setMessage("i gave you the question by it's id");
                    client.sendToClient(message);

                }                ///////////////////////////////BECKY////////////////////////////
                else if (request.equals("give me the subjects list for the manager")) {
                    message.setSubjects_list_from_server(getSubjectsForManager());
                    message.setMessage("i gave you all the subjects");
                    client.sendToClient(message);
                } else if (request.equals("give me all the questions of this subject")) {
//                    System.out.println("Becky's test1");
                    message.setQuestions_list_from_server(getAllQuestionsBySubjectCode(message.getSubjectCode()));
//                    System.out.println("Becky's test2");
                    message.setMessage("i gave you the questions by subject");
//                    System.out.println("Becky's test3");
                    client.sendToClient(message);
                } else if (request.equals("give me the subjects for manager")) { /*nawras 7/8/2023*/
                    message.setSubject_list_for_manager(getSubjectsForManager());
                    message.setMessage("i gave you the subject list for manager");
                    client.sendToClient(message);
                } else if (request.equals("give Me The Subject Courses For Manager")) { /*nawras 7/8/2023*/
                    message.setCourses_list_for_manager(getAllCoursesBySubjectCode(message.getSubject_code_for_manager()));
                    message.setMessage("i gave you the subject's Courses for manager");
                    client.sendToClient(message);
                } else if (request.equals("Give Me The Exams After Choosing Subject And Course")) { /*nawras 7/8/2023*/

                    message.setExam_in_drawer_list_for_manager(getListOfExamInDrawerForManger(message.getSelcted_course_for_manager(), message.getSubject_selected_for_manager()));

                    message.setMessage("i gave you the The Exams After Choosing Subject And Course");
                    client.sendToClient(message);

                } else if (request.equals("give me the solved exams by exam id")) {
                    message.setSolved_exams_from_server(getSolvedExamsByExamCode(message.getSelectedexam_id()));
                    message.setMessage("all solved exams for this execution are given");
                    client.sendToClient(message);

                } //added razan 7-8
                else if (request.equals("Give me the examInDrawers by subject and course and teacher ID")) {
                    System.out.println("TABLE SIMPLE SERVER 1");
                    message.setExams_list_from_server(getExamsList_by_subjectANDcourseANDteacherID(message.getCourse_id(), message.getSubjectCode(), message.getLecturerId()));
                    System.out.println("TABLE SIMPLE SERVER 2");
                    message.setMessage("examInDrawers by subject and course and teacher ID");
                    client.sendToClient(message);
                } else if (request.equals("Give me the solvedExams statistics of ExamInDrawer by subject and course and teacher ID")) {
                    System.out.println("its razan in simple server 1");
                    message.setStatistics(getsolvedExamsStatistics(message.getExamCode(), message.getCourse_id(), message.getSubjectCode(), message.getLecturerId()));
                    System.out.println("its razan in simple server 2");
                    System.out.println("its razan in simple server 2 check size " + message.getStatistics().size());
                    message.setMessage("solvedExams statistics of ExamInDrawer by subject and course and teacher ID");
                    client.sendToClient(message);
                    System.out.println("NAWRAS");
                } else if (request.equals("reject the selected extensions requests")) {

                    for (TimeExtensionRequest extensionRequest : message.getTimeExtensionRequests_from_server()) {
                        deleteExtensionRequest(extensionRequest.getRequest_id());
                    }

                    Message message1 = new Message("the Extension requests rejected successfully");
                    message.setManager_id(message.getManager_id());
                    message1.setController_name("ManagerTimeExtensionController");
                    client.sendToClient(message1);

                } else {
                    sendToAllClients(message);
                }


            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            assert session != null;
            session.close();
        }
    }

    public void sendToAllClients(Message message) {
        try {
            for (SubscribedClient SubscribedClient : SubscribersList) {
                // System.out.println(SubscribedClient.getClient().getName());
                SubscribedClient.getClient().sendToClient(message);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
