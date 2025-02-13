package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collections;

import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.sql.Select;

import static il.cshaifasweng.OCSFMediatorExample.server.App.getAllExamsByAllSubjectsAndCourses;

public class App {

    public static Session session;
    public static SessionFactory sessionFactory = getSessionFactory();

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();

        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Grade.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Lecturer.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Manager.class);
        configuration.addAnnotatedClass(TimeExtensionRequest.class);
        configuration.addAnnotatedClass(ExamExecution.class);
        configuration.addAnnotatedClass(ExamExecutionDigital.class);
        configuration.addAnnotatedClass(ExamExecutionManual.class);
        configuration.addAnnotatedClass(ExamInDrawer.class);
        configuration.addAnnotatedClass(QuestionInDrawer.class);
        configuration.addAnnotatedClass(SolvedExam.class);
        configuration.addAnnotatedClass(SolvedQuestion.class);
        configuration.addAnnotatedClass(Subject.class);
        configuration.addAnnotatedClass(Score.class);
        configuration.addAnnotatedClass(SubjectLecturerLink.class);
        configuration.addAnnotatedClass(CourseQuestionInDrawerLink.class);
        configuration.addAnnotatedClass(LecturerExamexecutionsLink.class);
        configuration.addAnnotatedClass(WordFile.class);
        configuration.addAnnotatedClass(CourseLecturerLink.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }


    ///////////////////////////////////////generate///////////////////////////////////////


    private static void generateLecturers() throws Exception {

        Lecturer lect = new Lecturer(123056786, "F@3$k6*Gq", "Ethan", "Simmons");
        session.save(lect);
        session.flush();
        Lecturer lect0 = new Lecturer(987654320, "T8*l!2&Wf", "Sophia", "Hayes");
        session.save(lect0);
        session.flush();
        Lecturer lect1 = new Lecturer(456789121, "Z!1&d4*Vt", "Caleb", "Parker");
        session.save(lect1);
        session.flush();
        Lecturer lect2 = new Lecturer(321654981, "N6%m!7*Cx", "Lily", "Turner");
        session.save(lect2);
        session.flush();
        Lecturer lect3 = new Lecturer(987123457, "H@9*r3#Wq", "Lucas", "Edwards");
        session.save(lect3);
        session.flush();
        Lecturer lect4 = new Lecturer(987123654, "C7*s!5&Vx", "Ava", "Mitchell");
        session.save(lect4);
        session.flush();
        Lecturer lect5 = new Lecturer(369258147, "Q#2&f6*Lt", "Benjamin", "Martinez");
        session.save(lect5);
        session.flush();
        Lecturer lect6 = new Lecturer(654321780, "W5*y!1&Pv", "Olivia", "Thompson");
        session.save(lect6);
        session.flush();
        Lecturer lect7 = new Lecturer(789456125, "K9&p!3*Mg", "Gabriel", "Johnson");
        session.save(lect7);
        session.flush();
        Lecturer lect8 = new Lecturer(159753467, "V!4#g8*Xw", "Emma", "Anderson");
        session.save(lect8);
        session.flush();

    }

    private static void generateStudents() throws Exception {

        Student std0 = new Student(123456789, "X!4#p8*Tq", "Emma", "Johnson");
        session.save(std0);
        session.flush();
        Student std1 = new Student(987654321, "S@7$y2%Lw", "Noah", "Williams");
        session.save(std1);
        session.flush();
        Student std2 = new Student(456789123, "B2&z!6*Qv", "Olivia", "Jones");
        session.save(std2);
        session.flush();
        Student std3 = new Student(321654987, "M$1#g5*Tp", "Liam", "Smith");
        session.save(std3);
        session.flush();
        Student std4 = new Student(987123456, "R7%t!9*Fy", "Ava", "Brown");
        session.save(std4);
        session.flush();
        Student std5 = new Student(654321789, "D@3^s6&Hq", "Isabella", "Davis");
        session.save(std5);
        session.flush();
        Student std6 = new Student(789456123, "J9&f!2*Lx", "Sophia", "Miller");
        session.save(std6);
        session.flush();
        Student std7 = new Student(159753468, "G@4#v7*Xr", "Mia", "Wilson");
        session.save(std7);
        session.flush();
        Student std8 = new Student(369852147, "P5*x!8&Ns", "Jackson", "Moore");
        session.save(std8);
        session.flush();
        Student std9 = new Student(852741963, "L#6!d9*Tc", "Aiden", "Taylor");
        session.save(std9);
        session.flush();
        Student std10 = new Student(211579347, "Wesal1", "wesal", "amasha");
        session.save(std10);
        session.flush();

        /***************************** 8/8/2023*******************************/
        Student std11 = new Student(432198765, "$T8*m7&Gz", "Elijah", "Walker");
        session.save(std11);
        session.flush();

        Student std12 = new Student(876543710, "Q#2*v6$Rf", "Abigail", "Hernandez");
        session.save(std12);
        session.flush();

        Student std13 = new Student(654987321, "Z9!d1$Kx", "James", "Gonzalez");
        session.save(std13);
        session.flush();

        Student std14 = new Student(123890456, "H%4*q7@Nt", "Sophie", "Lopez");
        session.save(std14);
        session.flush();

        Student std15 = new Student(789456012, "C#3*s5&Jp", "Benjamin", "Moore");
        session.save(std15);
        session.flush();

        Student std16 = new Student(654321987, "E@5*w3&Tb", "Emily", "Perez");
        session.save(std16);
        session.flush();

        Student std17 = new Student(890123456, "M7*n!4&Yv", "Lucas", "King");
        session.save(std17);
        session.flush();

        Student std18 = new Student(210987654, "A$1*z8#Dc", "Aria", "Turner");
        session.save(std18);
        session.flush();

        Student std19 = new Student(543810987, "L6*s!9&Fj", "Mason", "Rivera");
        session.save(std19);
        session.flush();

        Student std20 = new Student(987654123, "G@3*p7#Hk", "Amelia", "Campbell");
        session.save(std20);
        session.flush();

        Student std51 = new Student(987651234, "P@9*m5#Kf", "Charlotte", "Martinez");
        session.save(std51);
        session.flush();

        Student std52 = new Student(567891234, "N#6*w4&Qv", "Henry", "Garcia");
        session.save(std52);
        session.flush();

        Student std53 = new Student(214365879, "X@5*t2#Ml", "Aria", "Davis");
        session.save(std53);
        session.flush();

        Student std54 = new Student(789156234, "G!8*b4#Yr", "James", "Johnson");
        session.save(std54);
        session.flush();

        Student std55 = new Student(876543192, "L@2*m6#Tx", "Ella", "Smith");
        session.save(std55);
        session.flush();

        Student std56 = new Student(125634789, "V#4*f7&Lj", "Oliver", "Brown");
        session.save(std56);
        session.flush();

        Student std57 = new Student(312498765, "C@3*k6#Wp", "Sophia", "Miller");
        session.save(std57);
        session.flush();

        Student std58 = new Student(987615234, "B!7*p4#Fn", "Liam", "Jones");
        session.save(std58);
        session.flush();

        Student std59 = new Student(543219876, "M@5*t3#Qc", "Mia", "Williams");
        session.save(std59);
        session.flush();

        Student std60 = new Student(213487695, "K#6*r5&Tv", "Noah", "Smith");
        session.save(std60);
        session.flush();

        Student std61 = new Student(987651243, "J@4*z2#Yk", "Emma", "Davis");
        session.save(std61);
        session.flush();

        Student std62 = new Student(654381729, "R#5*w6&Fm", "Lucas", "Hernandez");
        session.save(std62);
        session.flush();

        Student std63 = new Student(765413289, "S@7*q2#Nf", "Ava", "Johnson");
        session.save(std63);
        session.flush();

        Student std64 = new Student(124367859, "P#8*d6&Xz", "Jackson", "Martinez");
        session.save(std64);
        session.flush();

        Student std65 = new Student(231496857, "T@9*m2#Lb", "Chloe", "Garcia");
        session.save(std65);
        session.flush();

        Student std66 = new Student(951382647, "F#1*k7&Sx", "Liam", "Williams");
        session.save(std66);
        session.flush();

        Student std67 = new Student(753149286, "Q@2*n6#Jw", "Olivia", "Davis");
        session.save(std67);
        session.flush();

        Student std68 = new Student(693847215, "W#3*z8#Vq", "Ethan", "Smith");
        session.save(std68);
        session.flush();

        Student std69 = new Student(852317469, "M@4*o7#Hr", "Sophia", "Johnson");
        session.save(std69);
        session.flush();

        Student std70 = new Student(486937215, "B#5*k9#Pt", "Noah", "Martinez");
        session.save(std70);
        session.flush();

        Student std71 = new Student(159723846, "Z@6*r8&Lg", "Ella", "Garcia");
        session.save(std71);
        session.flush();

        Student std72 = new Student(324681975, "D#7*t2&Qm", "Henry", "Davis");
        session.save(std72);
        session.flush();

        Student std73 = new Student(987514623, "R@8*m5#Wc", "Mia", "Johnson");
        session.save(std73);
        session.flush();

        Student std74 = new Student(764125398, "T#9*s7&Fn", "Liam", "Miller");
        session.save(std74);
        session.flush();

        Student std75 = new Student(215498637, "L@1*f4#Xp", "Emma", "Smith");
        session.save(std75);
        session.flush();

        Student std76 = new Student(431567892, "N#2*g5&Yr", "Ava", "Williams");
        session.save(std76);
        session.flush();

        Student std77 = new Student(865217349, "M#3*p6&Lj", "Noah", "Davis");
        session.save(std77);
        session.flush();

        Student std78 = new Student(612587439, "V#4*r7&Zk", "Oliver", "Smith");
        session.save(std78);
        session.flush();

        Student std79 = new Student(931254678, "B#5*d8&Vz", "Sophia", "Johnson");
        session.save(std79);
        session.flush();

        Student std80 = new Student(214396857, "N#6*z9&Cl", "Liam", "Martinez");
        session.save(std80);
        session.flush();
        Student std81 = new Student(789624513, "F@5*t2#Zq", "Ethan", "Brown");
        session.save(std81);
        session.flush();

        Student std82 = new Student(852961437, "L#8*d5&Kw", "Emma", "Smith");
        session.save(std82);
        session.flush();

        Student std83 = new Student(931487625, "M#4*p6&Fv", "Oliver", "Johnson");
        session.save(std83);
        session.flush();

        Student std84 = new Student(214375869, "B#7*r8&Qb", "Sophia", "Williams");
        session.save(std84);
        session.flush();

        Student std85 = new Student(961582743, "N#9*z1&Jk", "Noah", "Davis");
        session.save(std85);
        session.flush();

        Student std86 = new Student(753298146, "K#1*s4&Lz", "Mia", "Martinez");
        session.save(std86);
        session.flush();

        Student std87 = new Student(316597824, "W#2*p5&Cr", "Liam", "Garcia");
        session.save(std87);
        session.flush();

        Student std88 = new Student(489126357, "Z#3*d6&Xm", "Ava", "Brown");
        session.save(std88);
        session.flush();

        Student std89 = new Student(684739215, "D#4*g7&Nq", "Jackson", "Smith");
        session.save(std89);
        session.flush();

        Student std90 = new Student(925837146, "V#5*m8&Vt", "Olivia", "Johnson");
        session.save(std90);
        session.flush();

        Student std91 = new Student(351476829, "H#6*r9&Gp", "Ella", "Miller");
        session.save(std91);
        session.flush();

        Student std92 = new Student(827194635, "S#7*t1&Fw", "Noah", "Davis");
        session.save(std92);
        session.flush();

        Student std93 = new Student(672915348, "R#8*p2&Zk", "Sophia", "Smith");
        session.save(std93);
        session.flush();

        Student std94 = new Student(149683257, "Y#9*s3&Lm", "Liam", "Martinez");
        session.save(std94);
        session.flush();

        Student std95 = new Student(548732916, "M#1*d4&Hx", "Ava", "Garcia");
        session.save(std95);
        session.flush();

        Student std96 = new Student(987624513, "F#2*g5&Nq", "Ethan", "Brown");
        session.save(std96);
        session.flush();

        Student std97 = new Student(852361479, "L#3*t6&Zv", "Emma", "Smith");
        session.save(std97);
        session.flush();

        Student std98 = new Student(931487265, "M#4*p7&Fr", "Oliver", "Johnson");
        session.save(std98);
        session.flush();

        Student std99 = new Student(214375968, "B#5*r8&Xk", "Sophia", "Williams");
        session.save(std99);
        session.flush();

        Student std100 = new Student(961582347, "N#6*z9&Jl", "Noah", "Davis");
        session.save(std100);
        session.flush();


        /************************************************************/
    }


    private static void generateSubjects_courses() throws Exception {


        Subject sub0 = new Subject("Mathematics");
        session.save(sub0);
        session.flush();

        Course course0 = new Course("Calculus I");
        session.save(course0);
        sub0.addCourse(course0);
        session.flush();

        Course course1 = new Course("Linear Algebra");
        session.save(course1);
        sub0.addCourse(course1);
        session.flush();

        Course course2 = new Course("Probability and Statistics");
        session.save(course2);
        sub0.addCourse(course2);
        session.flush();


        Course course3 = new Course("Differential Equations");
        session.save(course3);
        sub0.addCourse(course3);
        session.flush();

        Course course4 = new Course("Discrete Mathematics");
        session.save(course4);
        sub0.addCourse(course4);
        session.flush();


        Subject sub1 = new Subject("Computer Science");
        session.save(sub1);
        session.flush();

        Course course5 = new Course("Introduction to Programming");
        session.save(course5);
        sub1.addCourse(course5);
        session.flush();

        Course course6 = new Course("Data Structures and Algorithms");
        session.save(course6);
        sub1.addCourse(course6);
        session.flush();

        Course course7 = new Course("Databases");
        session.save(course7);
        sub1.addCourse(course7);
        session.flush();

        Course course8 = new Course("Artificial Intelligence");
        session.save(course8);
        sub1.addCourse(course8);
        session.flush();

        Course course9 = new Course("Software Engineering");
        session.save(course9);
        sub1.addCourse(course9);
        session.flush();


        Subject sub2 = new Subject("Physics");
        session.save(sub2);
        session.flush();


        course9 = new Course("Classical Mechanics");
        session.save(course9);
        sub2.addCourse(course9);
        session.flush();

        course9 = new Course("Electromagnetism");
        session.save(course9);
        sub2.addCourse(course9);
        session.flush();

        course9 = new Course("Quantum Mechanics");
        session.save(course9);
        sub2.addCourse(course9);
        session.flush();

        course9 = new Course("Thermodynamics");
        session.save(course9);
        sub2.addCourse(course9);
        session.flush();

        course9 = new Course("Astrophysics");
        session.save(course9);
        sub2.addCourse(course9);
        session.flush();


        Subject sub3 = new Subject("Biology");
        session.save(sub3);
        session.flush();


        course9 = new Course("Introduction to Biology");
        session.save(course9);
        sub3.addCourse(course9);
        session.flush();

        course9 = new Course("Genetics");
        session.save(course9);
        sub3.addCourse(course9);
        session.flush();

        course9 = new Course("Cell Biology");
        session.save(course9);
        sub3.addCourse(course9);
        session.flush();

        course9 = new Course("Ecology");
        session.save(course9);
        sub3.addCourse(course9);
        session.flush();

        course9 = new Course("Molecular Biology");
        session.save(course9);
        sub3.addCourse(course9);
        session.flush();


        Subject sub4 = new Subject("Psychology");
        session.save(sub4);
        session.flush();


        course9 = new Course("Introduction to Psychology");
        session.save(course9);
        sub4.addCourse(course9);
        session.flush();

        course9 = new Course("Cognitive Psychology");
        session.save(course9);
        sub4.addCourse(course9);
        session.flush();

        course9 = new Course("Social Psychology");
        session.save(course9);
        sub4.addCourse(course9);
        session.flush();

        course9 = new Course("Abnormal Psychology");
        session.save(course9);
        sub4.addCourse(course9);
        session.flush();

        course9 = new Course("Developmental Psychology");
        session.save(course9);
        sub4.addCourse(course9);
        session.flush();


        Subject sub5 = new Subject("History");
        session.save(sub5);
        session.flush();

        course9 = new Course("World History");
        session.save(course9);
        sub5.addCourse(course9);
        session.flush();

        course9 = new Course("U.S. History");
        session.save(course9);
        sub5.addCourse(course9);
        session.flush();

        course9 = new Course("European History");
        session.save(course9);
        sub5.addCourse(course9);
        session.flush();

        course9 = new Course("Ancient Civilizations");
        session.save(course9);
        sub5.addCourse(course9);
        session.flush();

        course9 = new Course("Modern History");
        session.save(course9);
        sub5.addCourse(course9);
        session.flush();


        Subject sub6 = new Subject("Literature");
        session.save(sub6);
        session.flush();

        course9 = new Course("Introduction to Literature");
        session.save(course9);
        sub6.addCourse(course9);
        session.flush();

        course9 = new Course("Shakespearean Plays");
        session.save(course9);
        sub6.addCourse(course9);
        session.flush();

        course9 = new Course("American Literature");
        session.save(course9);
        sub6.addCourse(course9);
        session.flush();

        course9 = new Course("Contemporary Fiction");
        session.save(course9);
        sub6.addCourse(course9);
        session.flush();

        course9 = new Course("Poetry Analysis");
        session.save(course9);
        sub6.addCourse(course9);
        session.flush();


        Subject sub7 = new Subject("Economics");
        session.save(sub7);
        sub7.addCourse(course9);
        session.flush();

        course9 = new Course("Microeconomics");
        session.save(course9);
        sub7.addCourse(course9);
        session.flush();

        course9 = new Course("Macroeconomics");
        session.save(course9);
        sub7.addCourse(course9);
        session.flush();


        course9 = new Course("International Economics");
        session.save(course9);
        sub7.addCourse(course9);
        session.flush();

        course9 = new Course("Econometrics");
        session.save(course9);
        sub7.addCourse(course9);
        session.flush();

        course9 = new Course("Game Theory");
        session.save(course9);
        sub7.addCourse(course9);
        session.flush();


        Subject sub8 = new Subject("Philosophy");
        session.save(sub8);
        session.flush();


        course9 = new Course("Introduction to Philosophy");
        session.save(course9);
        sub8.addCourse(course9);
        session.flush();

        course9 = new Course("Ethics");
        session.save(course9);
        sub8.addCourse(course9);
        session.flush();

        course9 = new Course("Logic");
        session.save(course9);
        sub8.addCourse(course9);
        session.flush();

        course9 = new Course("Epistemology");
        session.save(course9);
        sub8.addCourse(course9);
        session.flush();

        course9 = new Course("Philosophy of Mind");
        session.save(course9);
        sub8.addCourse(course9);
        session.flush();


        Subject sub9 = new Subject("Sociology");
        session.save(sub9);
        session.flush();

        course9 = new Course("Introduction to Sociology");
        session.save(course9);
        sub9.addCourse(course9);
        session.flush();

        course9 = new Course("Social Theory");
        session.save(course9);
        sub9.addCourse(course9);
        session.flush();

        course9 = new Course("Sociology of Gender");
        session.save(course9);
        sub9.addCourse(course9);
        session.flush();

        course9 = new Course("Race and Ethnicity");
        session.save(course9);
        sub9.addCourse(course9);
        session.flush();

        course9 = new Course("Sociology of Health");
        session.save(course9);
        sub9.addCourse(course9);
        session.flush();


    }

    private static void generateGrades() throws Exception {

        List<Student> students = getAll(Student.class);
        List<Course> courses = getAll(Course.class);

        Grade grade0 = new Grade(students.get(1), courses.get(0), 12);
        session.save(grade0);
        session.flush();
    }


    ////////////////////////////////////////add or remove things to the data base///////////////////////////

    public static void approveSolvedExamById(int solved_exam_id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String queryString1 = "SELECT s FROM SolvedExam s WHERE s.id = :solved_exam_id";
            Query<SolvedExam> query1 = session.createQuery(queryString1, SolvedExam.class);
            query1.setParameter("solved_exam_id", solved_exam_id);

            try {

                SolvedExam solvedExam = query1.getSingleResult();
                solvedExam.setApproved(true);
                session.update(solvedExam);
                session.flush();

                return;
            } catch (NoResultException e) {

            }

            transaction.commit(); // Commit the transaction after executing the delete query.

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception (logging, error handling, etc.)
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return;

    }


    public static int ChangeGradeBySolvedExamId(int solved_exam_id
            , int grade) {


        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String queryString1 = "SELECT s FROM SolvedExam s WHERE s.id = :solved_exam_id";
            Query<SolvedExam> query1 = session.createQuery(queryString1, SolvedExam.class);
            query1.setParameter("solved_exam_id", solved_exam_id);

            try {

                SolvedExam solvedExam = query1.getSingleResult();
                solvedExam.setFinalScore(grade);
                session.update(solvedExam);
                session.flush();

                return solvedExam.getFinalScore();
            } catch (NoResultException e) {

            }

            transaction.commit(); // Commit the transaction after executing the delete query.

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception (logging, error handling, etc.)
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return -1;
    }

    public static void AddCommentsBySolvedExamId(int solved_exam_id
            , String comments) {

        // System.out.println("the comments we recieve: "+comments);

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String queryString1 = "SELECT s FROM SolvedExam s WHERE s.id = :solved_exam_id";
            Query<SolvedExam> query1 = session.createQuery(queryString1, SolvedExam.class);
            query1.setParameter("solved_exam_id", solved_exam_id);

            try {

                SolvedExam solvedExam = query1.getSingleResult();
                solvedExam.setLecturerText(comments);
                session.update(solvedExam);
                session.flush();

                // System.out.println("the updated comments: "+solvedExam.getLecturerText());

            } catch (NoResultException e) {

            }

            transaction.commit(); // Commit the transaction after executing the delete query.

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception (logging, error handling, etc.)
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }


    public static void AddWordFilebyExecutionId(int exam_exec_id, byte[] fileContent) {
        String queryString1 = "SELECT e FROM ExamExecutionManual e WHERE e.execution_id = :exam_exec_id";
        Query<ExamExecutionManual> query1 = session.createQuery(queryString1, ExamExecutionManual.class);
        query1.setParameter("exam_exec_id", exam_exec_id);

        try {

            ExamExecutionManual examExecutionManual = query1.getSingleResult();

            examExecutionManual.setFinished(true);
            session.update(examExecutionManual);

            WordFile wordFile = new WordFile(examExecutionManual, fileContent);

            session.save(wordFile);
            session.flush();


            examExecutionManual.setWordFile(wordFile);
            session.update(examExecutionManual);
            session.flush();
        } catch (NoResultException e) {

        }
    }


    public static void deleteExtensionRequest(int extension_id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("delete request " + extension_id);
            String queryString = "DELETE FROM TimeExtensionRequest WHERE request_id = :extension_id";
            session.createQuery(queryString).setParameter("extension_id", extension_id).executeUpdate();

            transaction.commit(); // Commit the transaction after executing the delete query.
            System.out.println("Deleted successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception (logging, error handling, etc.)
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    public static void AddAnTimeExtensionRequest(List<ExamExecution> examExecutions, int extension_time, String request_body) {

        try {

            Session session = sessionFactory.openSession();
            Transaction transaction = null;


            try {
                transaction = session.beginTransaction();

                TimeExtensionRequest request;
                for (ExamExecution examExecution : examExecutions) {
                    System.out.println(examExecution.getExecution_id());
                    request = new TimeExtensionRequest(extension_time, request_body, examExecution);

                    System.out.println("before save");

                    try {
                        session.save(request);
                    } catch (Exception e) {
                        System.out.println("save problem");
                    }
                    System.out.println("before commit");
                    session.flush();
                    System.out.println("after flush");
                }

                transaction.commit();
                System.out.println("after commit");

            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                // Handle the exception
            } finally {
                session.close();
            }
        } catch (NoResultException e) {

        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////


    public static void addSolvedExam(int exam_exec_id, List<SolvedQuestion> solvedQuestions,
                                     String studentText, Date date, int duration, boolean selfSubmit) {
        String queryString1 = "SELECT e FROM ExamExecutionDigital e WHERE e.execution_id = :exam_exec_id";
        Query<ExamExecutionDigital> query1 = session.createQuery(queryString1, ExamExecutionDigital.class);
        query1.setParameter("exam_exec_id", exam_exec_id);

        try {

            ExamExecutionDigital examExecutionDigital = query1.getSingleResult();

            examExecutionDigital.setFinished(true);
            session.update(examExecutionDigital);

            SolvedExam solvedExam = new SolvedExam(examExecutionDigital, studentText, date, duration, selfSubmit);

            ZoneId jerusalemTimeZone = ZoneId.of("Asia/Jerusalem");
            ZonedDateTime currentTimeInJerusalem = ZonedDateTime.now(jerusalemTimeZone);

            // Set the start time to the current time
            solvedExam.getExamExecutionDigital().setEnd_time(currentTimeInJerusalem);
            session.update(solvedExam.getExamExecutionDigital());

            session.save(solvedExam);
            session.flush();

            int total_score = 0;

            for (SolvedQuestion solvedQuestion : solvedQuestions) {

                solvedQuestion.setSolvedExam(solvedExam);
                solvedQuestion.setScore(solvedQuestion.getGrade());

                if (solvedQuestion.getQuestion().getRightAnswer() != solvedQuestion.getAnswer()) {
                    solvedQuestion.setGrade(0);
                } else {
                    total_score += solvedQuestion.getGrade();
                }

                session.save(solvedQuestion);
                session.flush();

                solvedExam.getSolvedQuestions().add(solvedQuestion);
                session.update(solvedExam);
                session.flush();
            }

            solvedExam.setFinalScore(total_score);
            session.update(solvedExam);
            session.flush();


            examExecutionDigital.setSolved_exam(solvedExam);
            session.update(examExecutionDigital);
            session.flush();


        } catch (NoResultException e) {

        }


    }


    public static void addAnExamExecution(int exam_id, int teacher_id, List<Student> students, String type, String code) {


        String queryString1 = "SELECT e FROM ExamInDrawer e WHERE e.exam_code = :exam_id";
        Query<ExamInDrawer> query1 = session.createQuery(queryString1, ExamInDrawer.class);
        query1.setParameter("exam_id", exam_id);

        try {

            ExamInDrawer exam = query1.getSingleResult();

            String queryString = "SELECT l FROM Lecturer l WHERE l.userId = :teacher_id";
            Query<Lecturer> query = session.createQuery(queryString, Lecturer.class);
            query.setParameter("teacher_id", teacher_id);

            try {
                Lecturer lecturer = query.getSingleResult();

                ExamExecutionManual examExecutionManual;

                ExamExecutionDigital examExecutionDigital;


                List<ExamExecution> examExecutions = new ArrayList<>();


                for (Student student : students) {
                    if (type.equals("Manual")) {
                        examExecutionManual = new ExamExecutionManual(exam, lecturer, student, code);
                        session.save(examExecutionManual);
                        session.flush();

                        examExecutions.add(examExecutionManual);

                        // return;


                    } else {
                        examExecutionDigital = new ExamExecutionDigital(exam, lecturer, student, code);
                        session.save(examExecutionDigital);
                        session.flush();

                        examExecutions.add(examExecutionDigital);

                        // return;
                    }

                }


                LecturerExamexecutionsLink lecturerExamexecutionsLink = new LecturerExamexecutionsLink(lecturer, examExecutions);
                session.save(lecturerExamexecutionsLink);
                session.flush();


                lecturer.getLecturerExamexecutionsLinks().add(lecturerExamexecutionsLink);
                session.update(lecturer);
                session.flush();


                for (ExamExecution examExecution : examExecutions) {
                    examExecution.setLecturerExamexecutionsLink(lecturerExamexecutionsLink);
                    session.update(examExecution);
                    session.flush();
                }


            } catch (NoResultException e) {

            }


        } catch (NoResultException e) {

        }

    }


    public static void AddAnExamToTheDrawer(Subject subject, Course course, List<QuestionInDrawer> questios, int exam_time, int author_id, String teacherComments, String studentsComments) {


        List<Lecturer> lecturers = new ArrayList<>();
        String author_name = "";
        try {
            lecturers = getAllLecturers();
        } catch (Exception e) {
            return;
        }

        for (Lecturer lecturer : lecturers) {
            if (lecturer.getId() == author_id) {
                author_name = lecturer.getFirstName() + " " + lecturer.getLastName();
            }
        }

        // System.out.println("i'm here");


        ExamInDrawer exam = new ExamInDrawer(subject, course, exam_time, author_name, studentsComments, teacherComments);
        exam.setStudentsComments(studentsComments);
        exam.setTeacherComments(teacherComments);
        exam.setAuthor_id(author_id);
        session.save(exam);
        session.flush();

        // System.out.println("i'm here0");

        Score score;

        for (QuestionInDrawer question : questios) {
            //  System.out.println("i'm here1");
            score = new Score(exam, question, question.getTemporary_score());
            // System.out.println("i'm here2");

            session.save(score);
            //  System.out.println("i'm here3");
            session.update(exam);
            //  System.out.println("i'm here4");
            session.flush();
        }

        // System.out.println("i'm here5");

    }

    public static QuestionInDrawer addQuestion(Subject subject, List<Course> courses, String text, String option1, String option2, String option3, String option4, int rightAnswer) {


        QuestionInDrawer question = new QuestionInDrawer(subject, text, option1, option2, option3, option4, rightAnswer);

        session.save(question);

        CourseQuestionInDrawerLink link;

        for (Course course : courses) {
            link = new CourseQuestionInDrawerLink(course, question);
            session.save(link);
            session.flush();
        }

        session.flush();
        return question;
    }


    ///////////////////////////change or check or return primitive type /////////////////////////


    public static String average(int course_id, String course_name) {
        int sum = 0, average = 0;

        List<Course> courses = getAll(Course.class);
        for (Course cur : courses) {
            if (cur.getName() == course_name) {
                for (int i = 0; i < cur.getGrades().size(); i++) {
                    sum = sum + cur.getGrades().get(i).getGrade();
                }
                average = sum / cur.getGrades().size();
                cur.setCourse_average(average);

                return "I get course statistical information";
            }
        }

        return null;
    }

    public static String median(int course_id, String course_name) {
        List<Course> courses = getAll(Course.class);
        int median = 0;
//        List<Grade> grades;
        List<Integer> sortedList = null;

        for (Course cur : courses) {
            if (cur.getName() == course_name) {
//                grades=cur.getGrades();
////                int max=cur.getGrades().get(0).getGrade();
//
                for (int i = 1; i < cur.getGrades().size(); i++) {
                    sortedList.add(cur.getGrades().get(i).getGrade());
                }
                Collections.sort(sortedList);
                median = sortedList.get(sortedList.size() / 2);
                cur.setCourse_median(median);
                return "I get course statistical information";
            }
        }


        return null;
    }

    public static String manualExecution(int executionID) {

        if ((executionID == 123)) {
            return "execution code confirmed";
        } else {
            return "execution code not exist";
        }

    }

//    public static String checkFirstDone(int execution_code) {
//        if (execution_code >= 1000 && execution_code <= 9999) {
//            return "Execution code confirmed";
//        } else {
//            return "Wrong Execution code";
//        }
//    }
//
//    public static String checkSecondDone(int id) {
//        List<Student> students = getAll(Student.class);
//        for (Student s : students) {
//            if (s.getStudent_id() == id) {
//                //  System.out.println("RAZAN 1 ");
//                return "ID confirmed";
//            }
//        }
//        //   System.out.println("RAZAN 0 ");
//        return "Wrong ID";
//    }


    public static void changeGrade(int studentId, int courseId, int newGrade) throws Exception {


        List<Grade> student_grades = getGradesByStudentId(studentId);
        try {
            Grade grade = null;
            for (Grade g : student_grades) {
                //  System.out.println(g.getCourseid());
                if (g.getCourseid() == courseId) {
                    grade = g;
                    //   System.out.println("it is valid grade.");
                    break;
                }
            }

            if (grade == null) {
                throw new Exception("No grade found for the specified student and course.");
            }

            //System.out.println("it is valid grade.");

            // Update the grade
            grade.setGrade(newGrade);
            session.update(grade);

            session.getTransaction().commit(); // Save everything..commit();
            //  System.out.println("Grade updated successfully.");
        } catch (Exception e) {
            session.getTransaction().rollback();
            //  System.out.println("Error updating grade: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public static void logOut(int username) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession(); // Replace this with your session retrieval logic.
            transaction = session.beginTransaction();

            String queryString1 = "SELECT u FROM User u WHERE u.userId = :username";
            Query<User> query1 = session.createQuery(queryString1, User.class);
            query1.setParameter("username", username);
            User user = query1.getSingleResult();

            user.setLoggedIn(false);
            session.update(user);
            transaction.commit();
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static String logIn(int username, String password) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession(); // Replace this with your session retrieval logic.
            transaction = session.beginTransaction();

            String queryString1 = "SELECT u FROM User u WHERE u.userId = :username";
            Query<User> query1 = session.createQuery(queryString1, User.class);
            query1.setParameter("username", username);
            User user = query1.getSingleResult();

            if (user.isLoggedIn()) {
                return "you are already loggedIn";
            } else {
                if (user.getPassword().equals(password)) {
                    user.setLoggedIn(true);
                    session.update(user);
                    transaction.commit();

                    if (user instanceof Student) {
                        return "login confirmed student";
                    } else if (user instanceof Manager) {
                        return "login confirmed Manager";
                    } else if (user instanceof Lecturer) {
                        return "login confirmed Teacher";
                    }
                } else {
                    return "wrong password";
                }
            }
        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return "user not exist";
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return "";
    }


//    public static void logOut(int username) {
//        try {
//            String queryString1 = "SELECT u FROM User u WHERE u.userId = :username";
//            Query<User> query1 = session.createQuery(queryString1, User.class);
//            query1.setParameter("username", username);
//            User user = query1.getSingleResult();
//
//            user.setLoggedIn(false);
//            session.update(user);
//            session.getTransaction().commit();
//
//        } catch (NoResultException e) {
//
//            return;
//        }
//    }
//
//
//    public static String logIn(int username, String password) {
//
//
//        String queryString1 = "SELECT u FROM User u WHERE u.userId = :username";
//        Query<User> query1 = session.createQuery(queryString1, User.class);
//        query1.setParameter("username", username);
//
//        try {
//            User user = query1.getSingleResult();
//
//
//                if (user.isLoggedIn()) {
//                    return "you are already loggedIn";
//
//                } else {
//
//                    System.out.println("here0");
//
//                    if (user.getPassword().equals(password)) {
//                        System.out.println("password is okay");
//                        user.setLoggedIn(true);
//                        session.update(user);
//                        session.getTransaction().commit();
//
//                        if (user instanceof Student) {
//                            System.out.println("student");
//                            return "login confirmed student";
//
//
//                        } else if (user instanceof Manager) {
//                            System.out.println("Manager");
//                            return "login confirmed Manager";
//
//
//                        } else if (user instanceof Lecturer) {
//                            System.out.println("Teacher");
//                            return "login confirmed Teacher";
//                        }
//
//
//                    } else {
//
//                        return "wrong password";
//                    }
//                }
//
////            } catch (Exception ex) {
////                if (transaction != null) {
////                    transaction.rollback();
////                }
////            } finally {
////                session.close();
////            }
//
//        } catch (NoResultException e) {
//            return "user not exist";
//        }
//
//        return "";
//    }


//////////////////////get quires///////////////////////////////////

    public static List<SolvedExam> getSolvedExamsByExamCode(int exam_code) throws Exception {

        String queryString = "SELECT e FROM SolvedExam e WHERE e.approved = true And e.examExecutionDigital.exam.exam_code=:exam_code ";

        Query<SolvedExam> query = session.createQuery(queryString, SolvedExam.class);
        query.setParameter("exam_code", exam_code);


        try {
            List<SolvedExam> solvedExams = query.getResultList();

            for (SolvedExam solvedExam : solvedExams) {
                System.out.println("" + solvedExam.getId());
            }

            return solvedExams;
        } catch (NoResultException e) {
            System.out.println("no such result");
        }

        return new ArrayList<>();
    }


    public static int getLecturerWhoExecutedTheExamByExecutionId(int execution_id) {
        String queryString = "SELECT e.lecturer FROM ExamExecution e WHERE e.execution_id = :execution_id";

        Query<Lecturer> query = session.createQuery(queryString, Lecturer.class);
        query.setParameter("execution_id", execution_id);


        try {
            Lecturer lecturer = query.getSingleResult();
            return lecturer.getUser_id();

        } catch (NoResultException e) {
            System.out.println("no such result");
        }

        return -1;

    }

    public static int getLecturerExamexecutionsLinkByExecution_id(int execution_id) {
        String queryString = "SELECT e.lecturerExamexecutionsLink FROM ExamExecution e WHERE e.execution_id = :execution_id";

        Query<LecturerExamexecutionsLink> query = session.createQuery(queryString, LecturerExamexecutionsLink.class);
        query.setParameter("execution_id", execution_id);


        try {
            LecturerExamexecutionsLink link = query.getSingleResult();
            return link.getLecturerExamexecutionsLink_id();
        } catch (NoResultException e) {
            System.out.println("no such result");
        }

        return -1;

    }


    public static List<SolvedExam> getApprovedSolvedExamsbyCourseIdAndStudentId(int course_id, int student_id) {
        System.out.println("I AM IN APP :)");
        String queryString = "SELECT ee FROM Student ee WHERE ee.userId = :student_id ";
        Query<Student> query = session.createQuery(queryString, Student.class);
        query.setParameter("student_id", student_id);


        try {
            Student student = query.getSingleResult();

            Session session = sessionFactory.openSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                List<ExamExecutionDigital> examExecutionDigitalList = student.getExamExecutionsDigital();
                List<SolvedExam> solvedExams = new ArrayList<>();

                for (ExamExecutionDigital examExecutionDigital : examExecutionDigitalList) {
                    if (examExecutionDigital.getSolved_exam() != null) {//added razan 8-8
                        if (examExecutionDigital.getSolved_exam().isApproved()) {
                            if (examExecutionDigital.getExam().getCourse().getId() == course_id) {
                                solvedExams.add(examExecutionDigital.getSolved_exam());
                            }
                        }
                    }
                }
                System.out.println("DONE FOR");

                transaction.commit();

                System.out.println("I AM IN APP CHECK check final score " + solvedExams.get(0).getFinalScore());

                return solvedExams;

            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                // Handle the exception
            } finally {
                session.close();
            }

        } catch (NoResultException e) {
            System.out.println("there is no examexecutionsdigital");
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }


//    public static List<SolvedExam> getsolvedExamsList_by_subjectANDcourseANDteacherID(int course_id,int subject_id,int lecturerID) {
//
//        String queryString2 = "SELECT ee FROM SolvedExam ee WHERE ee.approved = true";
//        Query<SolvedExam> query2 = session.createQuery(queryString2, SolvedExam.class);
//
//        List<SolvedExam>solvedExams=new ArrayList<>();
//
//        for(SolvedExam solvedExam:query2.getResultList())
//        {
//            ExamInDrawer examInDrawer=solvedExam.getExamExecutionDigital().getExam();
//            if(examInDrawer.getAuthor_id()==lecturerID && examInDrawer.getSubject().getSubject_code()==subject_id &&
//                    examInDrawer.getCourse().getId()==course_id )
//            {
//                solvedExams.add(solvedExam);
//            }
//        }
//        return solvedExams;
//    }

    public static List<ExamInDrawer> getExamsList_by_subjectANDcourseANDteacherID(int course_id, int subject_id, int lecturerID) {

        String queryString2 = "SELECT ee FROM ExamInDrawer ee where ee.author_id =: lecturerID AND ee.course.id =: course_id and ee.subject.subject_code =:subject_id ";
        Query<ExamInDrawer> query2 = session.createQuery(queryString2, ExamInDrawer.class);

        query2.setParameter("lecturerID", lecturerID);
        query2.setParameter("course_id", course_id);
        query2.setParameter("subject_id", subject_id);


        try {
            List<ExamInDrawer> exams = query2.getResultList();
            return exams;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }


    }

    public static List<Integer> getsolvedExamsStatistics(int examInDrawerCode, int course_id, int subject_id, int lecturerID) {
        //statistics.get(0)=Count of executions
        //statistics.get(1)=Average solution time
        //statistics.get(2)=Average grades
        //statistics.get(3)=Median
        //statistics.get(4)=The number of students who got grades in 0-10
        //....
        //statistics.get(13)=The number of students who got grades in 91-100

        String queryString = "SELECT ee FROM SolvedExam ee WHERE ee.approved = true";
        Query<SolvedExam> query = session.createQuery(queryString, SolvedExam.class);

        try {
            List<SolvedExam> solvedExams0 = query.getResultList();
            List<SolvedExam> solvedExams = new ArrayList<>();

            for (SolvedExam solvedExam : solvedExams0) {
                ExamInDrawer examInDrawer = solvedExam.getExamExecutionDigital().getExam();
                if (examInDrawer.getAuthor_id() == lecturerID && examInDrawer.getSubject().getSubject_code() == subject_id &&
                        examInDrawer.getCourse().getId() == course_id && examInDrawer.getExam_code() == examInDrawerCode) {
                    solvedExams.add(solvedExam);
                }
            }


            List<Integer> statistics = new ArrayList<>();

            if (solvedExams.size() == 0) {
                for (int i = 0; i < 20; i++) {
                    statistics.add(0);
                }

            } else {
                //-------------------------Count of executions----------------------------------------------
                statistics.add(solvedExams.size());

                //------------------------Average solution time-----------------------------------------------
                //The return value is the Average solution time
                //        return averageDuration.toMinutes();
                Duration totalDuration = Duration.ZERO;
                for (SolvedExam SE : solvedExams) {
                    totalDuration = totalDuration.plus(Duration.between(SE.getExamExecutionDigital().getStart_time(), SE.getExamExecutionDigital().getEnd_time()));
                }
                Duration averageDuration = totalDuration.dividedBy(solvedExams.size());
                // Long time=averageDuration.toMinutes();
                Long time = averageDuration.toMinutes();
                int time2 = time.intValue();
                statistics.add(time2);

                System.out.println("its razan in APP 1 check time2 " + time2);

                //------------------------Average grade-----------------------------------------------
                int counter = 0;
                int averageGrade = 0;
                for (SolvedExam SE : solvedExams) {
                    counter = counter + SE.getExamExecutionDigital().getSolved_exam().getFinalScore();
                }
                averageGrade = counter / solvedExams.size();
                statistics.add(averageGrade);

                System.out.println("its razan in APP 1 check averageGrade " + averageGrade);

                //-------------------------Median----------------------------------------------
                List<Integer> grades = new ArrayList<>();
                for (SolvedExam SE : solvedExams) {
                    grades.add(SE.getExamExecutionDigital().getSolved_exam().getFinalScore());
                }
                Collections.sort(grades);
                // Calculate the median
                int middleIndex = grades.size() / 2;
                int median = 0;
                if (grades.size() % 2 == 0) {
                    // Even number of elements
                    median = grades.get(middleIndex - 1);
                } else {
                    // Odd number of elements
                    median = grades.get(middleIndex);
                }
                statistics.add(median);
                System.out.println("its razan in APP 1 check median " + median);

                //------------------------Decimal distribution-----------------------------------------------
                counter = 0;
                int flag = 0;
                for (int i = 0; i <= 100; i = i + 10) {
                    if (i == 10) flag = 1;
                    for (SolvedExam SE : solvedExams) {
                        int grade = SE.getExamExecutionDigital().getSolved_exam().getFinalScore();
                        if (grade > i && grade <= (i + 10)) {
                            counter++;
                        }
                        if (grade == 0 && flag == 0) {
                            statistics.get(3).equals(statistics.get(3) + 1);
                        }
                    }
                    statistics.add(counter);
                    counter = 0;
                }

                int x = 1;
                for (int j = 4; j <= 14; j++) {
                    System.out.println("values in range " + x + "is " + statistics.get(j));
                    x = x + 10;
                }

                System.out.println("its razan in APP 1 END");


            }

            return statistics;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }


    }


    public static List<ExamExecutionDigital> getAllDigitalExams() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExamExecutionDigital> query = builder.createQuery(ExamExecutionDigital.class);
        query.from(ExamExecutionDigital.class);
        List<ExamExecutionDigital> data = session.createQuery(query).getResultList();
        return data;
    }


    /*****************************************************************************/
    /******************************** nawras 7/8/2023 *********************************************/
    public static List<Subject> getSubjectsForManager() throws Exception {
        List<Subject> subjectlist = new ArrayList<>();
        subjectlist = getAllSubjects();
        return subjectlist;
    }

    public static List<Course> getAllCoursesBySubjectCode(int subject_code_for_manager) {
        List<Course> courseslist = new ArrayList<>();
        List<Subject> subjectslist = getAll(Subject.class);
//        System.out.println("014");
        for (Subject sub : subjectslist) {
//            System.out.println("015");

            if (sub.getSubject_code() == subject_code_for_manager) {
//                System.out.println("016");
                courseslist = sub.getCourses();
                System.out.println(courseslist.get(1));
//                System.out.println("017");
                return courseslist;
            }
        }
//        return courseslist;
        return null;
    }

    public static List<ExamInDrawer> getListOfExamInDrawerForManger(Course selcted_Course, Subject selected_Subject) throws Exception {
//        List<ExamInDrawer> listOfExams = new ArrayList<>();
        List<ExamInDrawer> requiredlistOfExams = new ArrayList<>();
        List<ExamInDrawer> listexams = new ArrayList<>();
        //  System.out.println("jana 7");

        listexams = getAllExams();
        for (ExamInDrawer exam : listexams) {
//            System.out.println("015");
            if ((exam.getCourse().getId() == selcted_Course.getId()) && (exam.getSubject().getSubject_code() == selected_Subject.getSubject_code())) {
                //   System.out.println("JANANA0");
                requiredlistOfExams.add(exam);
                //   System.out.println("JANANA1");
            }
            //   System.out.println("jana 8");

        }
        //   System.out.println("jana 9");

        return requiredlistOfExams;
    }


    /*****************************************************************************/
    /*****************************************************************************/

    public static List<Lecturer> getlecturers() {
        String queryString = "SELECT s FROM Lecturer s";
        Query<Lecturer> query = session.createQuery(queryString, Lecturer.class);


        try {
            List<Lecturer> lecturers = query.getResultList();
            return lecturers;
        } catch (NoResultException e) {
            System.out.println("there is no such courses");
            return new ArrayList<>();
        }
    }


//    public static List<SolvedExam> getApprovedSolvedExamsbyCourseIdAndStudentId(int course_id, int student_id) {
//
//        String queryString = "SELECT s FROM Student s WHERE s.userId = :student_id";
//        Query<Student> query = session.createQuery(queryString, Student.class);
//        query.setParameter("student_id", student_id);
//
//        try {
//            Student student = query.getSingleResult();
//
//
//            Session session = sessionFactory.openSession();
//            Transaction transaction = null;
//
//            try {
//                transaction = session.beginTransaction();
//
//                List<ExamExecutionDigital> examExecutionDigitalList = student.getExamExecutionsDigital();
//                List<SolvedExam> solvedExams = new ArrayList<>();
//
//                for (ExamExecutionDigital examExecutionDigital : examExecutionDigitalList) {
//                    if (examExecutionDigital.getSolved_exam().isApproved()) {
//                        if (examExecutionDigital.getExam().getCourse().getId() == course_id) {
//                            solvedExams.add(examExecutionDigital.getSolved_exam());
//                        }
//                    }
//                }
//
//                transaction.commit();
//
//                return solvedExams;
//
//            } catch (Exception ex) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//                // Handle the exception
//            } finally {
//                session.close();
//            }
//
//        } catch (NoResultException e) {
//            System.out.println("there is no examexecutionsdigital");
//            return new ArrayList<>();
//        }
//        return new ArrayList<>();
//    }


    public static List<SolvedExam> getsolvedExamsList_ByLinkId(int linkId) {

        String queryString = "SELECT ee FROM LecturerExamexecutionsLink ee WHERE ee.lecturerExamexecutionsLink_id = :linkId";
        Query<LecturerExamexecutionsLink> query = session.createQuery(queryString, LecturerExamexecutionsLink.class);
        query.setParameter("linkId", linkId);

        try {
            LecturerExamexecutionsLink link = query.getSingleResult();
            List<ExamExecution> examExecutions = link.getExamExecutions();
            List<SolvedExam> solvedExams = new ArrayList<>();

            Session session = sessionFactory.openSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                for (ExamExecution examExecution : examExecutions) {
                    if (examExecution.isFinished()) {
                        //   System.out.println("finished exam exam");
                        if (examExecution instanceof ExamExecutionDigital) {

                            if (((ExamExecutionDigital) examExecution).getSolved_exam().isApproved() == false) {
                                System.out.println("not approved yet");
                                solvedExams.add(((ExamExecutionDigital) examExecution).getSolved_exam());
                            }

                        }

                    } else {
                        System.out.println("not valid exam");
                        //validExamExecutions.add(examExecution);
                    }
                }

                transaction.commit();

                // System.out.println("here 0");
                return solvedExams;


            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                // Handle the exception
            } finally {
                session.close();
            }

        } catch (RuntimeException e) {
            System.out.println("there is no exam executions");
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }


    //added razan 5-8
    public static List<ExamExecutionManual> getExamExecutionsList_ByLinkId_Manual(int linkId) {

        String queryString = "SELECT ee FROM LecturerExamexecutionsLink ee WHERE ee.lecturerExamexecutionsLink_id = :linkId";
        Query<LecturerExamexecutionsLink> query = session.createQuery(queryString, LecturerExamexecutionsLink.class);
        query.setParameter("linkId", linkId);

        try {
            LecturerExamexecutionsLink link = query.getSingleResult();
            List<ExamExecution> examExecutions = link.getExamExecutions();
            List<ExamExecutionManual> examExecutionManuals = new ArrayList<>();

            for (ExamExecution examExecution : examExecutions) {
                if (examExecution.isFinished()) {
                    System.out.println("valid exam");
                    if (examExecution instanceof ExamExecutionManual) {
                        examExecutionManuals.add((ExamExecutionManual) examExecution);
                    }

                } else {
                    System.out.println("not valid exam");
                    //validExamExecutions.add(examExecution);
                }
            }
            return examExecutionManuals;
        } catch (RuntimeException e) {
            System.out.println("there is no exam executions");
            return new ArrayList<>();
        }
    }

    public static String getStudentCommentsBySolvedExamId(int solved_exam_id) {

        String queryString = "SELECT s FROM SolvedExam s WHERE s.id = :solved_exam_id";

        Query<SolvedExam> query = session.createQuery(queryString, SolvedExam.class);
        query.setParameter("solved_exam_id", solved_exam_id);

        try {
            SolvedExam solvedExam = query.getSingleResult();

            return solvedExam.getExamExecutionDigital().getExam().getStudentsComments();

        } catch (RuntimeException e) {

            return "";
        }

    }

    public static String getTeacherCommentsBySolvedExamId(int solved_exam_id) {

        String queryString = "SELECT s FROM SolvedExam s WHERE s.id = :solved_exam_id";

        Query<SolvedExam> query = session.createQuery(queryString, SolvedExam.class);
        query.setParameter("solved_exam_id", solved_exam_id);

        try {
            SolvedExam solvedExam = query.getSingleResult();

            return solvedExam.getExamExecutionDigital().getExam().getTeacherComments();

        } catch (RuntimeException e) {

            return "";
        }

    }


    public static int getGradeBySolvedExamId(int solved_exam_id) {

        String queryString = "SELECT s FROM SolvedExam s WHERE s.id = :solved_exam_id";

        Query<SolvedExam> query = session.createQuery(queryString, SolvedExam.class);
        query.setParameter("solved_exam_id", solved_exam_id);

        try {
            SolvedExam solvedExam = query.getSingleResult();

            return solvedExam.getFinalScore();

        } catch (RuntimeException e) {

            return -1;
        }

    }

    public static List<SolvedQuestion> getSolvedQuestionsBySolvedExamId(int solved_exam_id, List<
            QuestionInDrawer> questions) {

        String queryString = "SELECT s FROM SolvedExam s WHERE s.id = :solved_exam_id";

        Query<SolvedExam> query = session.createQuery(queryString, SolvedExam.class);
        query.setParameter("solved_exam_id", solved_exam_id);

        try {
            SolvedExam solvedExam = query.getSingleResult();

            questions.clear();

            for (SolvedQuestion solvedQuestion : solvedExam.getSolvedQuestions()) {
                questions.add(solvedQuestion.getQuestion());
            }

            return solvedExam.getSolvedQuestions();

        } catch (RuntimeException e) {

            return new ArrayList<>();
        }

    }


    public static List<ExamExecution> getExamExecutionsList_ByLinkId(int linkId) {
        String queryString = "SELECT ee FROM LecturerExamexecutionsLink ee WHERE ee.lecturerExamexecutionsLink_id = :linkId";

        Query<LecturerExamexecutionsLink> query = session.createQuery(queryString, LecturerExamexecutionsLink.class);
        query.setParameter("linkId", linkId);

        try {
            LecturerExamexecutionsLink link = query.getSingleResult();

            List<ExamExecution> examExecutions = link.getExamExecutions();

            List<ExamExecution> validExamExecutions = new ArrayList<>();

            for (ExamExecution examExecution : examExecutions) {
                if (examExecution.isFinished() || examExecution.getStart_time() == null) {
                    System.out.println("not valid exam");
                } else {
                    System.out.println("valid exam");
                    validExamExecutions.add(examExecution);
                }
            }

            //  System.out.println("hereyhtgrfed");

            return validExamExecutions;
        } catch (RuntimeException e) {
            System.out.println("there is no exam executions");
            return new ArrayList<>();
        }
    }


    public static List<ExamExecution> getExamExecutionsListByLecturerId_approving(int lecturer_id, List<
            LecturerExamexecutionsLink> lecturerExamexecutionsLinks) {
        String queryString = "SELECT ee FROM LecturerExamexecutionsLink ee WHERE ee.lecturer.userId = :lecturerId";

        Query<LecturerExamexecutionsLink> query = session.createQuery(queryString, LecturerExamexecutionsLink.class);
        query.setParameter("lecturerId", lecturer_id);

        try {
            lecturerExamexecutionsLinks.clear(); // Clear the input list to update it with new data

            List<LecturerExamexecutionsLink> fetchedLinks = query.getResultList();

            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            List<ExamExecution> examExecutions = new ArrayList<>();

            try {
                transaction = session.beginTransaction();

                for (LecturerExamexecutionsLink fetchedLink : fetchedLinks) {
                    if (fetchedLink.getExamExecutions().size() != 0) {
                        boolean valid = false;
                        for (ExamExecution examExecution : fetchedLink.getExamExecutions()) {
                            if (examExecution.isFinished() == true && examExecution.isApproved() == false) {
                                valid = true;
                            }
                        }

                        if (valid) {
                            lecturerExamexecutionsLinks.add(fetchedLink); // Add fetched links to the provided list
                            examExecutions.add(fetchedLink.getExamExecutions().get(0));
                        }

                    }
                }

                transaction.commit();

            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                // Handle the exception
            } finally {
                session.close();
            }

            return examExecutions;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    public static List<ExamExecution> getExamExecutionsListByLecturerId(int lecturer_id, List<
            LecturerExamexecutionsLink> lecturerExamexecutionsLinks) {
        String queryString = "SELECT ee FROM LecturerExamexecutionsLink ee WHERE ee.lecturer.userId = :lecturerId";

        Query<LecturerExamexecutionsLink> query = session.createQuery(queryString, LecturerExamexecutionsLink.class);
        query.setParameter("lecturerId", lecturer_id);

        try {
            lecturerExamexecutionsLinks.clear(); // Clear the input list to update it with new data

            List<LecturerExamexecutionsLink> fetchedLinks = query.getResultList();

            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            List<ExamExecution> examExecutions = new ArrayList<>();

            try {
                transaction = session.beginTransaction();

                for (LecturerExamexecutionsLink fetchedLink : fetchedLinks) {
                    if (fetchedLink.getExamExecutions().size() != 0) {
                        boolean valid = false;
                        for (ExamExecution examExecution : fetchedLink.getExamExecutions()) {
                            if (examExecution.isFinished() == false && examExecution.getStart_time() != null) {
                                valid = true;
                            }
                        }

                        if (valid) {
                            lecturerExamexecutionsLinks.add(fetchedLink); // Add fetched links to the provided list
                            examExecutions.add(fetchedLink.getExamExecutions().get(0));
                        }

                    }
                }

                transaction.commit();

            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                // Handle the exception
            } finally {
                session.close();
            }

            return examExecutions;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void updateEndTimeByExecId(int exec_id, int extention_time) {
        String queryString = "SELECT ee FROM ExamExecution ee WHERE ee.execution_id = :exec_id";

        Query<ExamExecution> query = session.createQuery(queryString, ExamExecution.class);
        query.setParameter("exec_id", exec_id);

        try {
            ExamExecution examExecution = query.getSingleResult();


            // Calculate the end time by adding the exam duration to the start time
            ZonedDateTime endTime = examExecution.getEnd_time().plus(extention_time, ChronoUnit.MINUTES);
            examExecution.setEnd_time(endTime);
            session.update(examExecution);
            session.flush();

        } catch (NoResultException e) {

        }

    }

    public static List<QuestionInDrawer> getAllQuestionsBySubjectCode(int subjectCode) {
        String queryString = "SELECT q FROM QuestionInDrawer q " +
                "WHERE q.subject.subject_code = :subjectCode ";

        Query<QuestionInDrawer> query = session.createQuery(queryString, QuestionInDrawer.class);
        query.setParameter("subjectCode", subjectCode);
        List<QuestionInDrawer> questions = query.getResultList();
        return questions;
    }


    public static ExamInDrawer getExamInDrawerByExecCode(int exec_id) {
        String queryString = "SELECT ee FROM ExamExecution ee WHERE ee.execution_id = :exec_id";

        Query<ExamExecution> query = session.createQuery(queryString, ExamExecution.class);
        query.setParameter("exec_id", exec_id);

        try {
            ExamExecution examExecution = query.getSingleResult();
            return examExecution.getExam();
        } catch (NoResultException e) {
            System.out.println("there is no exam in drawer for this exam execution");
        }

        return null;

    }


    public static String getStudentCommentsByExecCode(int exec_id) {
        String queryString = "SELECT ee FROM ExamExecution ee WHERE ee.execution_id = :exec_id";

        Query<ExamExecution> query = session.createQuery(queryString, ExamExecution.class);
        query.setParameter("exec_id", exec_id);

        try {
            ExamExecution examExecution = query.getSingleResult();

            return examExecution.getExam().getStudentsComments();
        } catch (NoResultException e) {
            return "";
        }
    }

    public static int getTheTimeByExecCode(int exec_id) {
        String queryString = "SELECT ee FROM ExamExecution ee WHERE ee.execution_id = :exec_id";

        Query<ExamExecution> query = session.createQuery(queryString, ExamExecution.class);
        query.setParameter("exec_id", exec_id);

        try {
            ExamExecution examExecution = query.getSingleResult();

            ZoneId jerusalemTimeZone = ZoneId.of("Asia/Jerusalem");
            ZonedDateTime currentTimeInJerusalem = ZonedDateTime.now(jerusalemTimeZone);

            // Set the start time to the current time
            examExecution.setStart_time(currentTimeInJerusalem);

            int examDurationInMinutes = examExecution.getExam_duration();

            // Calculate the end time by adding the exam duration to the start time
            ZonedDateTime endTime = examExecution.getStart_time().plus(examDurationInMinutes, ChronoUnit.MINUTES);
            examExecution.setEnd_time(endTime);

            session.update(examExecution);
            session.flush();

            return examDurationInMinutes;
        } catch (NoResultException e) {
            return 0;
        }
    }


    public static String getExamExecutionCodeByExecId(int exec_id) {
        String queryString = "SELECT ee FROM ExamExecution ee WHERE ee.execution_id =:exec_id";

        Query<ExamExecution> query = session.createQuery(queryString, ExamExecution.class);
        query.setParameter("exec_id", exec_id);

        try {
            ExamExecution examExecution = query.getSingleResult();

//            for (ExamExecution e : examExecutions) {
//                System.out.println(e.getExecution_id());
//            }

            return examExecution.getExecution_code();
        } catch (NoResultException e) {
            return "";
        }

    }

    public static ExamInDrawer get_exam_by_execId(int exec_id) {

        String queryString = "SELECT ee FROM ExamExecution ee WHERE ee.execution_id = :exec_id";

        Query<ExamExecution> query = session.createQuery(queryString, ExamExecution.class);
        query.setParameter("exec_id", exec_id);

        try {
            ExamExecution examExecution = query.getSingleResult();

            return examExecution.getExam();
        } catch (NoResultException e) {
            return null;
        }

    }

    public static QuestionInDrawer getQuestionById(int question_id) {

        String queryString = "SELECT ee FROM QuestionInDrawer ee WHERE ee.QuestionInDrawer_id = :question_id";

        Query<QuestionInDrawer> query = session.createQuery(queryString, QuestionInDrawer.class);
        query.setParameter("question_id", question_id);

        try {
            QuestionInDrawer question = query.getSingleResult();

            return question;
        } catch (NoResultException e) {
            return null;
        }

    }

    public static List<Course> getQuestionCoursesById(int question_id) {

        String queryString = "SELECT ee FROM CourseQuestionInDrawerLink ee WHERE ee.question.QuestionInDrawer_id = :question_id";

        Query<CourseQuestionInDrawerLink> query = session.createQuery(queryString, CourseQuestionInDrawerLink.class);
        query.setParameter("question_id", question_id);

        try {
            List<CourseQuestionInDrawerLink> courseQuestionInDrawerLinkList = query.getResultList();
            List<Course> courses = new ArrayList<>();

            for (CourseQuestionInDrawerLink link : courseQuestionInDrawerLinkList) {
                courses.add(link.getCourse());
            }

            return courses;

        } catch (NoResultException e) {
            return new ArrayList<>();
        }

    }


    public static List<ExamExecution> getExamExecutionsListByStudentId(int student_id) {
        String queryString = "SELECT ee FROM ExamExecution ee WHERE ee.student.userId = :studentId AND ee.finished = false";

        Query<ExamExecution> query = session.createQuery(queryString, ExamExecution.class);
        query.setParameter("studentId", student_id);

        try {
            List<ExamExecution> examExecutions = query.getResultList();
            return examExecutions;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }


    public static List<Course> getCoursesListByStudentId(int student_id) {
        String queryString = "SELECT DISTINCT g.course FROM Grade g WHERE g.student.userId = :studentId";

        Query<Course> query = session.createQuery(queryString, Course.class);
        query.setParameter("studentId", student_id);

        try {
            List<Course> courses = query.getResultList();

            return courses;
        } catch (
                NoResultException e) {
            return new ArrayList<>();
        }


    }


    public static List<Student> getTheSelectedCourseOfTheExamStudents(int selected_exam_id) {


        String queryString1 = "SELECT e FROM ExamInDrawer e WHERE e.exam_code  = :selected_exam_id";

        Query<ExamInDrawer> query1 = session.createQuery(queryString1, ExamInDrawer.class);
        query1.setParameter("selected_exam_id", selected_exam_id);

        try {
            ExamInDrawer exam = query1.getSingleResult();

            //  System.out.println("the course of this exam is: "+exam.getCourse().getId());

            String queryString = "SELECT s FROM Student s " +
                    "JOIN s.grades grade " +
                    "WHERE grade.course.id = :course_id";

            Query<Student> query = session.createQuery(queryString, Student.class);
            query.setParameter("course_id", exam.getCourse().getId());

            try {
                List<Student> students = query.getResultList();
                return students;

            } catch (NoResultException e) {
                return new ArrayList<>();
            }


        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }


//get_exam_by_execCode()


    public static List<Score> getTheSelectedExamQuestionList(int selected_exam_id) {
        String hql = "FROM Score s WHERE s.exam.exam_code = :examId";

        Query<Score> query = session.createQuery(hql, Score.class);
        query.setParameter("examId", selected_exam_id);

        try {
            List<Score> scores = query.getResultList();

            return scores;

        } catch (NoResultException e) {

            return new ArrayList<>();
        }
    }

    public static String getTheSelectedExamTeacherComment(int selected_exam_id) {
        String hql = "FROM ExamInDrawer s WHERE s.exam_code = :selected_exam_id";

        Query<ExamInDrawer> query = session.createQuery(hql, ExamInDrawer.class);
        query.setParameter("selected_exam_id", selected_exam_id);

        try {
            ExamInDrawer exam = query.getSingleResult();

            return exam.getTeacherComments();

        } catch (NoResultException e) {

            return "";
        }

    }

    public static int getTheSelectedExamTime(int selected_exam_id) {
        String hql = "FROM ExamInDrawer s WHERE s.exam_code = :selected_exam_id";

        Query<ExamInDrawer> query = session.createQuery(hql, ExamInDrawer.class);
        query.setParameter("selected_exam_id", selected_exam_id);

        try {
            ExamInDrawer exam = query.getSingleResult();

            return exam.getExam_time();

        } catch (NoResultException e) {

            return -1;
        }

    }


    public static String getTheSelectedExamStudentsComment(int selected_exam_id) {
        String hql = "FROM ExamInDrawer s WHERE s.exam_code = :selected_exam_id";

        Query<ExamInDrawer> query = session.createQuery(hql, ExamInDrawer.class);
        query.setParameter("selected_exam_id", selected_exam_id);

        try {
            ExamInDrawer exam = query.getSingleResult();

            return exam.getStudentsComments();

        } catch (NoResultException e) {

            return "";
        }

    }


    public static List<Grade> getGradesByStudentId(int studentId) throws Exception {

//

        boolean found = false;

        List<Student> students = getAll(Student.class);
        for (int i = 0; i < students.size() && !found; i++) {
//            System.out.println("the student id is :"+students.get(i).getStudent_id());


            if (students.get(i).getStudent_id() == studentId) {
                found = true;
            }
        }
        if (found) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Grade> query = builder.createQuery(Grade.class);
            Root<Grade> gradeRoot = query.from(Grade.class);
            Join<Grade, Student> studentJoin = gradeRoot.join("student");
            query.select(gradeRoot).where(builder.equal(studentJoin.get("user_id"), studentId));
            List<Grade> grades = session.createQuery(query).getResultList();
//            System.out.println("getGradesByStudentId End");
            return grades;
        }

        // System.out.println("getGradesByStudentId End");
        return null;


    }

//    public static List<TimeExtensionRequest> Get_All_Time_Extension_requests() {
//
//
//        // System.out.println("i am in get all requests");
//        List<TimeExtensionRequest> timeExtensionRequests = new ArrayList<>();
//        timeExtensionRequests = getAll(TimeExtensionRequest.class);
//        return timeExtensionRequests;
//
//    }

    public static List<TimeExtensionRequest> Get_All_Time_Extension_requests() {


        String queryString1 = "SELECT e FROM TimeExtensionRequest e WHERE e.examExecution.finished = false and e.examExecution.start_time!=null ";
        Query<TimeExtensionRequest> query1 = session.createQuery(queryString1, TimeExtensionRequest.class);

        try {

            List<TimeExtensionRequest> timeExtensionRequests = query1.getResultList();

            return timeExtensionRequests;
        } catch (NoResultException e) {

        }

        return new ArrayList<>();

    }

    public static List<TimeExtensionRequest> Get_All_Time_Extension_requests_by_execution_code(int executionCode) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TimeExtensionRequest> query = builder.createQuery(TimeExtensionRequest.class);
        Root<TimeExtensionRequest> timeExtensionRequestRoot = query.from(TimeExtensionRequest.class);
        Join<TimeExtensionRequest, ExamExecution> examExecutionJoin = timeExtensionRequestRoot.join("examExecution");
        query.select(timeExtensionRequestRoot)
                .where(builder.equal(examExecutionJoin.get("execution_code"), executionCode));
        List<TimeExtensionRequest> timeExtensionRequests = session.createQuery(query).getResultList();
        return timeExtensionRequests;

    }

    public static List<QuestionInDrawer> getAllQuestions() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionInDrawer> query = builder.createQuery(QuestionInDrawer.class);
        query.from(QuestionInDrawer.class);
        List<QuestionInDrawer> data = session.createQuery(query).getResultList();
        return data;
    }

    public static List<ExamInDrawer> getAllExams() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExamInDrawer> query = builder.createQuery(ExamInDrawer.class);
        query.from(ExamInDrawer.class);
        List<ExamInDrawer> data = session.createQuery(query).getResultList();
        return data;
    }

    public static List<Lecturer> getAllLecturers() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lecturer> query = builder.createQuery(Lecturer.class);
        query.from(Lecturer.class);
        List<Lecturer> data = session.createQuery(query).getResultList();
        return data;
    }

    public static <T> List<T> getAll(Class<T> object) {


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }


    public static List<Student> getAllStudents() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        query.from(Student.class);
        List<Student> data = session.createQuery(query).getResultList();
        return data;
    }

    public static List<Course> getAllCourses() throws Exception {/*nawras*/
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        query.from(Course.class);
        List<Course> data = session.createQuery(query).getResultList();
        return data;
    }

    public static List<Subject> getAllSubjects() throws Exception {/*nawras*/
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
        query.from(Subject.class);
        List<Subject> data = session.createQuery(query).getResultList();
        return data;
    }


    public static List<Grade> getAllGrades() throws Exception {/*nawras*/
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Grade> query = builder.createQuery(Grade.class);
        query.from(Grade.class);
        List<Grade> data = session.createQuery(query).getResultList();
        return data;
    }

/////////////////////////////////////////////////////////////////////////


//////////////////////////addedddddddd by wesal///////////////////////////////////////////


    ///////AFFECTED

    public static List<Course> getAllCoursesByTeacherId(int teacherId) throws Exception {

        String queryString = "SELECT cl.course FROM CourseLecturerLink cl WHERE cl.lecturer.id = :teacherId";
        Query<Course> query = session.createQuery(queryString, Course.class);
        query.setParameter("teacherId", teacherId);

        List<Course> subjects = query.getResultList();

        session.getTransaction().commit();

        return subjects;
    }

    public static List<Course> getAllCoursesBySubjectCodeAndTeacherId(int subjectCode, int teacherId) {

        String queryString = "SELECT cl.course  FROM CourseLecturerLink cl WHERE cl.course.subject.subject_code= :subjectCode AND cl.lecturer.userId = :teacherId ";
        Query<Course> query = session.createQuery(queryString, Course.class);
        query.setParameter("subjectCode", subjectCode);
        query.setParameter("teacherId", teacherId);

        return query.getResultList();
    }

    public static List<Course> getCoursesListByLecturerIdAndSubjectsFilter(int lecturer_id, List<Subject> subjects) {
        String queryString = "SELECT cl.course FROM CourseLecturerLink cl WHERE cl.lecturer.id = :lecturer_id";
        Query<Course> query = session.createQuery(queryString, Course.class);
        query.setParameter("lecturer_id", lecturer_id);

        try {
            List<Course> courses = query.getResultList();

            List<Course> courseListfinal = new ArrayList<>();
            boolean add = false;

            for (Course course : courses) {
                add = false;
                for (Subject subject : subjects) {
                    if (course.getSubject().getSubject_code() == subject.getSubject_code()) {
                        add = true;
                    }
                }
                if (add) {
                    courseListfinal.add(course);
                }

            }

            return courseListfinal;

        } catch (NoResultException e) {
            System.out.println("there is no such courses");
            return new ArrayList<>();
        }

    }

    public static List<ExamInDrawer> getAllExamsByAllSubjectsAndCourses(int teacherId) throws Exception {

        String queryString = "SELECT s FROM Subject s " +
                "JOIN s.lecturerLinks link " +
                "WHERE link.lecturer.userId = :teacherId";

        Query<Subject> query = session.createQuery(queryString, Subject.class);
        query.setParameter("teacherId", teacherId);

        List<Subject> subjects = query.getResultList();

        String queryString1 = "SELECT cl.course FROM CourseLecturerLink cl WHERE cl.lecturer.userId  = :teacherId";

        Query<Course> query1 = session.createQuery(queryString1, Course.class);
        query1.setParameter("teacherId", teacherId);
        List<Course> courses = query1.getResultList();


        String queryString2 = "SELECT s FROM ExamInDrawer s";
        Query<ExamInDrawer> query2 = session.createQuery(queryString2, ExamInDrawer.class);


        try {
            List<ExamInDrawer> data = query2.getResultList();

            List<ExamInDrawer> datatemp = new ArrayList<>(data);

            boolean found;

            if (subjects != null) {
                for (ExamInDrawer exam : data) {
                    found = false;

                    for (Subject sub : subjects) {
                        if (exam.getSubject().getSubject_code() == sub.getSubject_code()) {
                            found = true;
                        }
                    }

                    if (!found) {
                        datatemp.remove(exam);
                    }
                }
            }

            data = new ArrayList<>(datatemp);


            if (courses != null) {
                //   System.out.println("courses isn't null");

                for (ExamInDrawer exam : data) {
                    found = false;

                    //      System.out.println("now we are checking courses for exam: " + exam.getExam_code());

                    for (Course course : courses) {
                        if (exam.getCourse().getId() == course.getId()) {
                            found = true;
                        }
                    }
                    if (!found) {
                        //     System.out.println("removing exam: " + exam.getExam_code());
                        datatemp.remove(exam);
                    }
                }
            }

            return datatemp;

        } catch (NoResultException e) {
            System.out.println("there is no such exams");
            return new ArrayList<>();
        }
    }


    //////////////TILL HERE AFFECTED


    public static List<ExamInDrawer> getAllExamsByFilter(int lecturer_id,
                                                         List<Subject> subjects,
                                                         List<Course> courses,
                                                         List<Lecturer> lecturers,
                                                         int range) throws Exception {

//        String queryString = "SELECT s FROM ExamInDrawer s";
//        Query<ExamInDrawer> query = session.createQuery(queryString, ExamInDrawer.class);


        try {
            System.out.println("hi");
            List<ExamInDrawer> data = getAllExamsByAllSubjectsAndCourses(lecturer_id);
            System.out.println("bye");

            List<ExamInDrawer> datatemp = new ArrayList<>(data);

            boolean found;

            if (subjects != null) {
                for (ExamInDrawer exam : data) {
                    found = false;

                    for (Subject sub : subjects) {
                        if (exam.getSubject().getSubject_code() == sub.getSubject_code()) {
                            found = true;
                        }
                    }

                    if (!found) {
                        datatemp.remove(exam);
                    }
                }
            }

            data = new ArrayList<>(datatemp);


            if (courses != null) {
                //System.out.println("courses isn't null");

                for (ExamInDrawer exam : data) {
                    found = false;

                    // System.out.println("now we are checking courses for exam: " + exam.getExam_code());

                    for (Course course : courses) {
                        if (exam.getCourse().getId() == course.getId()) {
                            found = true;
                        }
                    }
                    if (!found) {
                        //  System.out.println("removing exam: " + exam.getExam_code());
                        datatemp.remove(exam);
                    }
                }
            }


            data = new ArrayList<>(datatemp);


            if (lecturers != null) {
                //System.out.println("courses isn't null");
                for (ExamInDrawer exam : data) {
                    found = false;

                    for (Lecturer lec : lecturers) {
                        //  System.out.println(exam.getAuthor_name() + " vs. " + lec.getFirstName() + " " + lec.getLastName());
                        if (exam.getAuthor_id() == lec.getId()) {
                            found = true;
                        }
                    }

                    if (!found) {
                        //  System.out.println("removing exam: " + exam.getExam_code());
                        datatemp.remove(exam);
                    }
                }
            }

            data = new ArrayList<>(datatemp);


            if (range != -1) {
                //   System.out.println("filter by range");
                for (ExamInDrawer exam : data) {
                    if (exam.getExam_time() > range) {
                        //    System.out.println("removing exam: " + exam.getExam_code());
                        datatemp.remove(exam);
                    }
                }
            }


            data = new ArrayList<>(datatemp);

            //  System.out.println("done");

            return data;

        } catch (NoResultException e) {
            System.out.println("there is no such exams");
            return new ArrayList<>();
        }
    }


    public static List<QuestionInDrawer> getAllQuestionsBySubjectAndCourse(int subjectCode, int courseCode) {
        String queryString = "SELECT q FROM QuestionInDrawer q " +
                "JOIN q.courseQuestionInDrawerLinks link " +
                "WHERE q.subject.subject_code = :subjectCode " +
                "AND link.course.id = :courseCode";

        Query<QuestionInDrawer> query = session.createQuery(queryString, QuestionInDrawer.class);
        query.setParameter("subjectCode", subjectCode);
        query.setParameter("courseCode", courseCode);

        List<QuestionInDrawer> questions = query.getResultList();

        return questions;
    }


    public static List<Subject> getAllSubjectsByTeacherId(int teacherId) throws Exception {
        String queryString = "SELECT s FROM Subject s " +
                "JOIN s.lecturerLinks link " +
                "WHERE link.lecturer.userId = :teacherId";

        Query<Subject> query = session.createQuery(queryString, Subject.class);
        query.setParameter("teacherId", teacherId);

        List<Subject> subjects = query.getResultList();
        return subjects;
    }


    public static void main(String[] args) {
        try {
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
            } catch (HibernateException e) {
                e.printStackTrace();
            }


            /////////////////////generate users //////////////////////////////

            generateLecturers();  //10 lecturers
            generateStudents(); //10 students

            Manager man = new Manager(123456784, "manager", "falah", "sady");
            session.save(man);
            session.flush();

            man = new Manager(123456777, "manager", "bella", "scott");
            session.save(man);
            session.flush();

            ////////////////////generate subjects and courses/////////////////////

            generateSubjects_courses(); //10 subjects and 50 courses


            ////////////////////till now we have  //////////////////

            List<Student> students = getAll(Student.class);
            List<Course> courses = getAll(Course.class);
            List<Lecturer> lecturers = getAll(Lecturer.class);
            List<Subject> subjects = getAll(Subject.class);

            //////////////////add subjects and courses to lecturers ///////////////

            SubjectLecturerLink link = new SubjectLecturerLink(subjects.get(0), lecturers.get(0));
            session.save(link);
            SubjectLecturerLink link0 = new SubjectLecturerLink(subjects.get(0), lecturers.get(1));
            session.save(link0);

            CourseLecturerLink courseLecturerLink = new CourseLecturerLink(courses.get(0), lecturers.get(0));
            session.save(courseLecturerLink);
            courses.get(0).addCoursesLinks(courseLecturerLink);
            lecturers.get(0).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(1), lecturers.get(1));
            session.save(courseLecturerLink);
            courses.get(1).addCoursesLinks(courseLecturerLink);
            lecturers.get(1).addCoursesLinks(courseLecturerLink);


            courseLecturerLink = new CourseLecturerLink(courses.get(0), lecturers.get(1));
            session.save(courseLecturerLink);
            courses.get(0).addCoursesLinks(courseLecturerLink);
            lecturers.get(1).addCoursesLinks(courseLecturerLink);

            //////
            link = new SubjectLecturerLink(subjects.get(1), lecturers.get(2));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(1), lecturers.get(3));
            session.save(link0);

            courseLecturerLink = new CourseLecturerLink(courses.get(5), lecturers.get(2));
            session.save(courseLecturerLink);
            courses.get(5).addCoursesLinks(courseLecturerLink);
            lecturers.get(2).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(6), lecturers.get(3));
            session.save(courseLecturerLink);
            courses.get(6).addCoursesLinks(courseLecturerLink);
            lecturers.get(3).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(6), lecturers.get(2));
            session.save(courseLecturerLink);
            courses.get(6).addCoursesLinks(courseLecturerLink);
            lecturers.get(2).addCoursesLinks(courseLecturerLink);


            /////
            link = new SubjectLecturerLink(subjects.get(2), lecturers.get(4));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(2), lecturers.get(5));
            session.save(link0);
            link0 = new SubjectLecturerLink(subjects.get(2), lecturers.get(6));
            session.save(link0);

            courseLecturerLink = new CourseLecturerLink(courses.get(10), lecturers.get(4));
            session.save(courseLecturerLink);
            courses.get(10).addCoursesLinks(courseLecturerLink);
            lecturers.get(4).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(11), lecturers.get(5));
            session.save(courseLecturerLink);
            courses.get(11).addCoursesLinks(courseLecturerLink);
            lecturers.get(5).addCoursesLinks(courseLecturerLink);
            ///////////

            courseLecturerLink = new CourseLecturerLink(courses.get(11), lecturers.get(4));
            session.save(courseLecturerLink);
            courses.get(11).addCoursesLinks(courseLecturerLink);
            lecturers.get(4).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(11), lecturers.get(6));
            session.save(courseLecturerLink);
            courses.get(11).addCoursesLinks(courseLecturerLink);
            lecturers.get(6).addCoursesLinks(courseLecturerLink);


            link = new SubjectLecturerLink(subjects.get(3), lecturers.get(6));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(3), lecturers.get(7));
            session.save(link0);


            courseLecturerLink = new CourseLecturerLink(courses.get(15), lecturers.get(6));
            session.save(courseLecturerLink);
            courses.get(15).addCoursesLinks(courseLecturerLink);
            lecturers.get(6).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(16), lecturers.get(7));
            session.save(courseLecturerLink);
            courses.get(16).addCoursesLinks(courseLecturerLink);
            lecturers.get(7).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(15), lecturers.get(7));
            session.save(courseLecturerLink);
            courses.get(15).addCoursesLinks(courseLecturerLink);
            lecturers.get(7).addCoursesLinks(courseLecturerLink);
            ////////


            link = new SubjectLecturerLink(subjects.get(4), lecturers.get(8));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(4), lecturers.get(9));
            session.save(link0);

            courseLecturerLink = new CourseLecturerLink(courses.get(20), lecturers.get(8));
            session.save(courseLecturerLink);
            courses.get(20).addCoursesLinks(courseLecturerLink);
            lecturers.get(8).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(21), lecturers.get(9));
            session.save(courseLecturerLink);
            courses.get(21).addCoursesLinks(courseLecturerLink);
            lecturers.get(9).addCoursesLinks(courseLecturerLink);
            /////////////////

            link = new SubjectLecturerLink(subjects.get(5), lecturers.get(0));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(5), lecturers.get(1));
            session.save(link0);

            courseLecturerLink = new CourseLecturerLink(courses.get(25), lecturers.get(0));
            session.save(courseLecturerLink);
            courses.get(25).addCoursesLinks(courseLecturerLink);
            lecturers.get(0).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(26), lecturers.get(1));
            session.save(courseLecturerLink);
            courses.get(26).addCoursesLinks(courseLecturerLink);
            lecturers.get(1).addCoursesLinks(courseLecturerLink);
            //////////////

            link = new SubjectLecturerLink(subjects.get(6), lecturers.get(2));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(6), lecturers.get(3));
            session.save(link0);

            courseLecturerLink = new CourseLecturerLink(courses.get(30), lecturers.get(2));
            session.save(courseLecturerLink);
            courses.get(30).addCoursesLinks(courseLecturerLink);
            lecturers.get(2).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(31), lecturers.get(3));
            session.save(courseLecturerLink);
            courses.get(31).addCoursesLinks(courseLecturerLink);
            lecturers.get(3).addCoursesLinks(courseLecturerLink);
            /////////////////////////

            link = new SubjectLecturerLink(subjects.get(7), lecturers.get(4));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(7), lecturers.get(5));
            session.save(link0);

            courseLecturerLink = new CourseLecturerLink(courses.get(36), lecturers.get(4));
            session.save(courseLecturerLink);
            courses.get(36).addCoursesLinks(courseLecturerLink);
            lecturers.get(4).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(37), lecturers.get(5));
            session.save(courseLecturerLink);
            courses.get(37).addCoursesLinks(courseLecturerLink);
            lecturers.get(5).addCoursesLinks(courseLecturerLink);
            /////////////////

            link = new SubjectLecturerLink(subjects.get(8), lecturers.get(6));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(8), lecturers.get(7));
            session.save(link0);

            courseLecturerLink = new CourseLecturerLink(courses.get(41), lecturers.get(6));
            session.save(courseLecturerLink);
            courses.get(41).addCoursesLinks(courseLecturerLink);
            lecturers.get(6).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(42), lecturers.get(7));
            session.save(courseLecturerLink);
            courses.get(42).addCoursesLinks(courseLecturerLink);
            lecturers.get(7).addCoursesLinks(courseLecturerLink);
            ///////////////////////////////////////

            link = new SubjectLecturerLink(subjects.get(9), lecturers.get(8));
            session.save(link);
            link0 = new SubjectLecturerLink(subjects.get(9), lecturers.get(9));
            session.save(link0);

            courseLecturerLink = new CourseLecturerLink(courses.get(45), lecturers.get(8));
            session.save(courseLecturerLink);
            courses.get(45).addCoursesLinks(courseLecturerLink);
            lecturers.get(8).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(46), lecturers.get(9));
            session.save(courseLecturerLink);
            courses.get(46).addCoursesLinks(courseLecturerLink);
            lecturers.get(9).addCoursesLinks(courseLecturerLink);


            ///added razan
            link = new SubjectLecturerLink(subjects.get(1), lecturers.get(9));
            session.save(link);
            courseLecturerLink = new CourseLecturerLink(courses.get(6), lecturers.get(9));
            session.save(courseLecturerLink);
            courses.get(6).addCoursesLinks(courseLecturerLink);
            lecturers.get(9).addCoursesLinks(courseLecturerLink);

            courseLecturerLink = new CourseLecturerLink(courses.get(6), lecturers.get(1));
            session.save(courseLecturerLink);
            courses.get(6).addCoursesLinks(courseLecturerLink);
            lecturers.get(1).addCoursesLinks(courseLecturerLink);

            session.flush();


            ////////////////////generate questions//////////////////////

