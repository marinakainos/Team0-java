CREATE TABLE Employee (
    EmployeeID MEDIUMINT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Address VARCHAR(200) NOT NULL,
    NI CHAR(9) NOT NULL,
    IBAN CHAR(34),
    BIC VARCHAR(11),
    Salary INT NOT NULL CHECK (Salary >= 0),
    EmployeeNumber CHAR(8) NOT NULL,
    DepartmentID SMALLINT NOT NULL,
    UNIQUE(NI),
    UNIQUE(EmployeeNumber),
    CONSTRAINT FK_DepartmentID FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID)
);