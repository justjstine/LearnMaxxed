CREATE DATABASE LearnMaxxing -- Creation of Database
USE LearnMaxxing -- Use

-- Create Strand table
CREATE TABLE Strand (
    StrandID INT AUTO_INCREMENT PRIMARY KEY,
    StrandName VARCHAR(100) NOT NULL
);

-- Create Admin table
CREATE TABLE Admin (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(100) NOT NULL,
    Password VARCHAR(100) NOT NULL
);

-- Create Subscription table (defined early for FK usage)
CREATE TABLE Subscription (
    SubscriptionID INT AUTO_INCREMENT PRIMARY KEY,
    PlanType VARCHAR(50) NOT NULL,
    PaymentDetails DECIMAL(10,2)
);

-- Create User table
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    EmailAddress VARCHAR(50) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Created DATETIME DEFAULT CURRENT_TIMESTAMP,
    SubscriptionID INT,
    StrandID INT,
    PaymentID INT,
    FOREIGN KEY (SubscriptionID) REFERENCES Subscription(SubscriptionID),
    FOREIGN KEY (StrandID) REFERENCES Strand(StrandID),
    FOREIGN KEY (PaymentID) REFERENCES Payment(PaymentID)
);


-- Create Payment table
CREATE TABLE Payment (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    PaymentMethod VARCHAR(50) NOT NULL
);

-- Create Transaction table
CREATE TABLE Transaction (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    PaymentID INT,
    SubscriptionID INT,
    StrandID INT,
    TransactionDate DATE,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (PaymentID) REFERENCES Payment(PaymentID),
    FOREIGN KEY (SubscriptionID) REFERENCES Subscription(SubscriptionID),
    FOREIGN KEY (StrandID) REFERENCES Strand(StrandID)
);

-- Create Badge table
CREATE TABLE Badge (
    BadgeID INT AUTO_INCREMENT PRIMARY KEY,
    BadgeName VARCHAR(100) NOT NULL
);

-- Create UserBadge table
CREATE TABLE UserBadge (
    UserBadgeID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    BadgeID INT NOT NULL,
    AwardedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (UserID, BadgeID),
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (BadgeID) REFERENCES Badge(BadgeID)
);

DELIMITER $$

CREATE TRIGGER prevent_duplicate_badge
BEFORE INSERT ON UserBadge
FOR EACH ROW
BEGIN
    DECLARE badge_exists INT;

    SELECT COUNT(*) INTO badge_exists
    FROM UserBadge
    WHERE UserID = NEW.UserID AND BadgeID = NEW.BadgeID;

    IF badge_exists > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'User already has this badge!';
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER limit_free_user_badges
BEFORE INSERT ON UserBadge
FOR EACH ROW
BEGIN
    DECLARE user_subscription INT;
    DECLARE badge_count INT;

    -- Get the subscription type of the user
    SELECT SubscriptionID INTO user_subscription
    FROM User
    WHERE UserID = NEW.UserID;

    -- Count how many badges the user already has
    SELECT COUNT(*) INTO badge_count
    FROM UserBadge
    WHERE UserID = NEW.UserID;

    -- If user is Free (SubscriptionID = 2) and already has 2 badges, block insert
    IF user_subscription = 2 AND badge_count >= 2 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Free users can only earn a maximum of 2 badges.';
    END IF;
END$$

DELIMITER ;


-- Insert of Values Of Strand
INSERT INTO Strand (StrandName) VALUES 
('STEM'),
('ICT');

-- Insert of Values Subscription
INSERT INTO Subscription (PlanType, PaymentDetails) VALUES 
('Subscribed', 199.00),
('Free', 0.00);

-- Dummy Accounts 
INSERT INTO User (
    Username, EmailAddress, FirstName, LastName, Password, SubscriptionID, StrandID
) VALUES (
    'johndoe', 'johndoe@example.com', 'John', 'Doe', 'Pass123', 1, 1
);

INSERT INTO User (
    Username, EmailAddress, FirstName, LastName, Password, SubscriptionID, StrandID
) VALUES (
    'janesmith', 'janesmith@example.com', 'Jane', 'Smith', 'Pass456', 2, 2
);

-- Insertion of Admin 
INSERT INTO Admin (Username, Password) VALUES 
('admin', 'admin');

INSERT INTO Admin (Username, Password) VALUES 
('johnny', 'admin');

-- Insertion of Payment Methods
INSERT INTO Payment (PaymentMethod) VALUES 
('Gcash'),
('Bank'),
('Maya');

-- Insertion of Badges
INSERT INTO Badge (BadgeName) VALUES
('Chemistry'),
('Physics'),
('Biology'),
('Pre-Calculus'),
('Basic Calculus'),
('Computer Programming'),
('Computer Systems'),
('Web Development'),
('Animation'),
('Illustration')