// start add questions

            QuestionInDrawer question1 = new QuestionInDrawer(subjects.get(0),
                    "What is the derivative of f(x) = 3x^2 - 2x + 5?",
                    "6x - 2", "6x + 2", "3x^2 - 2", "3x^2 + 2", 1);
            session.save(question1);
            session.flush();

            CourseQuestionInDrawerLink link2 = new CourseQuestionInDrawerLink(courses.get(0), question1);
            session.save(link2);

// Question 2
            QuestionInDrawer question2 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following integrals represents the area under the curve y = sin(x) from 0 to π?",
                    "∫0 to π sin(x) dx", "∫0 to π sin(x) dx", "∫0 to π -sin(x) dx", "∫0 to π -sin(x) dx", 3);
            session.save(question2);
            session.flush();

            link2 = new CourseQuestionInDrawerLink(courses.get(0), question2);
            session.save(link2);

// Question 3
            QuestionInDrawer question3 = new QuestionInDrawer(subjects.get(0),
                    "What is the limit of (x^2 + 3x + 2) / (2x + 1) as x approaches infinity?",
                    "1/2", "2", "3/2", "1", 4);
            session.save(question3);
            session.flush();

            link2 = new CourseQuestionInDrawerLink(courses.get(0), question3);
            session.save(link2);

