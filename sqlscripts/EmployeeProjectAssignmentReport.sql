SELECT Project.ProjectName, Employee.Name
FROM Project, Employee, Assignment
WHERE Project.ProjectID = Assignment.ProjectID
AND Employee.EmployeeID = Assignment.EmployeeID
ORDER BY Project.ProjectName;