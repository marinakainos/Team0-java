SELECT Department.DepartmentID, Department.DepartmentName, Employee.Name
FROM Employee, Department
where Employee.DepartmentID = Department.DepartmentID
ORDER BY Department.DepartmentID;