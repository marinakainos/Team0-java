CREATE TABLE SalesEmployee (
    EmployeeID MEDIUMINT PRIMARY KEY UNIQUE NOT NULL,
    CommissionRate DECIMAL(4 , 4) NOT NULL CHECK (CommissionRate >= 0),
    TotalSales INT NOT NULL CHECK (TotalSales >= 0),
    FOREIGN KEY (EmployeeID) REFERENCES Employee (EmployeeID)
);