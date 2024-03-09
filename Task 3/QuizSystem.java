import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Question {
    String text;
    Map<String, Boolean> options;

    public Question(String text, Map<String, Boolean> options) {
        this.text = text;
        this.options = options;
    }

    public boolean isCorrect(String userAnswer) {
        return options.get(userAnswer) != null && options.get(userAnswer);
    }
}

class Quiz {
    ArrayList<Question> questions;

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);

            System.out.println("Question " + (i + 1) + ": " + currentQuestion.text);

            for (Map.Entry<String, Boolean> optionEntry : currentQuestion.options.entrySet()) {
                System.out.println(optionEntry.getKey());
            }

            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().trim();

            if (currentQuestion.isCorrect(userAnswer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect.\n");
            }
        }

        System.out.println("Quiz completed. Your score: " + score + "/" + questions.size());
    }
}

public class QuizSystem {
    public static void main(String[] args) {
        
        ArrayList<Question> questions = new ArrayList<>();

        
        Map<String, Boolean> mcq1Options = new LinkedHashMap<>();
        mcq1Options.put("A. Lion", false);
        mcq1Options.put("B. Bear", true);
        mcq1Options.put("C. Elephant", false);
        Question mcq1 = new Question("Which animal is omnivore?", mcq1Options);

        Map<String, Boolean> mcq2Options = new LinkedHashMap<>();
        mcq2Options.put("A. True", true);
        mcq2Options.put("B. False", false);
        Question mcq2 = new Question("Is Java a programming language?", mcq2Options);

        Map<String, Boolean> mcq3Options = new LinkedHashMap<>();
        mcq3Options.put("A. Hiroshima", false);
        mcq3Options.put("B. Tokyo", true);
        mcq3Options.put("C. Sapporo", false);
        Question mcq3 = new Question("What is the capital of Japan?", mcq3Options);

        questions.add(mcq1);
        questions.add(mcq2);
        questions.add(mcq3);

        
        Map<String, Boolean> tf1Options = new LinkedHashMap<>();
        tf1Options.put("A. True", true);
        tf1Options.put("B. False", false);
        Question tf1 = new Question("The Earth is round.", tf1Options);

        Map<String, Boolean> tf2Options = new LinkedHashMap<>();
        tf2Options.put("A. True", false);
        tf2Options.put("B. False", true);
        Question tf2 = new Question("Water is solid state.", tf2Options);

        questions.add(tf1);
        questions.add(tf2);

        
        Quiz quiz = new Quiz(questions);

        
        quiz.startQuiz();
    }
}