// Question 4
            QuestionInDrawer question4 = new QuestionInDrawer(subjects.get(0),
                    "At what point does the function f(x) = e^x intersect the x-axis?",
                    "x = 0", "x = 1", "x = -1", "It does not intersect the x-axis", 3);
            session.save(question4);
            session.flush();
            link2 = new CourseQuestionInDrawerLink(courses.get(0), question4);
            session.save(link2);

// Question 5
            QuestionInDrawer question5 = new QuestionInDrawer(subjects.get(0),
                    "What is the second derivative of y = 4x^3 - 2x^2 + 7x - 1?",
                    "24x - 4", "12x^2 - 4x + 7", "12x - 2", "0", 2);
            session.save(question5);
            session.flush();
            link2 = new CourseQuestionInDrawerLink(courses.get(0), question5);
            session.save(link2);

// Question 6
            QuestionInDrawer question6 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following is the Maclaurin series representation of sin(x)?",
                    "x - x^3/3! + x^5/5! - x^7/7!", "x + x^2/2! + x^3/3! + x^4/4!", "x - x^2 + x^3 - x^4", "1 - x^2/2 + x^4/24", 1);
            session.save(question6);
            session.flush();
            link2 = new CourseQuestionInDrawerLink(courses.get(0), question6);
            session.save(link2);

// Question 7
            QuestionInDrawer question7 = new QuestionInDrawer(subjects.get(0),
                    "What is the definite integral of ∫(0 to 2) x^2 dx?",
                    "4/3", "8/3", "2", "16/3", 2);
            session.save(question7);
            session.flush();
            link2 = new CourseQuestionInDrawerLink(courses.get(0), question7);
            session.save(link2);


// linear algebra *****************************************
// Question 16
            QuestionInDrawer question16 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following statements is true about the null space of a matrix?",
                    "The null space is always empty.",
                    "The null space is perpendicular to the column space.",
                    "The dimension of the null space is equal to the rank of the matrix.",
                    "The null space contains only the zero vector.", 4);
            session.save(question16);
            session.flush();
            CourseQuestionInDrawerLink link16 = new CourseQuestionInDrawerLink(courses.get(1), question16);
            session.save(link16);

// Question 17
            QuestionInDrawer question17 = new QuestionInDrawer(subjects.get(0),
                    "What is the dimension of the column space of a 3x5 matrix?",
                    "3",
                    "5",
                    "15",
                    "The dimension cannot be determined without additional information.", 2);
            session.save(question17);
            session.flush();
            CourseQuestionInDrawerLink link17 = new CourseQuestionInDrawerLink(courses.get(1), question17);
            session.save(link17);

// Question 18
            QuestionInDrawer question18 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following matrices is orthogonal?",
                    "| 1 0 | \n| 0 -1 |",
                    "| 1 0 | \n| 0 1 |",
                    "| 0 1 | \n| 1 0 |",
                    "| 1 1 | \n| 1 -1 |", 3);
            session.save(question18);
            session.flush();
            CourseQuestionInDrawerLink link18 = new CourseQuestionInDrawerLink(courses.get(1), question18);
            session.save(link18);

// Question 19
            QuestionInDrawer question19 = new QuestionInDrawer(subjects.get(0),
                    "What is the projection of the vector u = <3, 5> onto the vector v = <1, 2>?",
                    "<3, 3>",
                    "<2, 4>",
                    "<5, 10>",
                    "<4, 2>", 4);
            session.save(question19);
            session.flush();
            CourseQuestionInDrawerLink link19 = new CourseQuestionInDrawerLink(courses.get(1), question19);
            session.save(link19);

// Question 20
            QuestionInDrawer question20 = new QuestionInDrawer(subjects.get(0),
                    "What is the Jordan form of a diagonalizable matrix?",
                    "A matrix with all zeros.",
                    "A matrix with ones on the main diagonal.",
                    "A matrix with all eigenvalues on the main diagonal.",
                    "A matrix with all ones on the main diagonal.", 3);
            session.save(question20);
            session.flush();
            CourseQuestionInDrawerLink link20 = new CourseQuestionInDrawerLink(courses.get(1), question20);
            session.save(link20);

// Question 21
            QuestionInDrawer question21 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following is NOT a subspace of R^3?",
                    "The xy-plane",
                    "The x-axis",
                    "The set of all points on a line through the origin",
                    "The set of all points inside a sphere centered at the origin", 4);
            session.save(question21);
            session.flush();
            CourseQuestionInDrawerLink link21 = new CourseQuestionInDrawerLink(courses.get(1), question21);
            session.save(link21);

// Question 22
            QuestionInDrawer question22 = new QuestionInDrawer(subjects.get(0),
                    "What is the characteristic polynomial of a matrix A?",
                    "The polynomial formed by summing the diagonal elements of A.",
                    "The polynomial that determines the rank of A.",
                    "The polynomial formed by summing the elements of A.",
                    "The polynomial det(A - λI), where λ is an eigenvalue of A.", 4);
            session.save(question22);
            session.flush();
            CourseQuestionInDrawerLink link22 = new CourseQuestionInDrawerLink(courses.get(1), question22);
            session.save(link22);

// probability and statistic ****************************************
// Question 31
            QuestionInDrawer question31 = new QuestionInDrawer(subjects.get(0),
                    "In a sample of 100 observations, how many degrees of freedom are there when calculating the t-score?",
                    "99",
                    "100",
                    "101",
                    "200", 1);
            session.save(question31);
            session.flush();
            CourseQuestionInDrawerLink link31 = new CourseQuestionInDrawerLink(courses.get(2), question31);
            session.save(link31);

// Question 32
            QuestionInDrawer question32 = new QuestionInDrawer(subjects.get(0),
                    "What is the formula for the coefficient of determination (R-squared) in linear regression?",
                    "R^2 = 1 - SSR / SST",
                    "R^2 = SSR / SST",
                    "R^2 = 1 - SST / SSR",
                    "R^2 = SST / SSR", 1);
            session.save(question32);
            session.flush();
            CourseQuestionInDrawerLink link32 = new CourseQuestionInDrawerLink(courses.get(2), question32);
            session.save(link32);

// Question 33
            QuestionInDrawer question33 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following distributions is used to model the time between events in a Poisson process?",
                    "Exponential distribution",
                    "Normal distribution",
                    "Binomial distribution",
                    "Poisson distribution", 1);
            session.save(question33);
            session.flush();
            CourseQuestionInDrawerLink link33 = new CourseQuestionInDrawerLink(courses.get(2), question33);
            session.save(link33);

// Question 34
            QuestionInDrawer question34 = new QuestionInDrawer(subjects.get(0),
                    "What does a p-value less than the significance level (α) indicate in hypothesis testing?",
                    "The null hypothesis should be rejected.",
                    "The null hypothesis should be accepted.",
                    "The alternative hypothesis should be rejected.",
                    "The test is inconclusive.", 1);
            session.save(question34);
            session.flush();
            CourseQuestionInDrawerLink link34 = new CourseQuestionInDrawerLink(courses.get(2), question34);
            session.save(link34);

// Question 35
            QuestionInDrawer question35 = new QuestionInDrawer(subjects.get(0),
                    "In a chi-squared goodness-of-fit test, what is the null hypothesis?",
                    "The observed frequencies are significantly different from the expected frequencies.",
                    "The observed and expected frequencies are not significantly different.",
                    "The sample data is normally distributed.",
                    "The sample data has a certain variance.", 2);
            session.save(question35);
            session.flush();
            CourseQuestionInDrawerLink link35 = new CourseQuestionInDrawerLink(courses.get(2), question35);
            session.save(link35);

// Question 36
            QuestionInDrawer question36 = new QuestionInDrawer(subjects.get(0),
                    "What is the formula for calculating the expected value of a discrete random variable X?",
                    "E(X) = ∑(x * P(X = x))",
                    "E(X) = ∑(P(X = x))",
                    "E(X) = ∑(x) / ∑(P(X = x))",
                    "E(X) = ∑(x * P(X != x))", 1);
            session.save(question36);
            session.flush();
            CourseQuestionInDrawerLink link36 = new CourseQuestionInDrawerLink(courses.get(2), question36);
            session.save(link36);

