-- RESET DATABASE (Prevents errors if re-run)
DROP DATABASE IF EXISTS LibraryDB;
CREATE DATABASE LibraryDB;
USE LibraryDB;

-- CREATE TABLES

-- Authors Table
CREATE TABLE Authors (
    AuthorID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Nationality VARCHAR(50),
    BirthYear INT
);

-- Books Table (Now linked to Authors)
CREATE TABLE Books (
    ISBN VARCHAR(20) PRIMARY KEY,
    Title VARCHAR(150) NOT NULL,
    AuthorID INT,
    Genre VARCHAR(50),
    Quantity INT NOT NULL CHECK (Quantity >= 0),
    FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID)
);

-- Members Table
CREATE TABLE Members (
    MemberID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    Phone VARCHAR(20)
);

-- Loans Table
CREATE TABLE Loans (
    LoanID INT PRIMARY KEY AUTO_INCREMENT,
    MemberID INT,
    ISBN VARCHAR(20),
    LoanDate DATE NOT NULL,
    ReturnDate DATE,
    FOREIGN KEY (MemberID) REFERENCES Members(MemberID)
        ON DELETE CASCADE,
    FOREIGN KEY (ISBN) REFERENCES Books(ISBN)
);

-- INSERT AUTHORS (5)
INSERT INTO Authors (Name, Nationality, BirthYear) VALUES
('Robert C. Martin', 'American', 1952),
('Eric John', 'Nigerian', 1961),
('Pibo Shongwe', 'South African', 1950),
('Kweku Massi', 'Ghanian', 1955),
('Angel Kate', 'Ethopian', 1938);

-- INSERT BOOKS (10)
INSERT INTO Books VALUES
('1111', 'Clean Code', 1, 'Programming', 5),
('2222', 'Design Patterns', 2, 'Software Engineering', 3),
('3333', 'The C++ Programming Language', 3, 'Programming', 4),
('4444', 'Effective Java', 4, 'Programming', 6),
('5555', 'The Art of Computer Programming', 5, 'Computer Science', 2),
('6666', 'Refactoring', 1, 'Programming', 5),
('7777', 'Patterns of Enterprise Application Architecture', 2, 'Software Engineering', 4),
('8888', 'Programming Principles', 3, 'Programming', 3),
('9999', 'Java Foundations', 4, 'Programming', 7),
('1010', 'Algorithm Design', 5, 'Computer Science', 3);

-- INSERT MEMBERS (20)
INSERT INTO Members (Name, Email, Phone) VALUES
('Jake','jake@email.com','1001'),
('Bossu','bossu@email.com','1002'),
('Hassan','hassan@email.com','1003'),
('Aliya','aliya@email.com','1004'),
('Bashir','bashir@email.com','1005'),
('Adetolu','ade@email.com','1006'),
('Benneth','ben@email.com','1007'),
('Ajileye','aji@email.com','1008'),
('Yusuf','yus@email.com','1009'),
('Rabecca','rebby@email.com','1010'),
('Simon','sam@email.com','1011'),
('Jackky','jack@email.com','1012'),
('Kante','kent@email.com','1013'),
('Arnold','arry@email.com','1014'),
('Franky','frank@email.com','1015'),
('Joyce','joy@email.com','1016'),
('Zainab','zee@email.com','1017'),
('Blessing','bles@email.com','1018'),
('Alaji','aj@email.com','1019'),
('Jerry','jerry@email.com','1020');

-- INSERT SAMPLE LOANS
INSERT INTO Loans (MemberID, ISBN, LoanDate, ReturnDate) VALUES
(1,'1111','2026-01-01',NULL),
(2,'2222','2026-01-02',NULL),
(3,'1111','2026-01-03',NULL);

-- QUERY 1: Retrieve all books by a specific author
SELECT a.Name AS Author, b.Title, b.Genre
FROM Books b
JOIN Authors a ON b.AuthorID = a.AuthorID
WHERE a.Name = 'Pibo Shongwe';

-- QUERY 2: Identify members who borrowed a specific book
SELECT m.Name, b.Title
FROM Loans l
JOIN Members m ON l.MemberID = m.MemberID
JOIN Books b ON l.ISBN = b.ISBN
WHERE b.Title = 'Clean Code';

-- QUERY 3: ALTER Members table to add MembershipType
ALTER TABLE Members
ADD MembershipType VARCHAR(20);

