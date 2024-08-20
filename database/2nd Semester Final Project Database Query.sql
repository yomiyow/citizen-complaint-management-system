-- Contents:
-- 1. DDL Queries
-- 2. DML Queries
-- 3. DQL Queries

USE CitizenComplaintManagementSystem;

-- 1. DDL Queries

CREATE DATABASE CitizenComplaintManagementSystem;

CREATE TABLE [User] (
	[UserID] INT PRIMARY KEY IDENTITY(1,1),
	[Firstname] VARCHAR(50),
	[Lastname] VARCHAR(50),
	[ContactNo] VARCHAR(11),
	[Birthdate] DATE,
	[Street] VARCHAR(100),
	[Barangay] VARCHAR(100),
	[City] VARCHAR(100),
	[Province] VARCHAR(100),
	[ProfilePicture] IMAGE,
	Email VARCHAR(100) FOREIGN KEY REFERENCES Account(Email)
);

CREATE TABLE [Account] (
	[Email] VARCHAR(100) PRIMARY KEY,
	[Password] VARCHAR(100)
);

CREATE TABLE [UserComplaint] (
	[ComplaintNo] INT PRIMARY KEY IDENTITY(1,1),
	[Category] VARCHAR(50),
	[Description] VARCHAR(MAX),
	[CreatedDate] DATE,
	[Location] VARCHAR(255),
	[Landmark] VARCHAR(255),
	[UrgencyLevel] VARCHAR(50),
	[Status] VARCHAR(50),
	[Proof] IMAGE,
	[UserID] INT FOREIGN KEY REFERENCES [User](UserID)
);


-- DQL Queries
SELECT * FROM [UserComplaint];
SELECT * FROM [User];
SELECT * FROM [Account];

DELETE FROM [UserComplaint];
DELETE FROM [User];
DELETE FROM [Account];


-- reset the IDENTITY PRIMARY KEY count after  deleting all the rows.
DBCC CHECKIDENT ('[UserComplaint]', RESEED, 0);
DBCC CHECKIDENT ('[User]', RESEED, 0);



SELECT 
	[ProfilePicture],
	[Firstname] + ' ' + [Lastname] AS [Name],
	[Email],
	[ContactNo],
	[Street] + ' ' + [Barangay] + ' ' + [City] + ' ' + [Barangay] AS [Address]
FROM [User];

FROM [User];

UPDATE [Account]
SET [Status] = 'Active'
WHERE [Email] = 'romeoquinones@gmail.com'

SELECT  
    u.[Firstname] + ' ' + u.[Lastname], 
    u.[Email], u.[ContactNo], 
    u.[Street] + ' ' + u.[Barangay],
    u.[City] + ' ' + u.[Province], 
    a.[Status]
FROM [User] AS u 
INNER JOIN [Account] AS a 
ON u.[Email] = a.[Email];

SELECT 
	c.*, 
	u.[Firstname] + ' ' + u.[Lastname] AS [Fullname]
FROM [UserComplaint] AS c
INNER JOIN [User] AS u 
	ON c.[UserID] = u.[UserID]
WHERE c.[ComplaintNo] = 1;

UPDATE [Account]
SET [Role] = 'Admin'
WHERE [Email] = 'charlynrosales@gmail.com'