// Question 37
            QuestionInDrawer question37 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following is a property of a standard normal distribution?",
                    "It has a mean of 1 and a standard deviation of 0.",
                    "Its shape is symmetric and bell-shaped.",
                    "It has a mean of 0 and a standard deviation of 1.",
                    "It is used to model discrete events.", 3);
            session.save(question37);
            session.flush();
            CourseQuestionInDrawerLink link37 = new CourseQuestionInDrawerLink(courses.get(2), question37);
            session.save(link37);


//Differential Equations *********************
// Question 46
            QuestionInDrawer question46 = new QuestionInDrawer(subjects.get(0),
                    "What is the general solution of the first-order linear differential equation: dy/dx + 2xy = 3x?",
                    "y = e^(x^2) + C", "y = 2xe^x + C", "y = 3x^2 + C", "y = x^3 + C", 1);
            session.save(question46);
            session.flush();
            CourseQuestionInDrawerLink link46 = new CourseQuestionInDrawerLink(courses.get(3), question46);
            session.save(link46);

// Question 47
            QuestionInDrawer question47 = new QuestionInDrawer(subjects.get(0),
                    "Which method is used to solve homogeneous second-order linear differential equations with constant coefficients?",
                    "Separation of variables", "Substitution method", "Characteristic equation method", "Reduction of order", 3);
            session.save(question47);
            session.flush();
            CourseQuestionInDrawerLink link47 = new CourseQuestionInDrawerLink(courses.get(3), question47);
            session.save(link47);

// Question 48
            QuestionInDrawer question48 = new QuestionInDrawer(subjects.get(0),
                    "What is the solution of the differential equation: d^2y/dx^2 + 4y = 0?",
                    "y = e^(2x) + C", "y = e^(-2x) + C", "y = sin(2x) + C", "y = cos(2x) + C", 3);
            session.save(question48);
            session.flush();
            CourseQuestionInDrawerLink link48 = new CourseQuestionInDrawerLink(courses.get(3), question48);
            session.save(link48);

// Question 49
            QuestionInDrawer question49 = new QuestionInDrawer(subjects.get(0),
                    "Which method can be used to solve the Bernoulli differential equation: dy/dx + 2xy = y^3?",
                    "Separation of variables", "Substitution method", "Integrating factor method", "Exact differential equation method", 2);
            session.save(question49);
            session.flush();
            CourseQuestionInDrawerLink link49 = new CourseQuestionInDrawerLink(courses.get(3), question49);
            session.save(link49);

// Question 50
            QuestionInDrawer question50 = new QuestionInDrawer(subjects.get(0),
                    "What is the particular solution of the linear nonhomogeneous differential equation: dy/dx - 3y = 2e^x?",
                    "y = -2e^x", "y = e^x", "y = 2e^x", "y = 3e^x", 2);
            session.save(question50);
            session.flush();
            CourseQuestionInDrawerLink link50 = new CourseQuestionInDrawerLink(courses.get(3), question50);
            session.save(link50);

// Question 51
            QuestionInDrawer question51 = new QuestionInDrawer(subjects.get(0),
                    "Which method is used to solve first-order linear homogeneous differential equations?",
                    "Substitution method", "Separation of variables", "Characteristic equation method", "Integration by parts", 3);
            session.save(question51);
            session.flush();
            CourseQuestionInDrawerLink link51 = new CourseQuestionInDrawerLink(courses.get(3), question51);
            session.save(link51);

// Question 52
            QuestionInDrawer question52 = new QuestionInDrawer(subjects.get(0),
                    "What is the solution of the differential equation: dy/dx = y^2 + x?",
                    "y = 2tan(x + C)", "y = ln|x + C|", "y = e^(x^2)/2 + C", "y = 2x + C", 2);
            session.save(question52);
            session.flush();
            CourseQuestionInDrawerLink link52 = new CourseQuestionInDrawerLink(courses.get(3), question52);
            session.save(link52);

// Question 53
            QuestionInDrawer question53 = new QuestionInDrawer(subjects.get(0),
                    "Which type of differential equation can be solved using the substitution y = vx?",
                    "Homogeneous", "Bernoulli", "Linear nonhomogeneous", "Exact", 2);
            session.save(question53);
            session.flush();
            CourseQuestionInDrawerLink link53 = new CourseQuestionInDrawerLink(courses.get(3), question53);
            session.save(link53);


//discrete mathematics
// Question 61
            QuestionInDrawer question61 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following statements about prime numbers is true?",
                    "A. Every prime number is odd.", "B. Every prime number is even.",
                    "C. The only even prime number is 2.", "D. Prime numbers are consecutive integers.", 3);
            session.save(question61);
            session.flush();
            CourseQuestionInDrawerLink link61 = new CourseQuestionInDrawerLink(courses.get(4), question61);
            session.save(link61);

// Question 62
            QuestionInDrawer question62 = new QuestionInDrawer(subjects.get(0),
                    "How many vertices does a complete graph with 7 vertices have?",
                    "A. 7", "B. 14", "C. 21", "D. 28", 4);
            session.save(question62);
            session.flush();
            CourseQuestionInDrawerLink link62 = new CourseQuestionInDrawerLink(courses.get(4), question62);
            session.save(link62);

// Question 63
            QuestionInDrawer question63 = new QuestionInDrawer(subjects.get(0),
                    "In how many ways can you arrange the letters of the word 'MISSISSIPPI'?",
                    "A. 34650", "B. 69300", "C. 1260", "D. 210", 2);
            session.save(question63);
            session.flush();
            CourseQuestionInDrawerLink link63 = new CourseQuestionInDrawerLink(courses.get(4), question63);
            session.save(link63);

// Question 64
            QuestionInDrawer question64 = new QuestionInDrawer(subjects.get(0),
                    "What is the chromatic number of a cycle graph with 8 vertices?",
                    "A. 4", "B. 5", "C. 6", "D. 8", 2);
            session.save(question64);
            session.flush();
            CourseQuestionInDrawerLink link64 = new CourseQuestionInDrawerLink(courses.get(4), question64);
            session.save(link64);

// Question 65
            QuestionInDrawer question65 = new QuestionInDrawer(subjects.get(0),
                    "The handshake lemma in graph theory states that:",
                    "A. The sum of degrees of all vertices is twice the number of edges.",
                    "B. The sum of degrees of all vertices is equal to the number of edges.",
                    "C. The sum of degrees of all vertices is half the number of edges.",
                    "D. The sum of degrees of all vertices is four times the number of edges.", 1);
            session.save(question65);
            session.flush();
            CourseQuestionInDrawerLink link65 = new CourseQuestionInDrawerLink(courses.get(4), question65);
            session.save(link65);

// Question 66
            QuestionInDrawer question66 = new QuestionInDrawer(subjects.get(0),
                    "Which of the following statements is NOT true about trees?",
                    "A. A tree is a connected graph with no cycles.",
                    "B. Adding an edge to a tree will always create a cycle.",
                    "C. A tree with n vertices has n-1 edges.",
                    "D. A tree can have only one vertex.", 2);
            session.save(question66);
            session.flush();
            CourseQuestionInDrawerLink link66 = new CourseQuestionInDrawerLink(courses.get(4), question66);
            session.save(link66);

// Question 67
            QuestionInDrawer question67 = new QuestionInDrawer(subjects.get(0),
                    "How many different permutations are there of the letters in the word 'ALGORITHM'?",
                    "A. 5040", "B. 40320", "C. 362880", "D. 720", 3);
            session.save(question67);
            session.flush();
            CourseQuestionInDrawerLink link67 = new CourseQuestionInDrawerLink(courses.get(4), question67);
            session.save(link67);

// Question 68
            QuestionInDrawer question68 = new QuestionInDrawer(subjects.get(0),
                    "A Hamiltonian cycle in a graph is a cycle that:",
                    "A. Visits each vertex exactly once and returns to the starting vertex.",
                    "B. Visits each edge exactly once and returns to the starting edge.",
                    "C. Visits each vertex exactly once without returning to the starting vertex.",
                    "D. Visits each edge exactly once without returning to the starting edge.", 1);
            session.save(question68);
            session.flush();
            CourseQuestionInDrawerLink link68 = new CourseQuestionInDrawerLink(courses.get(4), question68);
            session.save(link68);


// Computer Science
//Introduction to Programming
// Question 76
            QuestionInDrawer question76 = new QuestionInDrawer(subjects.get(1),
                    "What is the output of the following code snippet?\n\n"
                            + "int x = 5;\n"
                            + "x = x++ + ++x;\n"
                            + "System.out.println(x);",
                    "A. 10", "B. 11", "C. 12", "D. 13", 3);
            session.save(question76);
            session.flush();
            CourseQuestionInDrawerLink link76 = new CourseQuestionInDrawerLink(courses.get(5), question76);
            session.save(link76);

// Question 77
            QuestionInDrawer question77 = new QuestionInDrawer(subjects.get(1),
                    "What does the acronym 'OOP' stand for in programming?",
                    "A. Obfuscated Output Programming", "B. Original Object Parsing",
                    "C. Object-Oriented Programming", "D. Operational Output Procedure", 3);
            session.save(question77);
            session.flush();
            CourseQuestionInDrawerLink link77 = new CourseQuestionInDrawerLink(courses.get(5), question77);
            session.save(link77);

// Question 78
            QuestionInDrawer question78 = new QuestionInDrawer(subjects.get(1),
                    "Which of the following is NOT a primitive data type in Java?",
                    "A. int", "B. boolean", "C. string", "D. double", 3);
            session.save(question78);
            session.flush();
            CourseQuestionInDrawerLink link78 = new CourseQuestionInDrawerLink(courses.get(5), question78);
            session.save(link78);

// Question 79
            QuestionInDrawer question79 = new QuestionInDrawer(subjects.get(1),
                    "What is the purpose of a 'for' loop in programming?",
                    "A. To print text on the screen.",
                    "B. To declare and initialize variables.",
                    "C. To execute a block of code repeatedly a specified number of times.",
                    "D. To define a custom function.", 3);
            session.save(question79);
            session.flush();
            CourseQuestionInDrawerLink link79 = new CourseQuestionInDrawerLink(courses.get(5), question79);
            session.save(link79);

// Question 80
            QuestionInDrawer question80 = new QuestionInDrawer(subjects.get(1),
                    "Which of the following sorting algorithms has the worst-case time complexity of O(n^2)?",
                    "A. Merge Sort", "B. Quick Sort", "C. Insertion Sort", "D. Radix Sort", 3);
            session.save(question80);
            session.flush();
            CourseQuestionInDrawerLink link80 = new CourseQuestionInDrawerLink(courses.get(5), question80);
            session.save(link80);

// Question 81
            QuestionInDrawer question81 = new QuestionInDrawer(subjects.get(1),
                    "In object-oriented programming, what is encapsulation?",
                    "A. The process of converting source code into machine code.",
                    "B. The process of breaking down a complex problem into smaller subproblems.",
                    "C. The process of hiding implementation details and exposing only necessary features.",
                    "D. The process of converting a high-level programming language into assembly language.", 3);
            session.save(question81);
            session.flush();
            CourseQuestionInDrawerLink link81 = new CourseQuestionInDrawerLink(courses.get(5), question81);
            session.save(link81);

// Question 82
            QuestionInDrawer question82 = new QuestionInDrawer(subjects.get(1),
                    "What is the term for a function calling itself within its own body?",
                    "A. Iteration", "B. Enumeration", "C. Recursion", "D. Overloading", 3);
            session.save(question82);
            session.flush();
            CourseQuestionInDrawerLink link82 = new CourseQuestionInDrawerLink(courses.get(5), question82);
            session.save(link82);


//Data Structures and Algorithms
// Question 91
            QuestionInDrawer question91 = new QuestionInDrawer(subjects.get(1),
                    "Which data structure is best suited for implementing a LIFO (Last-In-First-Out) behavior?",
                    "Queue", "Stack", "Tree", "LinkedList", 2);
            session.save(question91);
            session.flush();
            CourseQuestionInDrawerLink link91 = new CourseQuestionInDrawerLink(courses.get(6), question91);
            session.save(link91);

// Question 92
            QuestionInDrawer question92 = new QuestionInDrawer(subjects.get(1),
                    "What is the average time complexity of searching an element in a balanced binary search tree?",
                    "O(1)", "O(log n)", "O(n)", "O(n log n)", 2);
            session.save(question92);
            session.flush();
            CourseQuestionInDrawerLink link92 = new CourseQuestionInDrawerLink(courses.get(6), question92);
            session.save(link92);

// Question 93
            QuestionInDrawer question93 = new QuestionInDrawer(subjects.get(1),
                    "Which sorting algorithm has the worst-case time complexity of O(n^2)?",
                    "Merge Sort", "Quick Sort", "Bubble Sort", "Insertion Sort", 2);
            session.save(question93);
            session.flush();
            CourseQuestionInDrawerLink link93 = new CourseQuestionInDrawerLink(courses.get(6), question93);
            session.save(link93);

// Question 94
            QuestionInDrawer question94 = new QuestionInDrawer(subjects.get(1),
                    "In which data structure does the 'FIFO' (First-In-First-Out) principle apply?",
                    "Heap", "Stack", "Queue", "Tree", 3);
            session.save(question94);
            session.flush();
            CourseQuestionInDrawerLink link94 = new CourseQuestionInDrawerLink(courses.get(6), question94);
            session.save(link94);

// Question 95
            QuestionInDrawer question95 = new QuestionInDrawer(subjects.get(1),
                    "What is the time complexity of inserting an element at the end of an ArrayList with 'n' elements, assuming no resizing is needed?",
                    "O(1)", "O(log n)", "O(n)", "O(n log n)", 1);
            session.save(question95);
            session.flush();
            CourseQuestionInDrawerLink link95 = new CourseQuestionInDrawerLink(courses.get(6), question95);
            session.save(link95);

// Question 96
            QuestionInDrawer question96 = new QuestionInDrawer(subjects.get(1),
                    "Which data structure is used for implementing breadth-first search in a graph?",
                    "Stack", "Queue", "Heap", "LinkedList", 2);
            session.save(question96);
            session.flush();
            CourseQuestionInDrawerLink link96 = new CourseQuestionInDrawerLink(courses.get(6), question96);
            session.save(link96);

// Question 97
            QuestionInDrawer question97 = new QuestionInDrawer(subjects.get(1),
                    "What is the worst-case time complexity of the selection sort algorithm?",
                    "O(1)", "O(log n)", "O(n)", "O(n^2)", 4);
            session.save(question97);
            session.flush();
            CourseQuestionInDrawerLink link97 = new CourseQuestionInDrawerLink(courses.get(6), question97);
            session.save(link97);


//Database************************
// Question 106
            QuestionInDrawer question106 = new QuestionInDrawer(subjects.get(1),
                    "Which SQL keyword is used to retrieve data from a database?",
                    "A. UPDATE", "B. DELETE", "C. SELECT", "D. INSERT", 3);
            session.save(question106);
            session.flush();
            CourseQuestionInDrawerLink link106 = new CourseQuestionInDrawerLink(courses.get(7), question106);
            session.save(link106);

// Question 107
            QuestionInDrawer question107 = new QuestionInDrawer(subjects.get(1),
                    "What is the purpose of a primary key in a database table?",
                    "A. It ensures data integrity by enforcing unique values.",
                    "B. It provides a way to encrypt sensitive data.",
                    "C. It creates a link between two tables.",
                    "D. It allows for faster query execution.", 1);
            session.save(question107);
            session.flush();
            CourseQuestionInDrawerLink link107 = new CourseQuestionInDrawerLink(courses.get(7), question107);
            session.save(link107);

// Question 108
            QuestionInDrawer question108 = new QuestionInDrawer(subjects.get(1),
                    "In a relational database, what is the relationship between two tables connected by a foreign key?",
                    "A. One-to-One", "B. One-to-Many", "C. Many-to-One", "D. Many-to-Many", 2);
            session.save(question108);
            session.flush();
            CourseQuestionInDrawerLink link108 = new CourseQuestionInDrawerLink(courses.get(7), question108);
            session.save(link108);

// Question 109
            QuestionInDrawer question109 = new QuestionInDrawer(subjects.get(1),
                    "Which SQL command is used to change the structure of a table?",
                    "A. ALTER TABLE", "B. UPDATE TABLE", "C. MODIFY TABLE", "D. CHANGE TABLE", 1);
            session.save(question109);
            session.flush();
            CourseQuestionInDrawerLink link109 = new CourseQuestionInDrawerLink(courses.get(7), question109);
            session.save(link109);

// Question 110
            QuestionInDrawer question110 = new QuestionInDrawer(subjects.get(1),
                    "What is a transaction in the context of a database?",
                    "A. A user query.", "B. A database backup.", "C. A set of related tables.",
                    "D. A sequence of one or more SQL statements.", 4);
            session.save(question110);
            session.flush();
            CourseQuestionInDrawerLink link110 = new CourseQuestionInDrawerLink(courses.get(7), question110);
            session.save(link110);

// Question 111
            QuestionInDrawer question111 = new QuestionInDrawer(subjects.get(1),
                    "Which of the following joins returns only the matching rows between two tables?",
                    "A. INNER JOIN", "B. LEFT JOIN", "C. RIGHT JOIN", "D. FULL OUTER JOIN", 1);
            session.save(question111);
            session.flush();
            CourseQuestionInDrawerLink link111 = new CourseQuestionInDrawerLink(courses.get(7), question111);
            session.save(link111);

// Question 112
            QuestionInDrawer question112 = new QuestionInDrawer(subjects.get(1),
                    "In a relational database, what does the term 'ACID' stand for?",
                    "A. Atomic, Composite, Isolated, Durable",
                    "B. Aggregated, Committed, Inherited, Decoupled",
                    "C. Atomicity, Consistency, Isolation, Durability",
                    "D. Affected, Conserved, Integrated, Deactivated", 3);
            session.save(question112);
            session.flush();
            CourseQuestionInDrawerLink link112 = new CourseQuestionInDrawerLink(courses.get(7), question112);
            session.save(link112);


// Artificial Intelligence************
// Question 121
            QuestionInDrawer question121 = new QuestionInDrawer(subjects.get(1),
                    "What term describes the ability of a machine to perform tasks that typically require human intelligence?",
                    "Automation", "Robotics", "Artificial Intelligence", "Machine Learning", 3);
            session.save(question121);
            session.flush();
            CourseQuestionInDrawerLink link121 = new CourseQuestionInDrawerLink(courses.get(8), question121);
            session.save(link121);

// Question 122
            QuestionInDrawer question122 = new QuestionInDrawer(subjects.get(1),
                    "Which technique involves training a model to improve its performance on a specific task using labeled data?",
                    "Unsupervised Learning", "Reinforcement Learning", "Supervised Learning", "Semi-Supervised Learning", 3);
            session.save(question122);
            session.flush();
            CourseQuestionInDrawerLink link122 = new CourseQuestionInDrawerLink(courses.get(8), question122);
            session.save(link122);

// Question 123
            QuestionInDrawer question123 = new QuestionInDrawer(subjects.get(1),
                    "Which AI technique involves mapping sensory inputs to desired outputs?",
                    "Reinforcement Learning", "Unsupervised Learning", "Supervised Learning", "Neural Networks", 3);
            session.save(question123);
            session.flush();
            CourseQuestionInDrawerLink link123 = new CourseQuestionInDrawerLink(courses.get(8), question123);
            session.save(link123);

// Question 124
            QuestionInDrawer question124 = new QuestionInDrawer(subjects.get(1),
                    "What AI approach involves representing knowledge as a set of symbols and rules for manipulating those symbols?",
                    "Machine Learning", "Expert Systems", "Deep Learning", "Natural Language Processing", 2);
            session.save(question124);
            session.flush();
            CourseQuestionInDrawerLink link124 = new CourseQuestionInDrawerLink(courses.get(8), question124);
            session.save(link124);

// Question 125
            QuestionInDrawer question125 = new QuestionInDrawer(subjects.get(1),
                    "Which technique involves allowing an AI agent to learn by interacting with an environment and receiving feedback?",
                    "Supervised Learning", "Unsupervised Learning", "Reinforcement Learning", "Deep Learning", 3);
            session.save(question125);
            session.flush();
            CourseQuestionInDrawerLink link125 = new CourseQuestionInDrawerLink(courses.get(8), question125);
            session.save(link125);

// Question 126
            QuestionInDrawer question126 = new QuestionInDrawer(subjects.get(1),
                    "Which AI technique is inspired by the structure and function of the human brain?",
                    "Machine Learning", "Reinforcement Learning", "Expert Systems", "Neural Networks", 4);
            session.save(question126);
            session.flush();
            CourseQuestionInDrawerLink link126 = new CourseQuestionInDrawerLink(courses.get(8), question126);
            session.save(link126);

// Question 127
            QuestionInDrawer question127 = new QuestionInDrawer(subjects.get(1),
                    "What is the process of converting human language into a format that machines can understand?",
                    "Speech Recognition", "Machine Translation", "Natural Language Processing", "Image Recognition", 3);
            session.save(question127);
            session.flush();
            CourseQuestionInDrawerLink link127 = new CourseQuestionInDrawerLink(courses.get(8), question127);
            session.save(link127);


// Software Engineering********************
// Question 136
            QuestionInDrawer question136 = new QuestionInDrawer(subjects.get(1),
                    "Which software development process model involves a linear sequence of stages where each stage must be completed before moving to the next one?",
                    "Agile", "Waterfall", "Spiral", "Scrum", 2);
            session.save(question136);
            session.flush();
            CourseQuestionInDrawerLink link136 = new CourseQuestionInDrawerLink(courses.get(9), question136);
            session.save(link136);

// Question 137
            QuestionInDrawer question137 = new QuestionInDrawer(subjects.get(1),
                    "What is the purpose of the UML (Unified Modeling Language) in software engineering?",
                    "To write code directly", "To create user interfaces", "To design databases", "To model software systems", 4);
            session.save(question137);
            session.flush();
            CourseQuestionInDrawerLink link137 = new CourseQuestionInDrawerLink(courses.get(9), question137);
            session.save(link137);

// Question 138
            QuestionInDrawer question138 = new QuestionInDrawer(subjects.get(1),
                    "Which software testing technique involves subjecting the software to abnormal or unexpected inputs?",
                    "Unit testing", "Integration testing", "Black-box testing", "Stress testing", 4);
            session.save(question138);
            session.flush();
            CourseQuestionInDrawerLink link138 = new CourseQuestionInDrawerLink(courses.get(9), question138);
            session.save(link138);

// Question 139
            QuestionInDrawer question139 = new QuestionInDrawer(subjects.get(1),
                    "What is the main purpose of version control systems like Git in software development?",
                    "To manage project finances", "To design user interfaces", "To track and manage changes in source code", "To generate documentation", 3);
            session.save(question139);
            session.flush();
            CourseQuestionInDrawerLink link139 = new CourseQuestionInDrawerLink(courses.get(9), question139);
            session.save(link139);

// Question 140
            QuestionInDrawer question140 = new QuestionInDrawer(subjects.get(1),
                    "Which software development methodology emphasizes continuous collaboration between developers and stakeholders?",
                    "Waterfall", "Agile", "Scrum", "Spiral", 2);
            session.save(question140);
            session.flush();
            CourseQuestionInDrawerLink link140 = new CourseQuestionInDrawerLink(courses.get(9), question140);
            session.save(link140);

// Question 141
            QuestionInDrawer question141 = new QuestionInDrawer(subjects.get(1),
                    "Which type of software testing involves testing individual components or modules in isolation?",
                    "Integration testing", "System testing", "Regression testing", "Unit testing", 4);
            session.save(question141);
            session.flush();
            CourseQuestionInDrawerLink link141 = new CourseQuestionInDrawerLink(courses.get(9), question141);
            session.save(link141);

// Question 142
            QuestionInDrawer question142 = new QuestionInDrawer(subjects.get(1),
                    "What is the primary goal of software requirements engineering?",
                    "To create user documentation", "To design user interfaces", "To write efficient code", "To gather and document user needs", 4);
            session.save(question142);
            session.flush();
            CourseQuestionInDrawerLink link142 = new CourseQuestionInDrawerLink(courses.get(9), question142);
            session.save(link142);


//Physics**********
//Classical Mechanics
// Question 151
            QuestionInDrawer question151 = new QuestionInDrawer(subjects.get(2), "Which of the following is NOT a vector quantity?", "Force", "Velocity", "Temperature", "Acceleration", 3);
            session.save(question151);
            session.flush();
            CourseQuestionInDrawerLink link151 = new CourseQuestionInDrawerLink(courses.get(10), question151);
            session.save(link151);

// Question 152
            QuestionInDrawer question152 = new QuestionInDrawer(subjects.get(2), "A car travels in a straight line with a constant speed. What can you say about its acceleration?", "It is zero.", "It is decreasing.", "It is increasing.", "It is constant but not zero.", 1);
            session.save(question152);
            session.flush();
            CourseQuestionInDrawerLink link152 = new CourseQuestionInDrawerLink(courses.get(10), question152);
            session.save(link152);

// Question 153
            QuestionInDrawer question153 = new QuestionInDrawer(subjects.get(2), "A ball is thrown vertically upward. What is its acceleration at the highest point?", "9.8 m/s² downward", "0 m/s²", "9.8 m/s² upward", "It varies with the initial velocity", 0);
            session.save(question153);
            session.flush();
            CourseQuestionInDrawerLink link153 = new CourseQuestionInDrawerLink(courses.get(10), question153);
            session.save(link153);

// Question 154
            QuestionInDrawer question154 = new QuestionInDrawer(subjects.get(2), "What force keeps an object moving in a circular path?", "Frictional force", "Centripetal force", "Tension force", "Normal force", 1);
            session.save(question154);
            session.flush();
            CourseQuestionInDrawerLink link154 = new CourseQuestionInDrawerLink(courses.get(10), question154);
            session.save(link154);

// Question 155
            QuestionInDrawer question155 = new QuestionInDrawer(subjects.get(2), "The work done by a force is defined as the product of the force and:", "Displacement", "Velocity", "Time", "Acceleration", 0);
            session.save(question155);
            session.flush();
            CourseQuestionInDrawerLink link155 = new CourseQuestionInDrawerLink(courses.get(10), question155);
            session.save(link155);

// Question 156
            QuestionInDrawer question156 = new QuestionInDrawer(subjects.get(2), "Which of the following equations is used to calculate the force of gravity between two masses?", "F = ma", "F = G(m1*m2)/r^2", "F = mgh", "F = p/t", 1);
            session.save(question156);
            session.flush();
            CourseQuestionInDrawerLink link156 = new CourseQuestionInDrawerLink(courses.get(10), question156);
            session.save(link156);

// Question 157
            QuestionInDrawer question157 = new QuestionInDrawer(subjects.get(2), "An object at rest will remain at rest unless acted upon by an external force. This statement is known as:", "Newton's First Law", "Newton's Second Law", "Newton's Third Law", "Law of Gravitation", 0);
            session.save(question157);
            session.flush();
            CourseQuestionInDrawerLink link157 = new CourseQuestionInDrawerLink(courses.get(10), question157);
            session.save(link157);


//Electromagnetism*********

// Question 166
            QuestionInDrawer question166 = new QuestionInDrawer(subjects.get(2), "A charged particle experiences a force when moving through a magnetic field because of:",
                    "Gravitational attraction between charges.",
                    "Electric field induced by motion.",
                    "Inertia of the particle.",
                    "Nuclear forces between charges.",
                    2);
            session.save(question166);
            session.flush();
            CourseQuestionInDrawerLink link166 = new CourseQuestionInDrawerLink(courses.get(11), question166);
            session.save(link166);

// Question 167
            QuestionInDrawer question167 = new QuestionInDrawer(subjects.get(2), "A solenoid is used to:",
                    "Generate static electricity.",
                    "Produce heat through resistive losses.",
                    "Create a constant magnetic field.",
                    "Transform electrical energy into mechanical energy.",
                    3);
            session.save(question167);
            session.flush();
            CourseQuestionInDrawerLink link167 = new CourseQuestionInDrawerLink(courses.get(11), question167);
            session.save(link167);

// Question 168
            QuestionInDrawer question168 = new QuestionInDrawer(subjects.get(2), "What happens to the resistance of a wire when its length is doubled, while its cross-sectional area remains constant?",
                    "It becomes half.",
                    "It doubles.",
                    "It remains unchanged.",
                    "It becomes four times.",
                    2);
            session.save(question168);
            session.flush();
            CourseQuestionInDrawerLink link168 = new CourseQuestionInDrawerLink(courses.get(11), question168);
            session.save(link168);

// Question 169
            QuestionInDrawer question169 = new QuestionInDrawer(subjects.get(2), "The phenomenon of electromagnetic induction is most closely related to:",
                    "Electric potential energy.",
                    "Magnetic monopoles.",
                    "The flow of electric current.",
                    "Changing magnetic fields inducing voltage.",
                    4);
            session.save(question169);
            session.flush();
            CourseQuestionInDrawerLink link169 = new CourseQuestionInDrawerLink(courses.get(11), question169);
            session.save(link169);

// Question 170
            QuestionInDrawer question170 = new QuestionInDrawer(subjects.get(2), "A moving electron in a magnetic field will experience a force that is:",
                    "Parallel to its velocity.",
                    "Opposite to its velocity.",
                    "Perpendicular to its velocity.",
                    "Along the direction of the magnetic field.",
                    3);
            session.save(question170);
            session.flush();
            CourseQuestionInDrawerLink link170 = new CourseQuestionInDrawerLink(courses.get(11), question170);
            session.save(link170);

// Question 171
            QuestionInDrawer question171 = new QuestionInDrawer(subjects.get(2), "A transformer is primarily used to:",
                    "Convert AC to DC.",
                    "Step up or step down voltage.",
                    "Store electrical energy.",
                    "Generate magnetic fields.",
                    2);
            session.save(question171);
            session.flush();
            CourseQuestionInDrawerLink link171 = new CourseQuestionInDrawerLink(courses.get(11), question171);
            session.save(link171);

// Question 172
            QuestionInDrawer question172 = new QuestionInDrawer(subjects.get(2), "The unit of electric charge is:",
                    "Ampere.",
                    "Coulomb.",
                    "Ohm.",
                    "Watt.",
                    2);
            session.save(question172);
            session.flush();
            CourseQuestionInDrawerLink link172 = new CourseQuestionInDrawerLink(courses.get(11), question172);
            session.save(link172);


//Quantum Mechanics

// Question 181
            QuestionInDrawer question181 = new QuestionInDrawer(subjects.get(2), "What is the Heisenberg uncertainty principle?",
                    "A principle that states the position and momentum of a particle cannot be precisely measured simultaneously.",
                    "A principle that describes the behavior of classical mechanics at high energies.",
                    "A principle that defines the energy levels of electrons in an atom.",
                    "A principle that explains the wave-particle duality of light.", 1);
            session.save(question181);
            session.flush();
            CourseQuestionInDrawerLink link181 = new CourseQuestionInDrawerLink(courses.get(12), question181);
            session.save(link181);

// Question 182
            QuestionInDrawer question182 = new QuestionInDrawer(subjects.get(2), "What is a quantum state?",
                    "The classical state of a system described by its position and momentum.",
                    "A set of properties that cannot be simultaneously measured.",
                    "A complete description of a physical system using wave functions.",
                    "The point in time when a particle's position is most accurately known.", 3);
            session.save(question182);
            session.flush();
            CourseQuestionInDrawerLink link182 = new CourseQuestionInDrawerLink(courses.get(12), question182);
            session.save(link182);

