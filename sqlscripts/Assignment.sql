CREATE TABLE Assignment (
    ProjectID MEDIUMINT NOT NULL,
    EmployeeID MEDIUMINT NOT NULL,
    StartDate DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY (ProjectID, EmployeeID, StartDate)
);