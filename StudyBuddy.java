import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StudyBuddy {
    static Scanner sc = new Scanner(System.in);
    static String[] topics = {
        "Classes and Objects", "Relationships and Inheritance",
        "Polymorphism, super and final", "Abstraction and Interfaces",
        "Packages and Access Modifiers", "Object and Wrapper Classes",
        "String, StringBuilder and StringBuffer", "Date and Calendar",
        "Exception Handling"
    };
    static int[] confidence = new int[9];
    static int[] attempts = new int[9];
    static int[] correct = new int[9];
    static ArrayList<Question> questions = new ArrayList<>();

    static class Question {
        int topic, answer;
        String question;
        String[] options;
        Question(int topic, String question, String a, String b, String c, int answer) {
            this.topic = topic;
            this.question = question;
            this.options = new String[]{a, b, c};
            this.answer = answer;
        }
    }

    static void add(int topic, String question, String a, String b, String c, int answer) {
        questions.add(new Question(topic, question, a, b, c, answer));
    }

    static void loadQuestions() {
        add(0, "What is a class?", "Blueprint for objects", "Loop", "Database", 1);
        add(0, "What is an object?", "Package", "Instance of a class", "Keyword", 2);
        add(0, "Encapsulation combines data and methods with...?", "Only loops", "Only inheritance", "Controlled access", 3);
        add(0, "Which keyword refers to the current object?", "this", "super", "final", 1);
        add(0, "A static variable belongs to the...?", "Method", "Class", "Loop", 2);
        add(0, "A getter is used to...?", "Delete an object", "Create a package", "Read a field", 3);
        add(0, "A setter is used to...?", "Update a field", "Import a class", "Stop a program", 1);
        add(0, "Garbage collection can reclaim memory of...?", "All objects immediately", "Unreachable objects", "Only arrays", 2);
        add(1, "Inheritance represents which relationship?", "HAS-A", "USES-ONLY", "IS-A", 3);
        add(1, "A Car containing an Engine represents...?", "HAS-A", "IS-A", "Overriding", 1);
        add(1, "Which relationship allows parts to exist independently?", "Composition", "Aggregation", "Compilation", 2);
        add(1, "Which relationship has strong ownership?", "Import", "Autoboxing", "Composition", 3);
        add(1, "A general connection between objects is...?", "Association", "Exception", "Unboxing", 1);
        add(2, "Runtime polymorphism commonly uses...?", "Static variables", "Method overriding", "Packages", 2);
        add(2, "Which keyword accesses superclass members?", "this", "import", "super", 3);
        add(2, "Can a final class be inherited?", "No", "Yes", "Only twice", 1);
        add(2, "Can a final method be overridden?", "Yes", "No", "Only using this", 2);
        add(2, "Dynamic method dispatch happens at...?", "File saving time", "Import time", "Runtime", 3);
        add(3, "Can an abstract class be instantiated directly?", "No", "Yes", "Only using static", 1);
        add(3, "A class implements an interface using...?", "import", "implements", "throw", 2);
        add(3, "Can a class implement multiple interfaces?", "No", "Only one", "Yes", 3);
        add(3, "How many abstract methods define a functional interface?", "One, excluding public Object methods", "Always two", "Always zero", 1);
        add(4, "Which keyword brings a type name into scope?", "throw", "import", "final", 2);
        add(4, "Which modifier provides the most restricted member access?", "public", "protected", "private", 3);
        add(4, "A package groups related...?", "Classes and interfaces", "Numbers only", "Objects in RAM only", 1);
        add(5, "Which method returns an object's string representation?", "equals()", "toString()", "hashCode()", 2);
        add(5, "Which method can define logical equality?", "toString()", "getClass()", "equals()", 3);
        add(5, "Objects equal according to equals() must have...?", "Equal hash codes", "Different hash codes", "Negative hash codes", 1);
        add(5, "What is the wrapper class for int?", "String", "Integer", "Float", 2);
        add(5, "Automatic conversion from int to Integer is...?", "Overriding", "Unboxing", "Autoboxing", 3);
        add(6, "A String object is...?", "Immutable", "Mutable", "Always empty", 1);
        add(6, "Which mutable string class has synchronized methods?", "StringBuilder", "StringBuffer", "String", 2);
        add(6, "Which method adds text to a StringBuilder?", "push()", "insertOnly()", "append()", 3);
        add(6, "Output: String s = \"Java\"; s.concat(\" AI\"); System.out.print(s);", "Java", "Java AI", "AI", 1);
        add(7, "Which class formats dates using patterns?", "StringBuffer", "SimpleDateFormat", "Integer", 2);
        add(7, "January is represented by Calendar.MONTH as...?", "1", "12", "0", 3);
        add(8, "Which block handles a matching exception?", "catch", "try", "throw", 1);
        add(8, "Which keyword explicitly throws an exception?", "throws", "throw", "finally", 2);
        add(8, "Which keyword declares exceptions in a method signature?", "catch", "throw", "throws", 3);
        add(8, "Which block normally runs after try/catch?", "finally", "import", "final", 1);
        add(8, "A custom checked exception can extend...?", "String", "Exception", "Integer", 2);
    }

    static String readLine(String message) {
        System.out.print(message);
        if (!sc.hasNextLine()) {
            saveProgress();
            System.exit(0);
        }
        return sc.nextLine().trim();
    }

    static int readNumber(String message, int min, int max) {
        while (true) {
            try {
                int number = Integer.parseInt(readLine(message));
                if (number >= min && number <= max) return number;
            } catch (NumberFormatException e) {
                // Ask again for invalid input.
            }
            System.out.println("Enter a number from " + min + " to " + max + ".");
        }
    }

    static void showDashboard() {
        System.out.println("\n========== DASHBOARD ==========");
        int totalAttempts = 0, totalCorrect = 0;
        for (int i = 0; i < topics.length; i++) {
            System.out.println((i + 1) + ". " + topics[i]
                + "\n   Confidence: " + confidence[i] + "/5"
                + " | Correct: " + correct[i] + "/" + attempts[i]);
            totalAttempts += attempts[i];
            totalCorrect += correct[i];
        }
        if (totalAttempts == 0) System.out.println("\nTake a quiz to check your progress.");
        else System.out.printf("%nOverall accuracy: %.1f%%%n", 100.0 * totalCorrect / totalAttempts);
    }

    static void setConfidence() {
        System.out.println("\n1 = Very weak, 5 = Very confident");
        for (int i = 0; i < topics.length; i++)
            confidence[i] = readNumber(topics[i] + ": ", 1, 5);
        saveProgress();
        System.out.println("Confidence saved!");
    }

    static double priority(int topic) {
        double errorRate = attempts[topic] == 0 ? 0.5
            : 1.0 - (double) correct[topic] / attempts[topic];
        return 60.0 * (5 - confidence[topic]) / 4.0 + 40.0 * errorRate;
    }

    static ArrayList<Integer> rankedTopics() {
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < topics.length; i++) order.add(i);
        order.sort((a, b) -> Double.compare(priority(b), priority(a)));
        return order;
    }

    static void predictTopics() {
        System.out.println("\n========== EXAM PREDICTOR ==========");
        System.out.println("Rule-based revision priorities, not guaranteed questions.");
        System.out.println("Scores use confidence and quiz mistakes.");
        System.out.println("No past-paper data is used.\n");
        for (int topic : rankedTopics()) {
            System.out.printf("%s | Priority: %.0f/100%n", topics[topic], priority(topic));
            for (Question q : questions) {
                if (q.topic == topic) {
                    System.out.println("Practice: " + q.question + "\n");
                    break;
                }
            }
        }
    }

    static void createStudyPlan() {
        LocalDate examDate;
        LocalDate today = LocalDate.now();
        while (true) {
            try {
                examDate = LocalDate.parse(readLine("Exam date (YYYY-MM-DD): "));
                if (!examDate.isBefore(today)) break;
            } catch (Exception e) {
                // Ask again for an invalid date.
            }
            System.out.println("Enter today or a future date.");
        }
        int minutes = readNumber("Available study minutes per day (10-720): ", 10, 720);
        long days = ChronoUnit.DAYS.between(today, examDate);
        long studyDays = Math.max(1, days);
        ArrayList<Integer> order = rankedTopics();
        System.out.println("\n========== STUDY PLAN ==========");
        if (days == 0) System.out.println("Exam today: use this for revision before the exam.");
        else System.out.println("Exam day is excluded from the plan.");
        for (int day = 0; day < Math.min(studyDays, 30); day++) {
            int topic = order.get(day % order.size());
            int conceptMinutes = minutes * 70 / 100;
            int practiceMinutes = minutes - conceptMinutes;
            System.out.println(today.plusDays(day) + " : " + topics[topic]
                + "\nConcepts: " + conceptMinutes + " minutes"
                + " | Practice: " + practiceMinutes + " minutes\n");
        }
        if (studyDays < topics.length)
            System.out.println("Time is short: this plan covers the highest-priority topics first.");
        if (studyDays > 30) System.out.println("First 30 days shown. Generate again later.");
    }

    static void startQuiz() {
        System.out.println("\n0. Mixed quiz");
        for (int i = 0; i < topics.length; i++) System.out.println((i + 1) + ". " + topics[i]);
        int selectedTopic = readNumber("Choose topic: ", 0, topics.length);
        ArrayList<Question> quizQuestions = new ArrayList<>();
        for (Question q : questions)
            if (selectedTopic == 0 || q.topic == selectedTopic - 1) quizQuestions.add(q);
        Collections.shuffle(quizQuestions);
        int count = readNumber("How many questions (1-" + quizQuestions.size() + ")? ", 1, quizQuestions.size());
        int score = 0;
        for (int i = 0; i < count; i++) {
            Question q = quizQuestions.get(i);
            System.out.println("\nQ" + (i + 1) + ". " + q.question);
            for (int j = 0; j < q.options.length; j++) System.out.println((j + 1) + ". " + q.options[j]);
            int answer = readNumber("Your answer: ", 1, 3);
            attempts[q.topic]++;
            if (answer == q.answer) {
                score++;
                correct[q.topic]++;
                System.out.println("Correct!");
            } else System.out.println("Correct answer: " + q.options[q.answer - 1]);
            saveProgress();
        }
        System.out.println("\nYour score: " + score + "/" + count);
    }

    static void showAnswers() {
        System.out.println("\n========== QUESTION BANK ==========");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ". " + q.question
                + "\nAnswer: " + q.options[q.answer - 1]);
        }
    }

    static void saveProgress() {
        Properties data = new Properties();
        for (int i = 0; i < topics.length; i++) {
            data.setProperty("confidence." + i, String.valueOf(confidence[i]));
            data.setProperty("attempts." + i, String.valueOf(attempts[i]));
            data.setProperty("correct." + i, String.valueOf(correct[i]));
        }
        try (FileOutputStream file = new FileOutputStream("progress.properties")) {
            data.store(file, "Study Buddy Progress");
        } catch (IOException e) {
            System.out.println("Could not save progress: " + e.getMessage());
        }
    }

    static void loadProgress() {
        Arrays.fill(confidence, 3);
        File file = new File("progress.properties");
        if (!file.exists()) return;
        Properties data = new Properties();
        try (FileInputStream in = new FileInputStream(file)) {
            data.load(in);
            int[] loadedConfidence = new int[9];
            int[] loadedAttempts = new int[9];
            int[] loadedCorrect = new int[9];
            for (int i = 0; i < topics.length; i++) {
                loadedConfidence[i] = Integer.parseInt(data.getProperty("confidence." + i, "3"));
                loadedAttempts[i] = Integer.parseInt(data.getProperty("attempts." + i,
                    data.getProperty("attempted." + i, "0")));
                loadedCorrect[i] = Integer.parseInt(data.getProperty("correct." + i, "0"));
                if (loadedConfidence[i] < 1 || loadedConfidence[i] > 5
                    || loadedAttempts[i] < 0 || loadedCorrect[i] < 0
                    || loadedCorrect[i] > loadedAttempts[i])
                    throw new IllegalArgumentException("Invalid progress");
            }
            confidence = loadedConfidence;
            attempts = loadedAttempts;
            correct = loadedCorrect;
        } catch (Exception e) {
            System.out.println("Could not load progress. Starting fresh.");
        }
    }

    public static void main(String[] args) {
        loadQuestions();
        loadProgress();
        System.out.println("======================================");
        System.out.println(" AI STUDY BUDDY + EXAM PREDICTOR");
        System.out.println(" Rule-based Java project");
        System.out.println("======================================");
        while (true) {
            System.out.println("\n1. Dashboard");
            System.out.println("2. Set confidence");
            System.out.println("3. Predict revision priorities");
            System.out.println("4. Create study plan");
            System.out.println("5. Start quiz");
            System.out.println("6. Questions and answers");
            System.out.println("0. Save and exit");
            int choice = readNumber("Choose an option: ", 0, 6);
            switch (choice) {
                case 1: showDashboard(); break;
                case 2: setConfidence(); break;
                case 3: predictTopics(); break;
                case 4: createStudyPlan(); break;
                case 5: startQuiz(); break;
                case 6: showAnswers(); break;
                case 0:
                    saveProgress();
                    System.out.println("All the best for your exam!");
                    return;
            }
        }
    }
}
