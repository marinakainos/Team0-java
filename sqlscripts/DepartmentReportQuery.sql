SELECT Department.DepartmentName, Employee.Name
FROM Employee, Department
WHERE Employee.DepartmentID = Department.DepartmentID
ORDER BY Department.DepartmentID;