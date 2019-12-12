--
-- File generated with SQLiteStudio v3.0.7 on Thu Dec 12 13:53:05 2019
--
-- Text encoding used: windows-1252
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Users_Subjects
CREATE TABLE Users_Subjects (usID PRIMARY KEY, SubjectID REFERENCES Subjects (SubjectID) ON DELETE CASCADE ON UPDATE CASCADE, UserID REFERENCES Users (UserID) ON DELETE CASCADE ON UPDATE CASCADE);

-- Table: Questions
CREATE TABLE Questions (QuestionID INTEGER PRIMARY KEY, Question STRING, Answer STRING, previouslyCorrect BOOLEAN, QuestionType STRING, IncorrectAns1 STRING, IncAns2 STRING, IncAns3 STRING);
INSERT INTO Questions (QuestionID, Question, Answer, previouslyCorrect, QuestionType, IncorrectAns1, IncAns2, IncAns3) VALUES (1, 'TestQ', 'TestA', 'False', 'Multi', 'TestIA1', 'TestIA2', 'TestIA3');
INSERT INTO Questions (QuestionID, Question, Answer, previouslyCorrect, QuestionType, IncorrectAns1, IncAns2, IncAns3) VALUES (2, 'testQuestion', 'testAnswer', 0, 'multi', 'inc1', 'inc2', 'inc3');

-- Table: Quiz_Questions
CREATE TABLE Quiz_Questions (qqID PRIMARY KEY, QuestionID REFERENCES Questions (QuestionID) ON DELETE CASCADE ON UPDATE CASCADE, QuizID REFERENCES Quizzes (QuizID) ON DELETE CASCADE ON UPDATE CASCADE);
INSERT INTO Quiz_Questions (qqID, QuestionID, QuizID) VALUES (1, 1, 1);

-- Table: Subjects
CREATE TABLE Subjects (SubjectID INTEGER PRIMARY KEY AUTOINCREMENT, SubjectName STRING);
INSERT INTO Subjects (SubjectID, SubjectName) VALUES (1, 'TempSubject');

-- Table: Topics
CREATE TABLE Topics (TopicID INTEGER PRIMARY KEY, TopicName STRING, SubjectID INTEGER REFERENCES Subjects (SubjectID) ON DELETE CASCADE ON UPDATE CASCADE);
INSERT INTO Topics (TopicID, TopicName, SubjectID) VALUES (1, 'TempTopic', 1);

-- Table: Quizzes
CREATE TABLE Quizzes (QuizID PRIMARY KEY, QuizName, ScoreID REFERENCES Scores (ScoreID) ON DELETE CASCADE ON UPDATE CASCADE, TopicID INTEGER REFERENCES Topics (TopicID) ON DELETE NO ACTION ON UPDATE NO ACTION);
INSERT INTO Quizzes (QuizID, QuizName, ScoreID, TopicID) VALUES (1, 'TempQuiz', 1, 1);

-- Table: Scores
CREATE TABLE Scores (ScoreID INTEGER PRIMARY KEY, UserID INTEGER REFERENCES Users (UserID) ON DELETE NO ACTION ON UPDATE NO ACTION MATCH SIMPLE, Score INTEGER);
INSERT INTO Scores (ScoreID, UserID, Score) VALUES (1, 1, 0);

-- Table: Users
CREATE TABLE Users (UserID INTEGER PRIMARY KEY AUTOINCREMENT, Email STRING, Password STRING);
INSERT INTO Users (UserID, Email, Password) VALUES (1, 'temporary', 'temporary');
INSERT INTO Users (UserID, Email, Password) VALUES (2, 'test@test.com', 'test');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