// Question 183
            QuestionInDrawer question183 = new QuestionInDrawer(subjects.get(2), "Which of the following particles is a boson?",
                    "Proton",
                    "Electron",
                    "Neutron",
                    "Photon", 4);
            session.save(question183);
            session.flush();
            CourseQuestionInDrawerLink link183 = new CourseQuestionInDrawerLink(courses.get(12), question183);
            session.save(link183);

// Question 184
            QuestionInDrawer question184 = new QuestionInDrawer(subjects.get(2), "What is the Pauli exclusion principle?",
                    "Particles with half-integer spins cannot exist.",
                    "No two electrons in an atom can have the same set of quantum numbers.",
                    "Electrons always occupy the lowest energy levels before higher ones.",
                    "The energy of a system remains constant unless acted upon by an external force.", 2);
            session.save(question184);
            session.flush();
            CourseQuestionInDrawerLink link184 = new CourseQuestionInDrawerLink(courses.get(12), question184);
            session.save(link184);

// Question 185
            QuestionInDrawer question185 = new QuestionInDrawer(subjects.get(2), "What is tunneling in quantum mechanics?",
                    "The process of a particle moving through a potential barrier without having enough energy.",
                    "The phenomenon where particles are confined to a small region of space.",
                    "The scattering of light by small particles.",
                    "The emission of electrons from a metal's surface due to light exposure.", 1);
            session.save(question185);
            session.flush();
            CourseQuestionInDrawerLink link185 = new CourseQuestionInDrawerLink(courses.get(12), question185);
            session.save(link185);

// Question 186
            QuestionInDrawer question186 = new QuestionInDrawer(subjects.get(2), "What is a quantum harmonic oscillator?",
                    "A particle with a fixed energy level.",
                    "A system that obeys classical mechanics.",
                    "A particle confined to move along a straight line.",
                    "A system that exhibits quantized energy levels and wave-like behavior.", 4);
            session.save(question186);
            session.flush();
            CourseQuestionInDrawerLink link186 = new CourseQuestionInDrawerLink(courses.get(12), question186);
            session.save(link186);

// Question 187
            QuestionInDrawer question187 = new QuestionInDrawer(subjects.get(2), "What does the Schrödinger equation describe?",
                    "The motion of classical particles in a vacuum.",
                    "The behavior of fluids in motion.",
                    "The behavior of particles at extremely high temperatures.",
                    "The evolution of quantum states over time.", 4);
            session.save(question187);
            session.flush();
            CourseQuestionInDrawerLink link187 = new CourseQuestionInDrawerLink(courses.get(12), question187);
            session.save(link187);


//Thermodynamics
// Question 196
            QuestionInDrawer question196 = new QuestionInDrawer(subjects.get(2), "Which law of thermodynamics states that energy cannot be created or destroyed, only transferred or converted from one form to another?", "Zeroth Law", "First Law", "Second Law", "Third Law", 1);
            session.save(question196);
            session.flush();
            CourseQuestionInDrawerLink link196 = new CourseQuestionInDrawerLink(courses.get(13), question196);
            session.save(link196);

// Question 197
            QuestionInDrawer question197 = new QuestionInDrawer(subjects.get(2), "The efficiency of a heat engine is given by:", "1 - (Qh / Qc)", "1 - (Qc / Qh)", "(Qh / Qc) - 1", "(Qc / Qh) - 1", 0);
            session.save(question197);
            session.flush();
            CourseQuestionInDrawerLink link197 = new CourseQuestionInDrawerLink(courses.get(13), question197);
            session.save(link197);

// Question 198
            QuestionInDrawer question198 = new QuestionInDrawer(subjects.get(2), "What is the relationship between pressure and volume of a given mass of gas at constant temperature?", "Pressure is directly proportional to volume.", "Pressure is inversely proportional to volume.", "Pressure and volume are not related.", "Pressure and volume vary randomly.", 1);
            session.save(question198);
            session.flush();
            CourseQuestionInDrawerLink link198 = new CourseQuestionInDrawerLink(courses.get(13), question198);
            session.save(link198);

// Question 199
            QuestionInDrawer question199 = new QuestionInDrawer(subjects.get(2), "The process of a gas changing from a gaseous state to a solid state is called:", "Sublimation", "Deposition", "Condensation", "Evaporation", 1);
            session.save(question199);
            session.flush();
            CourseQuestionInDrawerLink link199 = new CourseQuestionInDrawerLink(courses.get(13), question199);
            session.save(link199);

// Question 200
            QuestionInDrawer question200 = new QuestionInDrawer(subjects.get(2), "Which law of thermodynamics states that heat naturally flows from a region of higher temperature to a region of lower temperature?", "Zeroth Law", "First Law", "Second Law", "Third Law", 2);
            session.save(question200);
            session.flush();
            CourseQuestionInDrawerLink link200 = new CourseQuestionInDrawerLink(courses.get(13), question200);
            session.save(link200);

// Question 201
            QuestionInDrawer question201 = new QuestionInDrawer(subjects.get(2), "The heat energy required to raise the temperature of one gram of a substance by one degree Celsius is called:", "Heat capacity", "Specific heat", "Latent heat", "Internal energy", 1);
            session.save(question201);
            session.flush();
            CourseQuestionInDrawerLink link201 = new CourseQuestionInDrawerLink(courses.get(13), question201);
            session.save(link201);

// Question 202
            QuestionInDrawer question202 = new QuestionInDrawer(subjects.get(2), "Which of the following processes is a constant volume process?", "Isothermal process", "Isobaric process", "Adiabatic process", "Isochoric process", 3);
            session.save(question202);
            session.flush();
            CourseQuestionInDrawerLink link202 = new CourseQuestionInDrawerLink(courses.get(13), question202);
            session.save(link202);


//

// Question 211
            QuestionInDrawer question211 = new QuestionInDrawer(subjects.get(2), "What is the ultimate fate of a star with a mass several times that of the Sun?", "Black hole formation", "Neutron star formation", "White dwarf formation", "Planetary nebula formation", 0);
            session.save(question211);
            session.flush();
            CourseQuestionInDrawerLink link211 = new CourseQuestionInDrawerLink(courses.get(14), question211);
            session.save(link211);

// Question 212
            QuestionInDrawer question212 = new QuestionInDrawer(subjects.get(2), "Which astronomical phenomenon occurs when a massive star collapses under its own gravity?", "Supernova", "Pulsar", "Nebula", "Red giant", 0);
            session.save(question212);
            session.flush();
            CourseQuestionInDrawerLink link212 = new CourseQuestionInDrawerLink(courses.get(14), question212);
            session.save(link212);

// Question 213
            QuestionInDrawer question213 = new QuestionInDrawer(subjects.get(2), "What is the primary source of energy for main-sequence stars like the Sun?", "Fusion of hydrogen into helium", "Fusion of helium into heavier elements", "Nuclear fission", "Gravitational collapse", 0);
            session.save(question213);
            session.flush();
            CourseQuestionInDrawerLink link213 = new CourseQuestionInDrawerLink(courses.get(14), question213);
            session.save(link213);

// Question 214
            QuestionInDrawer question214 = new QuestionInDrawer(subjects.get(2), "Which type of galaxy has a distinct spiral shape with a central bulge and spiral arms?", "Elliptical galaxy", "Irregular galaxy", "Lenticular galaxy", "Spiral galaxy", 3);
            session.save(question214);
            session.flush();
            CourseQuestionInDrawerLink link214 = new CourseQuestionInDrawerLink(courses.get(14), question214);
            session.save(link214);

// Question 215
            QuestionInDrawer question215 = new QuestionInDrawer(subjects.get(2), "What is the name for a compact object with extremely high density, formed from the remnants of a massive star's core collapse?", "Planet", "White dwarf", "Neutron star", "Supernova", 2);
            session.save(question215);
            session.flush();
            CourseQuestionInDrawerLink link215 = new CourseQuestionInDrawerLink(courses.get(14), question215);
            session.save(link215);

// Question 216
            QuestionInDrawer question216 = new QuestionInDrawer(subjects.get(2), "Which phenomenon occurs when a massive star's core collapses, causing an explosion that briefly outshines an entire galaxy?", "Nebula formation", "Pulsar emission", "Black hole creation", "Supernova explosion", 3);
            session.save(question216);
            session.flush();
            CourseQuestionInDrawerLink link216 = new CourseQuestionInDrawerLink(courses.get(14), question216);
            session.save(link216);

// Question 217
            QuestionInDrawer question217 = new QuestionInDrawer(subjects.get(2), "What is the remnant left behind after a supernova explosion of a massive star?", "Black hole", "White dwarf", "Red giant", "Protostar", 0);
            session.save(question217);
            session.flush();
            CourseQuestionInDrawerLink link217 = new CourseQuestionInDrawerLink(courses.get(14), question217);
            session.save(link217);


//Biology
//Introduction to Biology
// Question 226
            QuestionInDrawer question226 = new QuestionInDrawer(subjects.get(3), "Which of the following is the basic unit of life?", "Cell", "Atom", "Protein", "Chromosome", 0);
            session.save(question226);
            session.flush();
            CourseQuestionInDrawerLink link226 = new CourseQuestionInDrawerLink(courses.get(15), question226);
            session.save(link226);

// Question 227
            QuestionInDrawer question227 = new QuestionInDrawer(subjects.get(3), "Which process allows plants to convert light energy into chemical energy?", "Photosynthesis", "Cellular respiration", "Fermentation", "Glycolysis", 0);
            session.save(question227);
            session.flush();
            CourseQuestionInDrawerLink link227 = new CourseQuestionInDrawerLink(courses.get(15), question227);
            session.save(link227);

// Question 228
            QuestionInDrawer question228 = new QuestionInDrawer(subjects.get(3), "What is the function of DNA?", "To store and transmit genetic information", "To provide energy to cells", "To aid in protein synthesis", "To regulate cell division", 0);
            session.save(question228);
            session.flush();
            CourseQuestionInDrawerLink link228 = new CourseQuestionInDrawerLink(courses.get(15), question228);
            session.save(link228);

// Question 229
            QuestionInDrawer question229 = new QuestionInDrawer(subjects.get(3), "Which organelle is responsible for protein synthesis?", "Golgi apparatus", "Mitochondrion", "Endoplasmic reticulum", "Ribosome", 3);
            session.save(question229);
            session.flush();
            CourseQuestionInDrawerLink link229 = new CourseQuestionInDrawerLink(courses.get(15), question229);
            session.save(link229);

// Question 230
            QuestionInDrawer question230 = new QuestionInDrawer(subjects.get(3), "What is the name of the process by which cells break down glucose to produce energy?", "Photosynthesis", "Cellular respiration", "Fermentation", "Glycolysis", 1);
            session.save(question230);
            session.flush();
            CourseQuestionInDrawerLink link230 = new CourseQuestionInDrawerLink(courses.get(15), question230);
            session.save(link230);

// Question 231
            QuestionInDrawer question231 = new QuestionInDrawer(subjects.get(3), "Which of the following is a monosaccharide?", "Sucrose", "Lactose", "Glucose", "Starch", 2);
            session.save(question231);
            session.flush();
            CourseQuestionInDrawerLink link231 = new CourseQuestionInDrawerLink(courses.get(15), question231);
            session.save(link231);

// Question 232
            QuestionInDrawer question232 = new QuestionInDrawer(subjects.get(3), "What is the function of mitochondria in a cell?", "Protein synthesis", "Cell division", "Energy production", "Lipid storage", 2);
            session.save(question232);
            session.flush();
            CourseQuestionInDrawerLink link232 = new CourseQuestionInDrawerLink(courses.get(15), question232);
            session.save(link232);


//Genetics
// Question 241
            QuestionInDrawer question241 = new QuestionInDrawer(subjects.get(3), "What is the function of tRNA in protein synthesis?",
                    "Transcribing DNA to RNA",
                    "Carrying amino acids to the ribosome",
                    "Initiating translation",
                    "Creating peptide bonds", 2);
            session.save(question241);
            session.flush();
            CourseQuestionInDrawerLink link241 = new CourseQuestionInDrawerLink(courses.get(16), question241);
            session.save(link241);

// Question 242
            QuestionInDrawer question242 = new QuestionInDrawer(subjects.get(3), "Which genetic disorder is caused by a trisomy of chromosome 21?",
                    "Cystic fibrosis",
                    "Hemophilia",
                    "Down syndrome",
                    "Muscular dystrophy", 3);
            session.save(question242);
            session.flush();
            CourseQuestionInDrawerLink link242 = new CourseQuestionInDrawerLink(courses.get(16), question242);
            session.save(link242);

// Question 243
            QuestionInDrawer question243 = new QuestionInDrawer(subjects.get(3), "What is the purpose of the polymerase chain reaction (PCR) in molecular biology?",
                    "To sequence DNA",
                    "To create recombinant DNA",
                    "To amplify DNA segments",
                    "To transcribe RNA", 3);
            session.save(question243);
            session.flush();
            CourseQuestionInDrawerLink link243 = new CourseQuestionInDrawerLink(courses.get(16), question243);
            session.save(link243);

// Question 244
            QuestionInDrawer question244 = new QuestionInDrawer(subjects.get(3), "In genetics, what is an operon?",
                    "A type of DNA repair mechanism",
                    "A region of non-coding DNA",
                    "A gene that codes for a ribosomal RNA",
                    "A group of genes regulated together with a single promoter", 4);
            session.save(question244);
            session.flush();
            CourseQuestionInDrawerLink link244 = new CourseQuestionInDrawerLink(courses.get(16), question244);
            session.save(link244);

// Question 245
            QuestionInDrawer question245 = new QuestionInDrawer(subjects.get(3), "What is epigenetics?",
                    "The study of extinct species",
                    "The study of ecological relationships",
                    "The study of genetic disorders",
                    "The study of heritable changes in gene expression without altering the DNA sequence", 4);
            session.save(question245);
            session.flush();
            CourseQuestionInDrawerLink link245 = new CourseQuestionInDrawerLink(courses.get(16), question245);
            session.save(link245);

// Question 246
            QuestionInDrawer question246 = new QuestionInDrawer(subjects.get(3), "Which genetic disorder is characterized by a lack of pigment in the skin, hair, and eyes?",
                    "Cystic fibrosis",
                    "Huntington's disease",
                    "Albinism",
                    "Sickle cell anemia", 3);
            session.save(question246);
            session.flush();
            CourseQuestionInDrawerLink link246 = new CourseQuestionInDrawerLink(courses.get(16), question246);
            session.save(link246);

// Question 247
            QuestionInDrawer question247 = new QuestionInDrawer(subjects.get(3), "What is a frameshift mutation?",
                    "A mutation that changes a single nucleotide",
                    "A mutation that results in the addition or deletion of nucleotides, causing a shift in the reading frame",
                    "A mutation that occurs in non-coding regions of DNA",
                    "A mutation that affects the shape of a protein", 2);
            session.save(question247);
            session.flush();
            CourseQuestionInDrawerLink link247 = new CourseQuestionInDrawerLink(courses.get(16), question247);
            session.save(link247);


//Cell Biology
// Question 256
            QuestionInDrawer question256 = new QuestionInDrawer(subjects.get(3), "What is the function of the endoplasmic reticulum?",
                    "Cellular respiration",
                    "Protein synthesis and lipid metabolism",
                    "Photosynthesis",
                    "Cellular communication", 2);
            session.save(question256);
            session.flush();
            CourseQuestionInDrawerLink link256 = new CourseQuestionInDrawerLink(courses.get(17), question256);
            session.save(link256);

// Question 257
            QuestionInDrawer question257 = new QuestionInDrawer(subjects.get(3), "Which organelle is responsible for generating ATP through oxidative phosphorylation?",
                    "Golgi apparatus",
                    "Nucleus",
                    "Lysosome",
                    "Mitochondrion", 4);
            session.save(question257);
            session.flush();
            CourseQuestionInDrawerLink link257 = new CourseQuestionInDrawerLink(courses.get(17), question257);
            session.save(link257);

// Question 258
            QuestionInDrawer question258 = new QuestionInDrawer(subjects.get(3), "What is the function of the Golgi apparatus?",
                    "Synthesizing ribosomal RNA",
                    "Synthesizing lipids",
                    "Modifying and packaging proteins",
                    "Generating ATP", 3);
            session.save(question258);
            session.flush();
            CourseQuestionInDrawerLink link258 = new CourseQuestionInDrawerLink(courses.get(17), question258);
            session.save(link258);

// Question 259
            QuestionInDrawer question259 = new QuestionInDrawer(subjects.get(3), "Which cellular structure is responsible for protein synthesis?",
                    "Chloroplast",
                    "Lysosome",
                    "Nucleolus",
                    "Ribosome", 4);
            session.save(question259);
            session.flush();
            CourseQuestionInDrawerLink link259 = new CourseQuestionInDrawerLink(courses.get(17), question259);
            session.save(link259);

// Question 260
            QuestionInDrawer question260 = new QuestionInDrawer(subjects.get(3), "What is the function of the cell membrane?",
                    "Synthesizing DNA",
                    "Transporting ions",
                    "Producing ATP",
                    "Regulating the movement of substances in and out of the cell", 4);
            session.save(question260);
            session.flush();
            CourseQuestionInDrawerLink link260 = new CourseQuestionInDrawerLink(courses.get(17), question260);
            session.save(link260);

// Question 261
            QuestionInDrawer question261 = new QuestionInDrawer(subjects.get(3), "What is the primary function of lysosomes?",
                    "Photosynthesis",
                    "Cellular respiration",
                    "Breaking down cellular waste and debris",
                    "Protein synthesis", 3);
            session.save(question261);
            session.flush();
            CourseQuestionInDrawerLink link261 = new CourseQuestionInDrawerLink(courses.get(17), question261);
            session.save(link261);

// Question 262
            QuestionInDrawer question262 = new QuestionInDrawer(subjects.get(3), "Which process involves the movement of water across a selectively permeable membrane?",
                    "Active transport",
                    "Diffusion",
                    "Endocytosis",
                    "Osmosis", 4);
            session.save(question262);
            session.flush();
            CourseQuestionInDrawerLink link262 = new CourseQuestionInDrawerLink(courses.get(17), question262);
            session.save(link262);


//Ecology
// Question 271
            QuestionInDrawer question271 = new QuestionInDrawer(subjects.get(3), "What term describes the maximum number of individuals an environment can support sustainably?", "Carrying capacity", "Biodiversity", "Habitat", "Population density", 0);
            session.save(question271);
            session.flush();
            CourseQuestionInDrawerLink link271 = new CourseQuestionInDrawerLink(courses.get(18), question271);
            session.save(link271);

// Question 272
            QuestionInDrawer question272 = new QuestionInDrawer(subjects.get(3), "Which ecological relationship involves one organism benefiting and the other being harmed?", "Commensalism", "Mutualism", "Predation", "Parasitism", 2);
            session.save(question272);
            session.flush();
            CourseQuestionInDrawerLink link272 = new CourseQuestionInDrawerLink(courses.get(18), question272);
            session.save(link272);

// Question 273
            QuestionInDrawer question273 = new QuestionInDrawer(subjects.get(3), "Which term refers to the process by which plants use sunlight to convert carbon dioxide and water into glucose and oxygen?", "Respiration", "Decomposition", "Photosynthesis", "Eutrophication", 2);
            session.save(question273);
            session.flush();
            CourseQuestionInDrawerLink link273 = new CourseQuestionInDrawerLink(courses.get(18), question273);
            session.save(link273);

// Question 274
            QuestionInDrawer question274 = new QuestionInDrawer(subjects.get(3), "Which of the following is NOT a primary consumer in a food chain?", "Herbivore", "Carnivore", "Omnivore", "Producer", 3);
            session.save(question274);
            session.flush();
            CourseQuestionInDrawerLink link274 = new CourseQuestionInDrawerLink(courses.get(18), question274);
            session.save(link274);

// Question 275
            QuestionInDrawer question275 = new QuestionInDrawer(subjects.get(3), "What is the role of decomposers in an ecosystem?", "They produce oxygen through photosynthesis.", "They break down dead organisms and recycle nutrients.", "They directly consume primary consumers.", "They are the top predators in food chains.", 1);
            session.save(question275);
            session.flush();
            CourseQuestionInDrawerLink link275 = new CourseQuestionInDrawerLink(courses.get(18), question275);
            session.save(link275);

// Question 276
            QuestionInDrawer question276 = new QuestionInDrawer(subjects.get(3), "Which term refers to the gradual and predictable changes in the composition of a community over time?", "Adaptation", "Succession", "Disruption", "Invasion", 1);
            session.save(question276);
            session.flush();
            CourseQuestionInDrawerLink link276 = new CourseQuestionInDrawerLink(courses.get(18), question276);
            session.save(link276);

// Question 277
            QuestionInDrawer question277 = new QuestionInDrawer(subjects.get(3), "What term describes the variety and variability of life in a particular habitat or ecosystem?", "Ecotone", "Population density", "Biomass", "Biodiversity", 3);
            session.save(question277);
            session.flush();
            CourseQuestionInDrawerLink link277 = new CourseQuestionInDrawerLink(courses.get(18), question277);
            session.save(link277);

//Molecular Biology
// Question 286
            QuestionInDrawer question286 = new QuestionInDrawer(subjects.get(3), "What molecule serves as the template for protein synthesis during translation?", "tRNA", "mRNA", "rRNA", "DNA", 1);
            session.save(question286);
            session.flush();
            CourseQuestionInDrawerLink link286 = new CourseQuestionInDrawerLink(courses.get(19), question286);
            session.save(link286);

// Question 287
            QuestionInDrawer question287 = new QuestionInDrawer(subjects.get(3), "Which of the following enzymes is responsible for unwinding DNA during replication?", "RNA polymerase", "DNA ligase", "Helicase", "Topoisomerase", 2);
            session.save(question287);
            session.flush();
            CourseQuestionInDrawerLink link287 = new CourseQuestionInDrawerLink(courses.get(19), question287);
            session.save(link287);

// Question 288
            QuestionInDrawer question288 = new QuestionInDrawer(subjects.get(3), "What is the process by which RNA is synthesized from a DNA template?", "Translation", "Replication", "Transcription", "Reverse transcription", 2);
            session.save(question288);
            session.flush();
            CourseQuestionInDrawerLink link288 = new CourseQuestionInDrawerLink(courses.get(19), question288);
            session.save(link288);

// Question 289
            QuestionInDrawer question289 = new QuestionInDrawer(subjects.get(3), "What is the function of transfer RNA (tRNA) in protein synthesis?", "Carries amino acids to the ribosome", "Codes for the amino acid sequence", "Forms peptide bonds", "Unwinds the DNA double helix", 0);
            session.save(question289);
            session.flush();
            CourseQuestionInDrawerLink link289 = new CourseQuestionInDrawerLink(courses.get(19), question289);
            session.save(link289);

// Question 290
            QuestionInDrawer question290 = new QuestionInDrawer(subjects.get(3), "Which cellular organelle is responsible for protein synthesis?", "Golgi apparatus", "Lysosome", "Mitochondrion", "Ribosome", 3);
            session.save(question290);
            session.flush();
            CourseQuestionInDrawerLink link290 = new CourseQuestionInDrawerLink(courses.get(19), question290);
            session.save(link290);

// Question 291
            QuestionInDrawer question291 = new QuestionInDrawer(subjects.get(3), "What is the name of the process by which DNA segments are cut and spliced together in genetic engineering?", "Translocation", "Replication", "Transformation", "Recombination", 3);
            session.save(question291);
            session.flush();
            CourseQuestionInDrawerLink link291 = new CourseQuestionInDrawerLink(courses.get(19), question291);
            session.save(link291);

// Question 292
            QuestionInDrawer question292 = new QuestionInDrawer(subjects.get(3), "Which enzyme is responsible for adding new nucleotides to a growing DNA strand during replication?", "DNA polymerase", "RNA polymerase", "Helicase", "Ligase", 0);
            session.save(question292);
            session.flush();
            CourseQuestionInDrawerLink link292 = new CourseQuestionInDrawerLink(courses.get(19), question292);
            session.save(link292);


//Psychology*************
//Introduction to Psychology

// Question 301
            QuestionInDrawer question301 = new QuestionInDrawer(subjects.get(4), "Which part of the brain is responsible for regulating basic survival functions like heart rate and breathing?",
                    "Hippocampus", "Prefrontal cortex", "Medulla oblongata", "Occipital lobe", 3);
            session.save(question301);
            session.flush();
            CourseQuestionInDrawerLink link301 = new CourseQuestionInDrawerLink(courses.get(20), question301);
            session.save(link301);

// Question 302
            QuestionInDrawer question302 = new QuestionInDrawer(subjects.get(4), "What psychological perspective emphasizes unconscious processes and early childhood experiences?",
                    "Behaviorism", "Cognitive psychology", "Humanism", "Psychoanalysis", 4);
            session.save(question302);
            session.flush();
            CourseQuestionInDrawerLink link302 = new CourseQuestionInDrawerLink(courses.get(20), question302);
            session.save(link302);

// Question 303
            QuestionInDrawer question303 = new QuestionInDrawer(subjects.get(4), "What term refers to a sudden, often temporary, alteration in one's level of consciousness?",
                    "Hallucination", "Delusion", "Euphoria", "Altered state of consciousness", 4);
            session.save(question303);
            session.flush();
            CourseQuestionInDrawerLink link303 = new CourseQuestionInDrawerLink(courses.get(20), question303);
            session.save(link303);

// Question 304
            QuestionInDrawer question304 = new QuestionInDrawer(subjects.get(4), "Which psychologist is known for his classical conditioning experiments with dogs?",
                    "B.F. Skinner", "Ivan Pavlov", "Jean Piaget", "Sigmund Freud", 2);
            session.save(question304);
            session.flush();
            CourseQuestionInDrawerLink link304 = new CourseQuestionInDrawerLink(courses.get(20), question304);
            session.save(link304);

// Question 305
            QuestionInDrawer question305 = new QuestionInDrawer(subjects.get(4), "The ability to understand and manage your own emotions is known as:",
                    "Emotional intelligence", "Social intelligence", "Cognitive dissonance", "Behavioral flexibility", 1);
            session.save(question305);
            session.flush();
            CourseQuestionInDrawerLink link305 = new CourseQuestionInDrawerLink(courses.get(20), question305);
            session.save(link305);

// Question 306
            QuestionInDrawer question306 = new QuestionInDrawer(subjects.get(4), "In operant conditioning, what term refers to the process of weakening a behavior by removing or withholding reinforcement?",
                    "Positive reinforcement", "Negative reinforcement", "Punishment", "Extinction", 4);
            session.save(question306);
            session.flush();
            CourseQuestionInDrawerLink link306 = new CourseQuestionInDrawerLink(courses.get(20), question306);
            session.save(link306);

// Question 307
            QuestionInDrawer question307 = new QuestionInDrawer(subjects.get(4), "Which stage of sleep is characterized by rapid eye movement (REM) and vivid dreams?",
                    "NREM-1", "NREM-3", "REM sleep", "Alpha sleep", 3);
            session.save(question307);
            session.flush();
            CourseQuestionInDrawerLink link307 = new CourseQuestionInDrawerLink(courses.get(20), question307);
            session.save(link307);


//Cognitive Psychology
// Question 316
            QuestionInDrawer question316 = new QuestionInDrawer(subjects.get(4),
                    "What is the process by which sensory information is organized and interpreted by the brain?",
                    "Encoding", "Attention", "Perception", "Storage", 3);
            session.save(question316);
            session.flush();
            CourseQuestionInDrawerLink link316 = new CourseQuestionInDrawerLink(courses.get(21), question316);
            session.save(link316);

// Question 317
            QuestionInDrawer question317 = new QuestionInDrawer(subjects.get(4),
                    "Which term refers to the tendency to perceive familiar objects as unchanging despite changes in sensory input?",
                    "Sensory adaptation", "Perceptual constancy", "Depth perception", "Sensory transduction", 2);
            session.save(question317);
            session.flush();
            CourseQuestionInDrawerLink link317 = new CourseQuestionInDrawerLink(courses.get(21), question317);
            session.save(link317);

// Question 318
            QuestionInDrawer question318 = new QuestionInDrawer(subjects.get(4),
                    "What is the term for the mental representation of objects, events, or scenes?",
                    "Sensation", "Perception", "Attention", "Consciousness", 2);
            session.save(question318);
            session.flush();
            CourseQuestionInDrawerLink link318 = new CourseQuestionInDrawerLink(courses.get(21), question318);
            session.save(link318);

// Question 319
            QuestionInDrawer question319 = new QuestionInDrawer(subjects.get(4),
                    "Which theory suggests that the brain compares incoming sensory information to stored representations of known objects?",
                    "Gestalt theory", "Feature integration theory", "Recognition-by-components theory", "Prototype theory", 3);
            session.save(question319);
            session.flush();
            CourseQuestionInDrawerLink link319 = new CourseQuestionInDrawerLink(courses.get(21), question319);
            session.save(link319);

// Question 320
            QuestionInDrawer question320 = new QuestionInDrawer(subjects.get(4),
                    "What term refers to our ability to focus on only a subset of all available sensory information?",
                    "Attention", "Perception", "Sensory adaptation", "Consciousness", 1);
            session.save(question320);
            session.flush();
            CourseQuestionInDrawerLink link320 = new CourseQuestionInDrawerLink(courses.get(21), question320);
            session.save(link320);

// Question 321
            QuestionInDrawer question321 = new QuestionInDrawer(subjects.get(4),
                    "The process of transforming sensory input into meaningful perceptions is known as:",
                    "Attention", "Encoding", "Sensory adaptation", "Perception", 4);
            session.save(question321);
            session.flush();
            CourseQuestionInDrawerLink link321 = new CourseQuestionInDrawerLink(courses.get(21), question321);
            session.save(link321);

// Question 322
            QuestionInDrawer question322 = new QuestionInDrawer(subjects.get(4),
                    "Which term describes the process by which our brain fills in gaps in sensory information to create a complete perception?",
                    "Bottom-up processing", "Top-down processing", "Perceptual constancy", "Gestalt processing", 2);
            session.save(question322);
            session.flush();
            CourseQuestionInDrawerLink link322 = new CourseQuestionInDrawerLink(courses.get(21), question322);
            session.save(link322);


//Social Psychology
// Question 331
            QuestionInDrawer question331 = new QuestionInDrawer(subjects.get(4),
                    "Which term refers to the tendency to attribute our own positive actions to internal factors and negative actions to external factors?",
                    "Fundamental attribution error", "Self-serving bias", "Actor-observer bias", "Cognitive dissonance", 2);
            session.save(question331);
            session.flush();
            CourseQuestionInDrawerLink link331 = new CourseQuestionInDrawerLink(courses.get(22), question331);
            session.save(link331);

// Question 332
            QuestionInDrawer question332 = new QuestionInDrawer(subjects.get(4),
                    "In social psychology, the tendency to conform to the behavior and opinions of others in a group is known as:",
                    "Groupthink", "Social facilitation", "Conformity", "Obedience", 3);
            session.save(question332);
            session.flush();
            CourseQuestionInDrawerLink link332 = new CourseQuestionInDrawerLink(courses.get(22), question332);
            session.save(link332);

// Question 333
            QuestionInDrawer question333 = new QuestionInDrawer(subjects.get(4),
                    "The process of adjusting one's behavior or thinking to coincide with a group standard is referred to as:",
                    "Compliance", "Social influence", "Normative social influence", "Informational social influence", 2);
            session.save(question333);
            session.flush();
            CourseQuestionInDrawerLink link333 = new CourseQuestionInDrawerLink(courses.get(22), question333);
            session.save(link333);

