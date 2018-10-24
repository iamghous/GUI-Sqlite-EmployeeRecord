
/**
* Employee class documentation here
* @author Noman Ghous
*/



import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;


public class EmployeeDAO {

	Connection connect = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	

	
	
	
	
	public EmployeeDAO(){
	}
	
	public Connection getConnection(){ 
	try{
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		
		String filename = file.getAbsolutePath();
		
		connect = DriverManager.getConnection("jdbc:sqlite:"+ filename);
		//connect = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\noman\\Desktop\\empdb.sqlite");
		System.out.println("Connection is Successful to sqlite");
		return connect;
	}
		catch(Exception e){
			System.out.println(e + "Sorry, Connection is Unsuccessful to sqlite");
			return null;
			
		}
	}
	
	public void closeConnection() throws SQLException{
		connect.close();
	}
	
	
	public ArrayList<Employee> selectAllEmployees(){
		
		 ArrayList<Employee> employee = new ArrayList<Employee>();
	       connect = getConnection();
	       String sql = "SELECT * FROM  employees ";
	       try {
	           pst = connect.prepareStatement(sql);
	           rs = pst.executeQuery();

	           
                Person per = new Employee();
                Employee emp = (Employee)per;
               
	           
	        	   while(rs.next())
	               {
	            	   String id = rs.getString("ID");		
	    				String name = rs.getString("Name");
	    				String g = rs.getString("Gender"); 
	    				char n = 0;
	    				for (int j = 0; j < g.length(); j++){
	    				       n = g.charAt(j);	
	    				}
	    				char gender = n;
	    				String dob = rs.getString("DOB");
	    				String address = rs.getString("Address");
	    				String postcode = rs.getString("PostCode");
	    				String ni = rs.getString("NIN");
	    				String jobtitle = rs.getString("Jobtitle");
	    				String sd = rs.getString("StartDate");
	    				String salary = rs.getString("Salary");
	    				String email = rs.getString("Email");
	    				emp = new Employee(id, name,gender,dob,address,postcode,ni,jobtitle,sd,salary,email);
	                    employee.add(emp);
	           }
	       } 
	      catch (Exception e) {
	           e.printStackTrace();
	       }
	       return employee;
	   }
	

	public Employee selectAllEmployeeByName(String name){
		
		try{
			connect = getConnection();
			String sql = ("select * from employees where Name  LIKE '%" + name +"%'");
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
			System.out.println(rs.getString("ID") + "  " + rs.getString("Name") + "  "+ rs.getString("Gender").charAt(0) + "  "+ rs.getString("DOB") + "  "+ rs.getString("Address") + "  "+ rs.getString("PostCode") + "  "+  rs.getString("NIN")  + "  "+ rs.getString("JobTitle") + "  "+  rs.getString("StartDate") + "  "+ rs.getString("Salary") + "  "+ rs.getString("Email"));
		}	
		return null;
		}
		catch(Exception e){	
			return null;
			
	}
		}
	
	public boolean insertEmployee(Employee employee){
	  
		
		ArrayList<Employee> e = new ArrayList<Employee>(selectAllEmployees());
		for (Employee emp : e){
		//employee = new Employee(emp.getId(),emp.getName(),emp.getGender(),emp.getDob(),emp.getAddress(),emp.getPostcode(),emp.getNatInscNo(),emp.getTitle(),emp.getStartDate(),emp.getSalray(),emp.getEmail());
			String id = emp.getId();
			String name = emp.getName();
			char gender =emp.getGender();
			String dob = emp.getDob();
			String address = emp.getAddress();
			String postcode = emp.getPostcode();
			String ni = emp.getNatInscNo();
			String jobtitle = emp.getTitle();
			String sd = emp.getStartDate();
			String salary = emp.getSalray();
			String email = emp.getEmail();
					
			emp.setId(id);
			emp.setName(name);
			emp.setGender(gender);
			emp.setDob(dob);
			emp.setAddress(address);
			emp.setPostcode(postcode);
			emp.setNatInscNo(ni);
			emp.setTitle(jobtitle);
			emp.setStartDate(sd);
			emp.setSalray(salary);
			emp.setEmail(email);
			
			System.out.println("The Data shown below is instered");
			emp.toString();
		}

			return true;
		}
	
		
	
	
	public boolean InsertEmplyeeAtID(Employee empp, String id){
		
		
		ArrayList<Employee> e = new ArrayList<Employee>(selectAllEmployees());
		//employee = new Employee(emp.getId(),emp.getName(),emp.getGender(),emp.getDob(),emp.getAddress(),emp.getPostcode(),emp.getNatInscNo(),emp.getTitle(),emp.getStartDate(),emp.getSalray(),emp.getEmail());
	    for(Employee emp : e ){
			if (emp.getId().equals(id)){
				
			
			String Id = emp.getId();
			String name = emp.getName();
			char gender =emp.getGender();
			String dob = emp.getDob();
			String address = emp.getAddress();
			String postcode = emp.getPostcode();
			String ni = emp.getNatInscNo();
			String jobtitle = emp.getTitle();
			String sd = emp.getStartDate();
			String salary = emp.getSalray();
			String email = emp.getEmail();
					
			emp.setId(Id);
			emp.setName(name);
			emp.setGender(gender);
			emp.setDob(dob);
			emp.setAddress(address);
			emp.setPostcode(postcode);
			emp.setNatInscNo(ni);
			emp.setTitle(jobtitle);
			emp.setStartDate(sd);
			emp.setSalray(salary);
			emp.setEmail(email);
			
    System.out.println("The Data shown below is instered");
			emp.toString();
		}
		}
	return true;
	}
	

		public  boolean deleteEmployeeAtID(String id){
			
			try {
				
				String sql = "DELETE FROM employees WHERE ID = " + id;
				pst = connect.prepareStatement(sql);
				pst.executeUpdate();
				System.out.println("Deleted Succefully");
				return true;
			}
			catch(Exception e){
				
				System.out.println("Sorry there is some problem while DELTEING this data");
			return false;
		}
		
		}
}
      
	