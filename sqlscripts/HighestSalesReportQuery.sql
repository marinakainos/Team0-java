SELECT Employee.Name, SalesEmployee.TotalSales
FROM Employee, SalesEmployee
WHERE Employee.EmployeeID = SalesEmployee.EmployeeID
ORDER BY SalesEmployee.TotalSales DESC
LIMIT 1;