// Question 334
            QuestionInDrawer question334 = new QuestionInDrawer(subjects.get(4),
                    "The idea that people are more likely to comply with a request if it is followed by a smaller, related request is known as the:",
                    "Foot-in-the-door technique", "Door-in-the-face technique", "Low-ball technique", "Central route persuasion", 1);
            session.save(question334);
            session.flush();
            CourseQuestionInDrawerLink link334 = new CourseQuestionInDrawerLink(courses.get(22), question334);
            session.save(link334);

// Question 335
            QuestionInDrawer question335 = new QuestionInDrawer(subjects.get(4),
                    "The phenomenon where people work less effectively in a group compared to when working individually is called:",
                    "Social loafing", "Group polarization", "Deindividuation", "Social facilitation", 1);
            session.save(question335);
            session.flush();
            CourseQuestionInDrawerLink link335 = new CourseQuestionInDrawerLink(courses.get(22), question335);
            session.save(link335);

// Question 336
            QuestionInDrawer question336 = new QuestionInDrawer(subjects.get(4),
                    "Which term describes the tendency for individuals to exert less effort when working collectively than when working individually?",
                    "Groupthink", "Deindividuation", "Social loafing", "Normative social influence", 3);
            session.save(question336);
            session.flush();
            CourseQuestionInDrawerLink link336 = new CourseQuestionInDrawerLink(courses.get(22), question336);
            session.save(link336);

// Question 337
            QuestionInDrawer question337 = new QuestionInDrawer(subjects.get(4),
                    "The cognitive dissonance theory suggests that individuals experience discomfort when:",
                    "Their behavior aligns with their attitudes", "Their behavior is inconsistent with their attitudes", "They conform to group norms", "They experience social facilitation", 2);
            session.save(question337);
            session.flush();
            CourseQuestionInDrawerLink link337 = new CourseQuestionInDrawerLink(courses.get(22), question337);
            session.save(link337);


//Abnormal Psychology
// Question 346
            QuestionInDrawer question346 = new QuestionInDrawer(subjects.get(4), "Which of the following is a characteristic of generalized anxiety disorder?",
                    "A) Intense fear of specific objects or situations.",
                    "B) Recurrent panic attacks.",
                    "C) Excessive worry and apprehension about various aspects of life.",
                    "D) Sudden dissociative experiences.", 3);
            session.save(question346);
            session.flush();
            CourseQuestionInDrawerLink link346 = new CourseQuestionInDrawerLink(courses.get(23), question346);
            session.save(link346);

// Question 347
            QuestionInDrawer question347 = new QuestionInDrawer(subjects.get(4), "What is the primary symptom of dissociative identity disorder (DID)?",
                    "A) Intrusive thoughts and urges.",
                    "B) Sudden loss of memory and identity.",
                    "C) Excessive fear of social situations.",
                    "D) Hallucinations and delusions.", 2);
            session.save(question347);
            session.flush();
            CourseQuestionInDrawerLink link347 = new CourseQuestionInDrawerLink(courses.get(23), question347);
            session.save(link347);

// Question 348
            QuestionInDrawer question348 = new QuestionInDrawer(subjects.get(4), "Which neurotransmitter is often associated with mood disorders like depression?",
                    "A) Serotonin.",
                    "B) Dopamine.",
                    "C) Acetylcholine.",
                    "D) GABA.", 1);
            session.save(question348);
            session.flush();
            CourseQuestionInDrawerLink link348 = new CourseQuestionInDrawerLink(courses.get(23), question348);
            session.save(link348);

// Question 349
            QuestionInDrawer question349 = new QuestionInDrawer(subjects.get(4), "What is a potential symptom of schizophrenia?",
                    "A) Hypervigilance and exaggerated startle response.",
                    "B) Stable and consistent interpersonal relationships.",
                    "C) Enhanced ability to perceive and understand emotions in others.",
                    "D) Auditory hallucinations.", 4);
            session.save(question349);
            session.flush();
            CourseQuestionInDrawerLink link349 = new CourseQuestionInDrawerLink(courses.get(23), question349);
            session.save(link349);

// Question 350
            QuestionInDrawer question350 = new QuestionInDrawer(subjects.get(4), "What is the primary goal of exposure therapy?",
                    "A) Uncovering repressed memories.",
                    "B) Altering maladaptive thought patterns.",
                    "C) Providing a safe space for self-expression.",
                    "D) Reducing fear and anxiety through gradual exposure to feared stimuli.", 4);
            session.save(question350);
            session.flush();
            CourseQuestionInDrawerLink link350 = new CourseQuestionInDrawerLink(courses.get(23), question350);
            session.save(link350);

// Question 351
            QuestionInDrawer question351 = new QuestionInDrawer(subjects.get(4), "Which disorder involves alternating between extreme periods of elevated mood and depressive episodes?",
                    "A) Generalized anxiety disorder.",
                    "B) Bipolar disorder.",
                    "C) Schizoaffective disorder.",
                    "D) Obsessive-compulsive disorder.", 2);
            session.save(question351);
            session.flush();
            CourseQuestionInDrawerLink link351 = new CourseQuestionInDrawerLink(courses.get(23), question351);
            session.save(link351);

// Question 352
            QuestionInDrawer question352 = new QuestionInDrawer(subjects.get(4), "What is a symptom of antisocial personality disorder?",
                    "A) Excessive fear of social situations.",
                    "B) Instability in self-image and relationships.",
                    "C) Lack of remorse for hurting others.",
                    "D) Frequent panic attacks.", 3);
            session.save(question352);
            session.flush();
            CourseQuestionInDrawerLink link352 = new CourseQuestionInDrawerLink(courses.get(23), question352);
            session.save(link352);


//Developmental Psychology
            QuestionInDrawer question361 = new QuestionInDrawer(subjects.get(4), "Which theorist is known for the stages of psychosocial development?",
                    "Erik Erikson", "Jean Piaget", "Sigmund Freud", "Albert Bandura", 1);
            session.save(question361);
            session.flush();
            CourseQuestionInDrawerLink link361 = new CourseQuestionInDrawerLink(courses.get(24), question361);
            session.save(link361);

            QuestionInDrawer question362 = new QuestionInDrawer(subjects.get(4), "What is the term for a child's inability to see the world from another person's perspective?",
                    "Egocentrism", "Centration", "Reversibility", "Conservation", 1);
            session.save(question362);
            session.flush();
            CourseQuestionInDrawerLink link362 = new CourseQuestionInDrawerLink(courses.get(24), question362);
            session.save(link362);

            QuestionInDrawer question363 = new QuestionInDrawer(subjects.get(4), "Which attachment style is characterized by low anxiety and low avoidance?",
                    "Secure", "Anxious-Preoccupied", "Dismissive-Avoidant", "Fearful-Avoidant", 1);
            session.save(question363);
            session.flush();
            CourseQuestionInDrawerLink link363 = new CourseQuestionInDrawerLink(courses.get(24), question363);
            session.save(link363);

            QuestionInDrawer question364 = new QuestionInDrawer(subjects.get(4), "At what age does the sensorimotor stage occur according to Piaget's theory?",
                    "Birth to 2 years", "2 to 7 years", "7 to 11 years", "11 to adulthood", 1);
            session.save(question364);
            session.flush();
            CourseQuestionInDrawerLink link364 = new CourseQuestionInDrawerLink(courses.get(24), question364);
            session.save(link364);

            QuestionInDrawer question365 = new QuestionInDrawer(subjects.get(4), "Which cognitive development theory emphasizes the role of culture in development?",
                    "Sociocultural theory", "Psychoanalytic theory", "Behavioral theory", "Humanistic theory", 1);
            session.save(question365);
            session.flush();
            CourseQuestionInDrawerLink link365 = new CourseQuestionInDrawerLink(courses.get(24), question365);
            session.save(link365);

            QuestionInDrawer question366 = new QuestionInDrawer(subjects.get(4), "According to Erikson, what is the main developmental task of adolescence?",
                    "Identity vs. Role Confusion", "Trust vs. Mistrust", "Generativity vs. Stagnation", "Integrity vs. Despair", 1);
            session.save(question366);
            session.flush();
            CourseQuestionInDrawerLink link366 = new CourseQuestionInDrawerLink(courses.get(24), question366);
            session.save(link366);

            QuestionInDrawer question367 = new QuestionInDrawer(subjects.get(4), "What is the term for a child's ability to understand that objects continue to exist even when not seen?",
                    "Object permanence", "Centration", "Conservation", "Egocentrism", 1);
            session.save(question367);
            session.flush();
            CourseQuestionInDrawerLink link367 = new CourseQuestionInDrawerLink(courses.get(24), question367);
            session.save(link367);


//History
//world history
// Question 376
            QuestionInDrawer question376 = new QuestionInDrawer(subjects.get(5),
                    "Which ruler is known for establishing the Maurya Empire in ancient India?",
                    "Ashoka the Great", "Cyrus the Great", "Sultan Suleiman", "Queen Hatshepsut", 1);
            session.save(question376);
            session.flush();
            CourseQuestionInDrawerLink link376 = new CourseQuestionInDrawerLink(courses.get(25), question376);
            session.save(link376);

// Question 377
            QuestionInDrawer question377 = new QuestionInDrawer(subjects.get(5),
                    "The Boxer Rebellion in the late 19th century targeted foreign influences in which country?",
                    "Japan", "India", "China", "Vietnam", 3);
            session.save(question377);
            session.flush();
            CourseQuestionInDrawerLink link377 = new CourseQuestionInDrawerLink(courses.get(25), question377);
            session.save(link377);

// Question 378
            QuestionInDrawer question378 = new QuestionInDrawer(subjects.get(5),
                    "Which explorer is credited with circumnavigating the globe?",
                    "Marco Polo", "Christopher Columbus", "Ferdinand Magellan", "Vasco da Gama", 3);
            session.save(question378);
            session.flush();
            CourseQuestionInDrawerLink link378 = new CourseQuestionInDrawerLink(courses.get(25), question378);
            session.save(link378);

// Question 379
            QuestionInDrawer question379 = new QuestionInDrawer(subjects.get(5),
                    "The Opium Wars were fought primarily between China and which European country?",
                    "Britain", "France", "Spain", "Portugal", 1);
            session.save(question379);
            session.flush();
            CourseQuestionInDrawerLink link379 = new CourseQuestionInDrawerLink(courses.get(25), question379);
            session.save(link379);

// Question 380
            QuestionInDrawer question380 = new QuestionInDrawer(subjects.get(5),
                    "Who wrote the famous political treatise 'The Prince'?",
                    "Thomas Hobbes", "John Locke", "Niccolò Machiavelli", "Voltaire", 3);
            session.save(question380);
            session.flush();
            CourseQuestionInDrawerLink link380 = new CourseQuestionInDrawerLink(courses.get(25), question380);
            session.save(link380);

// Question 381
            QuestionInDrawer question381 = new QuestionInDrawer(subjects.get(5),
                    "Which ancient civilization is credited with inventing a system of writing known as hieroglyphics?",
                    "Greece", "Mesopotamia", "China", "Egypt", 4);
            session.save(question381);
            session.flush();
            CourseQuestionInDrawerLink link381 = new CourseQuestionInDrawerLink(courses.get(25), question381);
            session.save(link381);

// Question 382
            QuestionInDrawer question382 = new QuestionInDrawer(subjects.get(5),
                    "Who was the leader of the Bolshevik Party during the Russian Revolution of 1917?",
                    "Vladimir Lenin", "Joseph Stalin", "Leon Trotsky", "Mikhail Gorbachev", 1);
            session.save(question382);
            session.flush();
            CourseQuestionInDrawerLink link382 = new CourseQuestionInDrawerLink(courses.get(25), question382);
            session.save(link382);


//U.S. History
// Question 391
            QuestionInDrawer question391 = new QuestionInDrawer(subjects.get(5), "Which event directly led to the outbreak of the American Revolutionary War?",
                    "A) Boston Tea Party", "B) Stamp Act Congress", "C) Proclamation of 1763", "D) French and Indian War", 1);
            session.save(question391);
            session.flush();
            CourseQuestionInDrawerLink link391 = new CourseQuestionInDrawerLink(courses.get(26), question391);
            session.save(link391);

// Question 392
            QuestionInDrawer question392 = new QuestionInDrawer(subjects.get(5), "What was the main purpose of the Emancipation Proclamation issued by Abraham Lincoln?",
                    "A) To end the Civil War", "B) To grant equal rights to all citizens", "C) To abolish slavery in Confederate states", "D) To promote westward expansion", 3);
            session.save(question392);
            session.flush();
            CourseQuestionInDrawerLink link392 = new CourseQuestionInDrawerLink(courses.get(26), question392);
            session.save(link392);

// Question 393
            QuestionInDrawer question393 = new QuestionInDrawer(subjects.get(5), "Which U.S. president is associated with the New Deal policies during the Great Depression?",
                    "A) Franklin D. Roosevelt", "B) Herbert Hoover", "C) Calvin Coolidge", "D) Woodrow Wilson", 1);
            session.save(question393);
            session.flush();
            CourseQuestionInDrawerLink link393 = new CourseQuestionInDrawerLink(courses.get(26), question393);
            session.save(link393);

// Question 394
            QuestionInDrawer question394 = new QuestionInDrawer(subjects.get(5), "The case of Brown v. Board of Education of Topeka (1954) is significant because it led to:",
                    "A) Desegregation of schools", "B) Prohibition of child labor", "C) Women's suffrage", "D) Native American citizenship", 1);
            session.save(question394);
            session.flush();
            CourseQuestionInDrawerLink link394 = new CourseQuestionInDrawerLink(courses.get(26), question394);
            session.save(link394);

// Question 395
            QuestionInDrawer question395 = new QuestionInDrawer(subjects.get(5), "Which of the following was a major factor contributing to the Dust Bowl during the 1930s?",
                    "A) Overgrazing of livestock", "B) Excessive rainfall", "C) Volcanic eruptions", "D) Dam construction", 1);
            session.save(question395);
            session.flush();
            CourseQuestionInDrawerLink link395 = new CourseQuestionInDrawerLink(courses.get(26), question395);
            session.save(link395);

// Question 396
            QuestionInDrawer question396 = new QuestionInDrawer(subjects.get(5), "The Monroe Doctrine of 1823 primarily aimed to:",
                    "A) Establish trade relations with European powers", "B) Annex Texas", "C) Expand westward", "D) Limit European colonization in the Americas", 4);
            session.save(question396);
            session.flush();
            CourseQuestionInDrawerLink link396 = new CourseQuestionInDrawerLink(courses.get(26), question396);
            session.save(link396);

// Question 397
            QuestionInDrawer question397 = new QuestionInDrawer(subjects.get(5), "Which U.S. Supreme Court case upheld the legality of racial segregation 'separate but equal' doctrine?",
                    "A) Plessy v. Ferguson", "B) Brown v. Board of Education", "C) Roe v. Wade", "D) Marbury v. Madison", 1);
            session.save(question397);
            session.flush();
            CourseQuestionInDrawerLink link397 = new CourseQuestionInDrawerLink(courses.get(26), question397);
            session.save(link397);

// Question 398

//European History
// Question 406
            QuestionInDrawer question406 = new QuestionInDrawer(subjects.get(5), "Which event marked the beginning of the French Revolution?", "Industrial Revolution", "American Revolution", "Glorious Revolution", "Storming of the Bastille", 4);
            session.save(question406);
            session.flush();
            CourseQuestionInDrawerLink link406 = new CourseQuestionInDrawerLink(courses.get(27), question406);
            session.save(link406);

// Question 407
            QuestionInDrawer question407 = new QuestionInDrawer(subjects.get(5), "What was the main goal of the Congress of Vienna?", "To establish colonial empires", "To redraw national borders", "To spread Enlightenment ideas", "To abolish monarchies", 2);
            session.save(question407);
            session.flush();
            CourseQuestionInDrawerLink link407 = new CourseQuestionInDrawerLink(courses.get(27), question407);
            session.save(link407);

// Question 408
            QuestionInDrawer question408 = new QuestionInDrawer(subjects.get(5), "Which leader is known for the unification of Italy?", "Louis XIV", "Bismarck", "Napoleon Bonaparte", "Giuseppe Garibaldi", 4);
            session.save(question408);
            session.flush();
            CourseQuestionInDrawerLink link408 = new CourseQuestionInDrawerLink(courses.get(27), question408);
            session.save(link408);

// Question 409
            QuestionInDrawer question409 = new QuestionInDrawer(subjects.get(5), "What was the significance of the Treaty of Versailles after World War I?", "It established the League of Nations", "It granted Germany significant territorial gains", "It imposed heavy reparations on France", "It led to the rise of the Ottoman Empire", 1);
            session.save(question409);
            session.flush();
            CourseQuestionInDrawerLink link409 = new CourseQuestionInDrawerLink(courses.get(27), question409);
            session.save(link409);

// Question 410
            QuestionInDrawer question410 = new QuestionInDrawer(subjects.get(5), "Which country was ruled by Tsar Nicholas II before the Russian Revolution?", "France", "Germany", "Austria-Hungary", "Russia", 4);
            session.save(question410);
            session.flush();
            CourseQuestionInDrawerLink link410 = new CourseQuestionInDrawerLink(courses.get(27), question410);
            session.save(link410);

// Question 411
            QuestionInDrawer question411 = new QuestionInDrawer(subjects.get(5), "The Renaissance originated in which Italian city?", "Venice", "Milan", "Rome", "Florence", 4);
            session.save(question411);
            session.flush();
            CourseQuestionInDrawerLink link411 = new CourseQuestionInDrawerLink(courses.get(27), question411);
            session.save(link411);

// Question 412
            QuestionInDrawer question412 = new QuestionInDrawer(subjects.get(5), "Who wrote 'The Prince', a political treatise on governing power?", "William Shakespeare", "Niccolò Machiavelli", "Leonardo da Vinci", "Galileo Galilei", 2);
            session.save(question412);
            session.flush();
            CourseQuestionInDrawerLink link412 = new CourseQuestionInDrawerLink(courses.get(27), question412);
            session.save(link412);


//Ancient Civilizations
// Question 421
            QuestionInDrawer question421 = new QuestionInDrawer(subjects.get(5), "Which ancient civilization built the city of Machu Picchu?", "Egyptians", "Greeks", "Incas", "Mayans", 3);
            session.save(question421);
            session.flush();
            CourseQuestionInDrawerLink link421 = new CourseQuestionInDrawerLink(courses.get(28), question421);
            session.save(link421);

// Question 422
            QuestionInDrawer question422 = new QuestionInDrawer(subjects.get(5), "The Code of Hammurabi is associated with which ancient civilization?", "Sumerians", "Persians", "Phoenicians", "Babylonians", 4);
            session.save(question422);
            session.flush();
            CourseQuestionInDrawerLink link422 = new CourseQuestionInDrawerLink(courses.get(28), question422);
            session.save(link422);

// Question 423
            QuestionInDrawer question423 = new QuestionInDrawer(subjects.get(5), "Which ancient civilization built the Great Pyramids of Giza?", "Romans", "Greeks", "Egyptians", "Mesopotamians", 3);
            session.save(question423);
            session.flush();
            CourseQuestionInDrawerLink link423 = new CourseQuestionInDrawerLink(courses.get(28), question423);
            session.save(link423);

// Question 424
            QuestionInDrawer question424 = new QuestionInDrawer(subjects.get(5), "The Indus Valley Civilization is known for developing a system of writing known as what?", "Cuneiform", "Hieroglyphics", "Sanskrit", "Indus Script", 4);
            session.save(question424);
            session.flush();
            CourseQuestionInDrawerLink link424 = new CourseQuestionInDrawerLink(courses.get(28), question424);
            session.save(link424);

// Question 425
            QuestionInDrawer question425 = new QuestionInDrawer(subjects.get(5), "Which ancient civilization is credited with creating the first known alphabet?", "Phoenicians", "Greeks", "Mayans", "Persians", 1);
            session.save(question425);
            session.flush();
            CourseQuestionInDrawerLink link425 = new CourseQuestionInDrawerLink(courses.get(28), question425);
            session.save(link425);

// Question 426
            QuestionInDrawer question426 = new QuestionInDrawer(subjects.get(5), "Which river was crucial to the development of ancient Egyptian civilization?", "Nile", "Tigris", "Euphrates", "Indus", 1);
            session.save(question426);
            session.flush();
            CourseQuestionInDrawerLink link426 = new CourseQuestionInDrawerLink(courses.get(28), question426);
            session.save(link426);

// Question 427
            QuestionInDrawer question427 = new QuestionInDrawer(subjects.get(5), "The Trojan War is a legendary conflict in ancient Greek mythology, described in the works of which poet?", "Homer", "Sappho", "Euripides", "Aeschylus", 1);
            session.save(question427);
            session.flush();
            CourseQuestionInDrawerLink link427 = new CourseQuestionInDrawerLink(courses.get(28), question427);
            session.save(link427);


//Modern History
// Question 436
            QuestionInDrawer question436 = new QuestionInDrawer(subjects.get(5),
                    "Who was the first female Prime Minister of the United Kingdom?",
                    "Margaret Thatcher", "Angela Merkel", "Jacinda Ardern", "Indira Gandhi", 1);
            session.save(question436);
            session.flush();
            CourseQuestionInDrawerLink link436 = new CourseQuestionInDrawerLink(courses.get(29), question436);
            session.save(link436);

// Question 437
            QuestionInDrawer question437 = new QuestionInDrawer(subjects.get(5),
                    "The Bolshevik Revolution of 1917 led to the establishment of which country?",
                    "Germany", "Italy", "Soviet Union", "United States", 3);
            session.save(question437);
            session.flush();
            CourseQuestionInDrawerLink link437 = new CourseQuestionInDrawerLink(courses.get(29), question437);
            session.save(link437);

// Question 438
            QuestionInDrawer question438 = new QuestionInDrawer(subjects.get(5),
                    "The Berlin Wall fell in which year, marking a symbolic end to the Cold War?",
                    "1989", "1991", "1985", "1995", 1);
            session.save(question438);
            session.flush();
            CourseQuestionInDrawerLink link438 = new CourseQuestionInDrawerLink(courses.get(29), question438);
            session.save(link438);

// Question 439
            QuestionInDrawer question439 = new QuestionInDrawer(subjects.get(5),
                    "Which leader is associated with the Civil Rights Movement and gave the 'I've Been to the Mountaintop' speech?",
                    "Malcolm X", "Nelson Mandela", "Martin Luther King Jr.", "Mahatma Gandhi", 3);
            session.save(question439);
            session.flush();
            CourseQuestionInDrawerLink link439 = new CourseQuestionInDrawerLink(courses.get(29), question439);
            session.save(link439);

// Question 440
            QuestionInDrawer question440 = new QuestionInDrawer(subjects.get(5),
                    "The Treaty of Versailles, signed after World War I, imposed heavy reparations on which country?",
                    "United Kingdom", "Germany", "France", "Italy", 2);
            session.save(question440);
            session.flush();
            CourseQuestionInDrawerLink link440 = new CourseQuestionInDrawerLink(courses.get(29), question440);
            session.save(link440);

// Question 441
            QuestionInDrawer question441 = new QuestionInDrawer(subjects.get(5),
                    "Who was the leader of the Soviet Union during the Cuban Missile Crisis?",
                    "Vladimir Putin", "Mikhail Gorbachev", "Leon Trotsky", "Nikita Khrushchev", 4);
            session.save(question441);
            session.flush();
            CourseQuestionInDrawerLink link441 = new CourseQuestionInDrawerLink(courses.get(29), question441);
            session.save(link441);

// Question 442
            QuestionInDrawer question442 = new QuestionInDrawer(subjects.get(5),
                    "The Holocaust primarily targeted which group of people?",
                    "Political Leaders", "Children", "Jews", "Soldiers", 3);
            session.save(question442);
            session.flush();
            CourseQuestionInDrawerLink link442 = new CourseQuestionInDrawerLink(courses.get(29), question442);
            session.save(link442);


///Literature*****************
//Introduction to Literature

// Question 451
            QuestionInDrawer question451 = new QuestionInDrawer(subjects.get(6), "Which novel is often referred to as the 'Great American Novel'?", "To Kill a Mockingbird", "The Grapes of Wrath", "Moby-Dick", "The Catcher in the Rye", 2);
            session.save(question451);
            session.flush();
            CourseQuestionInDrawerLink link451 = new CourseQuestionInDrawerLink(courses.get(30), question451);
            session.save(link451);

// Question 452
            QuestionInDrawer question452 = new QuestionInDrawer(subjects.get(6), "Who wrote the play 'Romeo and Juliet'?", "William Shakespeare", "George Orwell", "F. Scott Fitzgerald", "Jane Austen", 1);
            session.save(question452);
            session.flush();
            CourseQuestionInDrawerLink link452 = new CourseQuestionInDrawerLink(courses.get(30), question452);
            session.save(link452);

// Question 453
            QuestionInDrawer question453 = new QuestionInDrawer(subjects.get(6), "In '1984', what is the ruling party's slogan?", "War is Peace", "Freedom is Slavery", "Ignorance is Wisdom", "Love is Hate", 1);
            session.save(question453);
            session.flush();
            CourseQuestionInDrawerLink link453 = new CourseQuestionInDrawerLink(courses.get(30), question453);
            session.save(link453);

// Question 454
            QuestionInDrawer question454 = new QuestionInDrawer(subjects.get(6), "Which author wrote the novel 'Pride and Prejudice'?", "Charlotte Brontë", "Emily Dickinson", "Jane Austen", "Louisa May Alcott", 3);
            session.save(question454);
            session.flush();
            CourseQuestionInDrawerLink link454 = new CourseQuestionInDrawerLink(courses.get(30), question454);
            session.save(link454);

// Question 455
            QuestionInDrawer question455 = new QuestionInDrawer(subjects.get(6), "What is the famous opening line of 'Moby-Dick'?", "Call me Ishmael.", "It was a bright cold day in April...", "In my younger and more vulnerable years...", "All happy families are alike...", 1);
            session.save(question455);
            session.flush();
            CourseQuestionInDrawerLink link455 = new CourseQuestionInDrawerLink(courses.get(30), question455);
            session.save(link455);

// Question 456
            QuestionInDrawer question456 = new QuestionInDrawer(subjects.get(6), "Which of these plays was written by Tennessee Williams?", "Death of a Salesman", "The Crucible", "A Streetcar Named Desire", "The Glass Menagerie", 3);
            session.save(question456);
            session.flush();
            CourseQuestionInDrawerLink link456 = new CourseQuestionInDrawerLink(courses.get(30), question456);
            session.save(link456);

// Question 457
            QuestionInDrawer question457 = new QuestionInDrawer(subjects.get(6), "What is the name of the fictional land described in 'The Lord of the Rings'?", "Narnia", "Hogwarts", "Middle-earth", "Westeros", 3);
            session.save(question457);
            session.flush();
            CourseQuestionInDrawerLink link457 = new CourseQuestionInDrawerLink(courses.get(30), question457);
            session.save(link457);


//Shakespearean Plays
// Question 466
            QuestionInDrawer question466 = new QuestionInDrawer(subjects.get(6), "In which Shakespearean play would you find the character Iago?",
                    "A) Hamlet", "B) Macbeth", "C) Othello", "D) King Lear", 3);
            session.save(question466);
            session.flush();
            CourseQuestionInDrawerLink link466 = new CourseQuestionInDrawerLink(courses.get(31), question466);
            session.save(link466);

// Question 467
            QuestionInDrawer question467 = new QuestionInDrawer(subjects.get(6), "Which Shakespearean play features the famous line 'To be or not to be'?",
                    "A) Romeo and Juliet", "B) Julius Caesar", "C) Othello", "D) Hamlet", 4);
            session.save(question467);
            session.flush();
            CourseQuestionInDrawerLink link467 = new CourseQuestionInDrawerLink(courses.get(31), question467);
            session.save(link467);

// Question 468
            QuestionInDrawer question468 = new QuestionInDrawer(subjects.get(6), "The character Lady Macbeth is known for her ambition and manipulation in which play?",
                    "A) The Tempest", "B) Much Ado About Nothing", "C) Macbeth", "D) A Midsummer Night's Dream", 3);
            session.save(question468);
            session.flush();
            CourseQuestionInDrawerLink link468 = new CourseQuestionInDrawerLink(courses.get(31), question468);
            session.save(link468);

// Question 469
            QuestionInDrawer question469 = new QuestionInDrawer(subjects.get(6), "Which Shakespearean play is set on an enchanted island and features the character Prospero?",
                    "A) Hamlet", "B) Twelfth Night", "C) The Tempest", "D) Antony and Cleopatra", 3);
            session.save(question469);
            session.flush();
            CourseQuestionInDrawerLink link469 = new CourseQuestionInDrawerLink(courses.get(31), question469);
            session.save(link469);

// Question 470
            QuestionInDrawer question470 = new QuestionInDrawer(subjects.get(6), "What is the name of the fairy king in 'A Midsummer Night's Dream'?",
                    "A) Oberon", "B) Puck", "C) Titania", "D) Ariel", 1);
            session.save(question470);
            session.flush();
            CourseQuestionInDrawerLink link470 = new CourseQuestionInDrawerLink(courses.get(31), question470);
            session.save(link470);

// Question 471
            QuestionInDrawer question471 = new QuestionInDrawer(subjects.get(6), "Which play includes the characters Rosalind, Orlando, and Jacques?",
                    "A) The Merchant of Venice", "B) As You Like It", "C) King Lear", "D) Richard III", 2);
            session.save(question471);
            session.flush();
            CourseQuestionInDrawerLink link471 = new CourseQuestionInDrawerLink(courses.get(31), question471);
            session.save(link471);

// Question 472
            QuestionInDrawer question472 = new QuestionInDrawer(subjects.get(6), "The tragic hero Brutus is a central character in which Shakespearean play?",
                    "A) Macbeth", "B) Othello", "C) Julius Caesar", "D) Hamlet", 3);
            session.save(question472);
            session.flush();
            CourseQuestionInDrawerLink link472 = new CourseQuestionInDrawerLink(courses.get(31), question472);
            session.save(link472);

//American Literature
// Question 481
            QuestionInDrawer question481 = new QuestionInDrawer(subjects.get(6), "Which American author wrote the novel 'Moby-Dick'?",
                    "A) Nathaniel Hawthorne", "B) Herman Melville", "C) Mark Twain", "D) Edgar Allan Poe", 2);
            session.save(question481);
            session.flush();
            CourseQuestionInDrawerLink link481 = new CourseQuestionInDrawerLink(courses.get(32), question481);
            session.save(link481);

// Question 482
            QuestionInDrawer question482 = new QuestionInDrawer(subjects.get(6), "The short story 'The Tell-Tale Heart' is attributed to which writer?",
                    "A) Ralph Waldo Emerson", "B) Henry James", "C) Edgar Allan Poe", "D) Washington Irving", 3);
            session.save(question482);
            session.flush();
            CourseQuestionInDrawerLink link482 = new CourseQuestionInDrawerLink(courses.get(32), question482);
            session.save(link482);

// Question 483
            QuestionInDrawer question483 = new QuestionInDrawer(subjects.get(6), "Which novel, often considered a masterpiece of American literature, tells the story of Hester Prynne and her daughter Pearl?",
                    "A) 'The Scarlet Letter'", "B) 'Adventures of Huckleberry Finn'", "C) 'Moby-Dick'", "D) 'The Great Gatsby'", 1);
            session.save(question483);
            session.flush();
            CourseQuestionInDrawerLink link483 = new CourseQuestionInDrawerLink(courses.get(32), question483);
            session.save(link483);

// Question 484
            QuestionInDrawer question484 = new QuestionInDrawer(subjects.get(6), "The fictional character Jay Gatsby is central to which novel?",
                    "A) 'The Catcher in the Rye'", "B) 'The Grapes of Wrath'", "C) 'The Great Gatsby'", "D) 'To Kill a Mockingbird'", 3);
            session.save(question484);
            session.flush();
            CourseQuestionInDrawerLink link484 = new CourseQuestionInDrawerLink(courses.get(32), question484);
            session.save(link484);

