package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Employee;
import com.example.demo.model.Student;
import com.example.demo.model.dao.StudentRepo;

@Service
public class StudentService {

	private StudentRepo studentRepo;
	

	
	
	@Autowired
	public StudentService(StudentRepo studentRepo) {
		
		this.studentRepo = studentRepo;
	}

	@Transactional
	public Boolean saveStudentdata(Student student)
	{
		return studentRepo.saveSudentData(student);
	}
	
	
	@Transactional
	public Boolean saveEmployeedata(Employee employee)
	{
		return studentRepo.saveEmployeeData(employee);
		
	}
	
	@Transactional
	 public Boolean updateEmployeeData(Integer id, Employee employee) {
        return studentRepo.updateEmployeeData(id, employee);
    }
	
	@Transactional
	public Boolean deleteEmployeeData(Integer id)
	{
		return studentRepo.deleteEmployeeData(id);
	}
	
	
	/*public List<Employee> readEmployeeData()
	{
		return studentRepo.readEmployeeData();
	}*/
	
	 @Transactional
	    public int[] batchInsertConfigData(List<Student> student) {
	        return studentRepo.batchInsertConfigData(student);
	    }
}
