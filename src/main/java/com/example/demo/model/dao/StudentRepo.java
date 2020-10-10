package com.example.demo.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Student;

@Repository
public class StudentRepo {

	
	private JdbcTemplate jdbcTemplate;

	
	@Autowired
	public StudentRepo(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	
	 public static String Save_Student_Data="Insert into student values(?,?)";		
	  public Boolean saveSudentData(Student student)
	  {
		  
		  
		  int saveCode=jdbcTemplate.update(Save_Student_Data,student.getId(),student.getName());
		  
		  if(saveCode==1)
		  {
			return true;  
		  }
		  else
		  {
			  return true;
		  }
		
		  
	  }
	  
	  public static String Save_Employee_Data="Insert into employee values(?,?,?,?)";
	  
	  public static String UPDATE_Employee_DATA_BY_ID = "UPDATE EMPLOYEE SET first_name = ?, last_Name = ?, email = ? where id=? ";
	  
	  public static String DELETE_EMPLOYEE_DATA_BY_ID="DELETE FROM EMPLOYEE WHERE id=?";
	  
	  public static String READ_EMPLOYEE_DATA="SELECT * From EMPLOYEE";
	  
	  public static String SAVE_BATCH_STUDENT_DATA = "INSERT INTO STUDENT (id,name) VALUES (?,?)";

	  
		  public Boolean saveEmployeeData(Employee employee)
		  {
			  
			  
			  int saveCode=jdbcTemplate.update(Save_Employee_Data,employee.getId(),employee.getFirst_name(),employee.getLast_name(),employee.getEmail());
			  
			  if(saveCode==1)
			  {
				return true;  
			  }
			  else
			  {
				  return true;
			  }
			
			  
		  }
	
		  public Boolean updateEmployeeData(Integer id, Employee employee) {

		        int updateCode = jdbcTemplate.update(UPDATE_Employee_DATA_BY_ID, employee.getFirst_name(), employee.getLast_name(), employee.getEmail(),id);

		        if (updateCode == 1) {
		            return true;
		        } else {
		            return false;
		        }
		    }
		  	
		  
		 public Boolean deleteEmployeeData(Integer id)
		 {
			 int deletecode=jdbcTemplate.update(DELETE_EMPLOYEE_DATA_BY_ID, id);
			 if(deletecode==1)
			 {
				 return true;
			 }
			 else
			 {
				 return false;
			 }
		 }
		 
		 
		
		 public List<Employee> readEmployeeData()
		 {
			return jdbcTemplate.query(READ_EMPLOYEE_DATA, new RowMapper<Employee>(){

				@Override
				public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
					Employee employee=new Employee();
					employee.setId(rs.getInt("id"));
					employee.setFirst_name(rs.getString("first_Name"));
					employee.setLast_name(rs.getString("last_Name"));
					employee.setEmail(rs.getString("email"));
					return employee;
				}
				
			});
			 
		 }
		 
		 
		 
		 public int[] batchInsertConfigData(List<Student> student) {

		        return this.jdbcTemplate.batchUpdate(SAVE_BATCH_STUDENT_DATA,
		                new BatchPreparedStatementSetter() {
		                    public void setValues(PreparedStatement ps, int i) throws SQLException {
		                        ps.setInt(1, student.get(i).getId());
		                        ps.setString(2, student.get(i).getName());
		                        
		                    }

		                    public int getBatchSize() {
		                        return student.size();
		                    }
		                });
		    }

}