// Question 485
            QuestionInDrawer question485 = new QuestionInDrawer(subjects.get(6), "Which American playwright is known for works like 'A Streetcar Named Desire' and 'Cat on a Hot Tin Roof'?",
                    "A) Tennessee Williams", "B) Arthur Miller", "C) Eugene O'Neill", "D) Lorraine Hansberry", 1);
            session.save(question485);
            session.flush();
            CourseQuestionInDrawerLink link485 = new CourseQuestionInDrawerLink(courses.get(32), question485);
            session.save(link485);

// Question 486
            QuestionInDrawer question486 = new QuestionInDrawer(subjects.get(6), "The novel 'To Kill a Mockingbird' addresses themes of racial injustice and moral growth in which Southern U.S. state?",
                    "A) Georgia", "B) Alabama", "C) Mississippi", "D) Louisiana", 2);
            session.save(question486);
            session.flush();
            CourseQuestionInDrawerLink link486 = new CourseQuestionInDrawerLink(courses.get(32), question486);
            session.save(link486);

// Question 487
            QuestionInDrawer question487 = new QuestionInDrawer(subjects.get(6), "Which American author is known for his transcendentalist essays, including 'Self-Reliance' and 'Nature'?",
                    "A) Ralph Waldo Emerson", "B) Edgar Allan Poe", "C) Nathaniel Hawthorne", "D) Mark Twain", 1);
            session.save(question487);
            session.flush();
            CourseQuestionInDrawerLink link487 = new CourseQuestionInDrawerLink(courses.get(32), question487);
            session.save(link487);

//Contemporary Fiction
// Question 496
            QuestionInDrawer question496 = new QuestionInDrawer(subjects.get(6), "Which postmodern novel is known for its unconventional narrative structure and multiple perspectives, exploring the themes of time and identity?", "Infinite Jest", "Gravity's Rainbow", "White Noise", "House of Leaves", 4);
            session.save(question496);
            session.flush();
            CourseQuestionInDrawerLink link496 = new CourseQuestionInDrawerLink(courses.get(33), question496);
            session.save(link496);

// Question 497
            QuestionInDrawer question497 = new QuestionInDrawer(subjects.get(6), "In Salman Rushdie's novel 'Midnight's Children', the protagonist Saleem Sinai is born with a unique ability. What is it?", "Telekinesis", "Time Travel", "Mind Reading", "Telepathy", 2);
            session.save(question497);
            session.flush();
            CourseQuestionInDrawerLink link497 = new CourseQuestionInDrawerLink(courses.get(33), question497);
            session.save(link497);

// Question 498
            QuestionInDrawer question498 = new QuestionInDrawer(subjects.get(6), "Which author wrote the novel 'Blindness', depicting a society struck by a mysterious epidemic of sudden blindness?", "José Saramago", "Haruki Murakami", "Gabriel García Márquez", "Julian Barnes", 1);
            session.save(question498);
            session.flush();
            CourseQuestionInDrawerLink link498 = new CourseQuestionInDrawerLink(courses.get(33), question498);
            session.save(link498);

// Question 499
            QuestionInDrawer question499 = new QuestionInDrawer(subjects.get(6), "In Kazuo Ishiguro's novel 'Never Let Me Go', what is the central premise?", "A futuristic war", "A love triangle", "A boarding school mystery", "A zombie apocalypse", 3);
            session.save(question499);
            session.flush();
            CourseQuestionInDrawerLink link499 = new CourseQuestionInDrawerLink(courses.get(33), question499);
            session.save(link499);

// Question 500
            QuestionInDrawer question500 = new QuestionInDrawer(subjects.get(6), "Which novel by David Foster Wallace delves into the lives of a diverse group of characters, exploring themes of addiction, entertainment, and the search for meaning?", "The Corrections", "Freedom", "A Visit from the Goon Squad", "Infinite Jest", 4);
            session.save(question500);
            session.flush();
            CourseQuestionInDrawerLink link500 = new CourseQuestionInDrawerLink(courses.get(33), question500);
            session.save(link500);

// Question 501
            QuestionInDrawer question501 = new QuestionInDrawer(subjects.get(6), "Who is the author of the novel 'The Brief Wondrous Life of Oscar Wao', which weaves together the experiences of an overweight Dominican boy and his family?", "Junot Díaz", "Jhumpa Lahiri", "Chimamanda Ngozi Adichie", "Zadie Smith", 1);
            session.save(question501);
            session.flush();
            CourseQuestionInDrawerLink link501 = new CourseQuestionInDrawerLink(courses.get(33), question501);
            session.save(link501);

// Question 502
            QuestionInDrawer question502 = new QuestionInDrawer(subjects.get(6), "Which novel by Zadie Smith explores the lives of two girls who grow up in a multicultural London?", "White Teeth", "NW", "Swing Time", "On Beauty", 1);
            session.save(question502);
            session.flush();
            CourseQuestionInDrawerLink link502 = new CourseQuestionInDrawerLink(courses.get(33), question502);
            session.save(link502);


//Poetry Analysis
// Question 511
            QuestionInDrawer question511 = new QuestionInDrawer(subjects.get(6), "In Emily Dickinson's poem 'Because I could not stop for Death', what is the central metaphor?", "A journey", "A dream", "A battle", "A celebration", 1);
            session.save(question511);
            session.flush();
            CourseQuestionInDrawerLink link511 = new CourseQuestionInDrawerLink(courses.get(34), question511);
            session.save(link511);

// Question 512
            QuestionInDrawer question512 = new QuestionInDrawer(subjects.get(6), "Which poem by Robert Frost contains the famous line 'Two roads diverged in a wood, and I—'", "Stopping by Woods on a Snowy Evening", "The Road Not Taken", "Fire and Ice", "Birches", 2);
            session.save(question512);
            session.flush();
            CourseQuestionInDrawerLink link512 = new CourseQuestionInDrawerLink(courses.get(34), question512);
            session.save(link512);

// Question 513
            QuestionInDrawer question513 = new QuestionInDrawer(subjects.get(6), "What literary device is frequently used in Langston Hughes' poem 'The Weary Blues'?", "Alliteration", "Metaphor", "Anaphora", "Hyperbole", 1);
            session.save(question513);
            session.flush();
            CourseQuestionInDrawerLink link513 = new CourseQuestionInDrawerLink(courses.get(34), question513);
            session.save(link513);

// Question 514
            QuestionInDrawer question514 = new QuestionInDrawer(subjects.get(6), "In Sylvia Plath's poem 'Daddy', what is the significance of the speaker's relationship with her father?", "Joy and fulfillment", "Fear and resentment", "Indifference and apathy", "Friendship and camaraderie", 2);
            session.save(question514);
            session.flush();
            CourseQuestionInDrawerLink link514 = new CourseQuestionInDrawerLink(courses.get(34), question514);
            session.save(link514);

// Question 515
            QuestionInDrawer question515 = new QuestionInDrawer(subjects.get(6), "Which poetic device involves the repetition of consonant sounds at the beginning of words?", "Assonance", "Onomatopoeia", "Consonance", "Alliteration", 4);
            session.save(question515);
            session.flush();
            CourseQuestionInDrawerLink link515 = new CourseQuestionInDrawerLink(courses.get(34), question515);
            session.save(link515);

// Question 516
            QuestionInDrawer question516 = new QuestionInDrawer(subjects.get(6), "What poem by William Wordsworth begins with the lines 'I wandered lonely as a cloud'?", "Lines Composed a Few Miles Above Tintern Abbey", "Ode to a Nightingale", "To Autumn", "The Rime of the Ancient Mariner", 1);
            session.save(question516);
            session.flush();
            CourseQuestionInDrawerLink link516 = new CourseQuestionInDrawerLink(courses.get(34), question516);
            session.save(link516);

// Question 517
            QuestionInDrawer question517 = new QuestionInDrawer(subjects.get(6), "In T.S. Eliot's poem 'The Love Song of J. Alfred Prufrock', what is the main theme explored?", "Nature's beauty", "Youthful exuberance", "Isolation and insecurity", "Political activism", 3);
            session.save(question517);
            session.flush();
            CourseQuestionInDrawerLink link517 = new CourseQuestionInDrawerLink(courses.get(34), question517);
            session.save(link517);


//Economics***********************
//Microeconomics
// Question 526
            QuestionInDrawer question526 = new QuestionInDrawer(subjects.get(7), "Which concept in economics refers to the situation where one entity can't be made better off without making another entity worse off?", "Pareto efficiency", "Marginal utility", "Elasticity", "Opportunity cost", 1);
            session.save(question526);
            session.flush();
            CourseQuestionInDrawerLink link526 = new CourseQuestionInDrawerLink(courses.get(35), question526);
            session.save(link526);

// Question 527
            QuestionInDrawer question527 = new QuestionInDrawer(subjects.get(7), "What type of market structure features a large number of firms, differentiated products, and easy entry and exit?", "Monopoly", "Oligopoly", "Monopolistic competition", "Perfect competition", 3);
            session.save(question527);
            session.flush();
            CourseQuestionInDrawerLink link527 = new CourseQuestionInDrawerLink(courses.get(35), question527);
            session.save(link527);

// Question 528
            QuestionInDrawer question528 = new QuestionInDrawer(subjects.get(7), "If the cross-price elasticity of two goods is positive, what can be inferred about their relationship?", "They are substitutes", "They are complements", "There is no relationship", "They are inferior goods", 1);
            session.save(question528);
            session.flush();
            CourseQuestionInDrawerLink link528 = new CourseQuestionInDrawerLink(courses.get(35), question528);
            session.save(link528);

// Question 529
            QuestionInDrawer question529 = new QuestionInDrawer(subjects.get(7), "Which economic theory suggests that a government should increase its money supply to encourage spending during economic downturns?", "Supply-side economics", "Keynesian economics", "Monetarism", "Austrian economics", 2);
            session.save(question529);
            session.flush();
            CourseQuestionInDrawerLink link529 = new CourseQuestionInDrawerLink(courses.get(35), question529);
            session.save(link529);

// Question 530
            QuestionInDrawer question530 = new QuestionInDrawer(subjects.get(7), "In microeconomics, what term refers to the change in total cost resulting from producing one more unit of a good?", "Marginal cost", "Average cost", "Fixed cost", "Variable cost", 1);
            session.save(question530);
            session.flush();
            CourseQuestionInDrawerLink link530 = new CourseQuestionInDrawerLink(courses.get(35), question530);
            session.save(link530);

// Question 531
            QuestionInDrawer question531 = new QuestionInDrawer(subjects.get(7), "What market failure occurs when a single firm can supply a good to an entire market at a lower cost than two or more firms?", "Monopoly power", "Externality", "Asymmetric information", "Natural monopoly", 4);
            session.save(question531);
            session.flush();
            CourseQuestionInDrawerLink link531 = new CourseQuestionInDrawerLink(courses.get(35), question531);
            session.save(link531);

// Question 532
            QuestionInDrawer question532 = new QuestionInDrawer(subjects.get(7), "Which of the following is an example of a regressive tax?", "Progressive income tax", "Sales tax", "Property tax", "Corporate income tax", 2);
            session.save(question532);
            session.flush();
            CourseQuestionInDrawerLink link532 = new CourseQuestionInDrawerLink(courses.get(35), question532);
            session.save(link532);

//Macroeconomics
// Question 541
            QuestionInDrawer question541 = new QuestionInDrawer(subjects.get(7), "Which of the following is NOT considered a component of GDP?",
                    "Government spending", "Exports", "Personal savings", "Business investments", 3);
            session.save(question541);
            session.flush();
            CourseQuestionInDrawerLink link541 = new CourseQuestionInDrawerLink(courses.get(36), question541);
            session.save(link541);

// Question 542
            QuestionInDrawer question542 = new QuestionInDrawer(subjects.get(7), "What does the term 'inflation' refer to in economics?",
                    "Decrease in overall price levels", "Stable price levels", "Increase in overall price levels", "Zero economic growth", 2);
            session.save(question542);
            session.flush();
            CourseQuestionInDrawerLink link542 = new CourseQuestionInDrawerLink(courses.get(36), question542);
            session.save(link542);

// Question 543
            QuestionInDrawer question543 = new QuestionInDrawer(subjects.get(7), "Which of the following is a measure of the overall economic performance of a country?",
                    "Unemployment rate", "Individual income", "Household savings", "Consumer preferences", 0);
            session.save(question543);
            session.flush();
            CourseQuestionInDrawerLink link543 = new CourseQuestionInDrawerLink(courses.get(36), question543);
            session.save(link543);

// Question 544
            QuestionInDrawer question544 = new QuestionInDrawer(subjects.get(7), "What is the primary tool used by central banks to control the money supply?",
                    "Fiscal policy", "Monetary policy", "Trade policy", "Supply-side policy", 1);
            session.save(question544);
            session.flush();
            CourseQuestionInDrawerLink link544 = new CourseQuestionInDrawerLink(courses.get(36), question544);
            session.save(link544);

// Question 545
            QuestionInDrawer question545 = new QuestionInDrawer(subjects.get(7), "What is the term for the total value of all goods and services produced within a country in a given period?",
                    "Gross National Product (GNP)", "Gross Domestic Product (GDP)", "Net National Product (NNP)", "Net Domestic Product (NDP)", 1);
            session.save(question545);
            session.flush();
            CourseQuestionInDrawerLink link545 = new CourseQuestionInDrawerLink(courses.get(36), question545);
            session.save(link545);

// Question 546
            QuestionInDrawer question546 = new QuestionInDrawer(subjects.get(7), "Which of the following is a characteristic of a recession?",
                    "Rising GDP and increasing employment", "Falling GDP and decreasing employment", "Stable GDP and constant employment", "High inflation and low unemployment", 1);
            session.save(question546);
            session.flush();
            CourseQuestionInDrawerLink link546 = new CourseQuestionInDrawerLink(courses.get(36), question546);
            session.save(link546);

// Question 547
            QuestionInDrawer question547 = new QuestionInDrawer(subjects.get(7), "What is the term for the level of output an economy can produce at full employment?",
                    "Potential output", "Recessionary output", "Equilibrium output", "Unattainable output", 0);
            session.save(question547);
            session.flush();
            CourseQuestionInDrawerLink link547 = new CourseQuestionInDrawerLink(courses.get(36), question547);
            session.save(link547);


//International Economics
// Question 556
            QuestionInDrawer question556 = new QuestionInDrawer(subjects.get(7), "What economic theory suggests that a country should focus on producing goods and services in which it has a comparative advantage?", "Absolute advantage", "Mercantilism", "Comparative advantage", "Opportunity cost", 3);
            session.save(question556);
            session.flush();
            CourseQuestionInDrawerLink link556 = new CourseQuestionInDrawerLink(courses.get(37), question556);
            session.save(link556);

// Question 557
            QuestionInDrawer question557 = new QuestionInDrawer(subjects.get(7), "What term describes a situation where a country exports more goods and services than it imports?", "Trade deficit", "Trade surplus", "Balance of payments", "Exchange rate", 2);
            session.save(question557);
            session.flush();
            CourseQuestionInDrawerLink link557 = new CourseQuestionInDrawerLink(courses.get(37), question557);
            session.save(link557);

// Question 558
            QuestionInDrawer question558 = new QuestionInDrawer(subjects.get(7), "Which international economic organization aims to promote global monetary cooperation, secure financial stability, and facilitate international trade?", "World Bank", "International Monetary Fund (IMF)", "World Trade Organization (WTO)", "United Nations", 2);
            session.save(question558);
            session.flush();
            CourseQuestionInDrawerLink link558 = new CourseQuestionInDrawerLink(courses.get(37), question558);
            session.save(link558);

// Question 559
            QuestionInDrawer question559 = new QuestionInDrawer(subjects.get(7), "What type of trade barrier involves setting a limit on the quantity of a certain good that can be imported?", "Tariff", "Subsidy", "Quota", "Dumping", 3);
            session.save(question559);
            session.flush();
            CourseQuestionInDrawerLink link559 = new CourseQuestionInDrawerLink(courses.get(37), question559);
            session.save(link559);

// Question 560
            QuestionInDrawer question560 = new QuestionInDrawer(subjects.get(7), "What does the acronym NAFTA stand for?", "National Association of Foreign Trade Agencies", "North American Free Trade Agreement", "New Alliance for Trade Advancement", "Nordic Association of Fair Trade Agreements", 2);
            session.save(question560);
            session.flush();
            CourseQuestionInDrawerLink link560 = new CourseQuestionInDrawerLink(courses.get(37), question560);
            session.save(link560);

// Question 561
            QuestionInDrawer question561 = new QuestionInDrawer(subjects.get(7), "What is the term for a situation where a country is able to produce a good at a lower opportunity cost than another country?", "Absolute advantage", "Comparative advantage", "Trade deficit", "Exchange rate", 1);
            session.save(question561);
            session.flush();
            CourseQuestionInDrawerLink link561 = new CourseQuestionInDrawerLink(courses.get(37), question561);
            session.save(link561);

// Question 562
            QuestionInDrawer question562 = new QuestionInDrawer(subjects.get(7), "Which economic theory suggests that a country's wealth is determined by the accumulation of precious metals like gold and silver?", "Absolute advantage", "Mercantilism", "Comparative advantage", "Opportunity cost", 2);
            session.save(question562);
            session.flush();
            CourseQuestionInDrawerLink link562 = new CourseQuestionInDrawerLink(courses.get(37), question562);
            session.save(link562);


//Econometrics
// Question 571
            QuestionInDrawer question571 = new QuestionInDrawer(subjects.get(7), "In econometrics, what does the term 'heteroscedasticity' refer to?", "The presence of outliers in the data", "Nonlinearity in the regression equation", "The violation of the independence assumption", "Unequal variance of errors across observations", 4);
            session.save(question571);
            session.flush();
            CourseQuestionInDrawerLink link571 = new CourseQuestionInDrawerLink(courses.get(38), question571);
            session.save(link571);

// Question 572
            QuestionInDrawer question572 = new QuestionInDrawer(subjects.get(7), "What is the primary purpose of instrumental variables (IV) in econometrics?", "To address issues of multicollinearity", "To control for omitted variable bias", "To address endogeneity and establish causality", "To calculate the standard error of regression coefficients", 3);
            session.save(question572);
            session.flush();
            CourseQuestionInDrawerLink link572 = new CourseQuestionInDrawerLink(courses.get(38), question572);
            session.save(link572);

// Question 573
            QuestionInDrawer question573 = new QuestionInDrawer(subjects.get(7), "What econometric technique is used to model and forecast time series data that exhibits trend and seasonality?", "Linear regression", "Panel data analysis", "Ordinary least squares (OLS)", "Time series analysis", 4);
            session.save(question573);
            session.flush();
            CourseQuestionInDrawerLink link573 = new CourseQuestionInDrawerLink(courses.get(38), question573);
            session.save(link573);

// Question 574
            QuestionInDrawer question574 = new QuestionInDrawer(subjects.get(7), "What does the Durbin-Watson statistic help detect in a regression model?", "Heteroscedasticity", "Multicollinearity", "Serial correlation", "Endogeneity", 3);
            session.save(question574);
            session.flush();
            CourseQuestionInDrawerLink link574 = new CourseQuestionInDrawerLink(courses.get(38), question574);
            session.save(link574);

// Question 575
            QuestionInDrawer question575 = new QuestionInDrawer(subjects.get(7), "What is the purpose of the Breusch-Pagan test in econometrics?", "To test for heteroscedasticity", "To test for autocorrelation", "To test for endogeneity", "To test for multicollinearity", 1);
            session.save(question575);
            session.flush();
            CourseQuestionInDrawerLink link575 = new CourseQuestionInDrawerLink(courses.get(38), question575);
            session.save(link575);

// Question 576
            QuestionInDrawer question576 = new QuestionInDrawer(subjects.get(7), "What is the Gauss-Markov theorem in econometrics?", "A theorem that establishes the consistency of OLS estimators", "A theorem that addresses the issue of heteroscedasticity", "A theorem that explains the presence of multicollinearity", "A theorem that proves the unbiasedness of IV estimators", 1);
            session.save(question576);
            session.flush();
            CourseQuestionInDrawerLink link576 = new CourseQuestionInDrawerLink(courses.get(38), question576);
            session.save(link576);

// Question 577
            QuestionInDrawer question577 = new QuestionInDrawer(subjects.get(7), "What does the term 'endogeneity' refer to in econometrics?", "The presence of omitted variables in a model", "The presence of multicollinearity in a model", "The violation of the independence assumption", "The presence of a two-way relationship between variables", 4);
            session.save(question577);
            session.flush();
            CourseQuestionInDrawerLink link577 = new CourseQuestionInDrawerLink(courses.get(38), question577);
            session.save(link577);

//Game Theory
// Question 586
            QuestionInDrawer question586 = new QuestionInDrawer(subjects.get(7), "In game theory, what is the 'Nash equilibrium'?",
                    "A situation where one player wins everything", "A strategy profile where no player has an incentive to unilaterally deviate",
                    "A cooperative outcome reached through negotiation", "A situation where all players cooperate perfectly", 1);
            session.save(question586);
            session.flush();
            CourseQuestionInDrawerLink link586 = new CourseQuestionInDrawerLink(courses.get(39), question586);
            session.save(link586);

// Question 587
            QuestionInDrawer question587 = new QuestionInDrawer(subjects.get(7), "What does 'dominant strategy' refer to in game theory?",
                    "A strategy that guarantees a player the highest payoff regardless of the other player's choices",
                    "A strategy that leads to a cooperative outcome",
                    "A strategy that involves random decision-making",
                    "A strategy that maximizes the total payoff for all players", 0);
            session.save(question587);
            session.flush();
            CourseQuestionInDrawerLink link587 = new CourseQuestionInDrawerLink(courses.get(39), question587);
            session.save(link587);

// Question 588
            QuestionInDrawer question588 = new QuestionInDrawer(subjects.get(7), "What is the 'Prisoner's Dilemma'?",
                    "A situation where two players always cooperate",
                    "A situation where two players both confess to a crime",
                    "A situation where two players both remain silent about a crime",
                    "A situation where two players compete for a prize", 1);
            session.save(question588);
            session.flush();
            CourseQuestionInDrawerLink link588 = new CourseQuestionInDrawerLink(courses.get(39), question588);
            session.save(link588);

// Question 589
            QuestionInDrawer question589 = new QuestionInDrawer(subjects.get(7), "What is a 'zero-sum game'?",
                    "A game with no winners or losers",
                    "A game where the total payoff to all players is zero",
                    "A game with only one player",
                    "A game where one player's gain is equal to another player's loss", 3);
            session.save(question589);
            session.flush();
            CourseQuestionInDrawerLink link589 = new CourseQuestionInDrawerLink(courses.get(39), question589);
            session.save(link589);

// Question 590
            QuestionInDrawer question590 = new QuestionInDrawer(subjects.get(7), "Which concept in game theory addresses the idea that cooperation can be achieved through repeated interactions?",
                    "Collusion",
                    "Tit-for-tat",
                    "Dominant strategy",
                    "Pareto efficiency", 1);
            session.save(question590);
            session.flush();
            CourseQuestionInDrawerLink link590 = new CourseQuestionInDrawerLink(courses.get(39), question590);
            session.save(link590);

// Question 591
            QuestionInDrawer question591 = new QuestionInDrawer(subjects.get(7), "What is a 'mixed strategy' in game theory?",
                    "A strategy that involves using multiple players",
                    "A strategy that combines both cooperative and competitive elements",
                    "A strategy that involves randomizing actions with certain probabilities",
                    "A strategy that focuses solely on maximizing individual payoffs", 2);
            session.save(question591);
            session.flush();
            CourseQuestionInDrawerLink link591 = new CourseQuestionInDrawerLink(courses.get(39), question591);
            session.save(link591);

// Question 592
            QuestionInDrawer question592 = new QuestionInDrawer(subjects.get(7), "What is 'backward induction' in game theory?",
                    "A process of reasoning from the end of a game to the beginning",
                    "A strategy that focuses on the immediate gains",
                    "A form of collusion between players",
                    "A strategy that involves taking turns", 0);
            session.save(question592);
            session.flush();
            CourseQuestionInDrawerLink link592 = new CourseQuestionInDrawerLink(courses.get(39), question592);
            session.save(link592);


//Philosophy****************
//Introduction to Philosophy
// Question 601
            QuestionInDrawer question601 = new QuestionInDrawer(subjects.get(8), "Which ancient Greek philosopher is known for his method of questioning and his famous statement 'I know that I am intelligent, because I know that I know nothing'?",
                    "Plato", "Aristotle", "Socrates", "Epicurus", 2);
            session.save(question601);
            session.flush();
            CourseQuestionInDrawerLink link601 = new CourseQuestionInDrawerLink(courses.get(40), question601);
            session.save(link601);

// Question 602
            QuestionInDrawer question602 = new QuestionInDrawer(subjects.get(8), "What is the branch of philosophy that deals with the nature of reality?",
                    "Ethics", "Aesthetics", "Epistemology", "Metaphysics", 3);
            session.save(question602);
            session.flush();
            CourseQuestionInDrawerLink link602 = new CourseQuestionInDrawerLink(courses.get(40), question602);
            session.save(link602);

// Question 603
            QuestionInDrawer question603 = new QuestionInDrawer(subjects.get(8), "According to existentialism, what is the primary concern of human existence?",
                    "Achieving happiness", "Conforming to societal norms", "Discovering universal truths", "Creating individual meaning", 3);
            session.save(question603);
            session.flush();
            CourseQuestionInDrawerLink link603 = new CourseQuestionInDrawerLink(courses.get(40), question603);
            session.save(link603);

// Question 604
            QuestionInDrawer question604 = new QuestionInDrawer(subjects.get(8), "Which philosopher is known for his political theory outlined in the book 'Leviathan'?",
                    "Thomas Hobbes", "John Locke", "Jean-Jacques Rousseau", "Immanuel Kant", 0);
            session.save(question604);
            session.flush();
            CourseQuestionInDrawerLink link604 = new CourseQuestionInDrawerLink(courses.get(40), question604);
            session.save(link604);

// Question 605
            QuestionInDrawer question605 = new QuestionInDrawer(subjects.get(8), "Who formulated the 'categorical imperative' as a foundational principle of ethics?",
                    "Friedrich Nietzsche", "David Hume", "John Stuart Mill", "Immanuel Kant", 3);
            session.save(question605);
            session.flush();
            CourseQuestionInDrawerLink link605 = new CourseQuestionInDrawerLink(courses.get(40), question605);
            session.save(link605);

// Question 606
            QuestionInDrawer question606 = new QuestionInDrawer(subjects.get(8), "Which philosophy focuses on the concept of 'the greatest good for the greatest number'?",
                    "Utilitarianism", "Deontology", "Virtue ethics", "Existentialism", 0);
            session.save(question606);
            session.flush();
            CourseQuestionInDrawerLink link606 = new CourseQuestionInDrawerLink(courses.get(40), question606);
            session.save(link606);

// Question 607
            QuestionInDrawer question607 = new QuestionInDrawer(subjects.get(8), "Who proposed the allegory of the cave in his work 'The Republic'?",
                    "Plato", "Aristotle", "Socrates", "Epicurus", 0);
            session.save(question607);
            session.flush();
            CourseQuestionInDrawerLink link607 = new CourseQuestionInDrawerLink(courses.get(40), question607);
            session.save(link607);


//Ethics
// Question 616
            QuestionInDrawer question616 = new QuestionInDrawer(subjects.get(8), "According to Immanuel Kant, what is the foundational principle of morality?", "Greatest happiness principle", "Categorical imperative", "Golden mean", "Utilitarianism", 2);
            session.save(question616);
            session.flush();
            CourseQuestionInDrawerLink link616 = new CourseQuestionInDrawerLink(courses.get(41), question616);
            session.save(link616);

// Question 617
            QuestionInDrawer question617 = new QuestionInDrawer(subjects.get(8), "Which ethical theory emphasizes the importance of cultivating virtuous character traits?", "Deontology", "Utilitarianism", "Virtue ethics", "Egoism", 3);
            session.save(question617);
            session.flush();
            CourseQuestionInDrawerLink link617 = new CourseQuestionInDrawerLink(courses.get(41), question617);
            session.save(link617);

// Question 618
            QuestionInDrawer question618 = new QuestionInDrawer(subjects.get(8), "What is the central idea of John Stuart Mill's utilitarianism?", "The pursuit of pleasure is the only intrinsic good", "Acting in accordance with moral rules is paramount", "The greatest good for the individual is the highest priority", "The well-being of the majority is the ultimate goal", 1);
            session.save(question618);
            session.flush();
            CourseQuestionInDrawerLink link618 = new CourseQuestionInDrawerLink(courses.get(41), question618);
            session.save(link618);

// Question 619
            QuestionInDrawer question619 = new QuestionInDrawer(subjects.get(8), "According to the social contract theory, individuals come together to form a society in order to:", "Pursue individual desires without constraint", "Satisfy their natural egoistic tendencies", "Achieve perfect justice and equality", "Escape the state of nature and secure mutual protection", 4);
            session.save(question619);
            session.flush();
            CourseQuestionInDrawerLink link619 = new CourseQuestionInDrawerLink(courses.get(41), question619);
            session.save(link619);

// Question 620
            QuestionInDrawer question620 = new QuestionInDrawer(subjects.get(8), "What is the ethical concept that suggests actions are morally right if they lead to the greatest overall happiness?", "Virtue ethics", "Deontology", "Utilitarianism", "Egoism", 3);
            session.save(question620);
            session.flush();
            CourseQuestionInDrawerLink link620 = new CourseQuestionInDrawerLink(courses.get(41), question620);
            session.save(link620);

// Question 621
            QuestionInDrawer question621 = new QuestionInDrawer(subjects.get(8), "Which ethical theory posits that the right action is the one that follows a universalizable principle?", "Virtue ethics", "Utilitarianism", "Deontology", "Cultural relativism", 3);
            session.save(question621);
            session.flush();
            CourseQuestionInDrawerLink link621 = new CourseQuestionInDrawerLink(courses.get(41), question621);
            session.save(link621);

// Question 622
            QuestionInDrawer question622 = new QuestionInDrawer(subjects.get(8), "In ethics, what is the 'trolley problem' often used to explore?", "The ethics of lying", "The nature of moral duties", "The consequences of egoism", "The implications of utilitarianism", 2);
            session.save(question622);
            session.flush();
            CourseQuestionInDrawerLink link622 = new CourseQuestionInDrawerLink(courses.get(41), question622);
            session.save(link622);


//Logic
// Question 631
            QuestionInDrawer question631 = new QuestionInDrawer(subjects.get(8), "What is the term for a valid argument that has true premises?", "Fallacy", "Syllogism", "Deductive reasoning", "Inductive reasoning", 3);
            session.save(question631);
            session.flush();
            CourseQuestionInDrawerLink link631 = new CourseQuestionInDrawerLink(courses.get(42), question631);
            session.save(link631);

// Question 632
            QuestionInDrawer question632 = new QuestionInDrawer(subjects.get(8), "What type of logical fallacy involves attacking the person making an argument rather than addressing the argument itself?", "Ad hominem", "Straw man", "Appeal to authority", "False cause", 1);
            session.save(question632);
            session.flush();
            CourseQuestionInDrawerLink link632 = new CourseQuestionInDrawerLink(courses.get(42), question632);
            session.save(link632);

// Question 633
            QuestionInDrawer question633 = new QuestionInDrawer(subjects.get(8), "Which type of reasoning moves from specific observations to a general conclusion?", "Deductive reasoning", "Inductive reasoning", "Syllogism", "Fallacy", 2);
            session.save(question633);
            session.flush();
            CourseQuestionInDrawerLink link633 = new CourseQuestionInDrawerLink(courses.get(42), question633);
            session.save(link633);

