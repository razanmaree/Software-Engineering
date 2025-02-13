package il.cshaifasweng.OCSFMediatorExample.entities.entities;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WordConverter {


    public String generateWordFile(ExamInDrawer exam,List<Score> scores,String destinationPath) {
        XWPFDocument document = new XWPFDocument();

        // Adding exam details to the document
        XWPFParagraph examDetailsParagraph = document.createParagraph();
        examDetailsParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun examDetailsRun = examDetailsParagraph.createRun();
        examDetailsRun.setText("Subject: " + exam.getSubject_name());
        examDetailsRun.addCarriageReturn();
        examDetailsRun.setText("Course: " + exam.getCourse_name());
        examDetailsRun.addCarriageReturn();
        examDetailsRun.setText("Author: " + exam.getAuthor_name());
        examDetailsRun.addCarriageReturn();


        // Save the student comments for the exam
        String examStudentComments = exam.getStudentsComments();
        if (examStudentComments != null && !examStudentComments.isEmpty()) {
            XWPFParagraph examCommentsParagraph = document.createParagraph();
            examCommentsParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun examCommentsRun = examCommentsParagraph.createRun();
            examCommentsRun.setText("Teacher Comments for the Exam:");
            examCommentsRun.addCarriageReturn();

            XWPFRun commentsRun = examCommentsParagraph.createRun();
            commentsRun.setText(examStudentComments);
            commentsRun.addCarriageReturn();
        }

        // Adding exam questions to the document
        List<QuestionInDrawer> questions = new ArrayList<>();

        for (Score score :scores) {
            questions.add(score.getQuestion());
        }

        for (int i = 0; i < questions.size(); i++) {
            QuestionInDrawer question = questions.get(i);
            XWPFParagraph questionParagraph = document.createParagraph();
            questionParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun questionRun = questionParagraph.createRun();
            questionRun.setText("Question " + (i + 1) + ": " + question.getText());
            questionRun.addCarriageReturn();

            // Adding author_name and score of the question
            XWPFRun authorAndScoreRun = questionParagraph.createRun();
            authorAndScoreRun.setText("Author: " + exam.getAuthor_name());
            authorAndScoreRun.addCarriageReturn();
            Score score = scores.get(i);
            if (score != null) {
                authorAndScoreRun.setText("Score: " + score.getScore());
            } else {
                authorAndScoreRun.setText("Score: Not graded yet");
            }
            authorAndScoreRun.addCarriageReturn();

            // Adding question options to the document
            String[] options = {
                    question.getFirst_answer(),
                    question.getSecond_answer(),
                    question.getThird_answer(),
                    question.getFourth_answer()
            };

            for (int j = 0; j < options.length; j++) {
                XWPFParagraph optionParagraph = document.createParagraph();
                optionParagraph.setAlignment(ParagraphAlignment.LEFT); // Align options to the left
                optionParagraph.setIndentationLeft(0); // Set indentation to 0 (left-aligned)
                XWPFRun optionRun = optionParagraph.createRun();
                optionRun.setText((char) ('A' + j) + ". " + options[j]);
                optionRun.addCarriageReturn();
            }

            XWPFParagraph emptyParagraph = document.createParagraph(); // Adding an empty paragraph to separate questions
            emptyParagraph.setAlignment(ParagraphAlignment.LEFT);
            emptyParagraph.createRun().addCarriageReturn();
        }

        // Save the document to the specified destination path
        try {
            File file = new File(destinationPath, "exam_questions.docx");
            FileOutputStream fos = new FileOutputStream(file);
            document.write(fos);
            fos.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
