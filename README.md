# AI Study Buddy + Exam Predictor

A Java console application that helps students practice questions, identify weak topics and prepare a daily study plan.

> This version uses a rule-based revision-priority algorithm. It does not use a trained AI model or predict actual exam questions.

## Features

- 41 multiple-choice questions based on the Java OOP syllabus
- Topic-wise and mixed practice quizzes
- Confidence ratings from 1 to 5
- Revision priorities based on confidence and quiz performance
- Study plans based on exam date and daily study time
- Dashboard showing topic-wise results and overall accuracy
- Automatic progress saving and loading

## Technologies Used

- Java — JDK 17 or newer
- Arrays and ArrayList
- Scanner
- Lambda expressions and Comparator
- Exception handling
- File handling and Properties
- LocalDate and ChronoUnit

No Maven, external libraries, API key or MySQL setup is required.

## How to Run

1. Download or clone this repository.
2. Open the project folder in VS Code or a terminal.
3. Check that Java is installed:

   ```bash
   java -version
   ```

4. Run the application:

   ```bash
   java StudyBuddy.java
   ```

5. Enter a menu option in the terminal.

## Application Menu

| Option | Feature |
|--------|---------|
| 1 | Dashboard |
| 2 | Set confidence |
| 3 | Predict revision priorities |
| 4 | Create study plan |
| 5 | Start quiz |
| 6 | Questions and answers |
| 0 | Save and exit |

## How Revision Priority Works

The application calculates a score for each topic:

```text
Error Rate = 1 - (Correct Answers / Total Attempts)

Priority = 60 × (5 - Confidence) / 4 + 40 × Error Rate
```

- Lower confidence increases revision priority.
- More quiz mistakes increase revision priority.
- Topics with no attempts use a default error rate of 0.5.
- Higher-scoring topics appear first.
- The 60/40 weights are manually chosen.

### Example

For confidence 2, with 2 correct answers out of 5:

```text
Error Rate = 1 - 2/5 = 0.6
Priority = 45 + 24 = 69/100
```

A score of 69 indicates revision priority. It does not mean a 69% chance of appearing in an exam.

## Study Plan

The student enters an exam date and available study minutes per day.

The application assigns one topic per day in priority order, with approximately 70% of the time for concepts and the remaining time for practice.

For example, 60 minutes becomes:

- 42 minutes for concepts
- 18 minutes for practice

The plan displays up to 30 days. Short plans may cover only the highest-priority topics.

## Progress Storage

Progress is stored locally in `progress.properties`.

The file contains:

- Topic confidence ratings
- Attempt counts
- Correct-answer counts

Run the application from the same folder to restore saved progress. This version does not use a database.

## OOP Concepts Used

- Classes and objects
- Constructors
- `this` keyword
- Static members
- Static nested class
- Basic method-level abstraction
- Comparator-based polymorphism through a sorting lambda

## Team Contributions

| Member | Responsibility |
|--------|----------------|
| Kaushal Raj | Application structure, question bank, input validation and menu |
| [Member 2 Name] | Confidence ratings, priority algorithm, sorting and study plan |
| [Member 3 Name] | Quiz, dashboard and progress saving/loading |

## Limitations

- Questions are predefined and are not generated automatically.
- The application recommends revision priorities rather than actual exam questions.
- There is no trained AI model, chatbot or past-paper analysis.
- Progress is local, with no separate user accounts.
- The study plan does not track actual study time or topic completion.

## Future Improvements

- Graphical user interface
- User accounts and separate progress records
- External question bank
- JDBC and MySQL integration
- Improved planning based on topic difficulty
- Evaluation using real student-performance data

## Academic Purpose

This project demonstrates Java programming concepts through a student study-planning application.# study_buddy_exam_predictor