// Question 634
            QuestionInDrawer question634 = new QuestionInDrawer(subjects.get(8), "What is the Law of Excluded Middle?", "Every proposition is either true or false", "Every argument has a valid conclusion", "No two premises can contradict each other", "A proposition cannot be both true and false", 1);
            session.save(question634);
            session.flush();
            CourseQuestionInDrawerLink link634 = new CourseQuestionInDrawerLink(courses.get(42), question634);
            session.save(link634);

// Question 635
            QuestionInDrawer question635 = new QuestionInDrawer(subjects.get(8), "What is the principle of Modus Ponens in logic?", "If P is true, then Q is false", "If P is false, then Q is true", "If P implies Q, and P is true, then Q is true", "If P implies Q, and Q is true, then P is true", 3);
            session.save(question635);
            session.flush();
            CourseQuestionInDrawerLink link635 = new CourseQuestionInDrawerLink(courses.get(42), question635);
            session.save(link635);

// Question 636
            QuestionInDrawer question636 = new QuestionInDrawer(subjects.get(8), "What is the term for a statement that is true by definition and can be used to clarify other concepts?", "Fallacy", "Tautology", "Syllogism", "Conjecture", 2);
            session.save(question636);
            session.flush();
            CourseQuestionInDrawerLink link636 = new CourseQuestionInDrawerLink(courses.get(42), question636);
            session.save(link636);

// Question 637
            QuestionInDrawer question637 = new QuestionInDrawer(subjects.get(8), "What logical fallacy occurs when someone assumes that a premise is true simply because it has not been proven false?", "Appeal to ignorance", "Begging the question", "Circular reasoning", "Ad hominem", 1);
            session.save(question637);
            session.flush();
            CourseQuestionInDrawerLink link637 = new CourseQuestionInDrawerLink(courses.get(42), question637);
            session.save(link637);


//Epistemology
// Question 646
            QuestionInDrawer question646 = new QuestionInDrawer(subjects.get(8), "Which philosophical term describes the view that knowledge is primarily obtained through sensory experiences?",
                    "Empiricism", "Rationalism", "Skepticism", "Idealism", 0);
            session.save(question646);
            session.flush();
            CourseQuestionInDrawerLink link646 = new CourseQuestionInDrawerLink(courses.get(43), question646);
            session.save(link646);

// Question 647
            QuestionInDrawer question647 = new QuestionInDrawer(subjects.get(8), "Who proposed the 'Gettier problem', which challenges the traditional definition of knowledge?",
                    "Ludwig Wittgenstein", "Edmund Gettier", "Bertrand Russell", "Karl Popper", 1);
            session.save(question647);
            session.flush();
            CourseQuestionInDrawerLink link647 = new CourseQuestionInDrawerLink(courses.get(43), question647);
            session.save(link647);

// Question 648
            QuestionInDrawer question648 = new QuestionInDrawer(subjects.get(8), "What is the term for the belief that all knowledge is derived from and justified by reason?",
                    "Empiricism", "Skepticism", "Rationalism", "Pragmatism", 2);
            session.save(question648);
            session.flush();
            CourseQuestionInDrawerLink link648 = new CourseQuestionInDrawerLink(courses.get(43), question648);
            session.save(link648);

// Question 649
            QuestionInDrawer question649 = new QuestionInDrawer(subjects.get(8), "What is the philosophical term for the view that reality, including knowledge, is ultimately mental or immaterial?",
                    "Empiricism", "Materialism", "Skepticism", "Idealism", 3);
            session.save(question649);
            session.flush();
            CourseQuestionInDrawerLink link649 = new CourseQuestionInDrawerLink(courses.get(43), question649);
            session.save(link649);

// Question 650
            QuestionInDrawer question650 = new QuestionInDrawer(subjects.get(8), "According to the 'correspondence theory of truth', what is the nature of truth?",
                    "Truth is defined by its usefulness", "Truth is based on individual perspectives", "Truth is what corresponds to reality", "Truth is a matter of convention", 2);
            session.save(question650);
            session.flush();
            CourseQuestionInDrawerLink link650 = new CourseQuestionInDrawerLink(courses.get(43), question650);
            session.save(link650);

// Question 651
            QuestionInDrawer question651 = new QuestionInDrawer(subjects.get(8), "Who famously stated 'I think, therefore I am' as a foundational element of his epistemological philosophy?",
                    "John Locke", "Immanuel Kant", "René Descartes", "David Hume", 2);
            session.save(question651);
            session.flush();
            CourseQuestionInDrawerLink link651 = new CourseQuestionInDrawerLink(courses.get(43), question651);
            session.save(link651);

// Question 652
            QuestionInDrawer question652 = new QuestionInDrawer(subjects.get(8), "What is the term for the idea that knowledge is context-dependent and varies among different cultures or groups?",
                    "Skepticism", "Cultural relativism", "Empiricism", "Rationalism", 1);
            session.save(question652);
            session.flush();
            CourseQuestionInDrawerLink link652 = new CourseQuestionInDrawerLink(courses.get(43), question652);
            session.save(link652);


//Philosophy of Mind
// Question 661
            QuestionInDrawer question661 = new QuestionInDrawer(subjects.get(8), "What is the philosophical term for the view that mental states are identical to physical brain states?",
                    "Dualism", "Functionalism", "Materialism", "Idealism", 2);
            session.save(question661);
            session.flush();
            CourseQuestionInDrawerLink link661 = new CourseQuestionInDrawerLink(courses.get(44), question661);
            session.save(link661);

// Question 662
            QuestionInDrawer question662 = new QuestionInDrawer(subjects.get(8), "Which theory suggests that mental states are defined by their functions rather than their physical composition?",
                    "Behaviorism", "Physicalism", "Dualism", "Functionalism", 3);
            session.save(question662);
            session.flush();
            CourseQuestionInDrawerLink link662 = new CourseQuestionInDrawerLink(courses.get(44), question662);
            session.save(link662);

// Question 663
            QuestionInDrawer question663 = new QuestionInDrawer(subjects.get(8), "According to substance dualism, what are the two types of substances that exist?",
                    "Mind and matter", "Energy and matter", "Consciousness and unconsciousness", "Perception and sensation", 0);
            session.save(question663);
            session.flush();
            CourseQuestionInDrawerLink link663 = new CourseQuestionInDrawerLink(courses.get(44), question663);
            session.save(link663);

// Question 664
            QuestionInDrawer question664 = new QuestionInDrawer(subjects.get(8), "What is the philosophical term for the belief that mental states are reducible to physical brain states?",
                    "Functionalism", "Emergentism", "Dualism", "Physicalism", 3);
            session.save(question664);
            session.flush();
            CourseQuestionInDrawerLink link664 = new CourseQuestionInDrawerLink(courses.get(44), question664);
            session.save(link664);

// Question 665
            QuestionInDrawer question665 = new QuestionInDrawer(subjects.get(8), "Who proposed the 'Chinese Room' thought experiment to challenge the idea of strong artificial intelligence?",
                    "Daniel Dennett", "John Searle", "Thomas Nagel", "Hilary Putnam", 1);
            session.save(question665);
            session.flush();
            CourseQuestionInDrawerLink link665 = new CourseQuestionInDrawerLink(courses.get(44), question665);
            session.save(link665);

// Question 666
            QuestionInDrawer question666 = new QuestionInDrawer(subjects.get(8), "According to the 'identity theory', what is the relationship between mental states and brain states?",
                    "Mental states are identical to brain states", "Mental states are separate from brain states", "Mental states supervene on brain states", "Mental states cause brain states", 0);
            session.save(question666);
            session.flush();
            CourseQuestionInDrawerLink link666 = new CourseQuestionInDrawerLink(courses.get(44), question666);
            session.save(link666);

// Question 667
            QuestionInDrawer question667 = new QuestionInDrawer(subjects.get(8), "Which theory posits that mental states emerge from complex interactions of physical brain states?",
                    "Materialism", "Dualism", "Epiphenomenalism", "Emergentism", 3);
            session.save(question667);
            session.flush();
            CourseQuestionInDrawerLink link667 = new CourseQuestionInDrawerLink(courses.get(44), question667);
            session.save(link667);


//Sociology**************
//Introduction to Sociology
// Question 676
            QuestionInDrawer question676 = new QuestionInDrawer(subjects.get(9), "What is the sociological term for the established rules and norms that regulate human behavior?",
                    "Culture", "Norms", "Socialization", "Society", 1);
            session.save(question676);
            session.flush();
            CourseQuestionInDrawerLink link676 = new CourseQuestionInDrawerLink(courses.get(45), question676);
            session.save(link676);

// Question 677
            QuestionInDrawer question677 = new QuestionInDrawer(subjects.get(9), "Which concept refers to the beliefs, norms, behaviors, and products common to the members of a particular group?",
                    "Subculture", "Counter-culture", "Cultural relativism", "Cultural universals", 0);
            session.save(question677);
            session.flush();
            CourseQuestionInDrawerLink link677 = new CourseQuestionInDrawerLink(courses.get(45), question677);
            session.save(link677);

// Question 678
            QuestionInDrawer question678 = new QuestionInDrawer(subjects.get(9), "What is the term for the process by which individuals internalize the values, beliefs, and norms of a given society?",
                    "Acculturation", "Socialization", "Ethnocentrism", "Cultural assimilation", 1);
            session.save(question678);
            session.flush();
            CourseQuestionInDrawerLink link678 = new CourseQuestionInDrawerLink(courses.get(45), question678);
            session.save(link678);

// Question 679
            QuestionInDrawer question679 = new QuestionInDrawer(subjects.get(9), "Which sociological perspective emphasizes the role of power, conflict, and inequality in society?",
                    "Functionalism", "Symbolic interactionism", "Conflict theory", "Structuralism", 2);
            session.save(question679);
            session.flush();
            CourseQuestionInDrawerLink link679 = new CourseQuestionInDrawerLink(courses.get(45), question679);
            session.save(link679);

// Question 680
            QuestionInDrawer question680 = new QuestionInDrawer(subjects.get(9), "According to Emile Durkheim, what is the term for the degree of integration in a society?",
                    "Alienation", "Anomie", "Solidarity", "Conflict", 2);
            session.save(question680);
            session.flush();
            CourseQuestionInDrawerLink link680 = new CourseQuestionInDrawerLink(courses.get(45), question680);
            session.save(link680);

// Question 681
            QuestionInDrawer question681 = new QuestionInDrawer(subjects.get(9), "Which sociological perspective focuses on the micro-level interactions between individuals in society?",
                    "Conflict theory", "Structural functionalism", "Symbolic interactionism", "Feminist theory", 2);
            session.save(question681);
            session.flush();
            CourseQuestionInDrawerLink link681 = new CourseQuestionInDrawerLink(courses.get(45), question681);
            session.save(link681);

// Question 682
            QuestionInDrawer question682 = new QuestionInDrawer(subjects.get(9), "Who introduced the concept of the 'looking-glass self' to describe how individuals perceive themselves based on how others view them?",
                    "Karl Marx", "Max Weber", "Emile Durkheim", "Charles Cooley", 3);
            session.save(question682);
            session.flush();
            CourseQuestionInDrawerLink link682 = new CourseQuestionInDrawerLink(courses.get(45), question682);
            session.save(link682);


//Social Theory
// Question 691
            QuestionInDrawer question691 = new QuestionInDrawer(subjects.get(9), "Which sociological perspective emphasizes the role of power and inequality in shaping social relationships?", "Functionalism", "Conflict theory", "Symbolic interactionism", "Structuralism", 2);
            session.save(question691);
            session.flush();
            CourseQuestionInDrawerLink link691 = new CourseQuestionInDrawerLink(courses.get(46), question691);
            session.save(link691);

// Question 692
            QuestionInDrawer question692 = new QuestionInDrawer(subjects.get(9), "According to Emile Durkheim, what is the term for the degree to which individuals are integrated into the collective life of a society?", "Alienation", "Anomie", "Solidarity", "Conflict", 3);
            session.save(question692);
            session.flush();
            CourseQuestionInDrawerLink link692 = new CourseQuestionInDrawerLink(courses.get(46), question692);
            session.save(link692);

// Question 693
            QuestionInDrawer question693 = new QuestionInDrawer(subjects.get(9), "Which sociological perspective emphasizes the importance of symbols and shared meanings in social interactions?", "Functionalism", "Conflict theory", "Symbolic interactionism", "Structuralism", 3);
            session.save(question693);
            session.flush();
            CourseQuestionInDrawerLink link693 = new CourseQuestionInDrawerLink(courses.get(46), question693);
            session.save(link693);

// Question 694
            QuestionInDrawer question694 = new QuestionInDrawer(subjects.get(9), "What is the concept introduced by Michel Foucault that refers to the mechanisms through which societies exercise control and discipline over individuals?", "Power dynamics", "Panopticism", "Functionalism", "Role conflict", 2);
            session.save(question694);
            session.flush();
            CourseQuestionInDrawerLink link694 = new CourseQuestionInDrawerLink(courses.get(46), question694);
            session.save(link694);

// Question 695
            QuestionInDrawer question695 = new QuestionInDrawer(subjects.get(9), "Which sociological perspective focuses on the functions and dysfunctions of various aspects of society?", "Conflict theory", "Symbolic interactionism", "Structural functionalism", "Postmodernism", 3);
            session.save(question695);
            session.flush();
            CourseQuestionInDrawerLink link695 = new CourseQuestionInDrawerLink(courses.get(46), question695);
            session.save(link695);

// Question 696
            QuestionInDrawer question696 = new QuestionInDrawer(subjects.get(9), "What is the term for a dramatic and often traumatic change in the social structure or institutions of a society?", "Socialization", "Modernization", "Revolution", "Assimilation", 3);
            session.save(question696);
            session.flush();
            CourseQuestionInDrawerLink link696 = new CourseQuestionInDrawerLink(courses.get(46), question696);
            session.save(link696);

// Question 697
            QuestionInDrawer question697 = new QuestionInDrawer(subjects.get(9), "Which sociological perspective emphasizes the macro-level analysis of social structures and institutions?", "Symbolic interactionism", "Conflict theory", "Structuralism", "Functionalism", 3);
            session.save(question697);
            session.flush();
            CourseQuestionInDrawerLink link697 = new CourseQuestionInDrawerLink(courses.get(46), question697);
            session.save(link697);


//Sociology of Gender
// Question 706
            QuestionInDrawer question706 = new QuestionInDrawer(subjects.get(9), "What term refers to the expectations and behaviors associated with a particular social position, such as being male or female?", "Gender identity", "Sexuality", "Gender role", "Gender stereotype", 3);
            session.save(question706);
            session.flush();
            CourseQuestionInDrawerLink link706 = new CourseQuestionInDrawerLink(courses.get(47), question706);
            session.save(link706);

// Question 707
            QuestionInDrawer question707 = new QuestionInDrawer(subjects.get(9), "What is the concept that suggests that gender is not an innate quality, but rather a social construction created and reinforced by society?", "Biology theory", "Essentialism", "Socialization", "Social constructionism", 4);
            session.save(question707);
            session.flush();
            CourseQuestionInDrawerLink link707 = new CourseQuestionInDrawerLink(courses.get(47), question707);
            session.save(link707);

// Question 708
            QuestionInDrawer question708 = new QuestionInDrawer(subjects.get(9), "Which term describes the process of learning and internalizing the norms and expectations associated with one's gender?", "Gender essentialism", "Gender identity", "Gender socialization", "Gender segregation", 3);
            session.save(question708);
            session.flush();
            CourseQuestionInDrawerLink link708 = new CourseQuestionInDrawerLink(courses.get(47), question708);
            session.save(link708);

// Question 709
            QuestionInDrawer question709 = new QuestionInDrawer(subjects.get(9), "What is the term for the unequal distribution of power, resources, and opportunities based on gender?", "Gender essentialism", "Gender inequality", "Gender identity", "Gender segregation", 2);
            session.save(question709);
            session.flush();
            CourseQuestionInDrawerLink link709 = new CourseQuestionInDrawerLink(courses.get(47), question709);
            session.save(link709);

// Question 710
            QuestionInDrawer question710 = new QuestionInDrawer(subjects.get(9), "What is the term for the social and psychological differences between males and females, often influenced by societal expectations?", "Biology", "Sexuality", "Gender", "Genetics", 3);
            session.save(question710);
            session.flush();
            CourseQuestionInDrawerLink link710 = new CourseQuestionInDrawerLink(courses.get(47), question710);
            session.save(link710);

// Question 711
            QuestionInDrawer question711 = new QuestionInDrawer(subjects.get(9), "Which term describes the belief that there are innate, fixed, and essential differences between men and women?", "Gender essentialism", "Gender identity", "Gender socialization", "Gender segregation", 1);
            session.save(question711);
            session.flush();
            CourseQuestionInDrawerLink link711 = new CourseQuestionInDrawerLink(courses.get(47), question711);
            session.save(link711);

// Question 712
            QuestionInDrawer question712 = new QuestionInDrawer(subjects.get(9), "What is the term for the process of sorting individuals into social categories based on perceived gender differences?", "Gender socialization", "Gender identity", "Gender essentialism", "Gender segregation", 4);
            session.save(question712);
            session.flush();
            CourseQuestionInDrawerLink link712 = new CourseQuestionInDrawerLink(courses.get(47), question712);
            session.save(link712);


//Race and Ethnicity
// Question 721
            QuestionInDrawer question721 = new QuestionInDrawer(subjects.get(9), "What is the term for the process through which individuals from different racial and ethnic backgrounds come to see themselves as part of a shared culture?", "Acculturation", "Assimilation", "Cultural relativism", "Segregation", 2);
            session.save(question721);
            session.flush();
            CourseQuestionInDrawerLink link721 = new CourseQuestionInDrawerLink(courses.get(48), question721);
            session.save(link721);

// Question 722
            QuestionInDrawer question722 = new QuestionInDrawer(subjects.get(9), "What is the term for the belief that one's own culture is superior to others and should be the standard by which other cultures are measured?", "Cultural relativism", "Multiculturalism", "Ethnocentrism", "Cultural diffusion", 3);
            session.save(question722);
            session.flush();
            CourseQuestionInDrawerLink link722 = new CourseQuestionInDrawerLink(courses.get(48), question722);
            session.save(link722);

// Question 723
            QuestionInDrawer question723 = new QuestionInDrawer(subjects.get(9), "What concept refers to the unequal distribution of power, resources, and opportunities based on race?", "Racial segregation", "Racial diversity", "Racial inequality", "Racial bias", 3);
            session.save(question723);
            session.flush();
            CourseQuestionInDrawerLink link723 = new CourseQuestionInDrawerLink(courses.get(48), question723);
            session.save(link723);

// Question 724
            QuestionInDrawer question724 = new QuestionInDrawer(subjects.get(9), "What is the term for the systematic killing, displacement, and oppression of one ethnic or racial group by another?", "Genocide", "Colonialism", "Ethnocentrism", "Acculturation", 1);
            session.save(question724);
            session.flush();
            CourseQuestionInDrawerLink link724 = new CourseQuestionInDrawerLink(courses.get(48), question724);
            session.save(link724);

// Question 725
            QuestionInDrawer question725 = new QuestionInDrawer(subjects.get(9), "Which term refers to the physical separation of racial and ethnic groups in various aspects of social life?", "Cultural assimilation", "Racial segregation", "Ethnic pluralism", "Cultural relativism", 2);
            session.save(question725);
            session.flush();
            CourseQuestionInDrawerLink link725 = new CourseQuestionInDrawerLink(courses.get(48), question725);
            session.save(link725);

// Question 726
            QuestionInDrawer question726 = new QuestionInDrawer(subjects.get(9), "What is the term for a negative attitude or belief about a particular racial or ethnic group?", "Racial bias", "Racial diversity", "Racial integration", "Racial equality", 1);
            session.save(question726);
            session.flush();
            CourseQuestionInDrawerLink link726 = new CourseQuestionInDrawerLink(courses.get(48), question726);
            session.save(link726);

// Question 727
            QuestionInDrawer question727 = new QuestionInDrawer(subjects.get(9), "What is the term for the process of incorporating elements from one culture into another, often resulting in the loss or transformation of the original culture?", "Assimilation", "Acculturation", "Cultural diffusion", "Cultural relativism", 1);
            session.save(question727);
            session.flush();
            CourseQuestionInDrawerLink link727 = new CourseQuestionInDrawerLink(courses.get(48), question727);
            session.save(link727);


//Sociology of Health
// Question 736
            QuestionInDrawer question736 = new QuestionInDrawer(subjects.get(9), "What term describes a pattern of health-related disparities that are closely linked to social, economic, and environmental disadvantage?",
                    "Medicalization", "Healthcare system", "Health inequity", "Health consumerism", 2);
            session.save(question736);
            session.flush();
            CourseQuestionInDrawerLink link736 = new CourseQuestionInDrawerLink(courses.get(49), question736);
            session.save(link736);

// Question 737
            QuestionInDrawer question737 = new QuestionInDrawer(subjects.get(9), "What is the term for a model of health that focuses on the interactions between individuals, their social contexts, and broader social structures?",
                    "Biomedical model", "Psychosocial model", "Holistic model", "Epidemiological model", 1);
            session.save(question737);
            session.flush();
            CourseQuestionInDrawerLink link737 = new CourseQuestionInDrawerLink(courses.get(49), question737);
            session.save(link737);

// Question 738
            QuestionInDrawer question738 = new QuestionInDrawer(subjects.get(9), "Who introduced the concept of the 'sick role' to explain the behavior of individuals who are unwell?",
                    "Talcott Parsons", "Emile Durkheim", "Max Weber", "Karl Marx", 0);
            session.save(question738);
            session.flush();
            CourseQuestionInDrawerLink link738 = new CourseQuestionInDrawerLink(courses.get(49), question738);
            session.save(link738);

// Question 739
            QuestionInDrawer question739 = new QuestionInDrawer(subjects.get(9), "What term describes a situation in which a medical condition is defined and treated as an illness that requires medical intervention?",
                    "Epidemic", "Pandemic", "Medicalization", "Pathologization", 2);
            session.save(question739);
            session.flush();
            CourseQuestionInDrawerLink link739 = new CourseQuestionInDrawerLink(courses.get(49), question739);
            session.save(link739);

// Question 740
            QuestionInDrawer question740 = new QuestionInDrawer(subjects.get(9), "Which concept refers to the social, psychological, and behavioral aspects of being male or female?",
                    "Sex", "Gender", "Sexual orientation", "Gender identity", 1);
            session.save(question740);
            session.flush();
            CourseQuestionInDrawerLink link740 = new CourseQuestionInDrawerLink(courses.get(49), question740);
            session.save(link740);

// Question 741
            QuestionInDrawer question741 = new QuestionInDrawer(subjects.get(9), "According to the 'social gradient of health', what is the relationship between socioeconomic status and health?",
                    "Higher socioeconomic status leads to better health", "Higher socioeconomic status leads to worse health", "Socioeconomic status has no impact on health", "Health is solely determined by genetics", 0);
            session.save(question741);
            session.flush();
            CourseQuestionInDrawerLink link741 = new CourseQuestionInDrawerLink(courses.get(49), question741);
            session.save(link741);

// Question 742
            QuestionInDrawer question742 = new QuestionInDrawer(subjects.get(9), "What term describes the unequal distribution of health outcomes and access to healthcare among different populations?",
                    "Health inequality", "Health disparity", "Health diversity", "Health variance", 1);
            session.save(question742);
            session.flush();
            CourseQuestionInDrawerLink link742 = new CourseQuestionInDrawerLink(courses.get(49), question742);
            session.save(link742);

            ////////////////////////////////////////////////////// Add EXAMS to the drawer ///////////////////////////////////
//start add exams
            List<QuestionInDrawer> questions0 = getAllQuestions();

//1
            ExamInDrawer examr = new ExamInDrawer(subjects.get(0), courses.get(0), 75, "Ethan Simmons", "", "");
            examr.setAuthor_id(123056786);
            session.save(examr);
            session.flush();
            Score sr = new Score(examr, questions0.get(0), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(1), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(2), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(3), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(0), courses.get(1), 75, "Sophia Hayes", "", "");
            examr.setAuthor_id(123056786);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(9), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(10), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(11), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(12), 40);
            session.save(sr);

//----------------------------------------------------------------------------------------------

//2
            examr = new ExamInDrawer(subjects.get(1), courses.get(5), 75, "Sophia Hayes", "", "");
            examr.setAuthor_id(987654320);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(46), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(47), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(48), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(49), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(1), courses.get(6), 75, "Sophia Hayes", "", "");
            examr.setAuthor_id(987654320);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(50), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(51), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(52), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(53), 40);
            session.save(sr);

//3
            examr = new ExamInDrawer(subjects.get(2), courses.get(10), 75, "Caleb Parker", "", "");
            examr.setAuthor_id(456789121);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(81), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(82), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(83), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(84), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(2), courses.get(11), 75, "Caleb Parker", "", "");
            examr.setAuthor_id(456789121);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(85), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(86), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(87), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(88), 40);
            session.save(sr);

//4
            examr = new ExamInDrawer(subjects.get(3), courses.get(16), 75, "Lily Turner", "", "");
            examr.setAuthor_id(321654981);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(116), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(117), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(118), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(119), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(3), courses.get(17), 75, "Lily Turner", "", "");
            examr.setAuthor_id(321654981);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(120), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(121), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(122), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(123), 40);
            session.save(sr);
//5
            examr = new ExamInDrawer(subjects.get(4), courses.get(20), 75, "Lucas Edwards", "", "");
            examr.setAuthor_id(987123457);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(151), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(152), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(153), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(154), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(4), courses.get(21), 75, "Lucas Edwards", "", "");
            examr.setAuthor_id(987123457);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(155), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(156), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(157), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(158), 40);
            session.save(sr);
//6
            examr = new ExamInDrawer(subjects.get(5), courses.get(25), 75, "Ava Mitchell", "", "");
            examr.setAuthor_id(987123654);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(186), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(187), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(188), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(189), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(5), courses.get(26), 75, "Ava Mitchell", "", "");
            examr.setAuthor_id(987123654);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(190), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(191), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(192), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(193), 40);
            session.save(sr);
//7
            examr = new ExamInDrawer(subjects.get(6), courses.get(30), 75, "Benjamin Martinez", "", "");
            examr.setAuthor_id(369258147);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(221), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(222), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(223), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(224), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(6), courses.get(31), 75, "Benjamin Martinez", "", "");
            examr.setAuthor_id(369258147);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(225), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(226), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(227), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(228), 40);
            session.save(sr);
//8
            examr = new ExamInDrawer(subjects.get(7), courses.get(34), 75, "Olivia Thompson", "", "");
            examr.setAuthor_id(654321780);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(256), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(257), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(258), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(259), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(7), courses.get(35), 75, "Olivia Thompson", "", "");
            examr.setAuthor_id(654321780);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(260), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(261), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(262), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(263), 40);
            session.save(sr);
//9
            examr = new ExamInDrawer(subjects.get(8), courses.get(40), 75, "Gabriel Johnson", "", "");
            examr.setAuthor_id(789456125);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(291), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(292), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(293), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(294), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(8), courses.get(41), 75, "Gabriel Johnson", "", "");
            examr.setAuthor_id(789456125);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(295), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(296), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(297), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(298), 40);
            session.save(sr);
//10
            examr = new ExamInDrawer(subjects.get(9), courses.get(45), 75, "Emma Anderson", "", "");
            examr.setAuthor_id(159753467);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(326), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(327), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(328), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(329), 40);
            session.save(sr);

            examr = new ExamInDrawer(subjects.get(9), courses.get(47), 75, "Emma Anderson", "", "");
            examr.setAuthor_id(159753467);
            session.save(examr);
            session.flush();
            sr = new Score(examr, questions0.get(330), 10);
            session.save(sr);
            sr = new Score(examr, questions0.get(331), 20);
            session.save(sr);
            sr = new Score(examr, questions0.get(332), 30);
            session.save(sr);
            sr = new Score(examr, questions0.get(333), 40);
            session.save(sr);

//            ////////////////////tests/////////////

            Grade grade = new Grade(students.get(0), courses.get(0), 85);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(12), courses.get(0), 70);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(13), courses.get(0), 80);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(12), courses.get(2), 98);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(10), courses.get(2), 86);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(20), courses.get(2), 100);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(1), courses.get(8), 75);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(16), courses.get(7), 69);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(17), courses.get(7), 100);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(2), courses.get(7), 0);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(2), courses.get(2), 75);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(1), courses.get(15), 56);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(25), courses.get(15), 100);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(24), courses.get(15), 50);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(6), courses.get(20), 78);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(10), courses.get(20), 78);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(5), courses.get(20), 36);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(13), courses.get(40), 45);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(41), courses.get(40), 98);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(7), courses.get(40), 75);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(4), courses.get(38), 91);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(26), courses.get(38), 88);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(7), courses.get(38), 36);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(4), courses.get(25), 43);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(5), courses.get(16), 84);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(5), courses.get(13), 67);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(42), courses.get(13), 78);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(23), courses.get(13), 65);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(21), courses.get(33), 38);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(6), courses.get(33), 78);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(32), courses.get(33), 52);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(6), courses.get(49), 71);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(7), courses.get(35), 59);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(17), courses.get(35), 100);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(37), courses.get(35), 2);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(36), courses.get(10), 0);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(7), courses.get(10), 87);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(43), courses.get(10), 60);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(8), courses.get(27), 85);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(29), courses.get(27), 70);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(4), courses.get(27), 96);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(8), courses.get(8), 72);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(9), courses.get(26), 46);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(26), courses.get(26), 15);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(27), courses.get(26), 33);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(9), courses.get(36), 100);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(10), courses.get(0), 24);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(10), courses.get(32), 99);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(18), courses.get(32), 69);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(15), courses.get(32), 90);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(0), courses.get(0), 85);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(12), courses.get(0), 70);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(13), courses.get(0), 80);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(37), courses.get(2), 98);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(10), courses.get(2), 86);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(20), courses.get(2), 100);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(1), courses.get(8), 75);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(16), courses.get(7), 69);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(17), courses.get(7), 100);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(2), courses.get(7), 0);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(2), courses.get(2), 75);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(1), courses.get(15), 56);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(25), courses.get(15), 100);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(24), courses.get(15), 50);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(6), courses.get(20), 78);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(46), courses.get(20), 78);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(5), courses.get(20), 36);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(13), courses.get(40), 45);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(41), courses.get(40), 98);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(7), courses.get(40), 75);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(4), courses.get(38), 91);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(26), courses.get(38), 88);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(7), courses.get(38), 36);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(4), courses.get(25), 43);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(5), courses.get(16), 84);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(5), courses.get(13), 67);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(42), courses.get(13), 78);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(23), courses.get(13), 65);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(21), courses.get(33), 38);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(6), courses.get(33), 78);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(32), courses.get(33), 52);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(6), courses.get(49), 71);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(7), courses.get(35), 59);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(17), courses.get(35), 100);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(37), courses.get(35), 2);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(36), courses.get(10), 0);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(7), courses.get(10), 87);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(43), courses.get(10), 60);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(8), courses.get(27), 85);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(29), courses.get(27), 70);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(4), courses.get(27), 96);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(8), courses.get(8), 72);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(9), courses.get(26), 46);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(26), courses.get(26), 15);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(27), courses.get(26), 33);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(9), courses.get(36), 100);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(10), courses.get(0), 24);
            session.save(grade);
            session.flush();

            grade = new Grade(students.get(10), courses.get(32), 99);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(18), courses.get(32), 69);
            session.save(grade);
            session.flush();
            grade = new Grade(students.get(15), courses.get(32), 90);
            session.save(grade);
            session.flush();


            session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }

    }
}
